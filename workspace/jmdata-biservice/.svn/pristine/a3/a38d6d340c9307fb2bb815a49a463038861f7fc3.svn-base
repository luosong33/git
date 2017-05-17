package org.jumao.bi.utis;

import java.util.HashMap;
import java.util.Map;

public class PlatFormUtil {
	
	private static final Map<String, String> platformMap = new HashMap<String, String>();
    
    static {
    	PlatFormUtil.platformMap.put("100700", "1");//煤炭
    	PlatFormUtil.platformMap.put("100300", "2");//有色
    	PlatFormUtil.platformMap.put("100900", "3");//矿产
    	PlatFormUtil.platformMap.put("101000", "4");//农产品
    	PlatFormUtil.platformMap.put("100800", "5");//钢铁
    	PlatFormUtil.platformMap.put("101200", "6");//消费品
    	PlatFormUtil.platformMap.put("101400", "7");//食品
    	PlatFormUtil.platformMap.put("101100", "8");//工业品
    	PlatFormUtil.platformMap.put("101300", "9");//机械
    	PlatFormUtil.platformMap.put("100200", "10");//化工
    	PlatFormUtil.platformMap.put("102200", "11");//石油
    }
    
    public static String getPlatformV(String key){
    	return platformMap.get(key);
    }
}
