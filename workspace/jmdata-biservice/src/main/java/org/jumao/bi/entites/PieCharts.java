package org.jumao.bi.entites;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.jumao.bi.constant.MapConstants;
import org.jumao.bi.utis.PlatFormUtil;

public class PieCharts implements Serializable {

    private static final long serialVersionUID = -7275311248341173164L;
    private List<String>      legendData;
    private String            seriesName;
    private List<SeriesData>  seriesData;
    private String            title;

    public List<String> getLegendData() {
        return legendData;
    }

    public void setLegendData(List<String> legendData) {
        this.legendData = legendData;
    }

    public List<SeriesData> getSeriesData() {
        return seriesData;
    }

    public void setSeriesData(List<SeriesData> seriesData) {
        this.seriesData = seriesData;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSeriesName() {
        return seriesName;
    }

    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName;
    }

    /**
     * setPieCharts.
     * 
     * @author Administrator
     * @date 2017年6月12日 下午1:24:19
     * @param name
     * @param code
     * @param sortField
     * @param data
     * @param title
     * @param seriesName
     * @param total
     * @return
     */
    public static Map<String, Object> setPieCharts(String name, String code, String sortField, List<Map<String, Object>> data,
            String title, String seriesName, BigDecimal total) {
        List<SeriesData> seriesData = new ArrayList<SeriesData>();
        List<String> legendData = new ArrayList<String>();
        SeriesData sData = null;
        // 整理数据
        String keyPayToal = "paytotal";
        BigDecimal value = BigDecimal.ZERO;
        String nameStr = null;
        for (Map<String, Object> map : data) {
            sData = new SeriesData();
            if (map.get(keyPayToal) != null && "paytotal".equalsIgnoreCase(sortField)) {// 金额
                value = NumberUtils.createBigDecimal(map.get(keyPayToal).toString()).divide(BigDecimal.valueOf(10000), 2,
                        BigDecimal.ROUND_HALF_EVEN);
            } else {
                value = NumberUtils.createBigDecimal(map.get("ordernum").toString());
            }

            nameStr = "industry_id".equalsIgnoreCase(code) ? PlatFormUtil.getPlatformV(map.get(code).toString()) : map.get(name).toString();
            sData.setName(nameStr);
            sData.setCode(map.get(code).toString());
            sData.setValue(value);
            legendData.add(nameStr);
            seriesData.add(sData);
            if (total != null) {
                total = total.add(value);
            }
        }
        PieCharts pieCharts = new PieCharts();
        pieCharts.setTitle(title);
        pieCharts.setSeriesName(seriesName);
        pieCharts.setLegendData(legendData);
        pieCharts.setSeriesData(seriesData);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("total", total);
        map.put("pieCharts", pieCharts);
        return map;
    }

    /**
     * setPieCharts
     * 
     * @author Administrator
     * @date 2017年6月12日 下午1:28:43
     * @param data
     * @param key
     * @param title
     * @param seriesName
     * @return
     */
    public static PieCharts setPieCharts(List<Map<String, Object>> data, String key, String title, String seriesName) {
        List<SeriesData> seriesData = new ArrayList<SeriesData>();
        List<String> legendData = new ArrayList<String>();
        SeriesData sData = null;
        String name = null;
        String code = null;
        for (Map<String, Object> map : data) {
            code = map.get(key).toString();
            name = "industry_id".equalsIgnoreCase(key) ? PlatFormUtil.getPlatformV(code) : MapConstants.DEAL_MAP.get(key + "_"
                    + String.valueOf(map.get(key)));
            if (StringUtils.isBlank(name)) {
                continue;
            }
            sData = new SeriesData();
            sData.setName(name);
            sData.setValue(NumberUtils.createBigDecimal(map.get("count").toString()));
            sData.setCode(code);
            seriesData.add(sData);
            legendData.add(name);
        }

        PieCharts pieCharts = new PieCharts();
        pieCharts.setLegendData(legendData);
        pieCharts.setTitle(title);
        pieCharts.setSeriesName(seriesName);
        pieCharts.setSeriesData(seriesData);
        return pieCharts;
    }

}
