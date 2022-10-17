package cfg;

import java.util.ArrayList;

public class ArrayVar extends Variable {
    ArrayList<String> value = new ArrayList<>();
    public ArrayVar(String name, boolean isActive, String type) {
        super(name, isActive, type);
    }
    public ArrayVar(String name, boolean isActive, String type, ArrayList<String>value) {
        super(name, isActive, type);
        this.value = value;
    }

}
