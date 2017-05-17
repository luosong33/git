package org.jumao.bi.service.impl.trade.shop;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.Response;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.jumao.bi.entites.AreaBarCharts;
import org.jumao.bi.entites.LineCharts;
import org.jumao.bi.service.trade.shop.IShopSvc;
import org.jumao.bi.utis.PlatFormUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class ShopSvcImpl implements IShopSvc {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Response openTrend(String startDate, String endDate) throws Exception {
        Map<String, BigDecimal> map = new HashMap<String, BigDecimal>();
        List<String> xAxisData = getxAxisDataList(startDate, endDate, map, 0);

        StringBuffer sql = new StringBuffer(
                "select  strleft(cast(operate_time as string),10) as operate_date, count(distinct shop_id) count from jmbi_trade_shop_operate where operate_type=0 ");
        if (StringUtils.isNotBlank(startDate)) {
            sql.append(" and operate_time >= '").append(this.formatDate(startDate) + " 00:00:00'");
        }

        if (StringUtils.isNotBlank(endDate)) {
            sql.append(" and operate_time <= '").append(this.formatDate(endDate) + " 23:59:59'");
        }
        sql.append(" group by  strleft(cast(operate_time as string),10)").append(" order by strleft(cast(operate_time as string),10)");
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql.toString());
        for (Map<String, Object> row : rows) {
            map.put(row.get("operate_date").toString(), NumberUtils.createBigDecimal(row.get("count").toString()));
        }

        // 构造数据
        List<BigDecimal> seriesData = new ArrayList<BigDecimal>();
        List<String> keyList = new ArrayList<String>(map.keySet());
        Collections.sort(keyList);
        for (String key1 : keyList) {
            seriesData.add(map.get(key1));
        }
        LineCharts lineChart = new LineCharts();
        lineChart.setxAxisData(xAxisData);
        lineChart.setSeriesData(seriesData);
        return Response.ok(lineChart).build();
    }

    private List<String> getxAxisDataList(String startDate, String endDate, Map<String, BigDecimal> map, int interval) {
        List<String> list = new ArrayList<String>();
        Date date1 = org.jumao.bi.utis.DateUtils.parseDate(startDate, "yyyyMMdd");
        Date date2 = org.jumao.bi.utis.DateUtils.parseDate(endDate, "yyyyMMdd");

        if (interval == 0) {
            while (date1.getTime() <= date2.getTime()) {
                map.put(org.jumao.bi.utis.DateUtils.dateToString(date1, "yyyy-MM-dd"), BigDecimal.ZERO);
                list.add(org.jumao.bi.utis.DateUtils.dateToString(date1, "yyyy年MM月dd日"));
                date1 = org.apache.commons.lang3.time.DateUtils.addDays(date1, 1);
            }
        } else {
            while (date1.getTime() <= date2.getTime()) {
                list.add(org.jumao.bi.utis.DateUtils.dateToString(date1, "yyyy年MM月"));
                map.put(org.jumao.bi.utis.DateUtils.dateToString(date1, "yyyyMM"), BigDecimal.ZERO);
                date1 = org.apache.commons.lang3.time.DateUtils.addMonths(date1, 1);
            }
        }
        return list;
    }

    public Response perfect(String startDate, String endDate) throws Exception {
        String subSql = "";
        if (StringUtils.isNotBlank(startDate)) {
            subSql += " and s.create_time >= '" + this.formatDate(startDate) + " 00:00:00'";
        }
        if (StringUtils.isNotBlank(endDate)) {
            subSql += " and s.create_time <= '" + this.formatDate(endDate) + " 23:59:59'";
        }
        List<BigDecimal> seriesData = new ArrayList<BigDecimal>();
        StringBuffer sql = new StringBuffer();
        Long count = 0L;
        // 企业动态
        sql.append("SELECT count(*) FROM ").append(" jmbi_trade_shop s, jmbi_trade_comp_dynamic d,  jmbi_trade_company c ")
                .append(" where s.comp_id=c.comp_id and d.comp_id=c.comp_id").append(subSql);
        count = jdbcTemplate.queryForObject(sql.toString(), Long.class);
        seriesData.add(BigDecimal.valueOf(count));
        // 产品分类
        sql.setLength(0);
        sql.append("SELECT count(*) FROM ").append(" jmbi_trade_shop s, jmbi_trade_shop_goods_category c ")
                .append(" WHERE s.shop_id = c.shop_id ").append(subSql);
        count = jdbcTemplate.queryForObject(sql.toString(), Long.class);
        seriesData.add(BigDecimal.valueOf(count));
        // 企业介绍
        sql.setLength(0);
        sql.append("select count(distinct s.shop_id) from ").append(" jmbi_trade_shop s").append(" WHERE s.summary is not NULL")
                .append(subSql);
        count = jdbcTemplate.queryForObject(sql.toString(), Long.class);
        seriesData.add(BigDecimal.valueOf(count));

        // 背景图
        sql.setLength(0);
        sql.append("select count(distinct s.shop_id) from ").append(" jmbi_trade_shop s").append(" WHERE s.background is not NULL")
                .append(subSql);
        count = jdbcTemplate.queryForObject(sql.toString(), Long.class);
        seriesData.add(BigDecimal.valueOf(count));

        // LOGO
        sql.setLength(0);
        sql.append("select count(distinct s.shop_id) from ").append(" jmbi_trade_shop s").append(" WHERE s.logo is not NULL")
                .append(subSql);
        count = jdbcTemplate.queryForObject(sql.toString(), Long.class);
        seriesData.add(BigDecimal.valueOf(count));

        List<String> yAxisData = new ArrayList<String>(Arrays.asList("企业动态", "产品分类", "企业介绍", "背景图", "LOGO"));
        AreaBarCharts charts = new AreaBarCharts();
        charts.setyAxisData(yAxisData);
        charts.setSeriesData(seriesData);
        return Response.ok(charts).build();
    }

    public Response releaseRank(String startDate, String endDate, String platform) throws Exception {
        StringBuffer sql = new StringBuffer("select company_id,company_name, count(DISTINCT goods_id) as total ");
        sql.append(" from jmbi_trade_goods ").append(" where 1=1 ");
        if (StringUtils.isNotBlank(startDate)) {
            sql.append(" and create_time >= '").append(this.formatDate(startDate) + " 00:00:00'");
        }

        if (StringUtils.isNotBlank(endDate)) {
            sql.append(" and create_time <= '").append(this.formatDate(endDate) + " 23:59:59'");
        }

        if (StringUtils.isNotBlank(platform)) {
            sql.append(" and industry_id = ").append(PlatFormUtil.getPlatformV(platform));
        }
        sql.append(" group by company_id,company_name ").append(" ORDER BY total DESC ").append(" limit 5");
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql.toString());
        List<BigDecimal> seriesData = new ArrayList<BigDecimal>();
        List<String> yAxisData = new ArrayList<String>();
        for (Map<String, Object> row : rows) {
            yAxisData.add(row.get("company_name").toString());
            seriesData.add(NumberUtils.createBigDecimal(row.get("total").toString()));
        }

        AreaBarCharts charts = new AreaBarCharts();
        charts.setTitle("商品发布数排行");
        charts.setyAxisData(yAxisData);
        charts.setSeriesData(seriesData);
        return Response.ok(charts).build();
    }

    public Response categoryRank(String startDate, String endDate) throws Exception {
        StringBuffer sql = new StringBuffer("SELECT g.shop_id, count(DISTINCT shop_cate_id) total,comp_name ");
        sql.append(" FROM jmbi_trade_shop_goods_category g,jmbi_trade_shop p,jmbi_trade_company c ").append(
                " where g.shop_id = p.shop_id AND p.comp_id = c.comp_id AND g.delete_flag = 0 ");
        if (StringUtils.isNotBlank(startDate)) {
            sql.append(" and g.create_time >= '").append(this.formatDate(startDate) + " 00:00:00'");
        }

        if (StringUtils.isNotBlank(endDate)) {
            sql.append(" and g.create_time <= '").append(this.formatDate(endDate) + " 23:59:59'");
        }
        sql.append(" group by g.shop_id,c.comp_name ").append(" ORDER BY total DESC ").append(" limit 5");
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql.toString());
        List<BigDecimal> seriesData = new ArrayList<BigDecimal>();
        List<String> yAxisData = new ArrayList<String>();
        for (Map<String, Object> row : rows) {
            yAxisData.add(row.get("comp_name").toString());
            seriesData.add(NumberUtils.createBigDecimal(row.get("total").toString()));
        }

        AreaBarCharts charts = new AreaBarCharts();
        charts.setTitle("店铺分类排行");
        charts.setyAxisData(yAxisData);
        charts.setSeriesData(seriesData);
        return Response.ok(charts).build();
    }

    public Response dealRank(String startDate, String endDate, String platform) throws Exception {
        StringBuffer sql = new StringBuffer("select store_id, store_name, sum(pay_money) as total ");
        sql.append(" from jmbi_trade_order ").append(" where order_state<>10 ");
        if (StringUtils.isNotBlank(startDate)) {
            sql.append(" and create_time >= '").append(this.formatDate(startDate) + " 00:00:00'");
        }

        if (StringUtils.isNotBlank(endDate)) {
            sql.append(" and create_time <= '").append(this.formatDate(endDate) + " 23:59:59'");
        }

        if (StringUtils.isNotBlank(platform)) {
            sql.append(" and industry_id = ").append(PlatFormUtil.getPlatformV(platform));
        }
        sql.append(" group by store_id,store_name ").append(" ORDER BY total DESC ").append(" limit 5");
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql.toString());
        List<BigDecimal> seriesData = new ArrayList<BigDecimal>();

        List<String> yAxisData = new ArrayList<String>();
        for (Map<String, Object> row : rows) {
            yAxisData.add(row.get("store_name").toString());
            seriesData.add(NumberUtils.createBigDecimal(row.get("total").toString()));
        }

        AreaBarCharts charts = new AreaBarCharts();
        charts.setTitle("店铺成交排行");
        charts.setyAxisData(yAxisData);
        charts.setSeriesData(seriesData);
        return Response.ok(charts).build();
    }

    private String formatDate(String date) {
        if (StringUtils.isNotBlank(date)) {
            date = StringUtils.left(date, 4) + "-" + StringUtils.substring(date, 4, 6) + "-" + StringUtils.right(date, 2);
        }
        return date;
    }

    public Response releaseList(String startDate, String endDate, String platform) throws Exception {
        StringBuffer sql = new StringBuffer(
                "select g.company_id,g.company_name,CASE c.comp_category WHEN 1 THEN '工厂' WHEN 2 THEN '贸易商' WHEN 3 THEN '工贸一体' ELSE  '其他' END AS 'gsxz',count(DISTINCT g.goods_id) as total");
        sql.append(" from jmbi_trade_goods g,jmbi_trade_company c  ").append(" where g.company_id = c.comp_id ");
        if (StringUtils.isNotBlank(startDate)) {
            sql.append(" and g.create_time >= '").append(this.formatDate(startDate) + " 00:00:00'");
        }

        if (StringUtils.isNotBlank(endDate)) {
            sql.append(" and g.create_time <= '").append(this.formatDate(endDate) + " 23:59:59'");
        }

        if (StringUtils.isNotBlank(platform)) {
            sql.append(" and g.industry_id = ").append(PlatFormUtil.getPlatformV(platform));
        }
        sql.append(" group by company_id,company_name,gsxz ").append(" ORDER BY total DESC ").append(" limit 10");
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql.toString());
        return Response.ok(rows).build();
    }

    public Response categoryList(String startDate, String endDate) throws Exception {
        String subSql = "";
        if (StringUtils.isNotBlank(startDate)) {
            subSql += " and g.create_time >= '" + this.formatDate(startDate) + " 00:00:00'";
        }
        if (StringUtils.isNotBlank(endDate)) {
            subSql += " and g.create_time <= '" + this.formatDate(endDate) + " 23:59:59'";
        }
        StringBuffer sql = new StringBuffer(
                "SELECT CASE c.comp_category WHEN 1 THEN '工厂' WHEN 2 THEN '贸易商' ELSE  '工贸一体' END AS gsxz, g.shop_id, count(DISTINCT g.shop_cate_id) shop_cate_sum, c.comp_name, c.comp_id");
        sql.append(" from jmbi_trade_shop_goods_category g, jmbi_trade_shop p, jmbi_trade_company c")
                .append(" WHERE g.shop_id = p.shop_id AND p.comp_id = c.comp_id AND g.delete_flag = 0 ").append(subSql)
                .append(" GROUP BY g.shop_id, c.comp_name, c.comp_id,gsxz").append(" ORDER BY shop_cate_sum DESC").append(" limit 10");
        List<Map<String, Object>> cRows = jdbcTemplate.queryForList(sql.toString());// 公司top10
        sql.setLength(0);
        sql.append("SELECT count(goods_id) goods_num, g.company_id, c.category_name category_name")
                .append(" FROM jmbi_trade_shop_goods_category c")
                .append(" INNER JOIN jmbi_trade_goods g ON c.shop_cate_id = g.goods_category_grade1_id").append(subSql)
                .append(" GROUP BY g.company_id,c.shop_cate_id,c.category_name  ").append("order by g.company_id,goods_num DESC");
        List<Map<String, Object>> dRows = jdbcTemplate.queryForList(sql.toString());// 店铺每个分类下的商品数
        Map<String, String> cateMap = new HashMap<String, String>();
        String cateName = null;// 分类名称
        String companyId = null;// 店铺ID
        String tmp = null;
        for (Map<String, Object> map : dRows) {
            companyId = map.get("company_id").toString();
            cateName = map.get("category_name").toString();
            tmp = cateMap.get(companyId);
            if (StringUtils.isBlank(tmp)) {
                cateMap.put(companyId, cateName);
            } else {
                if (tmp.split(",").length < 3) {
                    cateMap.put(companyId, tmp + "," + cateName);
                }
            }
        }

        for (Map<String, Object> map : cRows) {
            map.put("category_name", cateMap.get(map.get("comp_id").toString()));
        }

        return Response.ok(cRows).build();
    }

    public Response dealList(String startDate, String endDate, String platform) throws Exception {
        String subSql = "";
        if (StringUtils.isNotBlank(startDate)) {
            subSql += " and o.create_time >= '" + this.formatDate(startDate) + " 00:00:00'";
        }
        if (StringUtils.isNotBlank(endDate)) {
            subSql += " and o.create_time <= '" + this.formatDate(endDate) + " 23:59:59'";
        }
        if (StringUtils.isNotBlank(platform)) {
            subSql += " and g.industry_id = " + PlatFormUtil.getPlatformV(platform);
        }
        StringBuffer sql = new StringBuffer(
                "SELECT o.store_id, c.comp_id, c.comp_name,sum(pay_money) AS money_total, count(DISTINCT o.order_id) order_num, CASE c.comp_category WHEN 1 THEN '工厂' WHEN 2 THEN '贸易商' WHEN 3 THEN '工贸一体' ELSE  '其他' END AS gsxz");
        sql.append(" FROM jmbi_trade_order o ").append(" INNER JOIN jmbi_trade_shop s ON o.store_id = s.shop_id ")
                .append(" INNER JOIN jmbi_trade_company c ON s.comp_id = c.comp_id ")
                .append(" WHERE  o.order_state IN (20, 30, 40, 50, 70) ").append(subSql)
                .append(" GROUP BY o.store_id, c.comp_id, c.comp_name,gsxz").append(" ORDER BY money_total DESC ").append(" LIMIT 10");
        List<Map<String, Object>> cRows = jdbcTemplate.queryForList(sql.toString());// 公司top10
        sql.setLength(0);
        sql.append("SELECT g.company_id,sum(o.pay_money) pay_menoy_total,d.goods_name").append(" FROM jmbi_trade_order o")
                .append(" INNER JOIN jmbi_trade_order_detail d ON o.order_id = d.order_id")
                .append(" INNER JOIN jmbi_trade_goods g ON d.goods_id = g.goods_id").append(" WHERE o.order_state IN (20, 30, 40, 50, 70)")
                .append(subSql).append(" GROUP BY g.company_id, d.goods_name").append(" ORDER BY g.company_id,pay_menoy_total DESC");

        List<Map<String, Object>> dRows = jdbcTemplate.queryForList(sql.toString());// 店铺每个分类下的商品数
        Map<String, String> cateMap = new HashMap<String, String>();
        String goodsName = null;// 商品名称
        String companyId = null;// 公司ID
        String tmp = null;
        for (Map<String, Object> map : dRows) {
            companyId = map.get("company_id").toString();
            if (map.get("goods_name") == null) {
                continue;
            }
            goodsName = map.get("goods_name").toString();
            tmp = cateMap.get(companyId);
            if (StringUtils.isBlank(tmp)) {
                cateMap.put(companyId, goodsName);
            } else {
                if (tmp.split(",").length < 3) {
                    cateMap.put(companyId, tmp + "," + goodsName);
                }
            }
        }

        for (Map<String, Object> map : cRows) {
            map.put("goods_name", cateMap.get(map.get("comp_id").toString()));
        }

        return Response.ok(cRows).build();
    }
}