import java.util.Stack;

public class VisitorInfo {
    private int value;
    private SymbolTableItem symbol;
    private boolean isValue;
    private Stack<Integer> trueBlock, falseBlock, condBlock;

    public VisitorInfo() {
        trueBlock = new Stack<>();
        falseBlock = new Stack<>();
        condBlock = new Stack<>();
    }
    public VisitorInfo(VisitorInfo info) {
        value = info.getValue();
        symbol = info.getItem();
        isValue = info.isValue();
        trueBlock = new Stack<>(); trueBlock.addAll(info.trueBlock);
        falseBlock = new Stack<>(); falseBlock.addAll(info.falseBlock);
        condBlock = new Stack<>(); condBlock.addAll(info.condBlock);
    }

    public int getCondBlock() {
        return condBlock.peek();
    }

    public void setCondBlock(int condBlock) {
        this.condBlock.push(condBlock);
    }

    public void removeCondBlock() {
        condBlock.pop();
    }

    public int getTrueBlock() {
        return trueBlock.peek();
    }

    public void setTrueBlock(int trueBlock) {
        this.trueBlock.push(trueBlock);
    }

    public void removeTrueBlock() {
        trueBlock.pop();
    }

    public int getFalseBlock() {
        return falseBlock.peek();
    }

    public void setFalseBlock(int falseBlock) {
        this.falseBlock.push(falseBlock);
    }

    public void removeFalseBlock() {
        falseBlock.pop();
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
