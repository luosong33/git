package org.jumao.jobs.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.jumao.jdata.spider.index.GeneralIndex;
import org.jumao.jdata.spider.index.IndustryIndex;
import org.jumao.jdata.spider.index.TopIndex;
import org.jumao.jobs.IIndexSpiderJobs;
import org.jumao.mails.mainmail.MailMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("indexspiderjobs")
public class IndexSpiderJobsImpl extends MailMessage implements IIndexSpiderJobs {

	@Value("${hadoop_location}")
	private String HADOOP_LOCATION;
	
	@Value("${general_index_hdfs_file}")
	private String GENERAL_HDFS_URI;

	@Value("${top_index_hdfs_file}")
	private String TOP_HDFS_URI;

	@Value("${farm_index_hdfs_file}")
	private String FARM_URI;

	@Value("${steel_index_hdfs_file}")
	private String STEEL_URI;

	@Value("${metals_index_hdfs_file}")
	private String METALS_URI;

	@Value("${chemical_index_hdfs_file}")
	private String CHEMICAL_URI;

	@Value("${energy_index_hdfs_file}")
	private String ENERGY_URI;

	public void spiderGeneralIndex() {
		try {
			GeneralIndex.spiderlAndWriteData(HADOOP_LOCATION + GENERAL_HDFS_URI);
		} catch (IOException ex) {
			super.sendEmail("spider General Index", ex);
		} catch (InterruptedException e) {
			super.sendEmail("spider General Index", e);
		}
	}

	public void spiderIndustryIndex()  {
		Map<String, String> hdfsMap = new HashMap<String, String>();
		hdfsMap.put("能源", HADOOP_LOCATION + ENERGY_URI);
		hdfsMap.put("化工", HADOOP_LOCATION + CHEMICAL_URI);
		hdfsMap.put("有色", HADOOP_LOCATION + METALS_URI);
		hdfsMap.put("钢铁", HADOOP_LOCATION + STEEL_URI);
		hdfsMap.put("农产品", HADOOP_LOCATION + FARM_URI);
		try {
			IndustryIndex.spiderLatestSciIndex(hdfsMap);
		} catch (IOException e) {
			super.sendEmail("spider Industry Index", e);
		} catch (InterruptedException e) {			
			super.sendEmail("spider Industry Index", e);
		}

	}

	public void spiderTopIndex()  {
		try {
			TopIndex.spiderlAndWriteData(HADOOP_LOCATION +TOP_HDFS_URI);
		} catch (IOException e) {
			super.sendEmail("spider top Index", e);
		} catch (InterruptedException e) {
			super.sendEmail("spider top Index", e);
		}
	}

	public String getGENERAL_HDFS_URI() {
		return GENERAL_HDFS_URI;
	}

	public void setGENERAL_HDFS_URI(String gENERAL_HDFS_URI) {
		GENERAL_HDFS_URI = gENERAL_HDFS_URI;
	}

	public String getTOP_HDFS_URI() {
		return TOP_HDFS_URI;
	}

	public void setTOP_HDFS_URI(String tOP_HDFS_URI) {
		TOP_HDFS_URI = tOP_HDFS_URI;
	}

	public String getFARM_URI() {
		return FARM_URI;
	}

	public void setFARM_URI(String fARM_URI) {
		FARM_URI = fARM_URI;
	}

	public String getSTEEL_URI() {
		return STEEL_URI;
	}

	public void setSTEEL_URI(String sTEEL_URI) {
		STEEL_URI = sTEEL_URI;
	}

	public String getMETALS_URI() {
		return METALS_URI;
	}

	public void setMETALS_URI(String mETALS_URI) {
		METALS_URI = mETALS_URI;
	}

	public String getCHEMICAL_URI() {
		return CHEMICAL_URI;
	}

	public void setCHEMICAL_URI(String cHEMICAL_URI) {
		CHEMICAL_URI = cHEMICAL_URI;
	}

	public String getENERGY_URI() {
		return ENERGY_URI;
	}

	public void setENERGY_URI(String eNERGY_URI) {
		ENERGY_URI = eNERGY_URI;
	}

	public String getHADOOP_LOCATION() {
		return HADOOP_LOCATION;
	}

	public void setHADOOP_LOCATION(String hADOOP_LOCATION) {
		HADOOP_LOCATION = hADOOP_LOCATION;
	}

}
