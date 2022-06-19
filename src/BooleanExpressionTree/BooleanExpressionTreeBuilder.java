package BooleanExpressionTree;

import java.util.Stack;

public class BooleanExpressionTreeBuilder {
    private String toInfix = null;

    public static BoolExpNode buildExpressionTree(char[] expression){
        if(expression[expression.length - 1] == 'T' || expression[expression.length - 1] == 'F') {
            throw new IllegalArgumentException("Invalid input: Last expression not an operator!");
        }
        Stack<BoolExpNode> tree = new Stack<BoolExpNode>();

        for(char c : expression){
            if(c == 'T' || c == 'F') {
                tree.push(new BoolOperandNode(c));
                continue;
            }if(c == '&'||c == '^'||c == '|' || c == '!'){
                BoolOperatorNode operator = new BoolOperatorNode(c);

                if(c == '!'){
                    operator.rightChild = tree.pop();
                    tree.push(operator);
                }else {
                    operator.rightChild = tree.pop();
                    operator.leftChild = tree.pop();
                    tree.push(operator);
                }
            } else {
            throw new IllegalArgumentException("");
        }
        }

        return tree.pop();
    }

    public static String toInfixString(BoolExpNode node){
        if(node.rightChild==null && node.leftChild==null){
            return Character.toString(node.symbol);
        }
        if(node.leftChild == null){
            String s ="";
            s+= Character.toString(node.symbol);
            s+= Character.toString(node.rightChild.symbol);
            return s;
        }
        if(node.rightChild == null){
            String s ="";
            s+= Character.toString(node.symbol);
            s+= Character.toString(node.rightChild.symbol);
            return s;


        }

            return ("("+(toInfixString(node.leftChild))+node.symbol+(toInfixString(node.rightChild))+")");


    }

    public static int countNodes(BoolExpNode node){
        if(node == null){
            return 0;
        }
        return countNodes(node.leftChild)+1+countNodes(node.rightChild);

    }
}
