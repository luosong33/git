package org.jumao.bi.entites;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class LineCharts implements Serializable {

    private static final long serialVersionUID = -5244773794123014069L;
    private String[]          legendData;
    private List<String>      xAxisData;
    private List<BigDecimal>  seriesData;

    public String[] getLegendData() {
        return legendData;
    }

    public void setLegendData(String[] legendData) {
        this.legendData = legendData;
    }

    public List<String> getxAxisData() {
        return xAxisData;
    }

    public void setxAxisData(List<String> xAxisData) {
        this.xAxisData = xAxisData;
    }

    public List<BigDecimal> getSeriesData() {
        return seriesData;
    }

    public void setSeriesData(List<BigDecimal> seriesData) {
        this.seriesData = seriesData;
    }

}
