package org.jumao.bi.service.impl.trade.oerate;

import com.alibaba.fastjson.JSON;
import org.jumao.bi.dao.trade.oerate.IOperationOverviewDao;
import org.jumao.bi.service.trade.operate.IOperationSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/26.
 */
public class OperationSvcImpl implements IOperationSvc {

    //    ImpalaJDBC impalaJDBC = new ImpalaJDBC();
    @Autowired
    private IOperationOverviewDao operationOverviewDao;

    /**
     * 运营总览顶部6个统计图  http://localhost:8080/v1/operate/total
     */
    public String getTotal() {
        /*Map map = new HashMap();
        map.put("memberTotal",getMemberTotal());
        map.put("OpenStoreTotal",getOpenStoreTotal());
        map.put("ItemTotal",getItemTotal());
        map.put("OrderTotal",getOrderTotal());
        map.put("PayTotal",getPayTotal());
        map.put("TranTotal",getTranTotal());*/
        String SQL = "select count(*) from jmbi_uc_jumore_user\n" +
                "union all\n" +
                "SELECT COUNT(*) FROM jmbi_trade_shop_operate WHERE operate_type=0\n" +
                "union all\n" +
                "select count(*) from  jmbi_trade_goods\n" +
                "union all\n" +
                "select count(*) from jmbi_trade_order\n" +
                "union all\n" +
                "select count(*) from jmbi_ep_trade where status in (2,3,4)\n" +
                "union all\n" +
                "select sum(pay_amt) from jmbi_ep_trade  where status in (2,3,4)";
        List<Map<String, Object>> topTotal = operationOverviewDao.getTopTotal(SQL);
        List<Map<String, Object>> topTotal_ = new ArrayList();

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("MemberTotal", topTotal.get(0).get("count(*)"));
        topTotal_.add(map);

        Map<String, Object> map1 = new HashMap<String, Object>();
        map1.put("OpenStoreTotal", topTotal.get(1).get("count(*)"));
        topTotal_.add(map1);

        Map<String, Object> map2 = new HashMap<String, Object>();
        map2.put("ItemTotal", topTotal.get(2).get("count(*)"));
        topTotal_.add(map2);

        Map<String, Object> map3 = new HashMap<String, Object>();
        map3.put("OrderTotal", topTotal.get(3).get("count(*)"));
        topTotal_.add(map3);

        Map<String, Object> map4 = new HashMap<String, Object>();
        map4.put("PayTotal", topTotal.get(4).get("count(*)"));
        topTotal_.add(map4);

        Map<String, Object> map5 = new HashMap<String, Object>();
        map5.put("TranTotal", topTotal.get(5).get("count(*)"));
        topTotal_.add(map5);

        String jsonString = JSON.toJSONString(topTotal_);
        return jsonString;
    }


    /**
     * 查询累计会员数
     * @return
     */
    /*public String getMemberTotal() {
        String SQL = "select count(*) from jmbi_uc_jumore_user";
        return impalaJDBC.getTotal(SQL);
    }

    *//**
     * 查询累计店铺开通数
     *//*
    public String getOpenStoreTotal(){
        String SQL = "SELECT COUNT(*) FROM jmbi_trade_shop_operate WHERE operate_type=0";
        return impalaJDBC.getTotal(SQL);
    }

    *//**
     * 查询累计发布商品数
     * @return
     *//*
    public String getItemTotal() {
        String SQL = "select count(*) from  jmbi_trade_goods";
        return impalaJDBC.getTotal(SQL);
    }

    *//**
     * 查询所有状态订单数
     *//*
    public String getOrderTotal(){
        String SQL = "select count(*) from jmbi_trade_order count(*) from jmbi_trade_order";
        return impalaJDBC.getTotal(SQL);
    }

    *//**
     * 查询累计付款笔数
     * @return
     *//*
    public String getPayTotal(){
        String SQL = "select count(*) from jmbi_ep_trade where status in (2,3,4)";
        return impalaJDBC.getTotal(SQL);
    }

    */

    /**
     * 累计交易金额
     *//*
    public String getTranTotal(){
        String SQL = "select sum(pay_amt) from jmbi_ep_trade  where status in (2,3,4)";
        return impalaJDBC.getTotal(SQL);
    }*/
    public static void main(String[] args) {
//        String payTotal = new OperationSvcImpl().getMemberTotal();
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring/applicationContext-bigdata.xml");
        OperationSvcImpl operationSvcImpl = ctx.getBean(OperationSvcImpl.class);
        String total = operationSvcImpl.getTotal();
        System.out.println();
    }


}
