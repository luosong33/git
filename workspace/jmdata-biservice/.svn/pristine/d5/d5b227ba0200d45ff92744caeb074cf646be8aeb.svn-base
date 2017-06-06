package org.jumao.bi.component;

import java.util.List;

import org.jumao.bi.entites.charts.NewPieChart;
import org.jumao.bi.utis.SpringContextUtil;
import org.springframework.stereotype.Component;

@Component
public class PieChartBuilder extends BaseChartBuilder {

	@Override
	public  void buildData(List<BaseInfo> baseInfos,BaseResponse response,ComponentContext context) throws Exception {
		// TODO Auto-generated method stub
		NewPieChart pieChart = SpringContextUtil.getBean(NewPieChart.class);
		pieChart.setSeriesData(getSeriesData(baseInfos,context.getReturnType()));
        pieChart.setLegendData(getLegendData(baseInfos));
        response.setPieChart(pieChart);
	}

}
