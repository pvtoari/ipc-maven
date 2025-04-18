package net.pvtoari.tonetbeans;

public class Macro {
    private String macroValue;
    private Targets macroTarget;

    private Macro(String value, Targets target) {
        this.macroValue = value;
        this.macroTarget = target;
    }

    public static Macro ofTarget(Targets target) {
        return new Macro(null, target);
    }
    
    public Macro define(String value) {
        this.setValue(value);

        return this;
    }

    public String getMacroValue() {
        return macroValue;
    }

    public void setValue(String macroValue) {
        this.macroValue = macroValue;
    }

    public Targets getTarget() {
        return macroTarget;
    }
}
