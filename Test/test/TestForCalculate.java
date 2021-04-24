package test;

import javaBean.BinaryTree;
import javaBean.Fraction;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import service.CheckAnswer;
import service.Generate;
import utils.BinaryTreeUtils;
import utils.CheckArgs;
import utils.Generator;
import utils.ListToBinaryTree;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * 1.assertTrue/False ([String message,]boolean condition);
 * 判断一个条件是true还是false。感觉这个最好用了，不用记下来那么多的方法名。
 *
 * 2.fail ([String message,]);
 * 失败，可以有消息，也可以没有消息。
 *
 * 3.assertEquals( [String message,]Object expected,Object actual);
 * 判断是否相等，可以指定输出错误信息。
 * 第一个参数是期望值，第二个参数是实际的值。
 * 这个方法对各个变量有多种实现。在JDK1.5中基本一样。
 * 但是需要主意的是float和double最后面多一个delta的值，可能是误差范围，不确定这个 单词什么意思，汗一个。
 *
 * 4.assertNotNull/Null( [String message,]Object obj);
 * 判读一个对象是否非空(非空)。
 *
 * 5.assertSame/NotSame ([String message,]Object expected,Object actual);
 * 判断两个对象是否指向同一个对象。看 内存地址 。
 *
 * 7.failNotSame/failNotEquals (String message, Object expected, Object actual)
 * 当不指向同一个内存地址或者不相等的时候，输出错误信息。
 * 注意信息是必须的，而且这个输出是格式化过的。
 */

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
     * 测试 题目查重
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
     * 测试service包 Generate功能
     */
    @Test
    public void TestGenerate(){
        Generate generateService = new Generate();
        generateService.generateExp(100,10);

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
        check.checkAnswer(new File("exerciseAnswer.txt"), new File("AnswerCopy.txt"));

        exception.expect(RuntimeException.class);
        exception.expectMessage("File Not Found!!!");
        check.checkAnswer(new File("exAnswer.txt"), new File("AnswerCopy.txt"));
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
}
