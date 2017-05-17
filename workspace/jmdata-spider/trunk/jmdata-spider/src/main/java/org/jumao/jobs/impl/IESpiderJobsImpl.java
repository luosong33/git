package org.jumao.jobs.impl;

import java.io.IOException;

import org.jumao.jdata.spider.ie.Sci99ImpAndExpProcessor;
import org.jumao.jobs.IIESpiderJobs;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("iespiderjobs")
public class IESpiderJobsImpl implements IIESpiderJobs{

	@Value("${hadoop_location}")
	private String HADOOP_LOCATION;
	
	@Value("${impandexp_index_hdfs_file}")
	private String IMPANDEXP_HDFS_URI;
	
    public void spiderSci99ImpAndExpProcessorJobs() {
		//String []args = null;
		Sci99ImpAndExpProcessor.spiderMain(HADOOP_LOCATION + IMPANDEXP_HDFS_URI);
    }
	public String getHADOOP_LOCATION() {
		return HADOOP_LOCATION;
	}
	public void setHADOOP_LOCATION(String hADOOP_LOCATION) {
		HADOOP_LOCATION = hADOOP_LOCATION;
	}
	public String getIMPANDEXP_HDFS_URI() {
		return IMPANDEXP_HDFS_URI;
	}
	public void setIMPANDEXP_HDFS_URI(String iMPANDEXP_HDFS_URI) {
		IMPANDEXP_HDFS_URI = iMPANDEXP_HDFS_URI;
	}
	
    
}
