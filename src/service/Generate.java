package service;

import javaBean.BinaryTree;
import javaBean.Fraction;
import utils.Calculate;
import utils.FileStream;
import utils.Generator;
import utils.ListToBinaryTree;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class Generate {
    public void generateExp(int nums, int range){
        if(nums<=0) return;
        List<String> expList = new ArrayList<>();
        Set<BinaryTree> expSet = new LinkedHashSet<>(); //利用Set查重，方法可改进
        List<String> answerList = new ArrayList<>();
        int expSize = 0; //题目个数
        while (expSize<nums){
            //生成中序表达式
            List<Object> exp = Generator.generator(range);
            //表达式转逆波兰式
            List<Object> expRPN = ListToBinaryTree.toPost(exp);
            //逆波兰式转二叉树
            BinaryTree binaryTree = ListToBinaryTree.expRPNToBinaryTree(expRPN);
            //binaryTree加入exp
            expSet.add(binaryTree);
            //binaryTree加入expSet时会自动去重，如果expSet.size等于expSize，说明题目重复了
            //只有expSet.size()>=expSize，才说明题目可以添加
            if(expSet.size()>=expSize){
                //计算逆波兰式，得到答案
                Fraction result = Calculate.getResult(expRPN);
                //result为null,说明计算过程中出现了负数
                //题目要求；计算过程不能产生负数，也就是说算术表达式中如果存在形如e1− e2的子表达式，那么e1≥ e2。
                if(result==null) continue;
                ++expSize;
                //把数据加入集合
                expList.add(expSize+"."+Generator.ListToString(exp));
                answerList.add(expSize+"."+result.toString());
            }
        }

        //输出数据到文件
        FileStream.outStream(expList, new File("Exercises.txt"));
        FileStream.outStream(answerList,new File("Answer.txt"));
    }
}
