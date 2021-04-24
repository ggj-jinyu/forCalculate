package service;

import utils.FileStream;

import java.io.*;
import java.util.List;

public class CheckAnswer {
    public void checkAnswer(File exerciseAnswer, File answer){
        if(exerciseAnswer==null || !exerciseAnswer.exists()
                || answer==null || !answer.exists()){
            System.out.println("文件未找到!!!");
            throw new RuntimeException("File Not Found!!!");
        }
        List<String> answerList = FileStream.inStream(answer);
        List<String> testAnswerList = FileStream.inStream(exerciseAnswer);
        StringBuilder correct = new StringBuilder();
        StringBuilder wrong = new StringBuilder();
        int correctNum = 0; //题目一致的数目
        int wrongNum = 0;   //不一致的数目
        for(int i = 0; i<answerList.size(); ++i){
            int index = answerList.get(i).lastIndexOf(" ");
            String answerStr = answerList.get(i).substring(index);
            String testAnswerStr = testAnswerList.get(i).substring(index);
            //判断答案是否一致
            if(answerStr.equals(testAnswerStr)){
                correct.append(i+1).append(",");
                ++correctNum;
            }else {
                wrong.append(i+1).append(",");
                ++wrongNum;
            }
        }
        correct.deleteCharAt(correct.lastIndexOf(","));//删除最后一个逗号
        wrong.deleteCharAt(wrong.lastIndexOf(","));
        String correctStr = "Correct: " + correctNum + "(" + correct + ")";
        String wrongStr = "Wrong: " + wrongNum + "(" + wrong + ")";
        //输出数据到文件
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("Grade.txt"))) {
            bw.write(wrongStr); bw.newLine();
            bw.write(correctStr); bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
