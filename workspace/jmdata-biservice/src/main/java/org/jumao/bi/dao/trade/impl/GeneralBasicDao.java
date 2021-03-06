package org.jumao.bi.dao.trade.impl;

import org.apache.log4j.Logger;
import org.jumao.bi.utis.GeneralUtils;
import org.jumao.bi.utis.LogUtils;
import org.jumao.bi.utis.RegCalUtils;
import org.jumao.bi.utis.RegDateUtils;
import org.jumao.bi.utis.StringUtils;
import org.jumao.bi.utis.Verifier;
import org.jumao.bi.utis.comparator.GroupByVoComp;
import org.jumao.bi.utis.constants.Key;
import org.jumao.bi.utis.constants.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Calendar;
import java.util.List;

/**
 * Created by kartty on 2017/5/4.
 */
public class GeneralBasicDao {

    private Logger logger = Logger.getLogger(GeneralBasicDao.class);

    protected GroupByVoComp groupByVoComp = new GroupByVoComp();

    @Autowired
    protected JdbcTemplate jdbcTemplate;

    public static final int Len4 = 4;
    public static final int Len6 = 6;
    public static final int Num86399 = 86399;
    protected String allTrade = "0"; //0 表示总交易平台
    protected String msPlatformId = "1001"; //master station 总站 id



    protected String checkMsPlatformId(String platform) {
        if (msPlatformId.equals(platform)) {
            platform = dealPlatform(platform, Len4);
        } else if (allTrade.equals(platform)) {
            platform = null;
        } else if (!Verifier.isEffectiveStr(platform)) {
            platform = null;
        } else {
            platform = null;
        }
        return platform;
    }


    /**
     * @param platform
     * @param needLen
     * @return
     */
    public String dealPlatform(String platform, int needLen) {
        int len = platform.length();
        if (len == needLen) {
            return platform;

        } else if (len < needLen) {
            int diff = needLen - len;
            StringBuilder sb = new StringBuilder(platform);
            for (int i = 0; i < diff; i++) {
                sb.append("0");
            }
            platform = sb.toString();

        } else {
            platform = platform.substring(0, needLen);
        }
        LogUtils.writeLogs(logger, StringUtils.joinStr(GeneralUtils.getCurrMethod(),
                " transform new platform:", platform));
        return platform;
    }

    protected String dealStartDate(String startDate) throws Exception {
        return RegDateUtils.formatToFullF(RegDateUtils.parseToNoHourAnd_(startDate));
    }

    protected String dealEndDate(String endDate) throws Exception {
        Calendar c = RegCalUtils.getCalendarBy(RegDateUtils.parseToNoHourAnd_(endDate));
        c.add(Calendar.SECOND, Num86399);//加上 24*60*60 -1 秒
        return RegDateUtils.formatToFullF(c.getTime());
    }


    protected <T> List<T> getVoList(String sql, Class<T> clazz) {
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<T>(clazz));
    }


    protected String getCrtTmGroupLength(boolean accurateToHour) {
        String tmLen = null;
        if (accurateToHour) {
            tmLen = "13";
        } else {
            tmLen = "10";
        }
        return StringUtils.joinStr("strleft(CAST(create_time AS string), ", tmLen, ")");
    }


    protected String sqlOfGroupByTradeCompanyCrtTm(String table, String firstCondi, String startDate, String endDate, boolean accurateToHour) {
        String tmGrpLen = getCrtTmGroupLength(accurateToHour);
        return StringUtils.joinStr(
                "select ", tmGrpLen, " as ", Key.CREATE_TIME,
                ", count(DISTINCT comp_id) as ", Key.TOTAL, " from ", table,
                " where ", firstCondi, " and delete_flag = 0 and",
                " (create_time between '", startDate, "' and '", endDate, "')",
                " group by ", Key.CREATE_TIME, " order by ", Key.CREATE_TIME, " asc");
    }


    protected String sqlOfGroupByTradeCompayArea(String firstCondi, String startDate, String endDate, int topEle) {
        return StringUtils.joinStr(
                "SELECT t.", Key.Type, ", a.area_name as ", Key.TYPE_NAME, ", t.", Key.TOTAL,
                " FROM (SELECT count(DISTINCT c.comp_id) as ", Key.TOTAL, ", c.address_province as ", Key.Type,
                " FROM ", Table.Trade_Company, " c WHERE ", firstCondi, " and c.delete_flag = 0 and c.address_province is not null and",
                " (c.create_time between '", startDate, "' and '", endDate, "')",
                " GROUP BY c.address_province",
                topEle > 0 ? StringUtils.joinStr(" order by ", Key.TOTAL, " desc limit ", topEle) : "",
                " ) t LEFT JOIN ", Table.Area, " a ON t.", Key.Type, " = a.area_code");
    }



}
