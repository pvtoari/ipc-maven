package net.pvtoari.tonetbeans;

public class CaseConverter {
    private String source;
    private String camelCase = "", snakeCase = "", pascalCase = "";

    public CaseConverter(String source) {
        this.source = source;

        normalize(source);
    }

    private void normalize(final String source) {
        this.source = source;

        this.source = this.source.replaceAll("([A-Z])", " $1");
        this.source = this.source.toLowerCase();
        this.source = this.source.replaceAll("[^a-zA-Z0-9]", " ");
        this.source = this.source.replaceAll("\\s+", " ");
    }

    public String toCamelCase() {
        if (!this.camelCase.isEmpty()) return this.camelCase;

        String[] parts = this.source.split(" ");
        StringBuilder camelCase = new StringBuilder(parts[0].toLowerCase());

        for (int i = 1; i < parts.length; i++) {
            camelCase.append(parts[i].substring(0, 1).toUpperCase());
            camelCase.append(parts[i].substring(1).toLowerCase());
        }

        return camelCase.toString();
    }

    public String toSnakeCase() {
        if (!this.snakeCase.isEmpty()) return this.snakeCase;

        return this.source.replaceAll(" ", "_");
    }

    public String toPascalCase() {
        if (!this.pascalCase.isEmpty()) return this.pascalCase;

        String camelCase = toCamelCase();

        return camelCase.substring(0, 1).toUpperCase() + camelCase.substring(1);
    }
}
