public class SymbolTable {
    private SymbolTableBlock currentBlock;
    private int registerCounter;

    public SymbolTable() {
        this.currentBlock = new SymbolTableBlock();
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
