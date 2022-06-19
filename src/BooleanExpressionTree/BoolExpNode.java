package BooleanExpressionTree;

public abstract class BoolExpNode {
    protected char symbol;
    public BoolExpNode leftChild;
    public BoolExpNode rightChild;

    public BoolExpNode(char symbol){
        this.symbol = symbol;
        rightChild = null;
        leftChild = null;
    }

    public abstract boolean evaluate();

    public String toString(){
        String s = "";
        s += symbol;
        return s;
    }
}
