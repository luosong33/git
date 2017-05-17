package org.jumao.bi.entites.trade.register;


import java.util.ArrayList;
import java.util.List;

/**
 * 可表示折线图、柱状图、条形图
 */
public class RegLineChart {

    private String legendData = new String();
    private String[] xAxisData = new String[0];
    private String[] yAxisData = new String[0];
    private long[] seriesData = new long[0];

    public String getLegendData() {
        return legendData;
    }

    public void setLegendData(String legendData) {
        this.legendData = legendData;
    }

    public String[] getxAxisData() {
        return xAxisData;
    }

    public void setxAxisData(String[] xAxisData) {
        this.xAxisData = xAxisData;
    }

    public String[] getyAxisData() {
        return yAxisData;
    }

    public void setyAxisData(String[] yAxisData) {
        this.yAxisData = yAxisData;
    }

    public long[] getSeriesData() {
        return seriesData;
    }

    public void setSeriesData(long[] seriesData) {
        this.seriesData = seriesData;
    }
}