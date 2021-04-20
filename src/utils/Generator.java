package utils;

import javaBean.Fraction;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Generator {
    private final static String[] Operator = {"+","-","×","÷"};
    static int flagNum = -1; //-1代表可添加"("，0代表可添加")"，1代表不可添加
    private static Random random = new Random();

    private Generator(){ }

/*
    //无括号生成器
    public static List<Object> generator(int range){
        //操作符个数,1~3
        int count = random.nextInt(3) + 1;

        List<Object> list = new ArrayList<Object>();
        int numerator = random.nextInt(range)+1;
        int denominator = random.nextInt(range)+1;
        list.add(new Fraction(numerator,denominator));
        while (count>0){
            setList(list,range);
            --count;
        }
        return list;
    }

    //设置操作符和操作符后的一个分数
    private static void setList(List<Object> list, int range) {
        //数值范围：取零无意义，则 [1,range+1)
        int numerator = random.nextInt(range)+1;
        int denominator = random.nextInt(range)+1;
        Fraction f = new Fraction(numerator,denominator);
        String operatorStr = Operator[random.nextInt(4)]; //[0,4)
        //装载集合
        list.add(operatorStr);
        list.add(f);
    }
*/

    public static List<Object> generator(int range){
        List<String> exp = new ArrayList<>();
        int nums = generatorOperator(exp);
        List<Fraction> numList = generatorNum(nums, range);
        List<Object> list = new ArrayList<>();
        int numIndex = 0;
        int i = 0;
        if(exp.get(0).equals("(")) {
            list.add(exp.get(0));
            ++i;
        }
        list.add(numList.get(0)); ++numIndex;
        for (; i<exp.size(); i++) {
            String str = exp.get(i);
            //[2, -, 6, (, +, 1‘1/6, )]
            if(str.equals("+") || str.equals("-") || str.equals("×") || str.equals("÷")){
                list.add(str);
                if( (i+1)<exp.size() && "(".equals(exp.get(i+1)) ){
                    list.add(exp.get(i+1)); ++i;
                }
                list.add(numList.get(numIndex)); ++numIndex;
            }else list.add(str);
        }
        return list;
    }

    //生成分数
    private static List<Fraction> generatorNum(int nums, int range){
        List<Fraction> list = new ArrayList<>();
        for(int i = nums; i>0; --i){
            int numerator = random.nextInt(range)+1;
            int denominator = random.nextInt(range)+1;
            list.add(new Fraction(numerator,denominator));
        }
        return list;
    }

    //生成算术操作符
    private static int generatorOperator(List<String> exp) {
        int i = random.nextInt(3)+1; //操作符个数：[1,4)
        //数值的个数 = 操作数 + 1;
        int nums = i + 1;
        while (i>0){
            switch (random.nextInt(5)) {
                case 0:
                    if (flagNum == -1) { exp.add("("); ++flagNum; }
                    break;
                case 1:
                    exp.add("+"); --i; replenish(exp);
                    break;
                case 2:
                    exp.add("-"); --i; replenish(exp);
                    break;
                case 3:
                    exp.add("×"); --i; replenish(exp);
                    break;
                case 4:
                    exp.add("÷"); --i; replenish(exp);
                    break;
            }
        }
        replenishBracket(exp);//判断"("是否被匹配，否则补充")"
        flagNum = -1; //重置flagNum,以便下次也可以生成括号
        //返回数值的个数
        return nums;
    }

    //50%的概率加入")"
    private static void replenish(List<String> exp){
        if (random.nextInt(2) == 0 && flagNum == 0) {
            exp.add(")");
            ++flagNum;
        }
    }

    //若存在"("未被匹配，则在末尾加个")"
    private static void replenishBracket(List<String> exp) {
        boolean flag = false; //false:无"("或匹配成功
        for (String str : exp) {
            if(str.equals("(")) flag = !flag;
            if(str.equals(")")) flag = !flag;
        }
        if(flag) exp.add(")");
    }

    //List转字符串
    public static String ListToString(List<Object> list){
        StringBuilder sb = new StringBuilder();
        for (Object o : list) {
            if(o instanceof String){
                sb.append(o).append(" ");
            }else sb.append(o.toString()).append(" ");
        }
        sb.append("=");
        return sb.toString();
    }
}
