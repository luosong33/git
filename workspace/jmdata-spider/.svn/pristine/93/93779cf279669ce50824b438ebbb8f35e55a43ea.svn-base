package org.jumao.jobs.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.jumao.jobs.ISpiderJobs;
import org.jumao.mails.mainmail.MailSenderMain;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
  
@Component("spiderjobs")
public class SpiderJobs implements ISpiderJobs{
	
	//@Scheduled(cron="${Sci99ImpAndExpProcessorJobscron}")
    public void spiderjob1() {
		
        System.out.println("+++++++++++++++++++");
        
		try{
			System.out.println("任务1进行中。。。");
			String aa = "sfdaa";
			aa.charAt(10);
        }catch(Exception e){
        	System.out.println("===========try 测试抛出异常 catch================");
        }
      
        System.out.println("+++++++++++++++++++");
    }
    
   // @Scheduled(cron="0/5 * * * * ? ")   //每5秒执行一次
    public void spiderschTest1() {
        Date date = new Date();
        SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = sim.format(date);
        System.out.println("+++++++++++++++++++");
        System.out.println("这是spiderschTest1定时器1，每五秒执行一次,当前时间：" + dateStr);
		String aa = "sfdaa";
		aa.charAt(10);
        
    }
    
    //@Scheduled(cron="0/5 * * * * ? ")   //每5秒执行一次
    public void spiderthowns() throws Exception{
        Date date = new Date();
        SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = sim.format(date);
        System.out.println("+++++++++++++++++++");
        System.out.println("这是spiderthowns定时器1，每五秒执行一次,当前时间：" + dateStr);

		String aa = "sfdaa";
		aa.charAt(10);
      
        
    }
} 
