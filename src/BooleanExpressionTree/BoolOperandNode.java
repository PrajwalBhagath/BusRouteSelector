package BooleanExpressionTree;

public class BoolOperandNode extends BoolExpNode{

    public BoolOperandNode(char Symbol){
        super(Symbol);
    }


    public boolean evaluate() {
        if(symbol == 'T'){
            return true;
        }
        if(symbol == 'F'){
            return false;
        }

        throw new RuntimeException("There was an Operand error");
    }
}
