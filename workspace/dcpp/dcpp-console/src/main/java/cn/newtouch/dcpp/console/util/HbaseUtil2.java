package cn.newtouch.dcpp.console.util;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.filter.PrefixFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by Administrator on 2017/3/21.
 */
public class HbaseUtil2 {


    static Configuration conf =null;
    Connection hTablePool = null;

   
    public static void main(String[] args){
        System.setProperty("HADOOP_USER_NAME", "root");
        HbaseUtil2 hu = new HbaseUtil2();
        TableName tn = TableName.valueOf("student");
        System.out.println("shanchuqian##############################");
        hu.deleteCell(tn,"1001","info","name");
//        hu.createTable("student3", "info,colume".split(","));
        System.out.println("shanchuhou ##############################");
    }

    public HbaseUtil2() {
        conf = new Configuration();
        String zk_list = "node1,node2,node3";
        conf.set("hbase.zookeeper.quorum", zk_list);
        conf.set("fs.hdfs.impl", org.apache.hadoop.hdfs.DistributedFileSystem.class.getName());

        try {
            hTablePool = HConnectionManager.createConnection(conf) ;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void insert(TableName tableName, String rowKey, String family,
                       String quailifer, String value) {
        Table table = null;
        try {
            table = hTablePool.getTable(tableName) ;
            Put put = new Put(rowKey.getBytes());
            put.add(family.getBytes(), quailifer.getBytes(), value.getBytes()) ;
            table.put(put);
        } catch (Exception e) {
            e.printStackTrace();
        }finally
        {
            try {
                table.close() ;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public void insert(TableName tableName,String rowKey,String family,String quailifer[],String value[])
    {
        Table table = null;
        try {
            table = hTablePool.getTable(tableName) ;
            Put put = new Put(rowKey.getBytes());
            // �������
            for (int i = 0; i < quailifer.length; i++) {
                String col = quailifer[i];
                String val = value[i];
                put.add(family.getBytes(), col.getBytes(), val.getBytes());
            }
            table.put(put);
        } catch (Exception e) {
            e.printStackTrace();
        }finally
        {
            try {
                table.close() ;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void save(List<Put> Put, TableName tableName) {
        Table table = null;
        try {
            table = hTablePool.getTable(tableName);
            table.put(Put) ;
        }
        catch (Exception e) {
        }finally
        {
            try {
                table.close() ;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


    public void deleteRecords(TableName tableName, String rowKeyLike){
        Table table = null;
        try {
            table = hTablePool.getTable(tableName) ;
            PrefixFilter filter = new PrefixFilter(rowKeyLike.getBytes());
            Scan scan = new Scan();
            scan.setFilter(filter);
            ResultScanner scanner = table.getScanner(scan) ;
            List<Delete> list = new ArrayList<Delete>() ;
            for (Result rs : scanner) {
                Delete del = new Delete(rs.getRow());
                list.add(del) ;
            }
            table.delete(list);
        }
        catch (Exception e) {
            e.printStackTrace() ;
        }
        finally
        {
            try {
                table.close() ;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


  
    public void deleteCell(TableName tableName, String rowkey,String cf,String column){
        Table table = null;
        try {
            System.out.println("ִ��ɾ�� #########################");
            table = hTablePool.getTable(tableName);
            System.out.println("�õ�table #########################");
            Delete del = new Delete(rowkey.getBytes());
            System.out.println("�õ�del���� #########################");
//            del.deleteColumn(cf.getBytes(), column.getBytes());
            del.addColumn(cf.getBytes(), column.getBytes());
            System.out.println("����del   #########################");
//            table.delete(del);
            table.delete(del);
            System.out.println("ɾ���ɹ� #########################");
        }
        catch (Exception e) {
            e.printStackTrace() ;
        }
        finally
        {
            try {
                table.close() ;
                System.out.println("hbase���ӹر� #########################");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public   void createTable(String tableName, String[] columnFamilys){
        try {
            HBaseAdmin admin = new HBaseAdmin(conf);
            if (admin.tableExists(tableName)) {
                System.err.println("�˱��Ѵ��ڣ�");
            } else {
                HTableDescriptor tableDesc = new HTableDescriptor(
                        TableName.valueOf(tableName));

                for (String columnFamily : columnFamilys) {
                    tableDesc.addFamily(new HColumnDescriptor(columnFamily));
                }

                admin.createTable(tableDesc);
                System.err.println("����ɹ�!");

            }
            admin.close();
        } catch (MasterNotRunningException e) {
            e.printStackTrace();
        } catch (ZooKeeperConnectionException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}