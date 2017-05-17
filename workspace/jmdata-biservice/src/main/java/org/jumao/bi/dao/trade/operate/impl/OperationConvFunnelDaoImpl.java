package org.jumao.bi.dao.trade.operate.impl;

import java.util.ArrayList;
import java.util.List;

import org.jumao.bi.dao.trade.operate.IOperationConvFunnelDao;
import org.jumao.bi.entites.ParamBean;
import org.jumao.bi.entites.operate.ConvFunnelInfo;
import org.jumao.commons.frameworks.jmframework.hbaseutil.dao.IHBaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository("operationConvFunnelDao")
public class OperationConvFunnelDaoImpl  implements IOperationConvFunnelDao {

    @Autowired
    IHBaseDao hbasedao;
    
    @Autowired
    JdbcTemplate jdbcTemplate;
    
    public List<ConvFunnelInfo> getConvFunnel(ParamBean param) throws Exception{
		// TODO Auto-generated method stub
    	String wholeSql = "select count(distinct companyid)"
    			+ " from jmbi_user_traces t"
    			+ " where strleft(t.logintime,8)  between '" + param.getStartDate()
    			+ "' and '" + param.getEndDate()
    			+ "' and t.bizcode = '" + param.getPlatform()
    			+ "' and t.companyid<>'0'";
    	String listSql = "";
    	String detailSql = "";
    	String cartSql = "select count(DISTINCT u.comp_id) from jmbi_trade_cart u"
    			+ " where cast(u.comp_id as string) in ("
    			+ " select distinct companyid"
    			+ " from jmbi_user_traces t"
    			+ " where strleft(t.logintime,8)  between '" + param.getStartDate()
    			+ "' and '" + param.getEndDate()
    			+ "' and t.bizcode = '" + param.getPlatform() 
    			+ "' and t.companyid<>'0')";
//    	String cartSql = "";
    	String orderCreatedSql = "select count(DISTINCT c.comp_id)"
    			+ " from jmbi_trade_order o ,jmbi_trade_company c ,jmbi_trade_center_user u"
    			+ "  where o.member_id=u.user_id and u.company_id=c.comp_id "
    			+ " and cast(u.company_id  as string) in ("
    			+ " select distinct companyid"
    			+ " from jmbi_user_traces t"
    			+ " where strleft(t.logintime,8)  between '" + param.getStartDate()
    			+ "' and '" + param.getEndDate()
    			+ "' and t.bizcode = '" + param.getPlatform()
    			+ "' and t.companyid<>'0')";
    	String orderPayedSql = "select count(DISTINCT c.comp_id)"
    			+ " from jmbi_trade_order o ,jmbi_trade_company c ,jmbi_trade_center_user u"
    			+ "  where o.member_id=u.user_id and u.company_id=c.comp_id"
    			+ " and o.order_state in (40,50,70)"
    			+ " and cast(u.company_id  as string) in ("
    			+ " select distinct companyid"
    			+ " from jmbi_user_traces t"
    			+ " where strleft(t.logintime,8)  between '" + param.getStartDate()
    			+ "' and '" + param.getEndDate()
    			+ "' and t.bizcode = '" + param.getPlatform()
    			+ "' and t.companyid<>'0')";;
    	String shipSql = "select count(DISTINCT c.comp_id)"
    			+ " from jmbi_trade_order o ,jmbi_trade_company c ,jmbi_trade_center_user u"
    			+ "  where o.member_id=u.user_id and u.company_id=c.comp_id"
    			+ " and o.order_state in (50,70)"
    			+ " and cast(u.company_id  as string) in ("
    			+ " select distinct companyid"
    			+ " from jmbi_user_traces t"
    			+ " where strleft(t.logintime,8)  between '" + param.getStartDate()
    			+ "' and '" + param.getEndDate()
    			+ "' and t.bizcode = '" + param.getPlatform()
    			+ "' and t.companyid<>'0')";
    	String receiptSql = "select count(DISTINCT c.comp_id)"
    			+ " from jmbi_trade_order o ,jmbi_trade_company c ,jmbi_trade_center_user u"
    			+ "  where o.member_id=u.user_id and u.company_id=c.comp_id"
    			+ " and o.order_state=70"
    			+ " and cast(u.company_id  as string) in ("
    			+ " select distinct companyid"
    			+ " from jmbi_user_traces t"
    			+ " where strleft(t.logintime,8)  between '" + param.getStartDate()
    			+ "' and '" + param.getEndDate()
    			+ "' and t.bizcode = '" + param.getPlatform()
    			+ "' and t.companyid<>'0')";
    	
    	Integer wholeUV = jdbcTemplate.queryForObject(wholeSql, Integer.class);
//    	Integer listUV = jdbcTemplate.queryForObject(listSql, Integer.class);
//    	Integer detailUV = jdbcTemplate.queryForObject(detailSql, Integer.class);
    	Integer cartUV = jdbcTemplate.queryForObject(cartSql, Integer.class);
//    	Integer wholeUV = 0;
    	Integer listUV = 0;
    	Integer detailUV = 0;
//    	Integer cartUV = 0;
        Integer orderCreatedUV = jdbcTemplate.queryForObject(orderCreatedSql, Integer.class);
        Integer orderPayedUV = jdbcTemplate.queryForObject(orderPayedSql, Integer.class);
        Integer shipUV = jdbcTemplate.queryForObject(shipSql, Integer.class);
        Integer receiptUV = jdbcTemplate.queryForObject(receiptSql, Integer.class);
        
        List<ConvFunnelInfo> convFunnelList = new ArrayList<ConvFunnelInfo>();
        
        //收集各种uv到list里
        collectItem("整站UV",wholeUV,convFunnelList);
        collectItem("列表UV",listUV,convFunnelList);
        collectItem("详情UV",detailUV,convFunnelList);
        collectItem("购物车UV",cartUV,convFunnelList);
        collectItem("下单UV",orderCreatedUV,convFunnelList);
        collectItem("订单支付UV",orderPayedUV,convFunnelList);
        collectItem("确认发货UV",shipUV,convFunnelList);
        collectItem("确认收货UV",receiptUV,convFunnelList);
        
        return convFunnelList;
	}
    
    private void collectItem(String itemName,Integer itemValue,List<ConvFunnelInfo> list){
    	ConvFunnelInfo info = new ConvFunnelInfo();
    	info.setItemName(itemName);
    	info.setItemValue(itemValue);
    	list.add(info);
    }

}