package javaBean;

/**
 * 分数类
 */
public class Fraction {
    int numerator; //分子
    int denominator; //分母

    //无参构造器
    public Fraction() {
    }

    //正整数的构造器
    public Fraction(int numerator) {
        this.numerator = numerator;
        this.denominator = 1;
    }

    //分数带参构造器
    public Fraction(int numerator, int denominator) {
        //求得最小公倍数gcd，并利用gcd将分数化为最简
        int gcd = LeastCommonMultiple(numerator,denominator);
        this.numerator = numerator/gcd;
        this.denominator = denominator/gcd;
    }

    public int getNumerator() {
        return numerator;
    }

    public void setNumerator(int numerator) {
        this.numerator = numerator;
    }

    public int getDenominator() {
        return denominator;
    }

    public void setDenominator(int denominator) {
        this.denominator = denominator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Fraction fraction = (Fraction) o;

        if (numerator != fraction.numerator) return false;
        return denominator == fraction.denominator;
    }

    @Override
    public String toString() {
       if(numerator<denominator) return numerator + "/" +denominator;
       if(numerator%denominator==0) return ""+numerator/denominator;
       else
           return numerator/denominator + "‘" + (numerator%denominator) + "/" +denominator;
    }

    /**
     * 辗转相除法求最大公约数
     * @param num1 正整数
     * @param num2 正整数
     * @return 返回最大公约数
     */
    private int LeastCommonMultiple(int num1, int num2){
        if(num2 == 0)
            return num1;
        return LeastCommonMultiple(num2, num1 % num2);
    }
}
