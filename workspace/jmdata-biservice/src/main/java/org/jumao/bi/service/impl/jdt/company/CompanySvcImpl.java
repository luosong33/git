package org.jumao.bi.service.impl.jdt.company;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.jumao.bi.constant.ServiceConst;
import org.jumao.bi.dao.jdt.company.ICompanyDao;
import org.jumao.bi.entites.AreaBarCharts;
import org.jumao.bi.entites.BarChartsResult;
import org.jumao.bi.entites.BarSeries;
import org.jumao.bi.entites.CompanyAmountBean;
import org.jumao.bi.entites.CompanyNumBean;
import org.jumao.bi.entites.ResponseResult;
import org.jumao.bi.entites.ResultList;
import org.jumao.bi.service.jdt.company.ICompanySvc;
import org.jumao.bi.utis.DateUtils;
import org.jumao.commons.frameworks.jmframework.commutil.CalendarUtils;
import org.springframework.beans.factory.annotation.Autowired;

public class CompanySvcImpl implements ICompanySvc {

	@Autowired
	ICompanyDao companyDao;

	private Logger logger = Logger.getLogger(this.getClass());

	// =============企业分类统计：新增企业、活动企业数量=================
	/**
	 * 企业分类统计：企业新增数：按照周、月统计{都是按照天显示}
	 */
	private static final String dayAddCompanyNum = "jmbi:dayAddCompanyNum";

	/**
	 * 企业分类统计：企业新增数：按照季、年统计{都是按照月显示}
	 */
	private static final String monthAddCompanyNum = "jmbi:monthAddCompanyNum";

	/**
	 * 企业分类统计：企业活跃数：按照周、月统计{都是按照天显示}
	 */
	private static final String dayActivityCompanyNum = "jmbi:dayActivityCompanyNum";

	/**
	 * 企业分类统计：企业活跃数：按照季、年统计{都是按照月显示}
	 */
	private static final String monthActivityCompanyNum = "jmbi:monthActivityCompanyNum";
	// =============企业分类统计：新增企业、活动企业数量=================

	//

	//

	// =============企业分类统计：几个top统计=================
	/**
	 * 企业分类统计：客户企业订单数top
	 */
	private static final String customCompanyOrderNum = "jmbi:customCompanyOrderNum";

	/**
	 * 企业分类统计：拍档企业订单数top
	 */
	private static final String partnerCompanyOrderNum = "jmbi:partnerCompanyOrderNum";

	/**
	 * 企业分类统计：客户企业支出top
	 */
	private static final String customCompanyBillAmount = "jmbi:customCompanyBillAmount";

	/**
	 * 企业分类统计：拍档企业收入top
	 */
	private static final String partnerCompanyBillAmount = "jmbi:partnerCompanyBillAmount";

	/**
	 * 企业分类统计：客户企业:订单货值top
	 */
	private static final String customCompanyOrderAmount = "jmbi:customCompanyOrderAmount";

	/**
	 * 企业分类统计：拍档企业:订单货值top
	 */
	private static final String partnerCompanyOrderAmount = "jmbi:partnerCompanyOrderAmount";

	public Response getCompanyAmount(String timeType, String startDate,
			String endDate, String customsCode, String chart) {
		if (logger.isDebugEnabled()) {
			logger.debug("start getCompanyAmount>>timeType: " + timeType
					+ " startDate:" + startDate + " endDate:" + endDate
					+ " customsCode:" + customsCode + " chart:" + chart);
		}
		if (null == customsCode || "all".equals(customsCode)) {// 当前端选择关区选全国时，这里设置为空字符，后面查询
			customsCode = "";
		}
		AreaBarCharts areaBarCharts = new AreaBarCharts();
		boolean validRequest = (null != timeType && null != startDate && null != endDate);
		if (!validRequest) {
			areaBarCharts.setStatus(buildResponseResult(
					ServiceConst.INVADLID_REQUEST_CODE,
					ServiceConst.REQUEST_MSG));
			return Response.serverError().entity(areaBarCharts).build();
		}

		String toDate = endDate;// hbase查询：结束时间：后退一天
		toDate = CalendarUtils.nextDateStr(endDate);
		String title = "";// 图表：名称
		String tableName = "";// 查询的图表：{企业分类统计的}1、客户企业订单数top；；2、拍档企业订单数top；；
								// 3、客户企业支出top
								// 4、拍档企业收入top；；5、客户企业：订单货值top；；6、拍档企业:订单货值top
		int point = 4;// 保留几位小数;统计订单数量是保留0位小数；收入、支出保留0位。其他都是4位
		if ("1".equals(chart)) {
			tableName = this.customCompanyOrderNum;
			title = "客户企业订单数TOP5";
			point = 0;
		} else if ("2".equals(chart)) {
			tableName = this.partnerCompanyOrderNum;
			title = "拍档企业订单数TOP5";
			point = 0;
		} else if ("3".equals(chart)) {
			tableName = this.customCompanyBillAmount;
			title = "客户企业支出TOP5";
			point = 0;
		} else if ("4".equals(chart)) {
			tableName = this.partnerCompanyBillAmount;
			title = "拍档企业收入TOP5";
			point = 0;
		} else if ("5".equals(chart)) {
			tableName = this.customCompanyOrderAmount;
			title = "客户企业订单货值TOP5";
		} else if ("6".equals(chart)) {
			tableName = this.partnerCompanyOrderAmount;
			title = "拍档企业订单货值TOP5";
		}
		String startKey = startDate + "_" + customsCode;
		String endKey = toDate + "_" + customsCode;

		List<CompanyAmountBean> list;
		try {
			list = companyDao.retrieveCompanyTopByPeriod(startKey, endKey,
					tableName, point, customsCode);
			List<CompanyAmountBean> toplist = new ArrayList<CompanyAmountBean>();
			// if ("week".equals(type)) {
			toplist = buildPeriodCompany(list, "not", point, chart);
			// }
			// 条形图：展示TOP5

			// charts = buildCompanyAreaCharts(weekMap, title);
			areaBarCharts = buildAreaBarChartsCompany(toplist, title);
			areaBarCharts.setStatus(buildResponseResult(
					ServiceConst.SUCCESS_CODE, ServiceConst.SUCCESS_MSG));
			return Response.ok().entity(areaBarCharts).build();

		} catch (IOException e) {
			if (logger.isDebugEnabled()) {
				logger.debug("index error: " + e.getMessage());
			}
			areaBarCharts.setStatus(buildResponseResult(
					ServiceConst.SERVER_ERROR_CODE, ServiceConst.ERROR_MSG));
			return Response.serverError().entity(areaBarCharts).build();
		}

	}

	public Response getCompanyAmountExport(String timeType, String startDate,
			String endDate, String customsCode, String chart) {
		if (logger.isDebugEnabled()) {
			logger.debug("start getCompanyAmountExport>>timeType: " + timeType
					+ " startDate:" + startDate + " endDate:" + endDate
					+ " customsCode:" + customsCode + " chart:" + chart);
		}
		if (null == customsCode || "all".equals(customsCode)) {// 当前端选择关区选全国时，这里设置为空字符，后面查询
			customsCode = "";
		}
		ResultList<CompanyAmountBean> result = new ResultList<CompanyAmountBean>();
		boolean validRequest = (null != timeType && null != startDate && null != endDate);
		if (!validRequest) {
			result.setStatus(buildResponseResult(
					ServiceConst.INVADLID_REQUEST_CODE,
					ServiceConst.REQUEST_MSG));
			return Response.serverError().entity(result).build();
		}

		String toDate = endDate;// hbase查询：结束时间：后退一天
		toDate = CalendarUtils.nextDateStr(endDate);
		String title = "";// 图表：名称
		String tableName = "";// 查询的图表：{企业分类统计的}1、客户企业订单数top；；2、拍档企业订单数top；；
								// 3、客户企业支出top
								// 4、拍档企业收入top；；5、客户企业：订单货值top；；6、拍档企业:订单货值top
		int point = 4;// 保留几位小数;只有统计订单数量是保留0位小数；收入、支出保留0位。其他都是4位
		if ("1".equals(chart)) {
			tableName = this.customCompanyOrderNum;
			title = "客户企业订单数TOP5";
			point = 0;
		} else if ("2".equals(chart)) {
			tableName = this.partnerCompanyOrderNum;
			title = "拍档企业订单数TOP5";
			point = 0;
		} else if ("3".equals(chart)) {
			tableName = this.customCompanyBillAmount;
			title = "客户企业支出TOP5";
			point = 0;
		} else if ("4".equals(chart)) {
			tableName = this.partnerCompanyBillAmount;
			title = "拍档企业收入TOP5";
			point = 0;
		} else if ("5".equals(chart)) {
			tableName = this.customCompanyOrderAmount;
			title = "客户企业订单货值TOP5";
		} else if ("6".equals(chart)) {
			tableName = this.partnerCompanyOrderAmount;
			title = "拍档企业订单货值TOP5";
		}
		String startKey = startDate + "_" + customsCode;
		String endKey = toDate + "_" + customsCode;

		List<CompanyAmountBean> list;
		try {
			list = companyDao.retrieveCompanyTopByPeriod(startKey, endKey,
					tableName, point, customsCode);
			List<CompanyAmountBean> toplist = new ArrayList<CompanyAmountBean>();
			// if ("week".equals(type)) {
			toplist = buildPeriodCompany(list, "export", point, chart);
			// }

			// 导出全部top

			result.setStatus(buildResponseResult(ServiceConst.SUCCESS_CODE,
					ServiceConst.SUCCESS_MSG));
			result.setList(toplist);
			return Response.ok().entity(toplist).build();// 导出返回List,

		} catch (IOException e) {
			if (logger.isDebugEnabled()) {
				logger.debug("index error: " + e.getMessage());
			}
			result.setStatus(buildResponseResult(
					ServiceConst.SERVER_ERROR_CODE, ServiceConst.ERROR_MSG));
			return Response.serverError().entity(result).build();
		}

	}

	/**
	 * 构建 条形图
	 * 
	 * @param toplist
	 * @param title
	 * @return
	 */
	private AreaBarCharts buildAreaBarChartsCompany(
			List<CompanyAmountBean> toplist, String title) {
		AreaBarCharts charts = new AreaBarCharts();

		List<String> yAxisData = new ArrayList<String>();
		//
		List<BigDecimal> seriesData = new ArrayList<BigDecimal>();

		if (toplist.size() > 0) {//
			for (int i = 0; i < toplist.size(); i++) {
				yAxisData.add(toplist.get(i).getCompanyName());
				seriesData.add(toplist.get(i).getTotalAmount());
			}
		}

		charts.setTitle(title);
		charts.setSeriesName("");
		charts.setyAxisData(yAxisData);
		charts.setSeriesData(seriesData);
		return charts;
	}

	/**
	 * 
	 * @param list
	 * @param isExport
	 *            :: export导出数据，不光取前10
	 * @param point
	 *            :: 保留几位小数
	 * @return
	 * @throws IOException
	 */
	private List<CompanyAmountBean> buildPeriodCompany(
			List<CompanyAmountBean> list, String isExport, int point,
			String chart) throws IOException {
		// 排序：从hbase里面查询出来的是乱序
		Collections.sort(list, new Comparator<CompanyAmountBean>() {
			public int compare(CompanyAmountBean arg0, CompanyAmountBean arg1) {
				return arg1.getTotalAmount().compareTo(arg0.getTotalAmount());
			}
		});

		List<CompanyAmountBean> topList = new ArrayList<CompanyAmountBean>();
		int i = 0;
		BigDecimal div = new BigDecimal(10000);
		for (CompanyAmountBean deal : list) {
			if (!"export".equals(isExport)) {// 导出数据，不光取前10
				if (i == 5) {
					break;// 只取TOP5
				}
			}
			i = i + 1;
			// 根据图表是否转换成万元、万美元
			if ("1".equals(chart) || "2".equals(chart)) {
				deal.setTotalAmount(deal.getTotalAmount().setScale(point,
						BigDecimal.ROUND_HALF_UP));// 保留几位小数
			} else if ("3".equals(chart) || "4".equals(chart)) {// 转化成：万元
				deal.setTotalAmount((deal.getTotalAmount().divide(div))
						.setScale(point, BigDecimal.ROUND_HALF_UP));// 保留几位小数
			} else if ("5".equals(chart) || "6".equals(chart)) {// 转化成：万美元
				deal.setTotalAmount((deal.getTotalAmount().divide(div))
						.setScale(point, BigDecimal.ROUND_HALF_UP));// 保留几位小数
			}
			deal.setIndex(i);
			topList.add(deal);
		}
		return topList;
	}

	// =============企业分类统计：几个top统计=================

	//

	//

	// =============企业分类统计：新增企业、活动企业数量=================
	public Response getCompanyNum(String timeType, String startDate,
			String endDate, String chart) {
		if (logger.isDebugEnabled()) {
			logger.debug("start getCompanyNum>>timeType: " + timeType
					+ " startDate:" + startDate + " endDate:" + endDate
					+ " chart:" + chart);
		}
		BarChartsResult areaBarCharts = new BarChartsResult();
		boolean validRequest = (null != timeType && null != startDate && null != endDate);
		if (!validRequest) {
			areaBarCharts.setStatus(buildResponseResult(
					ServiceConst.INVADLID_REQUEST_CODE,
					ServiceConst.REQUEST_MSG));
			return Response.serverError().entity(areaBarCharts).build();
		}

		String toDate = endDate;// hbase查询：结束时间：后退一天

		toDate = DateUtils.getToday(); // 前端要求数据T-1显示；所以超过昨天的都不显示
		String title = "";// 图表：名称
		String tableName = "";// 查询的图表：{企业分类统计的}{企业分类统计的}1、企业新增数；；2、企业活跃数
		int point = 0;// 保留几位小数;统计企业数量保留0位小数。

		if ("1".equals(chart)) {
			// 时间类型：week、month、quarter、year {2个表，一个是天统计，一个是月统计}
			if ("week".equals(timeType) || "month".equals(timeType)) {
				tableName = this.dayAddCompanyNum;
			} else if ("quarter".equals(timeType) || "year".equals(timeType)) {
				tableName = this.monthAddCompanyNum;
				startDate = startDate.substring(0, 6);// 季度、年的查询：rowkey只到月
				toDate = toDate.substring(0, 6) + "0";// 季度、年的查询：rowkey只到月+++加一位0，把本月的也查询出来
			}
			title = "企业新增数";
		} else if ("2".equals(chart)) {
			// 时间类型：week、month、quarter、year {2个表，一个是天统计，一个是月统计}
			if ("week".equals(timeType) || "month".equals(timeType)) {
				tableName = this.dayActivityCompanyNum;
			} else if ("quarter".equals(timeType) || "year".equals(timeType)) {
				tableName = this.monthActivityCompanyNum;
				startDate = startDate.substring(0, 6);// 季度、年的查询：rowkey只到月
				toDate = toDate.substring(0, 6) + "0";// 季度、年的查询：rowkey只到月+++加一位0，把本月的也查询出来
			}
			title = "企业活跃数";
		}

		String identity_parter = "1";// '身份：0客户，1拍档，2金融机构'
		String identity_custem = "0";// '身份：0客户，1拍档，2金融机构'
		// 查询：客户企业数
		String startKey_custem = identity_custem + "_" + startDate;
		String endKey_custem = identity_custem + "_" + toDate;
		// 查询：拍档企业数
		String startKey_parter = identity_parter + "_" + startDate;
		String endKey_parter = identity_parter + "_" + toDate;

		List<CompanyNumBean> list_parter;
		List<CompanyNumBean> list_custem;

		try {
			list_custem = companyDao.retrieveCompanyNumByPeriod(
					startKey_custem, endKey_custem, tableName, point, timeType);
			//
			list_parter = companyDao.retrieveCompanyNumByPeriod(
					startKey_parter, endKey_parter, tableName, point, timeType);

			List<CompanyNumBean> toplist_custem = new ArrayList<CompanyNumBean>();
			List<CompanyNumBean> toplist_parter = new ArrayList<CompanyNumBean>();
			// if ("week".equals(type)) {
			toplist_custem = buildPeriodCompanyNum(list_custem, "not", point,
					timeType);
			toplist_parter = buildPeriodCompanyNum(list_parter, "not", point,
					timeType);
			// }
			// 条形图：展示TOP5

			// charts = buildCompanyAreaCharts(weekMap, title);
			areaBarCharts = buildAreaBarChartsCompanyNum(toplist_custem,
					toplist_parter, title);
			areaBarCharts.setStatus(buildResponseResult(
					ServiceConst.SUCCESS_CODE, ServiceConst.SUCCESS_MSG));
			return Response.ok().entity(areaBarCharts).build();

		} catch (IOException e) {
			if (logger.isDebugEnabled()) {
				logger.debug("index error: " + e.getMessage());
			}
			areaBarCharts.setStatus(buildResponseResult(
					ServiceConst.SERVER_ERROR_CODE, ServiceConst.ERROR_MSG));
			return Response.serverError().entity(areaBarCharts).build();
		}

	}

	/**
	 * 构建 条形图
	 * 
	 * @param toplist
	 * @param title
	 * @return
	 */
	private BarChartsResult buildAreaBarChartsCompanyNum(
			List<CompanyNumBean> toplist_custem,
			List<CompanyNumBean> toplist_parter, String title) {

		BarChartsResult charts = new BarChartsResult();
		List<String> legendData = new ArrayList<String>();
		legendData.add("拍档");
		legendData.add("客户");
		//
		List<String> xAxisData = new ArrayList<String>();// 下标：只有一份
		//
		List<BarSeries> series = new ArrayList<BarSeries>();// 2个

		// 拍档
		BarSeries series_parter = new BarSeries();
		series_parter.setName("拍档");
		List<BigDecimal> seriesData_parter = new ArrayList<BigDecimal>();
		if (toplist_parter.size() > 0) {//
			for (int i = 0; i < toplist_parter.size(); i++) {
				// int onday =
				// Integer.parseInt(toplist_parter.get(i).getDate());
				// if (onday > yesterday) {// 前端要求数据T-1显示；所以超过昨天的都不显示
				// continue;
				// }
				seriesData_parter.add(toplist_parter.get(i).getTotalNum());
				xAxisData.add(toplist_parter.get(i).getDataLable());
			}
			series_parter.setData(seriesData_parter);
			series.add(series_parter);
		}
		// 客户
		BarSeries series_custem = new BarSeries();
		series_custem.setName("客户");
		List<BigDecimal> seriesData_custem = new ArrayList<BigDecimal>();
		if (toplist_custem.size() > 0) {//
			for (int i = 0; i < toplist_custem.size(); i++) {
				// int onday= Integer.parseInt(toplist_custem.get(i).getDate());
				// if(onday>yesterday){//前端要求数据T-1显示；所以超过昨天的都不显示
				// continue;
				// }
				seriesData_custem.add(toplist_custem.get(i).getTotalNum());
			}
			series_custem.setData(seriesData_custem);
			series.add(series_custem);
		}

		charts.setTitle(title);
		charts.setSeriesName("");
		charts.setLegendData(legendData);
		charts.setxAxisData(xAxisData);
		charts.setSeries(series);
		return charts;
	}

	/**
	 * 
	 * @param list
	 * @param isExport
	 *            :: export导出数据，不光取前10
	 * @return
	 * @throws IOException
	 */
	private List<CompanyNumBean> buildPeriodCompanyNum(
			List<CompanyNumBean> list, String isExport, int point,
			String timeType) throws IOException {
		// 用于根据时间合并数据
		Map<String, CompanyNumBean> map = new HashMap<String, CompanyNumBean>();

		if ("week".equals(timeType)) {// 产生周、的每一天；用于后面根据这些日期合并数据
			map = DateUtils.getAnyDayOfWeek(map, new Date());
		} else if ("month".equals(timeType)) {// 产生月的每一天；用于后面根据这些日期合并数据
			map = DateUtils.getAnyDayOfMonth(map, new Date());
		} else if ("quarter".equals(timeType)) {// 产生季的每个月天；用于后面根据这些日期合并数据
			map = DateUtils.getAnyMonthOfQuarter(map, new Date());
		} else if ("year".equals(timeType)) {// 产生年的每个月天；用于后面根据这些日期合并数据
			map = DateUtils.getAnyMonthOfYear(map, new Date());
		}

		// 按照map中存在的日期，对数据进行汇总
		for (CompanyNumBean company : list) {
			if (null != company && isNotNull(company.getDate())) {
				String key = "";
				if ("week".equals(timeType) || "month".equals(timeType)) {
					key = company.getDate();// 按照天统计汇总
				} else if ("quarter".equals(timeType)
						|| "year".equals(timeType)) {
					key = company.getDate().substring(0, 6);// 保留到年月：按照月统计汇总
				}
				//
				if (map.containsKey(key)) {// 已经存在的企业：累计其：金额
					CompanyNumBean value = map.get(key);
					value.setTotalNum(value.getTotalNum().add(
							company.getTotalNum()));
					// .setScale(point, BigDecimal.ROUND_HALF_UP)// 保留4位小数
					map.put(key, value);
				}
			}
		}

		// 把map中汇总后的数据取出存入topList
		List<CompanyNumBean> topList = new ArrayList<CompanyNumBean>();
		for (Entry<String, CompanyNumBean> entry : map.entrySet()) {
			topList.add(entry.getValue());
		}
		//
		// // 排序：
		Collections.sort(topList, new Comparator<CompanyNumBean>() {
			public int compare(CompanyNumBean arg0, CompanyNumBean arg1) {
				BigDecimal x = new BigDecimal(arg1.getIndex());
				BigDecimal y = new BigDecimal(arg0.getIndex());
				return y.compareTo(x);
			}
		});

		return topList;
	}

	// =============企业分类统计：新增企业、活动企业数量=================

	/**
	 * 字符串：不为空：返回true
	 * 
	 * @param str
	 * @return
	 */
	private boolean isNotNull(String str) {
		if (null != str && str.trim().length() > 0) {
			return true;
		} else {
			return false;
		}
	}

	private ResponseResult buildResponseResult(String code, String message) {
		ResponseResult result = new ResponseResult();
		result.setCode(code);
		result.setMessage(message);
		return result;
	}

}