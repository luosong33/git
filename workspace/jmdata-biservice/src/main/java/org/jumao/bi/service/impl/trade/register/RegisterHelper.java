package org.jumao.bi.service.impl.trade.register;

import org.apache.log4j.Logger;
import org.jumao.bi.entites.trade.register.ChartResp;
import org.jumao.bi.entites.trade.register.RegLineChart;
import org.jumao.bi.utis.LogUtils;
import org.jumao.bi.utis.RegCalUtils;
import org.jumao.bi.utis.RegDateUtils;
import org.jumao.bi.utis.StringUtils;

import java.util.*;

/**
 * Created by kartty on 2017/5/3.
 */
public class RegisterHelper {

    private Logger logger = Logger.getLogger(RegisterHelper.class);

    public static Map<Integer, String> dataSrcNameMap = new HashMap<Integer, String>();
    public static Map<Integer, String> compCategoryMap = new HashMap<Integer, String>();
    public static Map<Integer, String> licTpyeNameMap = new HashMap<Integer, String>();
//    public static Map<Integer, String> provinceCodeNameMap = new HashMap<Integer, String>();
    public static String ELSE_NAME = "其他";

    static {
        //1 前台注册 2 后台创建 3 中台自己注册 4 中台管理员创建 98 手动创建 99 认证中心同步
        dataSrcNameMap.put(1, "前台注册");
        dataSrcNameMap.put(2, "后台创建");
        dataSrcNameMap.put(3, "中台自己注册");
        dataSrcNameMap.put(4, "中台管理员创建");
        dataSrcNameMap.put(98, "手动创建");
        dataSrcNameMap.put(99, "认证中心同步");

        compCategoryMap.put(1, "工厂");
        compCategoryMap.put(2, "贸易商");
        compCategoryMap.put(3, "工贸一体");

        //0 多证合一 1 普通三证
        licTpyeNameMap.put(0, "多证合一");
        licTpyeNameMap.put(1, "普通三证");

//        provinceCodeNameMap.put(86061, "陕西");provinceCodeNameMap.put(86062, "甘肃");
//        provinceCodeNameMap.put(86063, "青海");provinceCodeNameMap.put(86064, "宁夏");
//        provinceCodeNameMap.put(86065, "新疆");provinceCodeNameMap.put(86011, "北京");
//        provinceCodeNameMap.put(86012, "天津");provinceCodeNameMap.put(86013, "河北");
//        provinceCodeNameMap.put(86014, "山西");provinceCodeNameMap.put(86015, "内蒙");
//        provinceCodeNameMap.put(86021, "辽宁");provinceCodeNameMap.put(86022, "吉林");
//        provinceCodeNameMap.put(86023, "黑龙江");provinceCodeNameMap.put(86031, "上海");
//        provinceCodeNameMap.put(86032, "江苏");provinceCodeNameMap.put(86033, "浙江");
//        provinceCodeNameMap.put(86034, "安徽");provinceCodeNameMap.put(86035, "福建");
//        provinceCodeNameMap.put(86036, "江西");provinceCodeNameMap.put(86037, "山东");
//        provinceCodeNameMap.put(86071, "河南");provinceCodeNameMap.put(86072, "湖北");
//        provinceCodeNameMap.put(86073, "湖南");provinceCodeNameMap.put(86044, "广东");
//        provinceCodeNameMap.put(86045, "广西");provinceCodeNameMap.put(86046, "海南");
//        provinceCodeNameMap.put(86050, "重庆");provinceCodeNameMap.put(86051, "四川");
//        provinceCodeNameMap.put(86052, "贵州");provinceCodeNameMap.put(86053, "云南");
//        provinceCodeNameMap.put(86054, "西藏");
        //provinceCodeNameMap.put(, "");provinceCodeNameMap.put(, "");

    }


    protected String checkTypeName(Integer type, String name) {
        if (name == null) {
            String typeStr = null;
            if (type == null) {
                typeStr = "n";
            } else {
                typeStr = String.valueOf(type);
            }
            name = StringUtils.joinStr(RegisterHelper.ELSE_NAME, "#", typeStr);
        }
        return name;
    }


    protected String dealRatePercent(double totalD, double sumCounts) {
        String rate = String.valueOf(totalD / sumCounts * 100);
        StringBuilder sb = new StringBuilder();

        if (totalD != 0D) {
            int len = rate.length();
            int dotIdx = rate.indexOf(".");
            if (dotIdx > 0) {
                rate = rate.substring(0, dotIdx + 3);
            } else {
                rate = rate.substring(0, 5);
            }
        }

        sb.append(rate).append("%");
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


    protected String parseTime(String createTime, boolean formatToNhAnd_, boolean accurateToHour) throws Exception {
        String timeParsed = null;
        if (accurateToHour) {
            timeParsed = getHourFromCreateTime(createTime, formatToNhAnd_);
        } else {
            if (formatToNhAnd_) {
                timeParsed = RegDateUtils.formatToNoHourAnd_(RegDateUtils.parseStartWithFF(createTime));
            } else {
                timeParsed = createTime;
            }
        }
        return timeParsed;
    }

    private String getHourFromCreateTime(String createTime, boolean formatToNhAnd_) throws Exception {
        Date date = null;
        if (formatToNhAnd_) {
            date = RegDateUtils.parseStartWithFF(createTime);
        } else {
            date = RegDateUtils.parseToNoHourAnd_(createTime);
        }

        Calendar calendar = RegCalUtils.getCalendarBy(date);
        return String.valueOf(calendar.get(Calendar.HOUR_OF_DAY));
    }


}