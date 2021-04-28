import service.CheckAnswer;
import service.Generate;
import utils.CheckArgs;

import java.io.File;

public class Main {
    private static final Generate generateExp = new Generate();
    private static final CheckAnswer checkAnswer = new CheckAnswer();

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        if (CheckArgs.checkArgs(args, "-n", "-r")) {
            generate(args);
            System.out.println("题目已经生成，可在jar包同目录下的Exercises.txt和Answer.txt查看");
        } else if (CheckArgs.checkArgs(args, "-e", "-a")) {
            check(args);
            System.out.println("答案检查完毕，可在jar包同目录下的Grade.txt查看结果");
        } else if (CheckArgs.checkArgs(args, "-help")){
            System.out.println("HELP:");
            System.out.println("生成表达式和答案：java -jar forCalculate -n [题目个数] -r [数值范围(>=0)]");
            System.out.println("检测答案：java -jar forCalculate -e [用户答案文件名] -a [正确答案文件名]");
        } else {
            System.out.println("参数不合法");
        }
        long end = System.currentTimeMillis();
        System.out.println("总花费：" + (end - start) + "毫秒");
    }

    private static void check(String[] args) {
        String exercisesAnswer = null;
        String answer = null;
        try{
            for (int i = 0; i < args.length; i++) {
                if("-e".equals(args[i])) {
                    exercisesAnswer=args[i+1];continue;
                }
                if("-a".equals(args[i])) answer=args[i+1];
            }
            checkAnswer.checkAnswer(new File(exercisesAnswer),new File(answer));
        }catch (Exception e){
            System.out.println("参数输入格式有误或文件不存在！");
            System.out.println(e.getMessage());
        }
    }

    private static void generate(String[] args) {
        int num = 0;
        int range = 0;
        try{
            for (int i = 0; i < args.length; i++) {
                if("-n".equals(args[i])) {
                    num=Integer.parseInt(args[i+1]);continue;
                }
                if("-r".equals(args[i])) range=Integer.parseInt(args[i+1]);
            }
        }catch (Exception e){
            System.out.println("参数输入格式有误！");
            System.out.println(e.getMessage());
            System.exit(0);
        }
        generateExp.generateExp(num,range);
    }

}
