package org.jumao.spider.utils;

import java.io.IOException;

import org.jumao.spider.beans.AgentBean;

import cz.mallat.uasparser.OnlineUpdater;
import cz.mallat.uasparser.UASparser;
import cz.mallat.uasparser.UserAgentInfo;

/**
 * Created by Edward on 2016/7/1.
 */
public class UserAgentUtil {

    public static UASparser uasParser = null;

    // 初始化uasParser对象
    static {
        try {
            uasParser = new UASparser(OnlineUpdater.getVendoredInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static AgentBean getUserAgentInfo(String agentStr) throws IOException {
        AgentBean agentBean = new AgentBean();
        UserAgentInfo userAgentInfo = UserAgentUtil.uasParser.parse(agentStr);
        if (null != userAgentInfo) {           
            agentBean.setDeviceType(userAgentInfo.getType());
            agentBean.setBrowserType(userAgentInfo.getUaFamily());      
        }
        
        return agentBean;
    }
    
    public static void main(String[] args)
    {
        String str = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.130 Safari/537.36";
        System.out.println(str);
        try {
            UserAgentInfo userAgentInfo = UserAgentUtil.uasParser.parse(str);
            System.out.println("操作系统名称："+userAgentInfo.getOsFamily());//
            System.out.println("操作系统："+userAgentInfo.getOsName());//
            System.out.println("浏览器名称："+userAgentInfo.getUaFamily());//
            System.out.println("浏览器版本："+userAgentInfo.getBrowserVersionInfo());//
            System.out.println("设备类型："+userAgentInfo.getDeviceType());
            System.out.println("浏览器:"+userAgentInfo.getUaName());
            System.out.println("类型："+userAgentInfo.getType());
            userAgentInfo.getUaFamily();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}