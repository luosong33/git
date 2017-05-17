package org.jumao.jobs.impl;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.jumao.jdata.spider.price.list.SelectPriceIndustry;
import org.jumao.jdata.spider.price.rank.SelectPriceRank;
import org.jumao.jobs.IPriceSpiderJobs;
import org.jumao.mails.mainmail.MailMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("pricespiderjobs")
public class PriceSpiderJobsImpl extends MailMessage implements IPriceSpiderJobs {

	@Value("${hadoop_location}")
	private String HADOOP_LOCATION;

	@Value("${price_rank_hdfs_file}")
	private String PRICE_RANK_HDFS_URI;

	@Value("${price_list_hdfs_file}")
	private String PRICE_List_HDFS_URI;

	private static Logger logger = Logger.getLogger(PriceSpiderJobsImpl.class);

	/* @Scheduled(cron="${SelectPriceIndustryJobscron}") */
	public void spiderSelectPriceIndustryJobs() {
		// String []args = null;
		try {
			SelectPriceIndustry
					.spideMain(HADOOP_LOCATION + PRICE_List_HDFS_URI);
		} catch (IOException e) {
			super.sendEmail("spider Select Price Industry", e);
		}
	}

	/* @Scheduled(cron="${SelectPriceRankJobscron}") */
	public void spiderSelectPriceRankJobs() {

		try {
			SelectPriceRank.spideMain(HADOOP_LOCATION + PRICE_RANK_HDFS_URI);
		} catch (IOException e) {
			super.sendEmail("spider Select Price Rank", e);
		}

	}

	public String getHADOOP_LOCATION() {
		return HADOOP_LOCATION;
	}

	public void setHADOOP_LOCATION(String hADOOP_LOCATION) {
		HADOOP_LOCATION = hADOOP_LOCATION;
	}

	public String getPRICE_RANK_HDFS_URI() {
		return PRICE_RANK_HDFS_URI;
	}

	public void setPRICE_RANK_HDFS_URI(String pRICE_RANK_HDFS_URI) {
		PRICE_RANK_HDFS_URI = pRICE_RANK_HDFS_URI;
	}

	public String getPRICE_List_HDFS_URI() {
		return PRICE_List_HDFS_URI;
	}

	public void setPRICE_List_HDFS_URI(String pRICE_List_HDFS_URI) {
		PRICE_List_HDFS_URI = pRICE_List_HDFS_URI;
	}

}
