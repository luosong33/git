echo "mapreduce test">>/d1/mapreduce-test.txt
source /etc/profile
echo "mapreduce test 2">>/d1/mapreduce-test.txt


echo 导入每天宏观指数数据 {存到：hbase："jmdata:generalData"}
hadoop jar /d1/dailytask/jmdata-jdata-mrs-index.jar org.jumao.jdata.mrs.index.GeneralIndexMR

echo 导入每天行业商品指数数据数据 {存到：hbase："jmdata:dailyIndex"}
hadoop jar /d1/dailytask/jmdata-jdata-mrs-index.jar org.jumao.jdata.mrs.index.IndexDataMR

echo 进出口数据::存入jmdata:importAndExport
hadoop jar /d1/dailytask/jmdata-jdata-mrs-ie.jar org.jumao.jdata.mrs.ie.ImportAndExportMR

