package service;

import utils.FileStream;

import java.io.*;
import java.util.List;

public class CheckAnswer {
    public void checkAnswer(){
        List<String> answerList = FileStream.inStream(new File("Answer.txt"));
        List<String> testAnswerList = FileStream.inStream(new File("exerciseAnswer.txt"));
        StringBuilder correct = new StringBuilder();
        StringBuilder wrong = new StringBuilder();
        int correctNum = 0;
        int wrongNum = 0;
        correct.append("(");
        wrong.append("(");
        for(int i = 0; i<answerList.size(); ++i){
            int index = answerList.get(i).lastIndexOf(" ");
            String answer = answerList.get(i).substring(index);
            String testAnswer = testAnswerList.get(i).substring(index);
            if(i==0){
                if(answer.equals(testAnswer)){
                    correct.append(i+1);
                    ++correctNum;
                }else {
                    wrong.append(i+1);
                    ++wrongNum;
                }
            }
            if(answer.equals(testAnswer)){
                correct.append(",").append(i+1);
            }else {
                wrong.append(",").append(i+1);
            }
        }
        String correctStr = "Correct: " + correctNum + correct;
        String wrongStr = "Wrong: " + wrongNum + wrong;

        try(BufferedWriter bw = new BufferedWriter(new FileWriter("Grade.txt"))) {
            bw.write(wrongStr); bw.newLine();
            bw.write(correctStr); bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
