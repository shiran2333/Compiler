import java.util.ArrayList;

public class SymbolTableBlock {
    private final SymbolTableBlock father;
    private final ArrayList<SymbolTableItem> symbols;
    public SymbolTableBlock() {
        this.father = null;
        this.symbols = new ArrayList<>();
    }

    public SymbolTableBlock(SymbolTableBlock father) {
        this.father = father;
        this.symbols = new ArrayList<>();
    }

    public SymbolTableBlock getFather() {
        return father;
    }

    public SymbolTableItem addVariableInteger(String name, int registerID) {
        SymbolTableItem item = null;
        if (isGlobal()) item = SymbolTableItem.newGlobalVariableInteger(name);
        else item = SymbolTableItem.newLocalVariableInteger(name);
        item.setRegisterID(registerID);
        symbols.add(item);
        return item;
    }

    public SymbolTableItem addConstantInteger(String name, int registerID) {
        SymbolTableItem item = null;
        if (isGlobal()) item = SymbolTableItem.newGlobalConstantInteger(name);
        else item = SymbolTableItem.newLocalConstantInteger(name);
        item.setRegisterID(registerID);
        symbols.add(item);
        return item;
    }

    public SymbolTableItem getSymbolByName(String name) {
        SymbolTableItem res = null;
        for (SymbolTableItem item : symbols)
            if (name.equals(item.getName()))
                res = item;
        return res;
    }

    private boolean isGlobal() {return father == null;}
}
