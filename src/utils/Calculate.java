package utils;

import javaBean.Fraction;

import java.util.List;
import java.util.Stack;

public class calculate {
    private calculate() { }

    /**
     * 计算逆波兰式，返回一个Fraction
     * @param reversePolish 逆波兰表达式
     * @return Fraction
     */
    public static Fraction getResult(List<Object> reversePolish){
        Stack<Fraction> fractionStack = new Stack<>();
        for (Object o : reversePolish) {
            if(o instanceof Fraction) fractionStack.push((Fraction) o);
            else {
                Fraction rightOperands = fractionStack.pop();
                Fraction leftOperands = fractionStack.pop();
                switch ((String) o){
                    case "+": fractionStack.push(FractionUtils.add(leftOperands, rightOperands)); break;
                    case "-": fractionStack.push(FractionUtils.subtract(leftOperands, rightOperands)); break;
                    case "×": fractionStack.push(FractionUtils.multiply(leftOperands, rightOperands)); break;
                    case "÷": fractionStack.push(FractionUtils.divide(leftOperands, rightOperands)); break;
                    default: System.err.println("reversePolish存在不可识别的操作符！"); break;
                }
            }
        }
        return fractionStack.pop();
    }
}
