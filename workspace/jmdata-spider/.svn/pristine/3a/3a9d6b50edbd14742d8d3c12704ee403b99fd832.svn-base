package org.jumao.jobs.impl;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.jumao.jobs.IOtherSpiderJobs;
import org.jumao.libs.bigdatas.ChangeListInPpi;
import org.jumao.libs.bigdatas.DownloadFromUrlInGBK;
import org.jumao.libs.bigdatas.DownloadFromUrlInUTF8;
import org.jumao.libs.bigdatas.FuturesInZc;
import org.jumao.libs.bigdatas.HomeSpider;
import org.jumao.libs.bigdatas.PriceExponentInZc;
import org.jumao.libs.bigdatas.TradePriceInZc;
import org.jumao.mails.mainmail.MailMessage;
import org.jumao.spider.utils.DateUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("otherspiderjobs")
public class OtherSpiderJobsImpl extends MailMessage implements
		IOtherSpiderJobs {

	@Value("${hadoop_location}")
	private String HADOOP_LOCATION;

	@Value("${trade_price_inzc_hdfs_file}")
	private String TRADE_PRICE_INZC_HDFS_URI;

	@Value("${change_list_inppi_hdfs_file}")
	private String CHANGE_LIST_INPPI_HDFS_URI;

	@Value("${price_exponent_inzc_hdfs_file}")
	private String PRICE_EXPONENT_INZC_HDFS_URI;

	@Value("${futures_inzc_hdfs_file}")
	private String FUTURES_INZC_HDFS_URI;

	@Value("${future_datadaily_insqs_hdfs_file}")
	private String FUTURE_DATADAILY_INSQS_HDFS_URI;

	@Value("${future_datadaily_inzqs_hdfs_file}")
	private String FUTURE_DATADAILY_INZQS_HDFS_URI;

	@Value("${future_datadaily_indqs_hdfs_file}")
	private String FUTURE_DATADAILY_INDQS_HDFS_URI;

	@Value("${home_jm_index_hdfs_file}")
	private String HOME_JM_INDEX_HDFS_URI;

	@Value("${home_gm_index_hdfs_file}")
	private String HOME_GM_INDEX_HDFS_URI;

	@Value("${home_jmi_index_hdfs_file}")
	private String HOME_JMI_INDEX_HDFS_URI;

	@Value("${home_price_index_hdfs_file}")
	private String HOME_PRICE_INDEX_HDFS_URI;

	@Value("${home_price_markprice_index_hdfs_file}")
	private String HOME_PRICE_MARKPRICE_INDEX_HDFS_URI;

	@Value("${home_auth_index_hdfs_file}")
	private String HOME_AUTH_INDEX_HDFS_URI;

	@Value("${home_comp_index_hdfs_file}")
	private String HOME_COMP_INDEX_HDFS_URI;

	private static Logger logger = Logger.getLogger(OtherSpiderJobsImpl.class);

	/*
	 * 上午爬取的数据
	 */
	/* @Scheduled(cron="${Sci99ImpAndExpProcessorJobscron}") */
	// T-1天数据：默认抓取时间：0:30 7:00 9:00
	public void spiderTradePriceJobs() {
		try {
			TradePriceInZc.spiderGetTradePrice(HADOOP_LOCATION
					+ TRADE_PRICE_INZC_HDFS_URI);
		} catch (IOException e) {
			super.sendEmail("spider Trade Price", e);
		}
	}

	// 数据为T-1。
	public void spiderPriceExponentJobs() {
		try {
			PriceExponentInZc.spiderGetPriceExponent(HADOOP_LOCATION
					+ PRICE_EXPONENT_INZC_HDFS_URI);
		} catch (IOException e) {
			super.sendEmail("spider Price Exponent", e);
		}
	}

	// 数据为T-1
	public void spiderFuturesJobs() {
		try {
			FuturesInZc.spiderGetCompareDate(HADOOP_LOCATION
					+ FUTURES_INZC_HDFS_URI);
		} catch (IOException e) {
			super.sendEmail("spider Futures", e);
		}
	}

	// 虽然更新时间初步为：下午4点。6点。
	// 但是数据展示为T-1，所以也在上午更新，随着spiderFuturesJobs一起更新。（如果下午更新了，则一天数据会变化2次，这样让人感觉不好。一起更新比较好。）
	public void spiderFuturesInSHFEJobs() {
		// 延时行情一般延时半小时。如果定时任务在当天执行，则在16点以后执行。如果在凌晨执行，则时间设为T-1;
		String date1 = DateUtils.getFormatDate(DateUtils.DAY_FORMAT_YYYYMMDD);

		String date = DateUtils.getNextDay(date1, -1,
				DateUtils.DAY_FORMAT_YYYYMMDD, DateUtils.DAY_FORMAT_YYYYMMDD);
		try {
			DownloadFromUrlInUTF8.spiderDownLoadFromUrl(
					"http://www.shfe.com.cn/data/dailydata/kx/kx" + date
							+ ".dat", HADOOP_LOCATION
							+ FUTURE_DATADAILY_INSQS_HDFS_URI, date);
		} catch (IOException e) {
			super.sendEmail("spider Futures In SHFE", e);
		}
	}

	// 更新时间初步为：下午4点。6点。 数据展示为T-1
	public void spiderFuturesInCZCEJobs() {
		// 延时行情一般延时半小时。如果定时任务在当天执行，则在16点以后执行。如果在凌晨执行，则时间设为T-1;
		String date1 = DateUtils.getFormatDate(DateUtils.DAY_FORMAT_YYYYMMDD);

		String date = DateUtils.getNextDay(date1, -1,
				DateUtils.DAY_FORMAT_YYYYMMDD, DateUtils.DAY_FORMAT_YYYYMMDD);
		String year = date.substring(0, 4);
		try {
			DownloadFromUrlInGBK.spiderDownLoadFromUrl(
					"http://www.czce.com.cn/portal/DFSStaticFiles/Future/"
							+ year + "/" + date + "/FutureDataDaily.txt",
					HADOOP_LOCATION + FUTURE_DATADAILY_INZQS_HDFS_URI, date);
		} catch (IOException e) {
			super.sendEmail("spider Futures In CZCE", e);
		}
	}

	// 更新时间初步为：下午4点。6点。 数据展示为T-1
	public void spiderFuturesInDCEJobs() {
		// 延时行情一般延时半小时。如果定时任务在当天执行，则在16点以后执行。如果在凌晨执行，则时间设为T-1;
		String date1 = DateUtils.getFormatDate(DateUtils.DAY_FORMAT_YYYYMMDD);

		String date = DateUtils.getNextDay(date1, -1,
				DateUtils.DAY_FORMAT_YYYYMMDD, DateUtils.DAY_FORMAT_YYYYMMDD);
		try {
			DownloadFromUrlInGBK
					.spiderDownLoadFromUrl(
							"http://218.25.154.81"
									+ "/PublicWeb/MainServlet?action=Pu00012_download&Pu00011_Input.trade_date="
									+ date
									+ "&Pu00011_Input.variety=all&Pu00011_Input.trade_type=0",
							HADOOP_LOCATION + FUTURE_DATADAILY_INDQS_HDFS_URI,
							date);
		} catch (IOException e) {
			super.sendEmail("spider Futures In DCE", e);
		}
	}

	/*
	 * 更新时间在下午的
	 */
	// 更新时间在下午 5点多以后。
	public void spiderChangeListJobs() {
		try {
			ChangeListInPpi.spiderGetChangeList(HADOOP_LOCATION
					+ CHANGE_LIST_INPPI_HDFS_URI);
		} catch (IOException e) {
			super.sendEmail("spider ChangeList", e);
		}
	}

	public void spiderHomeSpiderJobs() {
		String[] args = { HADOOP_LOCATION + HOME_JM_INDEX_HDFS_URI,
				HADOOP_LOCATION + HOME_GM_INDEX_HDFS_URI,
				HADOOP_LOCATION + HOME_JMI_INDEX_HDFS_URI,
				HADOOP_LOCATION + HOME_PRICE_INDEX_HDFS_URI,
				HADOOP_LOCATION + HOME_AUTH_INDEX_HDFS_URI };

	
		try {
			HomeSpider.spiderMain(args);
		} catch (InterruptedException e) {
			super.sendEmail("spider Home data", e);
		} catch (IOException e) {
			super.sendEmail("spider Home data", e);
		}
	}

	public String getHADOOP_LOCATION() {
		return HADOOP_LOCATION;
	}

	public void setHADOOP_LOCATION(String hADOOP_LOCATION) {
		HADOOP_LOCATION = hADOOP_LOCATION;
	}

	public String getTRADE_PRICE_INZC_HDFS_URI() {
		return TRADE_PRICE_INZC_HDFS_URI;
	}

	public void setTRADE_PRICE_INZC_HDFS_URI(String tRADE_PRICE_INZC_HDFS_URI) {
		TRADE_PRICE_INZC_HDFS_URI = tRADE_PRICE_INZC_HDFS_URI;
	}

	public String getCHANGE_LIST_INPPI_HDFS_URI() {
		return CHANGE_LIST_INPPI_HDFS_URI;
	}

	public void setCHANGE_LIST_INPPI_HDFS_URI(String cHANGE_LIST_INPPI_HDFS_URI) {
		CHANGE_LIST_INPPI_HDFS_URI = cHANGE_LIST_INPPI_HDFS_URI;
	}

	public String getPRICE_EXPONENT_INZC_HDFS_URI() {
		return PRICE_EXPONENT_INZC_HDFS_URI;
	}

	public void setPRICE_EXPONENT_INZC_HDFS_URI(
			String pRICE_EXPONENT_INZC_HDFS_URI) {
		PRICE_EXPONENT_INZC_HDFS_URI = pRICE_EXPONENT_INZC_HDFS_URI;
	}

	public String getFUTURES_INZC_HDFS_URI() {
		return FUTURES_INZC_HDFS_URI;
	}

	public void setFUTURES_INZC_HDFS_URI(String fUTURES_INZC_HDFS_URI) {
		FUTURES_INZC_HDFS_URI = fUTURES_INZC_HDFS_URI;
	}

	public String getFUTURE_DATADAILY_INSQS_HDFS_URI() {
		return FUTURE_DATADAILY_INSQS_HDFS_URI;
	}

	public void setFUTURE_DATADAILY_INSQS_HDFS_URI(
			String fUTURE_DATADAILY_INSQS_HDFS_URI) {
		FUTURE_DATADAILY_INSQS_HDFS_URI = fUTURE_DATADAILY_INSQS_HDFS_URI;
	}

	public String getFUTURE_DATADAILY_INZQS_HDFS_URI() {
		return FUTURE_DATADAILY_INZQS_HDFS_URI;
	}

	public void setFUTURE_DATADAILY_INZQS_HDFS_URI(
			String fUTURE_DATADAILY_INZQS_HDFS_URI) {
		FUTURE_DATADAILY_INZQS_HDFS_URI = fUTURE_DATADAILY_INZQS_HDFS_URI;
	}

	public String getFUTURE_DATADAILY_INDQS_HDFS_URI() {
		return FUTURE_DATADAILY_INDQS_HDFS_URI;
	}

	public void setFUTURE_DATADAILY_INDQS_HDFS_URI(
			String fUTURE_DATADAILY_INDQS_HDFS_URI) {
		FUTURE_DATADAILY_INDQS_HDFS_URI = fUTURE_DATADAILY_INDQS_HDFS_URI;
	}

	public String getHOME_JM_INDEX_HDFS_URI() {
		return HOME_JM_INDEX_HDFS_URI;
	}

	public void setHOME_JM_INDEX_HDFS_URI(String hOME_JM_INDEX_HDFS_URI) {
		HOME_JM_INDEX_HDFS_URI = hOME_JM_INDEX_HDFS_URI;
	}

	public String getHOME_GM_INDEX_HDFS_URI() {
		return HOME_GM_INDEX_HDFS_URI;
	}

	public void setHOME_GM_INDEX_HDFS_URI(String hOME_GM_INDEX_HDFS_URI) {
		HOME_GM_INDEX_HDFS_URI = hOME_GM_INDEX_HDFS_URI;
	}

	public String getHOME_JMI_INDEX_HDFS_URI() {
		return HOME_JMI_INDEX_HDFS_URI;
	}

	public void setHOME_JMI_INDEX_HDFS_URI(String hOME_JMI_INDEX_HDFS_URI) {
		HOME_JMI_INDEX_HDFS_URI = hOME_JMI_INDEX_HDFS_URI;
	}

	public String getHOME_PRICE_INDEX_HDFS_URI() {
		return HOME_PRICE_INDEX_HDFS_URI;
	}

	public void setHOME_PRICE_INDEX_HDFS_URI(String hOME_PRICE_INDEX_HDFS_URI) {
		HOME_PRICE_INDEX_HDFS_URI = hOME_PRICE_INDEX_HDFS_URI;
	}

	public String getHOME_PRICE_MARKPRICE_INDEX_HDFS_URI() {
		return HOME_PRICE_MARKPRICE_INDEX_HDFS_URI;
	}

	public void setHOME_PRICE_MARKPRICE_INDEX_HDFS_URI(
			String hOME_PRICE_MARKPRICE_INDEX_HDFS_URI) {
		HOME_PRICE_MARKPRICE_INDEX_HDFS_URI = hOME_PRICE_MARKPRICE_INDEX_HDFS_URI;
	}

	public String getHOME_AUTH_INDEX_HDFS_URI() {
		return HOME_AUTH_INDEX_HDFS_URI;
	}

	public void setHOME_AUTH_INDEX_HDFS_URI(String hOME_AUTH_INDEX_HDFS_URI) {
		HOME_AUTH_INDEX_HDFS_URI = hOME_AUTH_INDEX_HDFS_URI;
	}

	public String getHOME_COMP_INDEX_HDFS_URI() {
		return HOME_COMP_INDEX_HDFS_URI;
	}

	public void setHOME_COMP_INDEX_HDFS_URI(String hOME_COMP_INDEX_HDFS_URI) {
		HOME_COMP_INDEX_HDFS_URI = hOME_COMP_INDEX_HDFS_URI;
	}

}