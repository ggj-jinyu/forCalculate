package utils;

import javaBean.BinaryTree;
import javaBean.Fraction;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ListToBinaryTree {
    private ListToBinaryTree() { }

    //生成二叉树
    public static BinaryTree toBinaryTree(List<Object> exp, int range){
        return null;
    }

    public static List<Object> toPost(List<Object> exp){
        Stack<String> opStack = new Stack<>();
        List<Object> reversePolish = new ArrayList<>();

        for (Object o : exp) {
            if(o instanceof Fraction){
                reversePolish.add(o);
            }else {
                if(opStack.empty()) opStack.push((String) o);
                else if(o.equals("(")) opStack.push((String) o);
                else if(o.equals(")")){
                    while (true){
                        String opStr = opStack.pop();
                        if("(".equals(opStr)) break;
                        else reversePolish.add(opStr);
                    }
                }else {
                    //如果栈顶元素优先级高于o,应该出栈输出
                    while (priority(opStack.peek(), (String) o)) {
                        reversePolish.add(opStack.pop());
                        if(opStack.empty()) break;
                    }
                    opStack.push((String) o);
                }
            }
        }
        while (!opStack.empty()) {
            reversePolish.add(opStack.pop());
        }
        return reversePolish;
    }

    //获取操作符优先级
    private static int getPriority(String operator){
        switch (operator){
            case "+":
            case "-": return 1;
            case "×":
            case "÷": return 2;
            default: return 0;
        }
    }
    //比较操作符优先级
    private static boolean priority(String op1, String op2){
        return getPriority(op1)>=getPriority(op2);
    }
}
