package cn.newtouch.dcpp.console.util;

import java.sql.SQLException;
import java.sql.Connection;  
import java.sql.ResultSet;  
import java.sql.Statement;  
import java.sql.DriverManager;  
  
/** 
 * 测试hive 的客户端连接 
 * @author alexxiyang (https://github.com/alexxiyang) 
 * 
 */  
public class HiveJdbcCli {  
    
  /** 
   * 注意：hive-server2 引用的driver是  org.apache.hive.* 而 hive-server 是 org.apache.hadoop.hive.* 
   */  
  private static String driverName = "org.apache.hive.jdbc.HiveDriver";  
   
  /** 
   * @param args 
   * @throws SQLException 
   */  
  public static void main(String[] args) throws SQLException {  
      try {  
      Class.forName(driverName);  
    } catch (ClassNotFoundException e) {  
      // TODO Auto-generated catch block  
      e.printStackTrace();  
      System.exit(1);  
    }  
    //hive的默认端口是 10000，如果要修改就修改 hive-site.xml 文件的hive.server2.thrift.port 属性值  
    //默认用户名hive，默认密码为空  
    Connection con = DriverManager.getConnection("jdbc:hive2://10.133.250.73:21050/default", "hive", "");
      
    Statement stmt = con.createStatement();  
    //测试的表名 testhivedrivertable  
    String tableName = "test_car_prpcmain_1100";
      
    String sql = "select * from " + tableName + "where key = \"TDAA201511020000280455\"";
    System.out.println("Running: " + sql);  
    ResultSet res = stmt.executeQuery(sql);  
    while (res.next()) {  
      System.out.println(String.valueOf(res.getInt(1)) + "\t" + res.getString(2));  
    }  
  }  
}