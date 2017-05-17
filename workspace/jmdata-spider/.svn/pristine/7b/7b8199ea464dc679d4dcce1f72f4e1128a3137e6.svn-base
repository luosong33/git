package org.jumao.jobs.impl;

import java.io.IOException;

import org.jumao.jdata.spider.en.CategoryPriceSpider;
import org.jumao.jdata.spider.en.FuturesPriceSpider;
import org.jumao.jdata.spider.en.HomeSpider4English;
import org.jumao.jdata.spider.en.SpotPriceSpider;
import org.jumao.jobs.IEnglishSpiderJobs;
import org.jumao.mails.mainmail.MailMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("englishspiderjobs")
public class EnglishSpriderJobsImpl extends MailMessage implements IEnglishSpiderJobs {
    @Value("${hadoop_location}")
    private String HADOOP_LOCATION;
    //private static Logger logger = Logger.getLogger(EnglishSpriderJobsImpl.class);

    public void spiderCategoryPrice() {
        String[] args = {HADOOP_LOCATION + "/jmhdfs/jss/english/price/price.txt"};
        try {
            CategoryPriceSpider.spiderMain(args);
        } catch (IOException e) {
        	super.sendEmail("spider Category Price for ju-data", e);
        } catch (InterruptedException e) {
        	super.sendEmail("spider Category Price for ju-data", e);
        }
    }

    public void spiderFuturesPrice() {
        //期货每天，每周，每月，频率可能不一样，有可能继续拆分
        String[] args = {HADOOP_LOCATION + "/jmhdfs/jss/english/price/futuresPrice.txt"};
        try {
            FuturesPriceSpider.spiderMain(args);
        } catch (IOException e) {
        	super.sendEmail("spider Futures Price for ju-data", e);
        }
    }

    public void spiderSpotPrice() {
        //现货每天，每周，每月，频率可能不一样，有可能继续拆分
        String[] args = {HADOOP_LOCATION + "/jmhdfs/jss/english/price/spotPrice.txt"};
        try {
            SpotPriceSpider.spiderMain(args);
        } catch (IOException e) {
        	super.sendEmail("spider Spot Price for ju-data", e);
        }
    }

    public void spiderMTIBulkIndex() {
//		String[] args = {HADOOP_LOCATION + "/jmhdfs/jss/english/home/MTIBulkIndex.txt",
//				HADOOP_LOCATION + "/jmhdfs/jss/english/home/ccpi.txt",
//				HADOOP_LOCATION + "/jmhdfs/jss/english/home/commodityPriceIndex.txt",
//				HADOOP_LOCATION + "/jmhdfs/jss/english/home/tocomSpotPrice.txt"};
        String hdfsPath = HADOOP_LOCATION + "/jmhdfs/jss/english/home/MTIBulkIndex.txt";
        try {
            HomeSpider4English.spiderMTIBulkIndex(hdfsPath);
        } catch (IOException e) {
            e.printStackTrace();
            super.sendEmail("spider MTI Bulk Index for ju-data", e);
        }
    }

    /**
     * 目前只有此数据是实时数据。
     * 其他数据至少都是T-1天数据。
     *
     * @throws Exception
     */
    public void spiderTocomSpotPrice() {
        String hdfsPath = HADOOP_LOCATION + "/jmhdfs/jss/english/home/tocomSpotPrice.txt";
        try {
            HomeSpider4English.spiderTocomSpotPrice(hdfsPath);
        } catch (IOException e) {
        	 super.sendEmail("spider Tocom Spot Price for ju-data", e);
        }
    }

    public void spiderCCPI() {
        String hdfsPath = HADOOP_LOCATION + "/jmhdfs/jss/english/home/ccpi.txt";
        try {
            HomeSpider4English.spiderCCPI(hdfsPath);
        } catch (IOException e) {
        	super.sendEmail("spider CCPI for ju-data", e);
        }
    }

    public void spiderCommodityPriceIndex() {
        String hdfsPath = HADOOP_LOCATION + "/jmhdfs/jss/english/home/commodityPriceIndex.txt";
        try {
            HomeSpider4English.spiderCommodityPriceIndex(hdfsPath);
        } catch (IOException e) {
        	super.sendEmail("spider Commodity Price Index for ju-data", e);
        }
    }

    public String getHADOOP_LOCATION() {
        return HADOOP_LOCATION;
    }

    public void setHADOOP_LOCATION(String hADOOP_LOCATION) {
        HADOOP_LOCATION = hADOOP_LOCATION;
    }


}
