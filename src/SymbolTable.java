import java.util.ArrayList;

public class SymbolTable {
    private SymbolTableBlock currentBlock;
    private int registerCounter;

    public SymbolTable() {
        this.currentBlock = new SymbolTableBlock();
        ArrayList<SymbolTableItem> params;
        currentBlock.addIntFunction("getint", new ArrayList<>());
        currentBlock.addIntFunction("getch", new ArrayList<>());
        params = new ArrayList<>(); params.add(SymbolTableItem.newGlobalVariableInteger("test"));
        currentBlock.addVoidFunction("putint", params);
        params = new ArrayList<>(); params.add(SymbolTableItem.newGlobalVariableInteger("test"));
        currentBlock.addVoidFunction("putch", params);
        registerCounter = 0;
    }

    public void exitCurrentTable() {
        currentBlock = currentBlock.getFather();
    }

    public void createInnerTable() {
        currentBlock = new SymbolTableBlock(currentBlock);
    }

    public boolean currentBlockHasName(String name) {
        return currentBlock.getSymbolByName(name) != null;
    }

    public SymbolTableItem getSymbolByName(String name) {
        SymbolTableItem res = null;
        SymbolTableBlock current = currentBlock;
        while (res == null && current != null) {
            res = current.getSymbolByName(name);
            current = current.getFather();
        }
        return res;
    }

    public SymbolTableItem newVariableInteger(String name) {
        if (currentBlockHasName(name)) System.exit(2);
        return currentBlock.addVariableInteger(name, ++registerCounter);
    }

    public SymbolTableItem newConstantInteger(String name) {
        if (currentBlockHasName(name)) System.exit(2);
        return currentBlock.addConstantInteger(name, ++registerCounter);
    }

    public SymbolTableItem newRegister() {
        SymbolTableItem item = SymbolTableItem.newLocalConstantInteger("text");
        item.setRegisterID(++registerCounter);
        return item;
    }
}
