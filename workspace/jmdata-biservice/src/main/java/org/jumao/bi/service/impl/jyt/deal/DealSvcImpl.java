package org.jumao.bi.service.impl.jyt.deal;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.Response;

import org.apache.hadoop.hbase.filter.CompareFilter.CompareOp;
import org.jumao.bi.entites.PieCharts;
import org.jumao.bi.entites.SeriesData;
import org.jumao.bi.service.jyt.deal.IDealSvc;
import org.jumao.bi.utis.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class DealSvcImpl implements IDealSvc {

    @Autowired
    private JdbcTemplate       jdbcTemplate;
    public static final String Create_Time = "create_time";

    /**
     * @see org.jumao.bi.service.jyt.deal.IDealSvc#operate(java.lang.String,
     *      java.lang.String, java.lang.String)
     */
    public Response operate(String startDate, String endDate, String platform) throws Exception {
        String subSql = "";
        if (StringUtils.isNotBlank(startDate)) {
            subSql += StringUtils.getSqlCondition("", Create_Time, StringUtils.formatDate(startDate), CompareOp.GREATER_OR_EQUAL);
        }
        if (StringUtils.isNotBlank(endDate)) {
            subSql += StringUtils.getSqlCondition("", Create_Time, StringUtils.formatDate(endDate), CompareOp.LESS_OR_EQUAL);
        }
        StringBuffer sql = new StringBuffer(
                "SELECT count(*) count,sum(weight) weight,ifnull(round(sum(total_price)/10000.000,2),0) total_price");
        sql.append(" FROM jmbi_lg_order ").append(" WHERE delete_flag = '0' AND active_flag = '0' AND order_type IN ('1', '2') ");
        /*
         * StringBuffer paySql = new StringBuffer(
         * "SELECT count(c.payment_id) payment_count,ifnull(round(sum(c.pay_money)/10000.000,2),0) payment_money "
         * );
         * paySql.append(" FROM jmbi_trade_payment c,jmbi_trade_order o ").append
         * (" WHERE c.order_id = o.order_id AND c.pay_flag = 1 ");
         */
        String subYearSql = " and create_time like '" + org.jumao.bi.utis.DateUtils.getYear(null) + "%'";
        String yearSql = sql.toString() + subYearSql;
        // String yearPaySql = paySql.toString() + subYearSql;

        List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
        data.add(jdbcTemplate.queryForMap(sql.append(subSql).toString()));
        // data.get(0).putAll(jdbcTemplate.queryForMap(paySql.append(subSql).toString()));
        data.add(jdbcTemplate.queryForMap(yearSql));
        // data.get(1).putAll(jdbcTemplate.queryForMap(yearPaySql));

        return Response.ok(data).build();
    }

    /**
     * @see org.jumao.bi.service.jyt.deal.IDealSvc#translate(java.lang.String,
     *      java.lang.String, java.lang.String)
     */
    public Response translate(String startDate, String endDate, String platform) throws Exception {
        String subSql = "";

        List<SeriesData> seriesData = new ArrayList<SeriesData>();
        StringBuffer sql = new StringBuffer("select count(distinct c.comp_id)");// 下单uv
        sql.append(" from jmbi_trade_order o,jmbi_trade_company c,jmbi_trade_center_user u")
                .append(" where o.member_id=u.user_id and u.company_id=c.comp_id").append(subSql);
        setData(seriesData, sql.toString(), "下单UV");
        setData(seriesData, sql.toString().concat(" and o.order_state in (20,30,40,50,70)"), "确认订单UV");
        setData(seriesData, sql.toString().concat(" and o.contract_status=40"), "签订合同UV");
        setData(seriesData, sql.toString().concat(" and o.order_state in (40,50,70)"), "订单支付UV");

        PieCharts pieCharts = new PieCharts();
        pieCharts.setLegendData(Arrays.asList("下单UV", "确认订单UV", "签订合同UV", "订单支付UV"));
        pieCharts.setTitle("交易转化跟踪");
        pieCharts.setSeriesName("交易转化跟踪");
        pieCharts.setSeriesData(seriesData);
        return Response.ok(pieCharts).build();
    }

    private void setData(List<SeriesData> seriesDataList, String placeSql, String name) {
        long count = jdbcTemplate.queryForObject(placeSql, Long.class);
        SeriesData seriesData = new SeriesData();
        seriesData.setName(name);
        seriesData.setValue(BigDecimal.valueOf(count));
        seriesDataList.add(seriesData);
    }

    /**
     * @see org.jumao.bi.service.jyt.deal.IDealSvc#settle(java.lang.String,
     *      java.lang.String, java.lang.String)
     */
    public Response settle(String startDate, String endDate, String platform) throws Exception {
        StringBuffer sql = new StringBuffer("SELECT COUNT(pay_type) count,pay_type");
        sql.append(" from ").append("jmbi_lg_order").append(" where pay_type is not NULL ");
        if (StringUtils.isNotBlank(startDate)) {
            sql.append(StringUtils.getSqlCondition("", Create_Time, StringUtils.formatDate(startDate), CompareOp.GREATER_OR_EQUAL));
        }
        if (StringUtils.isNotBlank(endDate)) {
            sql.append(StringUtils.getSqlCondition("", Create_Time, StringUtils.formatDate(endDate), CompareOp.LESS_OR_EQUAL));
        }
        sql.append(" group by pay_type ").append("order by pay_type");
        List<Map<String, Object>> data = jdbcTemplate.queryForList(sql.toString());
        PieCharts pieCharts = PieCharts.setPieCharts(data, "pay_type", "结算方式占比", "结算方式占比");
        return Response.ok(pieCharts).build();
    }

    /**
     * @see org.jumao.bi.service.jyt.deal.IDealSvc#lineType(java.lang.String,
     *      java.lang.String, java.lang.String)
     */
    public Response lineType(String startDate, String endDate, String platform) throws Exception {
        StringBuffer sql = new StringBuffer("SELECT COUNT(line_type) count,line_type");
        sql.append(" from ").append("jmbi_lg_order").append(" where line_type is not NULL ");
        if (StringUtils.isNotBlank(startDate)) {
            sql.append(StringUtils.getSqlCondition("", Create_Time, StringUtils.formatDate(startDate), CompareOp.GREATER_OR_EQUAL));
        }
        if (StringUtils.isNotBlank(endDate)) {
            sql.append(StringUtils.getSqlCondition("", Create_Time, StringUtils.formatDate(endDate), CompareOp.LESS_OR_EQUAL));
        }
        sql.append(" group by line_type ").append("order by line_type");
        List<Map<String, Object>> data = jdbcTemplate.queryForList(sql.toString());
        PieCharts pieCharts = PieCharts.setPieCharts(data, "line_type", "运输方式", "运输方式");
        return Response.ok(pieCharts).build();
    }

    /**
     * @see org.jumao.bi.service.jyt.deal.IDealSvc#orderSource(java.lang.String, java.lang.String, java.lang.String)
     */
    public Response orderSource(String startDate, String endDate, String platform) throws Exception {
        StringBuffer sql = new StringBuffer("SELECT count(1) count,c.platform");
        sql.append(" from ").append("jmbi_lg_order o,jmbi_lg_company c").append(" where c.id = o.buyer_company_id ");
        if (StringUtils.isNotBlank(startDate)) {
            sql.append(StringUtils.getSqlCondition("o", Create_Time, StringUtils.formatDate(startDate), CompareOp.GREATER_OR_EQUAL));
        }
        if (StringUtils.isNotBlank(endDate)) {
            sql.append(StringUtils.getSqlCondition("o", Create_Time, StringUtils.formatDate(endDate), CompareOp.LESS_OR_EQUAL));
        }
        sql.append(" group by c.platform ").append("order by c.platform");
        List<Map<String, Object>> data = jdbcTemplate.queryForList(sql.toString());
        PieCharts pieCharts = PieCharts.setPieCharts(data, "platform", "订单来源", "订单来源");
        return Response.ok(pieCharts).build();
    }

}
