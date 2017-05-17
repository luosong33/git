package org.jumao.bi.service.impl.trade.deal;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.Response;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.jumao.bi.entites.PieCharts;
import org.jumao.bi.entites.SeriesData;
import org.jumao.bi.service.trade.deal.IDealSvc;
import org.jumao.bi.utis.PlatFormUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class DealSvcImpl implements IDealSvc {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Response operate(String startDate, String endDate, String platform) throws Exception {
        StringBuffer sql = new StringBuffer(
                " SELECT count(c.order_id) order_count,sum(c.pay_money) order_money,sum(if(p.payment_id is null,0,1)) payment_count,sum(IFNULL(p.pay_money,0.00)) payment_money");
        sql.append(" FROM jmbi_trade_order c").append(" left join jmbi_trade_payment p on c.order_id = p.order_id ")
                .append(" WHERE c.order_state in (20,30,40,50,70) and p.pay_flag=1");
        String yearSql = sql.toString();
        if (StringUtils.isNotBlank(startDate)) {
            sql.append(" and c.create_time >= '").append(this.formatDate(startDate)).append(" 00:00:00'");
        }
        if (StringUtils.isNotBlank(endDate)) {
            sql.append(" and c.create_time <= '").append(this.formatDate(endDate)).append(" 23:59:59'");
        }
        if (StringUtils.isNotBlank(platform)) {
            sql.append(" and c.industry_id = ").append(PlatFormUtil.getPlatformV(platform));
            yearSql += " and c.industry_id = " + PlatFormUtil.getPlatformV(platform) + " and year(order_date)="
                    + org.jumao.bi.utis.DateUtils.getYear(null);
        }
        List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
        data.add(jdbcTemplate.queryForMap(sql.toString()));
        data.add(jdbcTemplate.queryForMap(yearSql));

        return Response.ok(data).build();
    }

    private String formatDate(String date) {
        if (StringUtils.isNotBlank(date)) {
            date = StringUtils.left(date, 4) + "-" + StringUtils.substring(date, 4, 6) + "-" + StringUtils.right(date, 2);
        }
        return date;
    }

    public Response translate(String startDate, String endDate, String platform) throws Exception {
        String subSql = "";
        if (StringUtils.isNotBlank(startDate)) {
            subSql += " and o.create_time >= '" + this.formatDate(startDate) + " 00:00:00'";
        }
        if (StringUtils.isNotBlank(endDate)) {
            subSql += " and o.create_time <= '" + this.formatDate(endDate) + " 23:59:59'";
        }

        if (StringUtils.isNotBlank(platform)) {
            subSql += " and o.industry_id = " + PlatFormUtil.getPlatformV(platform);
        }

        List<SeriesData> seriesData = new ArrayList<SeriesData>();
        StringBuffer sql = new StringBuffer("select count(distinct c.comp_id)");// 下单uv
        sql.append(" from jmbi_trade_order o,jmbi_trade_company c,jmbi_trade_center_user u")
                .append(" where o.member_id=u.user_id and u.company_id=c.comp_id").append(subSql);
        setData(seriesData, sql.toString(), "下单uv");
        setData(seriesData, sql.toString().concat(" and o.order_state in (20,30,40,50,70)"), "确认订单uv");
        setData(seriesData, sql.toString().concat(" and o.contract_status=40"), "签订合同uv");
        setData(seriesData, sql.toString().concat(" and o.order_state in (40,50,70)"), "订单支付uv");

        PieCharts pieCharts = new PieCharts();
        pieCharts.setLegendData(Arrays.asList("下单uv", "确认订单uv", "签订合同uv", "订单支付uv"));
        pieCharts.setTitle("交易转化跟踪");
        pieCharts.setSeriesName("交易转化跟踪");
        pieCharts.setSeriesData(seriesData);
        return Response.ok(pieCharts).build();
    }

    private void setData(List<SeriesData> seriesData, String placeSql, String name) {
        long count = jdbcTemplate.queryForObject(placeSql, Long.class);
        SeriesData sData = new SeriesData();
        sData.setName(name);
        sData.setValue(BigDecimal.valueOf(count));
        seriesData.add(sData);
    }

    public Response pickup(String startDate, String endDate, String platform) throws Exception {
        StringBuffer sql = new StringBuffer("select o.shipping_type,count(distinct c.comp_id) count");
        sql.append(" from jmbi_trade_order o,jmbi_trade_company c,jmbi_trade_center_user u").append(
                " where o.member_id=u.user_id and u.company_id=c.comp_id and o.shipping_type is not NULL ");
        if (StringUtils.isNotBlank(startDate)) {
            sql.append(" and o.create_time >= '").append(this.formatDate(startDate)).append(" 00:00:00'");
        }
        if (StringUtils.isNotBlank(endDate)) {
            sql.append(" and o.create_time <= '").append(this.formatDate(endDate)).append(" 23:59:59'");
        }
        if (StringUtils.isNotBlank(platform)) {
            sql.append(" and o.industry_id = ").append(PlatFormUtil.getPlatformV(platform));
        }
        sql.append(" group by o.shipping_type").append(" order by o.shipping_type");
        List<Map<String, Object>> data = jdbcTemplate.queryForList(sql.toString());
        List<SeriesData> seriesData = new ArrayList<SeriesData>();

        addSeriesData(data, seriesData, "shipping_type");

        PieCharts pieCharts = new PieCharts();
        pieCharts.setLegendData(Arrays.asList("卖家配送", "买家自提"));
        pieCharts.setTitle("提货方式用户占比");
        pieCharts.setSeriesName("提货方式用户占比");
        pieCharts.setSeriesData(seriesData);
        return Response.ok(pieCharts).build();
    }

    private void addSeriesData(List<Map<String, Object>> data, List<SeriesData> seriesData, String key) {
        SeriesData sData = null;
        for (Map<String, Object> map : data) {
            sData = new SeriesData();
            sData.setName(String.valueOf(map.get(key)));
            sData.setValue(NumberUtils.createBigDecimal(map.get("count").toString()));
            seriesData.add(sData);
        }
    }

    @Override
    public Response delivery(String startDate, String endDate, String platform) throws Exception {
        StringBuffer sql = new StringBuffer("SELECT a.order_id,datediff(a.time1,b.time2) days ");
        sql.append("FROM (SELECT o.order_id,create_time time1 FROM jmbi_trade_order_track o WHERE o.memo = '订单确认') a,")
                .append("(SELECT o.order_id, o.create_time time2 FROM jmbi_trade_order_track o WHERE o.memo = '发货') b,jmbi_trade_order o ")
                .append(" WHERE a.order_id = b.order_id AND o.order_id = a.order_id");
        if (StringUtils.isNotBlank(startDate)) {
            sql.append(" and o.create_time >= '").append(this.formatDate(startDate)).append(" 00:00:00'");
        }
        if (StringUtils.isNotBlank(endDate)) {
            sql.append(" and o.create_time <= '").append(this.formatDate(endDate)).append(" 23:59:59'");
        }
        if (StringUtils.isNotBlank(platform)) {
            sql.append(" and o.industry_id = ").append(PlatFormUtil.getPlatformV(platform));
        }
        List<Map<String, Object>> data = jdbcTemplate.queryForList(sql.toString());
        List<SeriesData> seriesData = new ArrayList<SeriesData>();
        List<String> legendData = new ArrayList<String>();
        section(data, seriesData, legendData);

        PieCharts pieCharts = new PieCharts();
        pieCharts.setLegendData(legendData);
        pieCharts.setTitle("交货时间占比");
        pieCharts.setSeriesName("交货时间占比");
        pieCharts.setSeriesData(seriesData);
        return Response.ok(pieCharts).build();
    }

    private void section(List<Map<String, Object>> data, List<SeriesData> seriesData, List<String> legendData) {
        Map<String, Long> dataMap = new HashMap<String, Long>();
        long days = 0;
        String dataKey = null;
        for (Map<String, Object> map : data) {
            days = NumberUtils.toLong(map.get("days").toString(), 0);
            if (days >= 10) {
                dataKey = "1";
            } else if (days >= 7 && days < 10) {
                dataKey = "2";
            } else if (days >= 5 && days < 7) {
                dataKey = "3";
            } else if (days >= 3 && days < 5) {
                dataKey = "4";
            } else {
                dataKey = "5";
            }
            if (dataMap.get(dataKey) == null) {
                dataMap.put(dataKey, 1L);
            } else {
                dataMap.put(dataKey, dataMap.get(dataKey) + 1L);
            }
        }

        List<String> keyList = new ArrayList<String>(dataMap.keySet());
        Collections.sort(keyList);
        SeriesData sData = null;
        BigDecimal value = BigDecimal.ZERO;
        for (String key : keyList) {
            value = BigDecimal.valueOf(dataMap.get(key));
            if ("1".equals(key)) {
                key = "10-15天以内";
            } else if ("2".equals(key)) {
                key = "7-10天以内";
            } else if ("3".equals(key)) {
                key = "5-7天以内";
            } else if ("4".equals(key)) {
                key = "3-5天以内";
            } else if ("5".equals(key)) {
                key = "3天以内";
            }
            sData = new SeriesData();
            sData.setName(key);
            sData.setValue(value);
            seriesData.add(sData);
            legendData.add(key);
        }
    }

    public Response receipt(String startDate, String endDate, String platform) throws Exception {
        StringBuffer sql = new StringBuffer("SELECT a.order_id,datediff(a.time1,b.time2) days ");
        sql.append("FROM (SELECT o.order_id,create_time time1 FROM jmbi_trade_order_track o WHERE o.memo = '发货') a,")
                .append("(SELECT o.order_id, o.create_time time2 FROM jmbi_trade_order_track o WHERE o.memo = '确认收货') b,jmbi_trade_order o ")
                .append(" WHERE a.order_id = b.order_id AND o.order_id = a.order_id");
        if (StringUtils.isNotBlank(startDate)) {
            sql.append(" and o.create_time >= '").append(this.formatDate(startDate)).append(" 00:00:00'");
        }
        if (StringUtils.isNotBlank(endDate)) {
            sql.append(" and o.create_time <= '").append(this.formatDate(endDate)).append(" 23:59:59'");
        }
        if (StringUtils.isNotBlank(platform)) {
            sql.append(" and o.industry_id = ").append(PlatFormUtil.getPlatformV(platform));
        }
        List<Map<String, Object>> data = jdbcTemplate.queryForList(sql.toString());
        List<SeriesData> seriesData = new ArrayList<SeriesData>();
        List<String> legendData = new ArrayList<String>();
        this.section(data, seriesData, legendData);

        PieCharts pieCharts = new PieCharts();
        pieCharts.setLegendData(legendData);
        pieCharts.setTitle("确认收货时间");
        pieCharts.setSeriesName("确认收货货时间");
        pieCharts.setSeriesData(seriesData);
        return Response.ok(pieCharts).build();
    }

    public Response settle(String startDate, String endDate, String platform) throws Exception {
        StringBuffer sql = new StringBuffer("select clearing_type,count(distinct order_id) count,sum(pay_money)");
        sql.append(" from jmbi_trade_order ").append(" where clearing_type is not NULL ");
        if (StringUtils.isNotBlank(startDate)) {
            sql.append(" and create_time >= '").append(this.formatDate(startDate)).append(" 00:00:00'");
        }
        if (StringUtils.isNotBlank(endDate)) {
            sql.append(" and create_time <= '").append(this.formatDate(endDate)).append(" 23:59:59'");
        }
        if (StringUtils.isNotBlank(platform)) {
            sql.append(" and industry_id = ").append(PlatFormUtil.getPlatformV(platform));
        }
        sql.append(" group by clearing_type ").append("order by clearing_type");
        List<Map<String, Object>> data = jdbcTemplate.queryForList(sql.toString());
        List<SeriesData> seriesData = new ArrayList<SeriesData>();
        this.addSeriesData(data, seriesData, "clearing_type");

        PieCharts pieCharts = new PieCharts();
        pieCharts.setLegendData(Arrays.asList("定金支付", "担保支付", "全款支付"));
        pieCharts.setTitle("结算方式占比");
        pieCharts.setSeriesName("结算方式占比");
        pieCharts.setSeriesData(seriesData);
        return Response.ok(pieCharts).build();
    }

    public Response pay(String startDate, String endDate, String platform) throws Exception {
        StringBuffer sql = new StringBuffer("select payment_type,count(distinct order_id) count,sum(pay_money)");
        sql.append(" from jmbi_trade_order ").append(" where payment_type is not NULL ");
        if (StringUtils.isNotBlank(startDate)) {
            sql.append(" and create_time >= '").append(this.formatDate(startDate)).append(" 00:00:00'");
        }
        if (StringUtils.isNotBlank(endDate)) {
            sql.append(" and create_time <= '").append(this.formatDate(endDate)).append(" 23:59:59'");
        }
        if (StringUtils.isNotBlank(platform)) {
            sql.append(" and industry_id = ").append(PlatFormUtil.getPlatformV(platform));
        }
        sql.append(" GROUP BY payment_type").append(" order by payment_type");
        List<Map<String, Object>> data = jdbcTemplate.queryForList(sql.toString());
        List<SeriesData> seriesData = new ArrayList<SeriesData>();
        this.addSeriesData(data, seriesData, "payment_type");

        PieCharts pieCharts = new PieCharts();
        pieCharts.setLegendData(Arrays.asList("平台支付", "线下支付", "其它"));
        pieCharts.setTitle("支付方式");
        pieCharts.setSeriesName("支付方式");
        pieCharts.setSeriesData(seriesData);
        return Response.ok(pieCharts).build();
    }

    public Response contract(String startDate, String endDate, String platform) throws Exception {
        StringBuffer sql = new StringBuffer("select contract_type,count(distinct order_id) count,sum(pay_money)");
        sql.append(" from jmbi_trade_order ").append(" where contract_type is not NULL ");
        if (StringUtils.isNotBlank(startDate)) {
            sql.append(" and create_time >= '").append(this.formatDate(startDate)).append(" 00:00:00'");
        }
        if (StringUtils.isNotBlank(endDate)) {
            sql.append(" and create_time <= '").append(this.formatDate(endDate)).append(" 23:59:59'");
        }
        if (StringUtils.isNotBlank(platform)) {
            sql.append(" and industry_id = ").append(PlatFormUtil.getPlatformV(platform));
        }
        sql.append(" GROUP BY contract_type").append(" order by contract_type");
        List<Map<String, Object>> data = jdbcTemplate.queryForList(sql.toString());
        List<SeriesData> seriesData = new ArrayList<SeriesData>();
        this.addSeriesData(data, seriesData, "contract_type");

        PieCharts pieCharts = new PieCharts();
        pieCharts.setLegendData(Arrays.asList("电子合同", "线下合同", "无合同"));
        pieCharts.setTitle("合同方式");
        pieCharts.setSeriesName("合同方式");
        pieCharts.setSeriesData(seriesData);
        return Response.ok(pieCharts).build();
    }

    public Response areaAmount(String role, String startDate, String endDate, String platform, String orderby) throws Exception {
        String subSql = "";
        if (StringUtils.isNotBlank(startDate)) {
            subSql += " and o.create_time >= '" + this.formatDate(startDate) + " 00:00:00'";
        }
        if (StringUtils.isNotBlank(endDate)) {
            subSql += " and o.create_time <= '" + this.formatDate(endDate) + " 23:59:59'";
        }

        if (StringUtils.isNotBlank(platform)) {
            subSql += " and o.industry_id = " + PlatFormUtil.getPlatformV(platform);
        }
        StringBuffer sql = new StringBuffer();
        if ("seller".equalsIgnoreCase(role)) {// 卖家
            sql.append("SELECT ordernum,paytotal,address_province,IF(LOCATE(a.AREA_NAME,'黑龙江省_内蒙古自治区')>0,strleft(a.AREA_NAME,9),strleft(a.AREA_NAME, 6)) area_name ")
                    .append("FROM (SELECT count(DISTINCT order_id) ordernum,sum(pay_money) paytotal,c.address_province ")
                    .append("FROM jmbi_trade_order o,jmbi_trade_company c,jmbi_trade_shop s ")
                    .append("WHERE o.store_id = s.shop_id AND s.comp_id = c.comp_id AND c.address_province IS NOT NULL ").append(subSql)
                    .append(" GROUP BY address_province ) t ")
                    .append("LEFT JOIN jmbi_trade_operation_area a ON t.address_province = a.AREA_CODE");
        } else if ("buyer".equalsIgnoreCase(role)) {// 买家
            sql.append("SELECT ordernum,paytotal,address_province,IF(LOCATE(a.AREA_NAME,'黑龙江省_内蒙古自治区')>0,strleft(a.AREA_NAME,9),strleft(a.AREA_NAME, 6)) area_name ")
                    .append("FROM (SELECT count(DISTINCT order_id) ordernum,sum(pay_money) paytotal,c.address_province ")
                    .append("FROM jmbi_trade_order o,jmbi_trade_company c,jmbi_trade_center_user u")
                    .append("  WHERE o.member_id = u.user_id AND u.company_id = c.comp_id AND c.address_province IS NOT NULL ")
                    .append(subSql).append(" GROUP BY address_province ) t ")
                    .append("LEFT JOIN jmbi_trade_operation_area a ON t.address_province = a.AREA_CODE");
        } else {
            return Response.ok().build();
        }
        if (StringUtils.isNotBlank(orderby)) {
            sql.append(" order by t.").append(orderby).append(" desc");//方便取top ten
        }
        return Response.ok(jdbcTemplate.queryForList(sql.toString())).build();
    }

    public Response payChannel(String startDate, String endDate, String platform) throws Exception {
        StringBuffer sql = new StringBuffer("SELECT c.PAY_CHANNEL_NO,c.PAY_CHANNEL_NAME,count(DISTINCT t.order_no),sum(t.pay_amt) pay_amt ");
        sql.append(" FROM jmbi_ep_trade t, jmbi_epay_channel c").append(" where t.channel_no = c.pay_channel_no  ");
        if (StringUtils.isNotBlank(startDate)) {
            sql.append(" and t.create_time >= '").append(this.formatDate(startDate)).append(" 00:00:00'");
        }
        if (StringUtils.isNotBlank(endDate)) {
            sql.append(" and t.create_time <= '").append(this.formatDate(endDate)).append(" 23:59:59'");
        }
        if (StringUtils.isNotBlank(platform)) {
            platform = platform.endsWith("00") ? StringUtils.left(platform, 4) : platform;
            sql.append(" and t.industry_id = ").append(platform);
        }
        sql.append(" GROUP BY c.PAY_CHANNEL_NO, c.PAY_CHANNEL_NAME");
        List<Map<String, Object>> data = jdbcTemplate.queryForList(sql.toString());
        List<SeriesData> seriesData = new ArrayList<SeriesData>();
        List<String> legendData = new ArrayList<String>();
        SeriesData sData = null;
        for (Map<String, Object> map : data) {
            sData = new SeriesData();
            sData.setName(map.get("PAY_CHANNEL_NAME").toString());
            sData.setValue(NumberUtils.createBigDecimal(map.get("pay_amt").toString()));
            seriesData.add(sData);
            legendData.add(map.get("PAY_CHANNEL_NAME").toString());
        }

        PieCharts pieCharts = new PieCharts();
        pieCharts.setLegendData(legendData);
        pieCharts.setTitle("支付渠道金额占比");
        pieCharts.setSeriesName("支付渠道金额占比");
        pieCharts.setSeriesData(seriesData);
        return Response.ok(pieCharts).build();
    }
}
