package cn.newtouch.dcpp.console.util;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.sql.*;
import java.util.*;

public class ImpalaJDBC {

    private static final String JDBC_DRIVER_NAME = "org.apache.hive.jdbc.HiveDriver";
    // 设置 impalad host
//	private static final String IMPALAD_HOST = "10.133.250.73";  //  picc
//	private static final String IMPALAD_HOST = "chinallife-host10.sh2.newtouch.com";  //  newtouch
    private static final String IMPALAD_HOST = "192.168.223.151";  //
    // 设置 impalad JDBC port
//	private static final String IMPALAD_JDBC_PORT = "25010";  //  picc
    private static final String IMPALAD_JDBC_PORT = "21050";
    private static final String CONNECTION_URL = "jdbc:hive2://" + IMPALAD_HOST + ':' + IMPALAD_JDBC_PORT + "/;auth=noSasl";
    // sql语句样例
//	private static final String SQL_STATEMENT = "SELECT key FROM test_car_prpcmain_1100 where key = \"TDAA201511010001520501\";";  //  picc
    private static final String SQL_STATEMENT = "select * from  tb_emp_info;";

    public static void main(String[] args) {

        System.out.println(new ImpalaJDBC().getJson2());
        List<TbEmpInfo> resultList = new ImpalaJDBC().getJson2();
        /*for (String s: list){
			System.out.println(s);
		}*/
		/*for (TbEmpInfo tbEmpInfo : resultList) {
			for (HashMap<String, String> map : tbEmpInfo.getaData()) {
				Set set = map.keySet();
				Iterator it = set.iterator();
				while (it.hasNext()) {
					System.out.print(map.get(it.next())+":");
				}
			}
		}*/
    }

    public String getJson() {

        String json = null;
        Connection con = null;
        try {
            Class.forName(JDBC_DRIVER_NAME);
            con = DriverManager.getConnection(CONNECTION_URL);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(SQL_STATEMENT);

            while (rs.next()) {
                json = "{sum(sumpremium):" + rs.getString("sum(sumpremium)") + "}";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return json;
//				String aDataString = JSON.toJSONString(aData); //  http://www.cnblogs.com/hoojo/archive/2011/04/22/2024628.html
//				GsonBuilder gb = new GsonBuilder();
//				Gson gson = gb.create();
//				String aDataString = gson.toJson(aData);
    }

    public List getJson2() {
        Connection con = null;
        ArrayList<TbEmpInfo> resultList = new ArrayList<>();  //  返回的多条数据集合
        ArrayList<HashMap<String, String>> aData = new ArrayList<>();  //  代表一条数据，hashmap代表 字段名：字段值
        try {
            Class.forName(JDBC_DRIVER_NAME);
            con = DriverManager.getConnection(CONNECTION_URL);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(SQL_STATEMENT);
            while (rs.next()) {
                System.out.print(rs.getString(1)/*+":" + "\t" + rs.getString(2)*/);
                System.out.print(rs.getString(2));
                System.out.print(rs.getString(3));
//				System.out.println(rs.getString("tel"));
				/*List<String> columnList = TbEmpInfo.getColumn();  //  获取字段名集合，根据字段名获取值
				for (String column : columnList){
					HashMap<String, String> columnMap = new HashMap<>();
					columnMap.put(column,rs.getString(column));
					aData.add(columnMap);
//					columnMap.clear();
				}
//				String aDataString = JSON.toJSONString(aData); //  http://www.cnblogs.com/hoojo/archive/2011/04/22/2024628.html
//				GsonBuilder gb = new GsonBuilder();
//				Gson gson = gb.create();
//				String aDataString = gson.toJson(aData);
//				resultList.add(aDataString);
				TbEmpInfo tbEmpInfo = new TbEmpInfo();
				tbEmpInfo.setaData(aData);
				resultList.add(tbEmpInfo);*/
//				aData.clear();
            }
//			res.setColumn(column);
//			res.setValue(al);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
//		return JSON.toJSONString(resultList);
        return resultList;
    }
}