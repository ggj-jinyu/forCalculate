package utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileStream {
    private FileStream() { }

    public static void outStream(List<String> strList, String fileStr){
        if(strList == null || strList.isEmpty()) {
            throw new RuntimeException("FileStream/outStream的参数StrList为空");
        }
        //jdk7的异常处理方式，若有异常自动关闭BufferedWriter
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(fileStr))) {
            //遍历expression集合
            for (String str : strList) {
                bw.write(str);
                bw.newLine();
                bw.flush();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static List<String> inStream(String fileStr){
        List<String> list = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(fileStr))) {
            String line;
            while ((line=br.readLine())!=null){
                list.add(line);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

}
