package cn.newtouch.dcpp.console.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.HBaseAdmin;

import java.io.IOException;

/**
 * Created by Administrator on 2017/3/21.
 */
public class HbaseTest_ {

    public static Configuration config;

    static {
        /*config = HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.property.clientPort", "2181");
        configuration.set("hbase.zookeeper.quorum", "10.133.250.70");
        configuration.set("hbase.master", "10.133.250.70:600000");*/
//        config = HBaseConfiguration.create();// 配置
        config = new Configuration();
//        config.set("fs.defaultFS", "hdfs://10.133.250.70:8020");
//        config.set("hbase.zookeeper.quorum", "10.133.250.70,10.133.250.71,10.133.250.72,10.133.250.74,10.133.250.75");
        config.set("hbase.zookeeper.quorum","node1,node2,node3");
        config.set("hbase.zookeeper.property.clientPort", "2181");
    }

    public static void main(String... args) {
        createTable("ls_test");
    }


    /**
     * 创建表
     *
     * @param tableName
     */
    public static void createTable(String tableName) {
        System.out.println("start create table ......");
        try {
            HBaseAdmin hBaseAdmin = new HBaseAdmin(config);
            if (hBaseAdmin.tableExists(tableName)) {// 如果存在要创建的表，那么先删除，再创建
                hBaseAdmin.disableTable(tableName);
                hBaseAdmin.deleteTable(tableName);
                System.out.println(tableName + " is exist,detele....");
            }
            HTableDescriptor tableDescriptor = new HTableDescriptor(tableName);
            tableDescriptor.addFamily(new HColumnDescriptor("column1"));
            tableDescriptor.addFamily(new HColumnDescriptor("column2"));
            tableDescriptor.addFamily(new HColumnDescriptor("column3"));
            hBaseAdmin.createTable(tableDescriptor);
        } catch (MasterNotRunningException e) {
            e.printStackTrace();
        } catch (ZooKeeperConnectionException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("end create table ......");
    }


}
