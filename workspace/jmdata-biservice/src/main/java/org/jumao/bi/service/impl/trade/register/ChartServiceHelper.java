package org.jumao.bi.service.impl.trade.register;

import org.apache.log4j.Logger;
import org.jumao.bi.entites.trade.register.ChartResp;
import org.jumao.bi.entites.trade.register.RegLineChart;
import org.jumao.bi.utis.LogUtils;
import org.jumao.bi.utis.RegCalUtils;
import org.jumao.bi.utis.RegDateUtils;
import org.jumao.bi.utis.StringUtils;
import org.jumao.bi.utis.constants.Key;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by kartty on 2017/5/3.
 */
public class ChartServiceHelper {

    private Logger logger = Logger.getLogger(ChartServiceHelper.class);

    protected static final int Top_Ele0 = 0;
    protected static final int Top_Ele5 = 5;
    protected static final int Top_Ele10 = 10;

    public static Map<Integer, String> dataSrcNameMap = new HashMap<Integer, String>();
    public static Map<Integer, String> compCategoryMap = new HashMap<Integer, String>();
    public static Map<Integer, String> licTpyeNameMap = new HashMap<Integer, String>();
    public static Map<Integer, String> industryIdNameMap = new HashMap<Integer, String>();
    public static Map<Integer, String> lineTypeNameMap = new HashMap<Integer, String>();
    public static String ELSE_NAME = "其他";

    static {
        //1 前台注册 2 后台创建 3 中台自己注册 4 中台管理员创建 98 手动创建 99 认证中心同步
        dataSrcNameMap.put(Key.Num1, "前台注册");
        dataSrcNameMap.put(Key.Num2, "后台创建");
        dataSrcNameMap.put(Key.Num3, "中台自己注册");
        dataSrcNameMap.put(Key.Num4, "中台管理员创建");
        dataSrcNameMap.put(Key.Num98, "手动创建");
        dataSrcNameMap.put(Key.Num99, "认证中心同步");

        compCategoryMap.put(Key.Num1, "工厂");
        compCategoryMap.put(Key.Num2, "贸易商");
        compCategoryMap.put(Key.Num3, "工贸一体");

        //0 多证合一 1 普通三证
        licTpyeNameMap.put(Key.Num0, "多证合一");
        licTpyeNameMap.put(Key.Num1, "普通三证");

        industryIdNameMap.put(Key.Num1, "煤炭");
        industryIdNameMap.put(Key.Num2, "有色");
        industryIdNameMap.put(Key.Num3, "矿产");
        industryIdNameMap.put(Key.Num4, "农产品");
        industryIdNameMap.put(Key.Num5, "钢铁");
        industryIdNameMap.put(Key.Num6, "消费品");
        industryIdNameMap.put(Key.Num7, "食品");
        industryIdNameMap.put(Key.Num8, "工业品");
        industryIdNameMap.put(Key.Num9, "机械");
        industryIdNameMap.put(Key.Num10, "化工");
        industryIdNameMap.put(Key.Num11, "石油");

        lineTypeNameMap.put(Key.Num231, "公路");
        lineTypeNameMap.put(Key.Num232, "铁路");
        lineTypeNameMap.put(Key.Num233, "内河航运");
        lineTypeNameMap.put(Key.Num234, "海运");
        lineTypeNameMap.put(Key.Num235, "仓储");
        lineTypeNameMap.put(Key.Num236, "联运");
        lineTypeNameMap.put(Key.Num237, "空运");
    }


    protected String checkTypeName(Integer type, String name) {
        if (name == null) {
            //String typeStr = null;
            //if (type == null) {
                //typeStr = "n";
            //} else {
                //typeStr = String.valueOf(type);
            //}
            //name = StringUtils.joinStr(ChartServiceHelper.ELSE_NAME, "#", typeStr);
            name = ChartServiceHelper.ELSE_NAME;
        }
        return name;
    }


    protected String dealRatePercent(double top, double bottom, boolean transToPercent) {
        double res = 0D;
        if (transToPercent) {
            res = top / bottom * Key.Num100;
        } else {
            res = top / bottom;
        }
        String rate = String.valueOf(res);
        StringBuilder sb = new StringBuilder();

        if (bottom != 0D) {
            int len = rate.length();
            int dotIdx = rate.indexOf(".");
            if (dotIdx > 0 && len > dotIdx + Key.Num3) {
                rate = rate.substring(0, dotIdx + Key.Num3);
            }
        }

        sb.append(rate);
        if (transToPercent) {
            sb.append("%");
        }
        return sb.toString();
    }


    @Deprecated
    protected List<Map<String,Long>> genTypeNameTotalMaps(Map<String, ChartResp> legendCpMap) {
        List<Map<String, Long>> typeNameTotalMaps = new ArrayList<Map<String, Long>>();

        for (Map.Entry<String, ChartResp> entry : legendCpMap.entrySet()) {
            try {
                RegLineChart cp = entry.getValue().getLineChart();
                Map<String, Long> typeNameTotalMap = new HashMap<String, Long>();

                String[] typeNames = cp.getyAxisData();
                long[] typeCounts = cp.getSeriesData();

                for (int i = 0; i < typeNames.length; i++) {
                    try {
                        typeNameTotalMap.put(typeNames[i], typeCounts[i]);
                    } catch (Exception e) {
                        LogUtils.writeLogs(logger, e.getMessage());
                    }
                }

                typeNameTotalMaps.add(typeNameTotalMap);
            } catch (Exception e) {
                LogUtils.writeLogs(logger, e.getMessage());
            }
        }
        return typeNameTotalMaps;
    }

    @Deprecated
    protected void addMapForTypeNameTotal(List<Map<String, Long>> typeNameTotalMaps) {
        int size = typeNameTotalMaps.size();
        for (int i = 0; i < size; i++) {
            Map<String, Long> currMap = typeNameTotalMaps.get(i);

            for (int j = 0; j < size; j++) {
                try {
                    if (j != i) {
                        Map<String, Long> otherMap = typeNameTotalMaps.get(j);

                        for (Map.Entry<String, Long> otherEntry : otherMap.entrySet()) {
                            try {
                                String otherKey = otherEntry.getKey();
                                Long currOneVal = currMap.get(otherKey);

                                if (currOneVal == null) {
                                    currMap.put(otherKey, 0L);
                                }
                            } catch (Exception e) {
                                LogUtils.writeLogs(logger, e.getMessage());
                            }
                        }
                    }
                } catch (Exception e) {
                    LogUtils.writeLogs(logger, e.getMessage());
                }
            }
        }

    }


    /**
     * 百度统计里的 date 已经是 yyyyMMdd，所以不需要再 format
     * @param formatToNhAndUl ul = underline
     */
    protected String parseTime(String createTime, boolean formatToNhAndUl, boolean accurateToHour) throws Exception {
        String timeParsed = null;
        if (accurateToHour) {
            timeParsed = getHourFromCreateTime(createTime, formatToNhAndUl);
        } else {
            if (formatToNhAndUl) {
                timeParsed = RegDateUtils.formatToNoHourAnd_(RegDateUtils.parseStartWithFF(createTime));
            } else {
                timeParsed = createTime;
            }
        }
        return timeParsed;
    }

    /**
     * @param formatToNhAndUl ul = underline
     * @return
     * @throws Exception
     */
    private String getHourFromCreateTime(String createTime, boolean formatToNhAndUl) throws Exception {
        Date date = null;
//        if (formatToNhAnd_) {
            date = RegDateUtils.parseStartWithFF(createTime);
//        } else {
//            date = RegDateUtils.parseToNoHourAnd_(createTime);
//        }

        Calendar calendar = RegCalUtils.getCalendarBy(date);
        return String.valueOf(calendar.get(Calendar.HOUR_OF_DAY));
    }


    protected Map<String,Long> getEmptyDateTotalMap(String startDate, String endDate, boolean accurateToHour) throws Exception {
        Map<String,Long> dateTotalMap = new HashMap<String, Long>();

        Calendar startCal = RegCalUtils.getCalendarBy(RegDateUtils.parseToNoHourAnd_(startDate));
        Calendar endCal = RegCalUtils.getCalendarBy(RegDateUtils.parseToNoHourAnd_(endDate));

        if (accurateToHour) {
            for (int i = 0; i <= Key.Num23; i++) {
                dateTotalMap.put(String.valueOf(i), 0L);
            }
        } else {
            while (!startCal.equals(endCal)) {
                String curDateStr = RegDateUtils.formatToNoHourAnd_(startCal.getTime());
                dateTotalMap.put(curDateStr, 0L);

                startCal.add(Calendar.DAY_OF_MONTH, 1);
            }
        }

        return dateTotalMap;
    }


    protected String changeHourFormat(String time) {
        if (time.length() == 1) {
            time = StringUtils.joinStr("0", time, ":00");
        } else {
            time = StringUtils.joinStr(time, ":00");
        }
        return time;
    }



}
