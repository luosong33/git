echo "mapreduce test">>/d1/mapreduce-test.txt
source /etc/profile
echo "mapreduce test 2">>/d1/mapreduce-test.txt


echo 大宗商品价格涨跌榜 数据源：http://top.100ppi.com/zdb/detail-day---1.html 
hadoop jar /d1/dailytask/jmdata-jdata-mrs-other.jar org.jumao.jdata.mrs.other.CommodityPriceListMR

echo 数据源： 行业首页：数据来源：大宗商品涨跌榜http://top.100ppi.com/ 日榜
hadoop jar /d1/dailytask/jmdata-jdata-mrs-other.jar org.jumao.jdata.mrs.other.DailyChangeListMR

echo 数据源： 聚贸大宗商品交易价格:来自卓创大宗商品标杆价格。http://www.sci99.com/。卓创数据为T-1天
hadoop jar /d1/dailytask/jmdata-jdata-mrs-other.jar org.jumao.jdata.mrs.other.DailyTradePriceMR

echo 环球指数 数据源：http://consult.sci99.com/
hadoop jar /d1/dailytask/jmdata-jdata-mrs-other.jar org.jumao.jdata.mrs.other.GlobalIndexMR

echo 行业权威数数据 数据源：http://www.100ppi.com/info/
hadoop jar /d1/dailytask/jmdata-jdata-mrs-other.jar org.jumao.jdata.mrs.other.IndustryAuthorityDataMR

echo 大数聚权威发布大宗市场行情 数据源：http://www.sci99.com/
hadoop jar /d1/dailytask/jmdata-jdata-mrs-other.jar org.jumao.jdata.mrs.other.JuMoreAuthorityQuotationMR

echo 聚贸指数数据源：http://consult.sci99.com/
hadoop jar /d1/dailytask/jmdata-jdata-mrs-other.jar org.jumao.jdata.mrs.other.JuMoreIndexMR

