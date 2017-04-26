package org.jumao.bi.test;

/**
 * Created by Administrator on 2017/4/25.
 */
public class FirstConv {

    public static void main(String[] args) {

        String columnNameAll = "no\n" +
                "no\n" +
                "id\n" +
                "no\n" +
                "id\n" +
                "name\n" +
                "id\n" +
                "name\n" +
                "id\n" +
                "id\n" +
                "type\n" +
                "amt\n" +
                "url\n" +
                "name\n" +
                "img\n" +
                "name\n" +
                "tel\n" +
                "no\n" +
                "time\n" +
                "info\n" +
                "time\n" +
                "time\n" +
                "time\n" +
                "time\n" +
                "time\n" +
                "comment\n" +
                "delete\n" +
                "refund\n" +
                "time\n" +
                "time\n" +
                "time\n" +
                "direct\n" +
                "img\n" +
                "serial";
        String[] strings = columnNameAll.split("\n",-1);
        for (String s :  strings){
            //  首字母转大写
            char[] cs = s.toCharArray();
            cs[0] -= 32;
            s = String.valueOf(cs);
            System.out.println(s);
        }


    }

}
