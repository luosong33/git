package org.jumao.jobs.impl;

import org.jmdata.operatives.bzs.dataApi.BaiDuMain;
import org.jumao.jobs.IExportBaiduDataJobs;
import org.jumao.mails.mainmail.MailMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("baidudataexportjobs")
public class ExportBaiduDataJobsImpl extends MailMessage implements IExportBaiduDataJobs {

    @Value("${cdh_hdfs_location}")
    private String HADOOP_LOCATION;
    
	public void exportBaiduData()  {
		String[] args = {HADOOP_LOCATION + "/jmhdfs/data/data_baidu/baiduData.dat"};
		try {
			BaiDuMain.main(args);
		} catch (Exception e) {
			super.sendEmail("export baidu statistics data", e);
		}
		
	}

	public String getHADOOP_LOCATION() {
		return HADOOP_LOCATION;
	}

	public void setHADOOP_LOCATION(String hADOOP_LOCATION) {
		HADOOP_LOCATION = hADOOP_LOCATION;
	}

}
