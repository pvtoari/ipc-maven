package net.pvtoari.tonetbeans;

import java.io.*;
import java.util.LinkedList;
import java.util.stream.Stream;
import java.net.URL;
import java.nio.file.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProjectBuilder {
    private static final String TO_DEFINE = null;
    private final Parameters params;
    private final Path tempPath, outputPath, javafxPath;
    private final List<Path> filesToWrite, javaFilesToWrite;
    List<Macro> macros;

    public ProjectBuilder(Path outputPath, Path javafxPath, Parameters params) throws IOException {
        this.tempPath = Files.createTempDirectory("tonetbeans");
        System.out.println("Temp files are served in " + tempPath);

        this.outputPath = outputPath;
        this.javafxPath = javafxPath;

        this.params = params;
        this.macros = initializeMacros();

        copyResourcesToTemp();

        this.filesToWrite = getFilesToWrite();
        this.javaFilesToWrite = new LinkedList<>();

    }

    public void build() throws IOException {
        System.out.println("Building project...");

        Path rootFolder = outputPath.resolve(params.getSnakeCaseProjectName() + "-root");
        Path outputFolder = rootFolder.resolve(params.getSnakeCaseProjectName());
        Path libsFolder = rootFolder.resolve("libs");

        // create root folder
        System.out.println("Creating root folder " + rootFolder + "...");
        Files.createDirectories(rootFolder);

        // copy libs to output folder
        System.out.println("Copying to " + libsFolder + "...");
        recursiveCopy(javafxPath, libsFolder);

        tryMacrosOnResourceFiles();
        copyPackage();
        copyPackageResources();

        List<String> packageFiles = analyzePackage(Path.of("src/main/java").resolve(params.getPackageToPack().replace('.', '/')));
        generateModuleInfo(packageFiles);

        // copy final files to output folder
        System.out.println("Copying to " + outputFolder + "...");
        recursiveCopy(tempPath, outputFolder);

        // fix resource access
        tryMacrosOnJavaFiles();

        createReadme();

        System.out.println("Done!");
    }

    private List<Macro> initializeMacros() {
        return List.of(
                Macro.ofTarget(Targets.JAVAFX_LIBS_TARGET).define("libs"),
                Macro.ofTarget(Targets.EXPORTS_OPENS_TARGET).define(TO_DEFINE),
                Macro.ofTarget(Targets.PACKED_PACKAGE_NAME_TARGET).define(params.getPackageToPack()),
                Macro.ofTarget(Targets.SNAKE_CASE_PROJECT_NAME_TARGET).define(params.getSnakeCaseProjectName()),
                Macro.ofTarget(Targets.PASCAL_CASE_PROJECT_NAME_TARGET).define(params.getPascalCaseProjectName()),
                Macro.ofTarget(Targets.CAMEL_CASE_PROJECT_NAME_TARGET).define(params.getCamelCaseProjectName()),
                Macro.ofTarget(Targets.APP_VENDOR_TARGET).define(params.getAppVendor()),
                Macro.ofTarget(Targets.MAIN_CLASS_NAME_TARGET).define(params.getMainClassName()), // TODO
                Macro.ofTarget(Targets.MAIN_CLASS_FULL_NAME_TARGET).define(TO_DEFINE), // TODO
                Macro.ofTarget(Targets.RESOURCE_ACCESS_FIX_TARGET).define("/resources/net/pvtoari/"),
                Macro.ofTarget(Targets.PRACT7_ACCESS_FIX_TARGET).define(
                        """
                                <copy todir="${build.classes.dir}">
                                        <fileset dir="src/resources/net/pvtoari/ipcmaven/pract7/internationalizationIPC">
                                            <include name="**/*.properties"/>
                                            <include name="**/*.fxml"/>
                                            <include name="**/*.png"/>
                                        </fileset>
                                        <mapper from="*" to="net/pvtoari/ipcmaven/pract7/internationalizationIPC/*" type="glob"/>
                                    </copy>
                              """
                        )
        );
    }

    private static final byte MODULE_INFO = 0;
    private static final byte PROJECT_XML = 1;
    private static final byte PROJECT_PROPERTIES = 2;
    private static final byte BUILD_XML = 3;

    private List<Path> getFilesToWrite() {
        return List.of(
                tempPath.resolve("src/module-info.java"),
                tempPath.resolve("nbproject/project.xml"),
                tempPath.resolve("nbproject/project.properties"),
                tempPath.resolve("build.xml")
        );
    }

    private void copyResourcesToTemp() throws IOException {
        System.out.println("Copying from resources to " + tempPath);

        URL resourceUrl = getClass().getClassLoader().getResource("net/pvtoari/tonetbeans/files");

        if (resourceUrl == null) throw new IOException();

        Path sourcePath = Paths.get(resourceUrl.getPath());

        try (Stream<Path> files = Files.walk(sourcePath)) {
            for (Path file : (Iterable<Path>) files::iterator) {
                Path relativePath = sourcePath.relativize(file);
                Path targetPath = tempPath.resolve(relativePath);

                if (Files.isDirectory(file)) {
                    Files.createDirectories(targetPath);
                } else {
                    Files.createDirectories(targetPath.getParent());
                    Files.copy(file, targetPath, StandardCopyOption.REPLACE_EXISTING);
                }

                System.out.println("Copied " + file + " to " + targetPath);
            }
        }
    }

    private void copyPackage() throws IOException {
        String packageRelativePath = params.getPackageToPack().replace('.', '/');

        Path packagePath = Path.of("src/main/java").resolve(packageRelativePath);
        Path outputPath = tempPath.resolve("src").resolve(packageRelativePath);

        Files.walk(packagePath).forEach(file -> {
            try {
                Path target = outputPath.resolve(packagePath.relativize(file));
                Files.createDirectories(target.getParent());
                Files.copy(file, target, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        });
    }

    private void copyPackageResources() throws IOException {
        String packageRelativePath = params.getPackageToPack().replace('.', '/');

        Path packagePath = Path.of("src/main/resources").resolve(packageRelativePath);
        Path outputPath = tempPath.resolve("src/resources").resolve(packageRelativePath);

        Files.walk(packagePath).forEach(file -> {
            try {
                Path target = outputPath.resolve(packagePath.relativize(file));
                Files.createDirectories(target.getParent());
                Files.copy(file, target, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        });
    }

    private void tryMacrosOnFiles(List<Path> filesToWrite) throws IOException {
        for(Path path : filesToWrite) {
            File file = path.toFile();
            Path tempFile = Files.createTempFile(String.valueOf(System.currentTimeMillis()), ".tmp");

            try (BufferedReader reader = Files.newBufferedReader(file.toPath());
                 BufferedWriter writer = Files.newBufferedWriter(tempFile)) {

                String line;
                boolean changed = false;
                while ((line = reader.readLine()) != null) {
                    String original = line;

                    for (Macro macro : macros) {
                        String target = Pattern.quote(macro.getTarget().value());
                        String value = Matcher.quoteReplacement(macro.getMacroValue());
                        line = line.replaceAll(target, value);
                    }

                    if (!line.equals(original)) {
                        changed = true;
                        System.out.println("Changed line: " + original + " to " + line);
                    }

                    writer.write(line);
                    writer.newLine();
                }

                writer.flush();

                if (changed) {
                    System.out.println("Changed content of " + file.getName());
                    Files.move(tempFile, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
                } else {
                    System.out.println("No changes to content of " + file.getName());
                    Files.delete(tempFile);
                }
            }
        }
    }

    private void tryMacrosOnResourceFiles() throws IOException {
        tryMacrosOnFiles(filesToWrite);
    }

    private void tryMacrosOnJavaFiles() throws IOException {
        tryMacrosOnFiles(javaFilesToWrite);
    }

    private void recursiveCopy(Path from, Path to) throws IOException {
        if(Files.exists(to)) {
            System.out.println("Destination already exists: " + to);
            return;
        }

        if (Files.isDirectory(from)) {
            Files.createDirectories(to);

            try (Stream<Path> files = Files.list(from)) {
                for (Path file : (Iterable<Path>) files::iterator) recursiveCopy(file, to.resolve(file.getFileName()));
            }
        } else Files.copy(from, to);
    }

    private List<String> analyzePackage(Path packagePath) throws IOException {
        final String FILTER_PREFIX = "src/main/java/";

        final List<String> files = new LinkedList<>();

        Files.walk(packagePath).forEach(path -> {
            File file = path.toFile();
            String fileString = file.toString();

            if(file.isDirectory() && file.listFiles(File::isFile).length > 0) {
                String packageFullName = fileString
                        .substring(fileString.indexOf(FILTER_PREFIX) + FILTER_PREFIX.length())
                        .replaceAll("/", ".");

                files.add(packageFullName);
            } else if(file.isFile()) {
                Path relativePath = Path.of(params.getPackageToPack().replace('.', '/'))
                        .resolve(packagePath.relativize(path));

                Path tempFilePath = outputPath
                        .resolve(String.format("%s-root", params.getSnakeCaseProjectName()))
                        .resolve(params.getSnakeCaseProjectName())
                        .resolve("src")
                        .resolve(relativePath);

                javaFilesToWrite.add(tempFilePath);

                System.out.println("Adding file " + tempFilePath + " to javaFilesToWrite");
            }
        });

        return files;
    }

    private void generateModuleInfo(List<String> packageList) throws IOException {
        StringBuilder sb = new StringBuilder();

        for (String packageName : packageList) {
            sb.append("exports ").append(packageName).append(";\n");
            sb.append("opens ").append(packageName).append(" to javafx.fxml;\n");
        }

        File file = filesToWrite.get(MODULE_INFO).toFile();
        Path tempFile = Files.createTempFile(String.valueOf(System.currentTimeMillis()), ".tmp");

        try(BufferedReader reader = Files.newBufferedReader(file.toPath());
            BufferedWriter writer = Files.newBufferedWriter(tempFile)) {

            String line;
            String target = Pattern.quote(Targets.EXPORTS_OPENS_TARGET.value());
            String value = Matcher.quoteReplacement(sb.toString());
            boolean changed = false;
            while ((line = reader.readLine()) != null) {
                String original = line;

                line = line.replaceAll(target, value);

                if (!line.equals(original)) {
                    changed = true;
                    System.out.println("Changed line: " + original + " to " + line);
                }

                writer.write(line);
                writer.newLine();
            }

            if (changed) {
                System.out.println("Changed content of " + file.getName());
                Files.move(tempFile, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
            } else {
                System.out.println("No changes to content of " + file.getName());
                Files.delete(tempFile);
            }
        }
    }

    private void createReadme() {
        System.out.println("Creating README.md...");

        String readmeContent =
                """
                This project was generated by ToNetbeans tool by pvtoari.
                
                github.com/pvtoari
                
                ## How to run
                
                1. Open the project in NetBeans.
                2. Clean and build the project.
                3. Run files that implement a main method.
                """;

        Path readmePath = outputPath.resolve(params.getSnakeCaseProjectName() + "-root")
                .resolve("README.md");

        try (BufferedWriter writer = Files.newBufferedWriter(readmePath)) {
            writer.write(readmeContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
