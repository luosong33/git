package cn.newtouch.dcpp.console.util;

import java.io.*;

/**
 * Created by Administrator on 2017/3/21.
 */
public class IOUtil {

    public void util (String filepath_){
        BufferedReader reader = null;
        BufferedWriter writer = null;
        try {
            reader = new BufferedReader(new InputStreamReader(
                    new FileInputStream(filepath_), "gbk"));
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(new File(filepath_ + "_back")), "gbk"));
            String stry = "";
            while ((stry = reader.readLine()) != null) { // 当读取的一行不为空时,把读到的str的值赋给str1


                stry += "\r\n";
                writer.write(stry);
            }
            writer.flush();
            long end = System.currentTimeMillis();
        } catch (FileNotFoundException e) {
            System.out.println("找不到指定文件");
        } catch (IOException e) {
            System.out.println("读取文件失败");
        } finally {
            try {
                writer.close(); // 先开后关
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
