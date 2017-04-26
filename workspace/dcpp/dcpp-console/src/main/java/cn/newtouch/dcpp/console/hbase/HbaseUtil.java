package cn.newtouch.dcpp.console.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.PrefixFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by Administrator on 2017/3/21.
 */
public class HbaseUtil {

    static Configuration conf =null;
    HConnection hTablePool = null;

    public HbaseUtil() {
        conf = new Configuration();
        String zk_list = "node1,node2,node3";
        conf.set("hbase.zookeeper.quorum", zk_list);
        try {
            hTablePool = HConnectionManager.createConnection(conf) ;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void insert(String tableName, String rowKey, String family,
                       String quailifer, String value) {
        HTableInterface table = null;
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

    public void insert(String tableName,String rowKey,String family,String quailifer[],String value[])
    {
        HTableInterface table = null;
        try {
            table = hTablePool.getTable(tableName) ;
            Put put = new Put(rowKey.getBytes());
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
    public void save(List<Put> Put, String tableName) {
        HTableInterface table = null;
        try {
            table = hTablePool.getTable(tableName) ;
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


    public void deleteRecords(String tableName, String rowKeyLike){
        HTableInterface table = null;
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

    public void deleteCell(String tableName, String rowkey,String cf,String column){
        HTableInterface table = null;
        try {
            table = hTablePool.getTable(tableName) ;
            Delete del = new Delete(rowkey.getBytes());
            del.deleteColumn(cf.getBytes(), column.getBytes());
            table.delete(del);
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

    public   void createTable(String tableName, String[] columnFamilys){
        try {
            HBaseAdmin admin = new HBaseAdmin(conf);
            if (admin.tableExists(tableName)) {
                System.err.println("此表，已存在！");
            } else {
                HTableDescriptor tableDesc = new HTableDescriptor(
                        TableName.valueOf(tableName));

                for (String columnFamily : columnFamilys) {
                    tableDesc.addFamily(new HColumnDescriptor(columnFamily));
                }

                admin.createTable(tableDesc);
                System.err.println("建表成功!");

            }
            admin.close();// 关闭释放资源
        } catch (MasterNotRunningException e) {
            e.printStackTrace();
        } catch (ZooKeeperConnectionException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
