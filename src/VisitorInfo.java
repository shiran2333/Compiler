public class VisitorInfo {
    private int value;
    private SymbolTableItem symbol;
    private boolean isValue;

    public VisitorInfo() {}
    public VisitorInfo(VisitorInfo info) {
        value = info.getValue();
        symbol = info.getItem();
        isValue = info.isValue();
    }

    public int getValue() {
        if (isValue) return value;
        else return symbol.getRegisterID();
    }

    public SymbolTableItem getItem() {
        return symbol;
    }

    public void setValue(int value) {
        this.value = value;
        this.isValue = true;
    }

    public void setSymbol(SymbolTableItem symbol) {
        this.symbol = symbol;
        this.isValue = false;
    }

    @Override
    public String toString() {
        if (isValue) return String.format("%d", getValue());
        else return symbol.getRegisterString();
    }

    public boolean isValue() {return isValue;}
    public boolean isRegister() {return !isValue;}
}
