package cfg;

public abstract class  Variable {

    String name;
    boolean isActive;
    String type;

    public Variable(String name, boolean isActive, String type) {
        this.name = name;
        this.isActive = isActive;
        this.type = type;

    }

}
