package utils;

import javaBean.Fraction;

public class FractionUtils {
    private FractionUtils() { }

    //Fraction的加法运算
    public static Fraction add(Fraction f1, Fraction f2){
        int denominator = f1.getDenominator() * f2.getDenominator();
        int nominator = f1.getNumerator() * f2.getDenominator()
                + f1.getDenominator() * f2.getNumerator();
        return new Fraction(nominator,denominator);
    }

    //Fraction的减法运算
    public static Fraction subtract(Fraction f1, Fraction f2){
        //生成的题目中计算过程不能产生负数，若为负数则返回null
        if(f2.max(f1)) return null;
        else {
            int denominator = f1.getDenominator() * f2.getDenominator();
            int nominator = f1.getNumerator() * f2.getDenominator()
                    - f1.getDenominator() * f2.getNumerator();
            return new Fraction(nominator, denominator);
        }
    }

    //Fraction的乘法运算
    public static Fraction multiply(Fraction f1, Fraction f2){
        int nominator = f1.getNumerator() * f2.getNumerator();
        int denominator = f1.getDenominator() * f2.getDenominator();
        return new Fraction(nominator,denominator);
    }

    //Fraction的乘法运算
    public static Fraction divide(Fraction f1, Fraction f2){
        int nominator = f1.getNumerator() * f2.getDenominator();
        int denominator = f2.getNumerator() * f1.getDenominator();
        if(denominator == 0) return null;
        return new Fraction(nominator,denominator);
    }
}