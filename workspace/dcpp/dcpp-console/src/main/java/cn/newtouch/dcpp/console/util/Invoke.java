package cn.newtouch.dcpp.console.util;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by luosong on 2017/4/25.
 */
public class Invoke {
    public List getImpalaData(Object obj, String Sql) {
        ArrayList<Object> resultList = new ArrayList<Object>();  //  返回的多条数据集合
        try {
                Class c = obj.getClass();  //  通过类获取
                Object o = c.newInstance();  //  创建对象

                    String columnTypeName = "int";

                    try {
                        Class object = getColumnTypeClazz(columnTypeName);
                        Method method = c.getMethod("setBuyer_id",  getColumnTypeClazz(columnTypeName)); //  第一个参数是调用的方法名，第二个参数是这个方法传的值的类型
                        //反射调用方法
                        method.invoke(o, 1100);  //  第一个参数是对象名；第二个是你给这个方法传的值
                    } catch (Exception e) {
//                        logHelper.getBuilder().error("没有定义相应实体方法 = ", e);
                        e.printStackTrace();
                    }
                resultList.add(o);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultList;
    }

    /**
     * 根据根据查询返回的数据类型获得相应类型的class
     */
    public Class getColumnTypeClazz(String columnTypeName) {
        if (columnTypeName.equals("String")) return String.class;
        if (columnTypeName.equals("int"))return Integer.class;
        if (columnTypeName.equals("timestamp")) return Timestamp.class;
        if (columnTypeName.equals("String")) return Boolean.class;
        if (columnTypeName.equals("String")) return BigDecimal.class;
        if (columnTypeName.equals("smallint")) return Integer.class;
        if (columnTypeName.equals("smallint")) return Integer.class;
        return null;
    }

    public static void main(String[] args) {
//        List<Object> resultList = new ImpalaJDBC().getImpalaData(new HiveStudent(), "select age from hive_student");
//        Map<String, ArrayList<String>> column = new ImpalaJDBC().getColumn("select phone from hive_student", "phone");
        List<Object> resultList = new Invoke().getImpalaData(new EpTrade(), "select  *  from  ep_trade where  key = \'242\'");
        System.out.println("====================resultList====================size:" + resultList.size());
    }
}
