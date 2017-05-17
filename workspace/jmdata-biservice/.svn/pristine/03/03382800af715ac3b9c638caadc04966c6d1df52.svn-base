package org.jumao.bi.service.impl.trade.operate;

import java.util.ArrayList;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jumao.bi.entites.charts.CommonBean;
import org.jumao.bi.entites.charts.DoubleYAxisLineChart;
import org.jumao.bi.entites.charts.PieChart;
import org.jumao.bi.entites.operate.OrderInfo;
import org.jumao.bi.entites.operate.OrderPayResponse;
import org.jumao.bi.entites.operate.OrderTypeResponse;

public class OperationOrderSvcHelper {

    public static void buildOrderLineChart(List<OrderInfo> orderInfos, OrderPayResponse response) {

    	int size = orderInfos.size();
    	DoubleYAxisLineChart lineChart = new DoubleYAxisLineChart();
        String[] xAxisData = new String[size];
        List<Integer> seriesQuntityData = new ArrayList<Integer>(Collections.nCopies(size, 0));
        List<Double> seriesPayData = new ArrayList<Double>(Collections.nCopies(size, 0.0));
        for (int i = 0; i < orderInfos.size(); i++) {
        	xAxisData[i] = orderInfos.get(i).getGoodsCateName();
    		seriesQuntityData.set(i, orderInfos.get(i).getGoodsCateCnt());
    		seriesPayData.set(i, orderInfos.get(i).getGoodsCatePay());
//    		seriesPayData.add(Double.parseDouble("dfdf"));
        }
        Map<String,List> data = new HashMap<String,List>();
        data.put("left", seriesQuntityData);
        data.put("right", seriesPayData);
        lineChart.setLegendData(new String[]{"数量","金额"});
        lineChart.setxAxisData(xAxisData);
        lineChart.setSeriesData(data);
        
        response.setLineChart(lineChart);
    }

    public static void buildOrderPieChart(List<OrderInfo> orderInfos, OrderTypeResponse response) {
        PieChart pieChart = new PieChart();
        List<CommonBean> seriesData = new ArrayList<CommonBean>();
        String[] legendData = new String[orderInfos.size()];
        int i = 0;
        for (OrderInfo orderinfo : orderInfos) {

            String CateName = orderinfo.getGoodsCateName();
            Integer cateCnt = orderinfo.getGoodsCateCnt();
            CommonBean commonBean = new CommonBean(CateName, cateCnt.toString());
            seriesData.add(commonBean);
            legendData[i] = CateName;
            i++;
        }

        pieChart.setSeriesData(seriesData);
        pieChart.setLegendData(legendData);
        response.setPieChart(pieChart);
    }

}
