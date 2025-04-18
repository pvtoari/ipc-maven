
package net.pvtoari.tonetbeans;

import java.io.*;
import java.util.stream.Stream;
import java.net.URL;
import java.nio.file.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProjectBuilder {
    private static final String UNDEFINED = "//ничего";
    private final Parameters params;
    private final Path tempPath, outputPath, javafxPath;
    private final List<Path> filesToWrite;
    List<Macro> macros;

    public ProjectBuilder(Path outputPath, Path javafxPath, Parameters params) throws IOException {
        this.tempPath = Files.createTempDirectory("tonetbeans");
        this.outputPath = outputPath;
        this.javafxPath = javafxPath;

        this.params = params;
        this.macros = initializeMacros();

        copyResourcesToTemp();

        this.filesToWrite = getFilesToWrite();

        System.out.println("Temp files are served in " + tempPath);
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

        tryMacrosOnFiles();
        copyPackage();

        // copy final files to output folder
        System.out.println("Copying to " + outputFolder + "...");
        recursiveCopy(tempPath, outputFolder);

        System.out.println("Done!");
    }

    private List<Macro> initializeMacros() {
        return List.of(
                Macro.ofTarget(Targets.JAVAFX_LIBS_TARGET).define(outputPath.resolve("libs").toString()),
                Macro.ofTarget(Targets.EXPORTS_OPENS_TARGET).define(UNDEFINED),
                Macro.ofTarget(Targets.PACKED_PACKAGE_NAME_TARGET).define(params.getPackageToPack()),
                Macro.ofTarget(Targets.SNAKE_CASE_PROJECT_NAME_TARGET).define(params.getSnakeCaseProjectName()),
                Macro.ofTarget(Targets.PASCAL_CASE_PROJECT_NAME_TARGET).define(params.getPascalCaseProjectName()),
                Macro.ofTarget(Targets.CAMEL_CASE_PROJECT_NAME_TARGET).define(params.getCamelCaseProjectName()),
                Macro.ofTarget(Targets.APP_VENDOR_TARGET).define(params.getAppVendor()),
                Macro.ofTarget(Targets.MAIN_CLASS_NAME_TARGET).define(params.getMainClassName()),
                Macro.ofTarget(Targets.MAIN_CLASS_FULL_NAME_TARGET).define(UNDEFINED)
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


    private void tryMacrosOnFiles() throws IOException {
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

    private Path tryMacroOnPath(Path path) {
        String pathString = path.toString();

        boolean changed = false;
        for(Macro macro : macros) {
            String target = macro.getTarget().value();
            String value = macro.getMacroValue();

            pathString = pathString.replaceAll(Pattern.quote(target), Matcher.quoteReplacement(value));
            if (!pathString.equals(path.toString())) {
                changed = true;
                break;
            }
        }

        if (changed) System.out.println("Changed path to " + pathString);
        else System.out.println("No changes to path " + path);

        return Path.of(pathString);
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
}
