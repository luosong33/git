package cn.newtouch.dcpp.console.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.DriverManager;
/**
 * Created by Administrator on 2017/3/17.
 */
public class ImlalaJD {  
   
    private static final String SQL_STATEMENT = "SELECT * FROM hive_student;";
//    private static final String IMPALAD_HOST = "10.133.250.73";  //  picc
    private static final String IMPALAD_HOST = "chinallife-host10.sh2.newtouch.com";  //  newtouch
    private static final String IMPALAD_JDBC_PORT = "25010";
    private static final String CONNECTION_URL = "jdbc:hive2://" + IMPALAD_HOST + ':' + IMPALAD_JDBC_PORT + "/;auth=noSasl";
    private static final String JDBC_DRIVER_NAME = "org.apache.hive.jdbc.HiveDriver";
   
    public static void main(String[] args) {  
   
        System.out.println("\n=============================================");  
        System.out.println("Cloudera Impala JDBC Example");  
        System.out.println("Using Connection URL: " + CONNECTION_URL);  
        System.out.println("Running Query: " + SQL_STATEMENT);  
   
        Connection con = null;  
        try {
            Class.forName(JDBC_DRIVER_NAME);
            con = DriverManager.getConnection(CONNECTION_URL);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(SQL_STATEMENT);
            System.out.println("\n== Begin Query Results =========================");
   
            // print the results to the console
            while (rs.next()) {  
                // the example query returns one String column  
                System.out.println(rs.getString("key")+":"+rs.getString("name")+":"+rs.getString("age")); 
            }  
   
            System.out.println("== End Query Results ==============================");  
   
        } catch (SQLException e) {  
            e.printStackTrace();  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            try {  
                con.close();  
            } catch (Exception e) {  
                // swallow  
            }  
        }  
    }  
}

