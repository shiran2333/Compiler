import java.util.ArrayList;

public class SymbolTableItem {
    private final String name;
    private final int kind; // 0 -> void, 1 -> int
    private final int type; // 0 -> var, 1 -> const, 2 -> array, 3 -> const array, 4 -> function
    private final int global; // 0 -> local, 1 -> global
    private int registerID = -1;
    private ArrayList<SymbolTableItem> params;

    private static final int VOID = 0;
    private static final int INT = 1;

    private static final int VARIABLE = 0;
    private static final int CONSTANT = 1;
    private static final int VARIABLE_ARRAY = 2;
    private static final int CONSTANT_ARRAY = 3;
    private static final int FUNCTION = 4;

    private static final int LOCAL = 0;
    private static final int GLOBAL = 1;

    private SymbolTableItem(String name, int kind, int type, int global) {
        this.name = name;
        this.kind = kind;
        this.type = type;
        this.global = global;
        if (type == 4) params = new ArrayList<>();
    }

    private SymbolTableItem(int kind, int type, int global) {
        this.name = null;
        this.kind = kind;
        this.type = type;
        this.global = global;
        if (type == 4) params = new ArrayList<>();
    }

    private void setParams(ArrayList<SymbolTableItem> params) {
        this.params = new ArrayList<>();
        this.params.addAll(params);
    }

    public void setRegisterID(int registerID) {
        this.registerID = registerID;
    }

    public String getName() {
        return name;
    }

    public ArrayList<SymbolTableItem> getParams() {
        return params;
    }

    public int getRegisterID() {
        return registerID;
    }

    public String getRegisterString() {
        if (isGlobal()) return String.format("@%s", name);
        else return String.format("%%v%d", registerID);
    }

    public static SymbolTableItem newLocalVariableInteger(String name) {
        return new SymbolTableItem(name, INT, VARIABLE, LOCAL);
    }

    public static SymbolTableItem newGlobalVariableInteger(String name) {
        return new SymbolTableItem(name, INT, VARIABLE, GLOBAL);
    }

    public static SymbolTableItem newLocalConstantInteger(String name) {
        return new SymbolTableItem(name, INT, CONSTANT, LOCAL);
    }

    public static SymbolTableItem newGlobalConstantInteger(String name) {
        return new SymbolTableItem(name, INT, CONSTANT, GLOBAL);
    }

    public static SymbolTableItem newVoidFunction(String name, ArrayList<SymbolTableItem> params) {
        SymbolTableItem res = new SymbolTableItem(name, VOID, FUNCTION, GLOBAL);
        res.setParams(params);
        return res;
    }

    public boolean isINT() {return kind == INT;}
    public boolean isVOID() {return kind == VOID;}
    public boolean isLocal() {return global == LOCAL;}
    public boolean isGlobal() {return  global == GLOBAL;}
    public boolean isVariable() {return type == VARIABLE || type == VARIABLE_ARRAY;}
    public boolean isConstant() {return type == CONSTANT || type == CONSTANT_ARRAY;}
    public boolean isFunction() {return type == FUNCTION;}
}
