package test;

import javaBean.BinaryTree;
import javaBean.Fraction;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import service.CheckAnswer;
import service.Generate;
import utils.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class TestForCalculate {
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    /**
     * 测试javaBean包下的实体类Fraction
     */
    @Test
    public void TestFraction(){
        Fraction fraction1 = new Fraction();
        fraction1.setDenominator(3);
        fraction1.setNumerator(2);
        assert fraction1.toString().equals("2/3"); //断言 fraction1 为 2/3

        Fraction fraction2 = new Fraction(3); //一个参数的构造方法
        assert fraction2.toString().equals("3");

        Fraction fraction3 = new Fraction(2,3);
        assert !fraction3.toString().equals("1/2");
        //测试equals方法
        assert fraction1.equals(fraction3);
        assert !fraction1.equals(fraction2);
        //测试max方法
        assert fraction2.max(fraction1);
    }


    /**
     * 测试utils/Generator 算术表达式生成器
     */
    @Test
    public void TestGenerator(){
        int i = 0;
        while (i<5){
            List<Object> exp = Generator.generator(10);
            System.out.println(Generator.ListToString(exp));
            ++i;
            assert exp.size()>=1 && exp.size()<=9;
        }
    }


    /**
     * 测试utils/ListToBinaryTree 表达式中序转逆波兰式、二叉树BinaryTree的生成
     */
    @Test
    public void TestBinaryTree(){
        int i = 0;
        while (i<5) {
            //生成表达式
            List<Object> exp = Generator.generator(10);
            System.out.println(Generator.ListToString(exp));
            //表达式中序转逆波兰式
            List<Object> list = ListToBinaryTree.toPost(exp);
            System.out.println(Generator.ListToString(list));
            //二叉树BinaryTree的生成
            BinaryTree binaryTree = ListToBinaryTree.expRPNToBinaryTree(list);
            BinaryTreeUtils.postOrder(binaryTree.getRoot());
            System.out.println();
            System.out.println("---------");
            ++i;
        }
    }


    /**
     * 测试 题目查重的原理 是否正确
     */
    @Test
    public void TestIsSameTree(){
        Fraction f = new Fraction(3,4);
        Fraction f2 = new Fraction(3,5);
        Fraction f3 = new Fraction(2,3);
        List<Object> exp1 = new ArrayList<>();
        List<Object> exp2 = new ArrayList<>();
        exp1.add(f); exp2.add(f);
        exp1.add("+"); exp2.add("+");
        exp1.add(f2); exp2.add(f3);
        exp1.add("×"); exp2.add("×");
        exp1.add(f3); exp2.add(f2);
        BinaryTree binaryTree1 = ListToBinaryTree.createBinaryTree(exp1);
        BinaryTree binaryTree2 = ListToBinaryTree.createBinaryTree(exp2);
        //断言： 相同题目 3/4+3/5*2/3 和 3/4+2/3*3/5 生成的二叉树相等
        assert BinaryTreeUtils.isSameTree(binaryTree1.getRoot(), binaryTree2.getRoot());

        Set<BinaryTree> binaryTreeSet = new LinkedHashSet<>();
        binaryTreeSet.add(binaryTree1);
        int i = 0;
        while (i<15) {
            List<Object> exp = Generator.generator(9);
            BinaryTree binaryTree = ListToBinaryTree.createBinaryTree(exp);
            binaryTreeSet.add(binaryTree);
            if(i==6) binaryTreeSet.add(binaryTree2);
            ++i;
        }
        //断言：binaryTree1加入set后，add(binaryTree2)时会自动去重，仅保留一个，故set的数量应为16
        assert binaryTreeSet.size() == 16;
    }

    /**
     * 测试utils/FileStream的异常
     */
    @Test
    public void TestFileStream(){
        List<String> stringList = new ArrayList<>();

        exception.expect(RuntimeException.class);
        exception.expectMessage("FileStream/outStream的参数StrList为空");
        FileStream.outStream(stringList, "test.txt");

        stringList.add("测试1");
        exception.expect(IOException.class);
        FileStream.outStream(stringList," ");
        exception.expect(IOException.class);
        FileStream.inStream(" ");
    }


    /**
     * 测试utils/checkArgs
     */
    @Test
    public void TestCheckArgs(){
        String[] args = {"-n","15","-r","10"};
        String[] args1 = {"-n","15"};
        String[] args2 = {"-r","10"};
        String[] args3 = {};

        assert CheckArgs.checkArgs(args,"-n","-r");
        assert !CheckArgs.checkArgs(args1,"-n","-r");
        assert !CheckArgs.checkArgs(args2,"-n","-r");
        assert !CheckArgs.checkArgs(args3);
    }


    /**
     * 测试service包 Generate功能
     */
    @Test
    public void TestGenerate(){
        Generate generateService = new Generate();
        long start = System.currentTimeMillis();
        generateService.generateExp(10000,10);
        long end = System.currentTimeMillis();
        assert (end-start) < 1000;  //断言生成10000个题目所用时间小于1秒

        exception.expect(RuntimeException.class);
        exception.expectMessage("输入参数不符要求！");
        generateService.generateExp(0,10);
    }


    /**
     * 测试service包 检测答案checkAnswer功能
     */
    @Test
    public void TestCheckAnswer(){
        CheckAnswer check = new CheckAnswer();
        check.checkAnswer("exerciseAnswer.txt", "AnswerCopy.txt");

        exception.expect(RuntimeException.class);
        exception.expectMessage("File Is Empty!!!");
        check.checkAnswer("", "AnswerCopy.txt");

        exception.expect(IOException.class);
        check.checkAnswer(" ", "AnswerCopy.txt");

        exception.expect(IOException.class);
        check.checkAnswer("exAnswer.txt", "AnswerCopy.txt");
    }

}
