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
}
