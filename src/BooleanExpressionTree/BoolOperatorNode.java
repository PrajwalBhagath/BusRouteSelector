package BooleanExpressionTree;

public class BoolOperatorNode extends BoolExpNode {
    public BoolOperatorNode(char symbol){
        super(symbol);
    }

    public boolean evaluate(){
        boolean lchild = false;
        boolean rchild = false;

        if(rightChild != null){
            rchild = rightChild.evaluate();
        }

        if(leftChild != null){
            lchild = leftChild.evaluate();
        }

        if(symbol == '&'){
            return (lchild & rchild);
        }
        if(symbol == '|'){
            return (rchild | lchild);
        }

        if(symbol == '!'){
            return (! rchild);
        }

        if(symbol == '^'){
            return (lchild ^ rchild);
        }

        throw new RuntimeException("Operation was Unsuccessful");
    }
}
