package cfg;

public class NonArrayVar extends Variable{

    String value = null;

    public NonArrayVar(String name, boolean isActive, String type) {
        super(name, isActive, type);
    }

    public NonArrayVar(String name, boolean isActive, String type, String value) {
        super(name, isActive, type);
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
