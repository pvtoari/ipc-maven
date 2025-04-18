package net.pvtoari.tonetbeans;

public class Parameters {
    private final CaseConverter caseConverter;
    private final String packageToPack, pascalCaseProjectName, snakeCaseProjectName, camelCaseProjectName, mainClassName, appVendor;

    public Parameters(String packageToPack, String projectName, String mainClassName, String appVendor) {
        this.caseConverter = new CaseConverter(projectName);

        this.packageToPack = packageToPack;
        this.pascalCaseProjectName = caseConverter.toPascalCase();
        this.snakeCaseProjectName = caseConverter.toSnakeCase();
        this.camelCaseProjectName = caseConverter.toCamelCase();
        this.mainClassName = mainClassName;
        this.appVendor = appVendor;
    }

    public String getPackageToPack() {
        return packageToPack;
    }

    public String getPascalCaseProjectName() {
        return pascalCaseProjectName;
    }

    public String getSnakeCaseProjectName() {
        return snakeCaseProjectName;
    }

    public String getCamelCaseProjectName() {
        return camelCaseProjectName;
    }

    public String getMainClassName() {
        return mainClassName;
    }

    public String getAppVendor() {
        return appVendor;
    }
}
