package org.jumao.bi.service.impl.trade.shop;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import org.jumao.bi.component.BaseChartBuilder;
import org.jumao.bi.component.BaseDaoCallback;
import org.jumao.bi.component.BaseDataExtract;
import org.jumao.bi.component.ComponentContext;
import org.jumao.bi.entites.ParamBean;
import org.jumao.bi.service.trade.shop.IShopAnalysisSvc;
import org.jumao.bi.utis.SpringContextUtil;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Administrator on 2017/5/3.
 */
public class ShopAnalysisSvcImpl extends BaseDataExtract implements IShopAnalysisSvc {

	@Autowired
	private BaseDaoCallback baseDaoCallback;
	
	private BaseChartBuilder baseChartBuilder;
	
	//设置环境参数
	private ComponentContext setCustomContext(String platform, String startDate, String endDate,String storeId,
			Boolean transPlatIdFlag,String sql,String seriesName,Class<?> returnSeriesType,
			Class<?> returnItemIdType){
		ComponentContext context = SpringContextUtil.getBean(ComponentContext.class);
    	ParamBean param = new ParamBean(platform,startDate,endDate,storeId);
		List<String> trasList = new ArrayList<String>();
    	trasList.add("startDate");
    	trasList.add("endDate");
    	
    	super.setContext(param, trasList, transPlatIdFlag, sql, seriesName, returnSeriesType, returnItemIdType, context);
    	return context;
	}
	@Override  
    public Response getTopGoodsCategories(String platform, String startDate, String endDate) throws Exception{
    	String sql = "SELECT c.comp_id as itemId,c.comp_name as itemName,count(DISTINCT g.goods_id) as itemValue"
    			+ " FROM jmbi_trade_goods g join jmbi_trade_company c on g.company_id=c.comp_id"
    			+ " AND g.create_time >= ':startDate' AND g.create_time <= ':endDate'"
    			+ " GROUP BY c.comp_id, c.comp_name"
    			+ " ORDER BY count(DISTINCT g.goods_id) DESC limit 5";
		ComponentContext context = this.setCustomContext(platform, startDate, endDate, null, false,
				sql, "店铺公司名", null,null);
    	baseChartBuilder = SpringContextUtil.getBean("barChartBuilder");
    	return super.extractDataAndBuildChart(baseDaoCallback,baseChartBuilder,context);
    }
    @Override
    public Response getGoodsIndustryRatio(String platform, String startDate, String endDate, String itemId) throws Exception{
    	String sql = "SELECT g.company_id itemId,g.industry_id as itemName,count(DISTINCT g.goods_id) as itemValue"
    			+ " FROM jmbi_trade_goods g"
    			+ " where g.company_id = :itemId"
    			+ " AND g.create_time >= ':startDate' AND g.create_time <= ':endDate'"
    			+ " GROUP BY g.company_id, g.industry_id";
		ComponentContext context = this.setCustomContext(platform, startDate, endDate, itemId, true, sql,
				null, Long.class,null);
    	baseChartBuilder = SpringContextUtil.getBean("pieChartBuilder");
    	return super.extractDataAndBuildChart(baseDaoCallback,baseChartBuilder,context);
    }

	@Override
	public Response getTopTransactionMoney(String platform, String startDate,
			String endDate) throws Exception {
		// TODO Auto-generated method stub
    	String sql = "SELECT o.store_id as itemId,c.comp_id,c.comp_name as itemName,sum(pay_money) as itemValue"
    			+ " FROM jmbi_trade_order o"
    			+ " INNER JOIN jmbi_trade_shop s ON o.store_id = s.shop_id"
    			+ " INNER JOIN jmbi_trade_company c ON s.comp_id = c.comp_id"
    			+ " WHERE o.order_state IN (20, 30, 40, 50, 70)"
    			+ " AND o.create_time >= ':startDate' AND o.create_time <= ':endDate'"
    			+ " GROUP BY o.store_id, c.comp_id,c.comp_name"
    			+ " ORDER BY sum(pay_money) DESC LIMIT 5";
		ComponentContext context = this.setCustomContext(platform, startDate, endDate, null, false,
				sql, "店铺公司名", null,null);
    	baseChartBuilder = SpringContextUtil.getBean("barChartBuilder");
    	return super.extractDataAndBuildChart(baseDaoCallback,baseChartBuilder,context);
	}

	@Override
	public Response getMoneyIndustryRatio(String platform, String startDate,
			String endDate, String itemId) throws Exception {
    	String sql = "SELECT o.store_id as itemId,o.industry_id as itemName,"
    			+ " round(sum(pay_money)/10000,2) as itemValue"
    			+ " FROM jmbi_trade_order o"
    			+ " Where o.order_state IN (20, 30, 40, 50, 70)"
    			+ " AND o.store_id IN (:itemId)"
    			+ " AND o.create_time >= ':startDate' AND o.create_time <= ':endDate'"
    			+ " GROUP BY o.store_id, o.industry_id";
		ComponentContext context = this.setCustomContext(platform, startDate, endDate, itemId, true, sql,
				null, BigDecimal.class,null);
    	baseChartBuilder = SpringContextUtil.getBean("pieChartBuilder");
    	return super.extractDataAndBuildChart(baseDaoCallback,baseChartBuilder,context);
	}
    
}