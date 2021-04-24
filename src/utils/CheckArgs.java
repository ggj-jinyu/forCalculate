package utils;

import java.util.Arrays;
import java.util.stream.Stream;

public class CheckArgs {
    private CheckArgs(){ }

    //判断参数字符数组args中是否具有target中的所有元素
    public static boolean checkArgs(String[] args, String... target) {
        if (target == null || args == null || target.length<=0 || args.length<=0) {
            return false;
        }
        boolean exit = true;
        for (String targetStr : target) {
            Stream<String> stringStream = Arrays.stream(args).filter(s -> s.equals(targetStr));
            if(stringStream.count()==0) {
                exit = false;
                break;
            }
        }
        return exit;
    }
}
