package net.pvtoari.tonetbeans;

public enum Targets {
    JAVAFX_LIBS_TARGET("%tonetbeans.JAVAFX_LIBS%"),
    EXPORTS_OPENS_TARGET("%tonetbeans.EXPORTS_OPENS%"),
    PACKED_PACKAGE_NAME_TARGET("%tonetbeans.PACKED_PACKAGE_NAME%"),
    SNAKE_CASE_PROJECT_NAME_TARGET("%tonetbeans.SNAKE_CASE_PROJECT_NAME%"),
    PASCAL_CASE_PROJECT_NAME_TARGET("%tonetbeans.PASCAL_CASE_PROJECT_NAME%"),
    CAMEL_CASE_PROJECT_NAME_TARGET("%tonetbeans.CAMEL_CASE_PROJECT_NAME%"),
    APP_VENDOR_TARGET("%tonetbeans.APP_VENDOR%"),
    MAIN_CLASS_NAME_TARGET("%tonetbeans.MAIN_CLASS_NAME%"),
    MAIN_CLASS_FULL_NAME_TARGET("%tonetbeans.MAIN_CLASS_FULL_NAME%"),
    RESOURCE_ACCESS_FIX_TARGET("/net/pvtoari/"), // "/net/pvtoari" -> "/resources/net/pvtoari"
    PRACT7_ACCESS_FIX_TARGET("%tonetbeans.PRACT7_ACCESS_FIX%");

    private final String value;

    Targets(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
