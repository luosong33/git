package org.jumao.bi.component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.jumao.bi.entites.charts.CommonTBean;

public abstract class BaseChartBuilder {
	
	public abstract void buildData(List<BaseInfo> baseInfos,BaseResponse response,ComponentContext context) throws Exception;

	/**
	 * 获取seriesData数据
	 * @param baseInfos
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	public  List<CommonTBean<?>> getSeriesData(List<BaseInfo> baseInfos,Class<?> clazz) throws Exception{
		List<CommonTBean<?>> seriesData = new ArrayList<CommonTBean<?>>();
		for (BaseInfo baseInfo : baseInfos) {
            String itemName = baseInfo.getItemName();
            CommonTBean<?> commonTBean;
            if (clazz==BigDecimal.class){
            	BigDecimal itemValue =  new BigDecimal(baseInfo.getItemValue().toString());
                commonTBean = new CommonTBean<BigDecimal>(itemName, itemValue);
            }else if (clazz==Long.class){
            	Integer itemValue =  Integer.valueOf(baseInfo.getItemValue().toString());
                commonTBean = new CommonTBean<Integer>(itemName, itemValue);
            }else if (clazz==Double.class){
            	Double itemValue =  Double.valueOf(baseInfo.getItemValue().toString());
                commonTBean = new CommonTBean<Double>(itemName, itemValue);
            }else{
            	String itemValue =  baseInfo.getItemValue().toString();
                commonTBean = new CommonTBean<String>(itemName, itemValue);
            }
            
            seriesData.add(commonTBean);
        }
		return seriesData;
	}
	/**
	 * 获取图例legendData数据
	 * @param baseInfos
	 * @return
	 * @throws Exception
	 */
	public  String[] getLegendData(List<BaseInfo> baseInfos) throws Exception{
		String[] legendData = new String[baseInfos.size()];
		int i = 0;
        for (BaseInfo baseInfo : baseInfos) {
            String itemName = baseInfo.getItemName();
            legendData[i] = itemName;
            i++;
        }
		return legendData;
	}
}
