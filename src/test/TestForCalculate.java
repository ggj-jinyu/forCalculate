package test;

import javaBean.Fraction;
import org.junit.Test;
import utils.Generator;
import utils.ListToBinaryTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

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

    /**
     * 测试实体类Fraction
     */
    @Test
    public void TestFraction(){
        Fraction fraction1 = new Fraction();
        fraction1.setDenominator(3);
        fraction1.setNumerator(2);
        assert fraction1.toString().equals("2/3");

        Fraction fraction2 = new Fraction(3);
        assert fraction2.toString().equals("3");

        Fraction fraction3 = new Fraction(2,4);
        assert fraction3.toString().equals("1/2");
    }

    /**
     * 测试算术表达式生成器
     */
    @Test
    public void TestGenerator(){
        int i = 0;
        while (i<10){
            List<Object> exp = Generator.generator(9);
            System.out.println(Generator.ListToString(exp));
            ++i;
        }
    }

    /**
     * 测试 算术表达式 中序转后序
     */
    @Test
    public void TestListToBinaryTree(){
        int i = 0;
        while (i<15){
            List<Object> exp = Generator.generator(9);
            System.out.println(Generator.ListToString(exp));
            List<Object> list = ListToBinaryTree.toPost(exp);
            System.out.println(Generator.ListToString(list));
            System.out.println();
            ++i;
        }
    }


}
