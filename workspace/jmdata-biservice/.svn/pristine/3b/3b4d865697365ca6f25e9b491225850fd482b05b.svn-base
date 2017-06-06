package org.jumao.bi.component;

import java.util.List;

import org.jumao.bi.entites.charts.NewFunnelChart;
import org.jumao.bi.utis.SpringContextUtil;
import org.springframework.stereotype.Component;
@Component
public class FunnelChartBuilder extends BaseChartBuilder{
	
	@Override
	public void buildData(List<BaseInfo> baseInfos, BaseResponse response,
			ComponentContext context) throws Exception {
		// TODO Auto-generated method stub
		NewFunnelChart funnelChart = SpringContextUtil.getBean(NewFunnelChart.class);
		funnelChart.setSeriesData(getSeriesData(baseInfos,context.getReturnType()));
		funnelChart.setLegendData(getLegendData(baseInfos));
        response.setFunnelChart(funnelChart);
	}
	
}
