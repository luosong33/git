echo "mapreduce test">>/d1/mapreduce-test.txt
source /etc/profile
echo "mapreduce test 2">>/d1/mapreduce-test.txt


echo 导入每天指数涨跌排行数据 {存到：hbase："jmdata:topIndex"}
hadoop jar /d1/dailytask/jmdata-jdata-mrs-index.jar org.jumao.jdata.mrs.index.TopIndexMR


echo 数据源： 聚贸发布-主要商品指数MR。来自卓创价格指数:http://index.sci99.com/     
hadoop jar /d1/dailytask/jmdata-jdata-mrs-other.jar org.jumao.jdata.mrs.other.DailyPriceExponentMR



