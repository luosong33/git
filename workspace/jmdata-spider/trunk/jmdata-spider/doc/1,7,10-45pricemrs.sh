echo "mapreduce test">>/d1/mapreduce-test.txt
source /etc/profile
echo "mapreduce test 2">>/d1/mapreduce-test.txt


echo 按照大行业分类的：商品价格::存入jmdata:dailyPriceList
hadoop jar /d1/dailytask/jmdata-jdata-mrs-price.jar org.jumao.jdata.mrs.price.DailyPriceIndustry

echo 按照大行业分类的：商品价格涨跌排行表::存入jmdata:dailyPriceRank
hadoop jar /d1/dailytask/jmdata-jdata-mrs-price.jar org.jumao.jdata.mrs.price.DailyPriceRank

