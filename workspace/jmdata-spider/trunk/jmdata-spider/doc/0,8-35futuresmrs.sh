echo "mapreduce test">>/d1/mapreduce-test.txt
source /etc/profile
echo "mapreduce test 2">>/d1/mapreduce-test.txt



echo 数据源：郑州商品交易所 CZCE    ZhengzhouCommodity Exchange
hadoop jar /d1/dailytask/jmdata-jdata-mrs-other.jar org.jumao.jdata.mrs.other.DailyCZCEMR

echo 数据源：大连商品交易所 DCE     DalianCommodity Exchange
hadoop jar /d1/dailytask/jmdata-jdata-mrs-other.jar org.jumao.jdata.mrs.other.DailyDCEMR

echo 数据源：上海期货交易所 SHFE   ShanghaiFuturesExchange   
hadoop jar /d1/dailytask/jmdata-jdata-mrs-other.jar org.jumao.jdata.mrs.other.DailySHFEMR


echo 数据源：卓创现货与期货数据对比：http://www.sci99.com/price-comparison.html
hadoop jar /d1/dailytask/jmdata-jdata-mrs-other.jar org.jumao.jdata.mrs.other.DailySpotMR


