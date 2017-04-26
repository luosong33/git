package org.jumao.bi.service.impl.jdt.inout;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.jumao.bi.constant.ServiceConst;
import org.jumao.bi.dao.jdt.inout.IInOutDao;
import org.jumao.bi.entites.AreaBarCharts;
import org.jumao.bi.entites.InOutCountryBean;
import org.jumao.bi.entites.InOutCustomsBean;
import org.jumao.bi.entites.InOutGoodsBean;
import org.jumao.bi.entites.ResponseResult;
import org.jumao.bi.entites.ResultList;
import org.jumao.bi.service.jdt.inout.IInOutSvc;
import org.jumao.commons.frameworks.jmframework.commutil.CalendarUtils;
import org.springframework.beans.factory.annotation.Autowired;

public class InOutSvcImpl implements IInOutSvc {

	@Autowired
	IInOutDao inOutDao;

	private Logger logger = Logger.getLogger(this.getClass());

	// ===============进出口分类统计：进出口贸易国别top======================
	public Response getInOutCountryAmount(String timeType, String startDate,
			String endDate, String customsCode, String inout) {
		if (logger.isDebugEnabled()) {
			logger.debug("start getInOutCountryAmount>>timeType: " + timeType
					+ " startDate:" + startDate + " endDate:" + endDate
					+ " customsCode:" + customsCode + " inout:" + inout);
		}
		if(null==customsCode||"all".equals(customsCode)){//当前端选择关区选全国时，这里设置为空字符，后面查询
			customsCode="";
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
		String title = "";
		String inout_type = "1";// 进出口类型：1:进口；2:出口
		if ("in".equals(inout)) {
			title = "进口贸易国别TOP5";
			inout_type = "1";
		} else if ("out".equals(inout)) {
			title = "出口贸易国别TOP5";
			inout_type = "2";
		}
		String startKey = inout_type + "_" + startDate + "_" + customsCode;
		String endKey = inout_type + "_" + toDate + "_" + customsCode;

		List<InOutCountryBean> list;
		try {
			list = inOutDao
					.retrieveInOutCountryAmountByPeriod(startKey, endKey,customsCode);
			List<InOutCountryBean> toplist = new ArrayList<InOutCountryBean>();
			// if ("week".equals(type)) {
			toplist = buildPeriodCountry(list, "not");
			// }
			// 条形图：展示top5
			
			// charts = buildCompanyAreaCharts(weekMap, title);
			areaBarCharts = buildAreaBarChartsCountry(toplist, title);
			areaBarCharts.setStatus(buildResponseResult(
					ServiceConst.SUCCESS_CODE, ServiceConst.SUCCESS_MSG));
			return Response.ok().entity(areaBarCharts).build();

		} catch (IOException e) {
			if (logger.isDebugEnabled()) {
				logger.debug("getInOutCountryAmount error: " + e.getMessage());
			}
			areaBarCharts.setStatus(buildResponseResult(
					ServiceConst.SERVER_ERROR_CODE, ServiceConst.ERROR_MSG));
			return Response.serverError().entity(areaBarCharts).build();
		}

	}
	
	
	public Response getInOutCountryAmountExport(String timeType,
			String startDate, String endDate, String customsCode, String inout) {
		if (logger.isDebugEnabled()) {
			logger.debug("start getInOutCountryAmountExport>>timeType: " + timeType
					+ " startDate:" + startDate + " endDate:" + endDate
					+ " customsCode:" + customsCode + " inout:" + inout);
		}
		if(null==customsCode||"all".equals(customsCode)){//当前端选择关区选全国时，这里设置为空字符，后面查询
			customsCode="";
		}
		ResultList<InOutCountryBean> result = new ResultList<InOutCountryBean>();
		boolean validRequest = (null != timeType && null != startDate && null != endDate);
		if (!validRequest) {
			result.setStatus(buildResponseResult(
					ServiceConst.INVADLID_REQUEST_CODE,
					ServiceConst.REQUEST_MSG));
			return Response.serverError().entity(result).build();
		}

		String toDate = endDate;// hbase查询：结束时间：后退一天
		toDate = CalendarUtils.nextDateStr(endDate);
//		String title = "进口贸易国别TOP5";
		String inout_type = "1";// 进出口类型：1:进口；2:出口
		if ("in".equals(inout)) {
//			title = "进口贸易国别TOP5";
			inout_type = "1";
		} else if ("out".equals(inout)) {
//			title = "出口贸易国别TOP5";
			inout_type = "2";
		}
		String startKey = inout_type + "_" + startDate + "_" + customsCode;
		String endKey = inout_type + "_" + toDate + "_" + customsCode;

		List<InOutCountryBean> list;
		try {
			list = inOutDao
					.retrieveInOutCountryAmountByPeriod(startKey, endKey,customsCode);
			List<InOutCountryBean> toplist = new ArrayList<InOutCountryBean>();

			toplist = buildPeriodCountry(list, "export");
 
			return Response.ok().entity(toplist).build();// 导出返回List,

		} catch (IOException e) {
			if (logger.isDebugEnabled()) {
				logger.debug("getInOutCountryAmountExport error: " + e.getMessage());
			}
			result.setStatus(buildResponseResult(
					ServiceConst.SERVER_ERROR_CODE, ServiceConst.ERROR_MSG));
			return Response.serverError().entity(result).build();
		}

	}

//	public Response getInOutCountryAmountExport(String timeType,
//			String startDate, String endDate, String customsCode, String inout) {
//		if (logger.isDebugEnabled()) {
//			logger.debug("start getInCountryAmount>>timeType: " + timeType
//					+ " startDate:" + startDate + " endDate:" + endDate
//					+ " customsCode:" + customsCode + " inout:" + inout);
//		}
//		if(null==customsCode||"all".equals(customsCode)){//当前端选择关区选全国时，这里设置为空字符，后面查询
//			customsCode="";
//		}
//		ResultList<InOutCountryBean> result = new ResultList<InOutCountryBean>();
//		boolean validRequest = (null != timeType && null != startDate && null != endDate);
//		if (!validRequest) {
//			result.setStatus(buildResponseResult(
//					ServiceConst.INVADLID_REQUEST_CODE,
//					ServiceConst.REQUEST_MSG));
//			return Response.serverError().entity(result).build();
//		}
//
//		String toDate = endDate;// hbase查询：结束时间：后退一天
//		toDate = CalendarUtils.nextDateStr(endDate);
//		String inout_type = "1";// 进出口类型：1:进口；2:出口
//		if ("in".equals(inout)) {
//			inout_type = "1";
//		} else if ("out".equals(inout)) {
//			inout_type = "2";
//		}
//		String startKey = inout_type + "_" + startDate + "_" + customsCode;
//		String endKey = inout_type + "_" + toDate + "_" + customsCode;
//
//		List<InOutCountryBean> list;
//		try {
//			list = inOutDao
//					.retrieveInOutCountryAmountByPeriod(startKey, endKey,customsCode);
//			List<InOutCountryBean> toplist = new ArrayList<InOutCountryBean>();
//			// if ("week".equals(type)) {
//			toplist = buildPeriodCountry(list, "export");
//			// }
//
//			// 导出全部top
//		
//			result.setStatus(buildResponseResult(ServiceConst.SUCCESS_CODE,
//					ServiceConst.SUCCESS_MSG));
//			result.setList(toplist);
//			return Response.ok().entity(result).build();// 导出返回List,
//			// List<String[]> result = new ArrayList<String[]>();
//			// for (int i = 0; i < toplist.size(); i++) {
//			// wordData.get(i).setIndex(i + 1);// 排序
//			// String[] array = { wordData.get(i).getIndex() + "",
//			// wordData.get(i).getName(), wordData.get(i).getType(),
//			// wordData.get(i).getIndustry(),
//			// wordData.get(i).getCount() + "",
//			// wordData.get(i).getAmount().toPlainString(),
//			// wordData.get(i).getTopGoods() };
//			// result.add(array);
//			// }
//			//
//			// ExportExcel eu = new ExportExcel();
//			// HSSFWorkbook workbook = eu.exportExcel(
//			// "排名,公司名称,公司性质,所属行业,下单数量,下单金额(万),商品名称", "buy", result);
//			// ByteArrayOutputStream output = new ByteArrayOutputStream();
//			// workbook.write(output);
//			//
//			// byte[] ba = output.toByteArray();
//			// excelFile = new ByteArrayInputStream(ba);
//			// output.flush();
//			// output.close();
//
//		} catch (IOException e) {
//			if (logger.isDebugEnabled()) {
//				logger.debug("index error: " + e.getMessage());
//			}
//			result.setStatus(buildResponseResult(
//					ServiceConst.SERVER_ERROR_CODE, ServiceConst.ERROR_MSG));
//			return Response.serverError().entity(result).build();
//		}
//
//	}

	/**
	 * 构建 条形图
	 * 
	 * @param toplist
	 * @param title
	 * @return
	 */
	private AreaBarCharts buildAreaBarChartsCountry(
			List<InOutCountryBean> toplist, String title) {
		AreaBarCharts charts = new AreaBarCharts();

		List<String> yAxisData = new ArrayList<String>();
		//
		List<BigDecimal> seriesData = new ArrayList<BigDecimal>();

		if (toplist.size() > 0) {//
			for (int i = 0; i < toplist.size(); i++) {
				yAxisData.add(toplist.get(i).getCountryName());
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
	 * @return
	 * @throws IOException
	 */
	private List<InOutCountryBean> buildPeriodCountry(
			List<InOutCountryBean> list, String isExport) throws IOException {
		// 排序：从hbase里面查询出来的是乱序
		Collections.sort(list, new Comparator<InOutCountryBean>() {
			public int compare(InOutCountryBean arg0, InOutCountryBean arg1) {
				return arg1.getTotalAmount().compareTo(arg0.getTotalAmount());
			}
		});

		List<InOutCountryBean> topList = new ArrayList<InOutCountryBean>();
		int i = 0;
		BigDecimal div = new BigDecimal(10000);// 转化成：万美元
		for (InOutCountryBean deal : list) {
			if (!"export".equals(isExport)) {// 导出数据，不光取前10
				if (i == 5) {
					break;// 只取top5
				}
			}
			i = i + 1;
			deal.setTotalAmount((deal.getTotalAmount().divide(div)).setScale(4, BigDecimal.ROUND_HALF_UP));//保留几位小数
			deal.setIndex(i);
			topList.add(deal);
		}
		return topList;
	}

	// ===============进出口分类统计：进出口贸易国别top======================

	//

	//

	// ===============进出口分类统计：进出口口岸top======================
	public Response getInOutCustomsAmount(String timeType, String startDate,
			String endDate, String customsCode, String inout) {
		if (logger.isDebugEnabled()) {
			logger.debug("start getInOutCustomsAmount>>timeType: " + timeType
					+ " startDate:" + startDate + " endDate:" + endDate
					+ " customsCode:" + customsCode + " inout:" + inout);
		}
		if(null==customsCode||"all".equals(customsCode)){//当前端选择关区选全国时，这里设置为空字符，后面查询
			customsCode="";
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
		String title = "";
		String inout_type = "1";// 进出口类型：1:进口；2:出口
		if ("in".equals(inout)) {
			title = "进口口岸TOP5";
			inout_type = "1";
		} else if ("out".equals(inout)) {
			title = "出口口岸TOP5";
			inout_type = "2";
		}
		String startKey = inout_type + "_" + startDate + "_" + customsCode;
		String endKey = inout_type + "_" + toDate + "_" + customsCode;

		List<InOutCustomsBean> list;
		try {
			list = inOutDao
					.retrieveInOutCustomsAmountByPeriod(startKey, endKey,customsCode);
			List<InOutCustomsBean> toplist = new ArrayList<InOutCustomsBean>();
			// if ("week".equals(type)) {
			toplist = buildPeriodCustoms(list, "not");
			// }
			// 条形图：展示top5
			// charts = buildCompanyAreaCharts(weekMap, title);
			areaBarCharts = buildAreaBarChartsCustoms(toplist, title);
			areaBarCharts.setStatus(buildResponseResult(ServiceConst.SUCCESS_CODE,
					ServiceConst.SUCCESS_MSG));
			return Response.ok().entity(areaBarCharts).build();

		} catch (IOException e) {
			if (logger.isDebugEnabled()) {
				logger.debug("getInOutCustomsAmount error: " + e.getMessage());
			}
			areaBarCharts.setStatus(buildResponseResult(
					ServiceConst.SERVER_ERROR_CODE, ServiceConst.ERROR_MSG));
			return Response.serverError().entity(areaBarCharts).build();
		}

	}

	public Response getInOutCustomsAmountExport(String timeType,
			String startDate, String endDate, String customsCode, String inout) {
		if (logger.isDebugEnabled()) {
			logger.debug("start getInOutCustomsAmountExport>>timeType: "
					+ timeType + " startDate:" + startDate + " endDate:"
					+ endDate + " customsCode:" + customsCode + " inout:"
					+ inout);
		}
		if(null==customsCode||"all".equals(customsCode)){//当前端选择关区选全国时，这里设置为空字符，后面查询
			customsCode="";
		}
		ResultList<InOutCustomsBean> result = new ResultList<InOutCustomsBean>();
		boolean validRequest = (null != timeType && null != startDate && null != endDate);
		if (!validRequest) {
			result.setStatus(buildResponseResult(
					ServiceConst.INVADLID_REQUEST_CODE,
					ServiceConst.REQUEST_MSG));
			return Response.serverError().entity(result).build();
		}

		String toDate = endDate;// hbase查询：结束时间：后退一天
		toDate = CalendarUtils.nextDateStr(endDate);
		String inout_type = "1";// 进出口类型：1:进口；2:出口
		if ("in".equals(inout)) {
			inout_type = "1";
		} else if ("out".equals(inout)) {
			inout_type = "2";
		}
		String startKey = inout_type + "_" + startDate + "_" + customsCode;
		String endKey = inout_type + "_" + toDate + "_" + customsCode;

		List<InOutCustomsBean> list;
		try {
			list = inOutDao
					.retrieveInOutCustomsAmountByPeriod(startKey, endKey,customsCode);
			List<InOutCustomsBean> toplist = new ArrayList<InOutCustomsBean>();
			// if ("week".equals(type)) {
			toplist = buildPeriodCustoms(list, "export");
			// }
			// 导出全部top
			 
//			result.setStatus(buildResponseResult(ServiceConst.SUCCESS_CODE,
//					ServiceConst.SUCCESS_MSG));
//			result.setList(toplist);
			return Response.ok().entity(toplist).build();// 导出返回List,

		} catch (IOException e) {
			if (logger.isDebugEnabled()) {
				logger.debug("getInOutCustomsAmountExport error: " + e.getMessage());
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
	private AreaBarCharts buildAreaBarChartsCustoms(
			List<InOutCustomsBean> toplist, String title) {
		AreaBarCharts charts = new AreaBarCharts();

		List<String> yAxisData = new ArrayList<String>();
		//
		List<BigDecimal> seriesData = new ArrayList<BigDecimal>();

		if (toplist.size() > 0) {//
			for (int i = 0; i < toplist.size(); i++) {
				yAxisData.add(toplist.get(i).getCustomsName());
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
	 * @return
	 * @throws IOException
	 */
	private List<InOutCustomsBean> buildPeriodCustoms(
			List<InOutCustomsBean> list, String isExport) throws IOException {
		// 排序：从hbase里面查询出来的是乱序
		Collections.sort(list, new Comparator<InOutCustomsBean>() {
			public int compare(InOutCustomsBean arg0, InOutCustomsBean arg1) {
				return arg1.getTotalAmount().compareTo(arg0.getTotalAmount());
			}
		});

		List<InOutCustomsBean> topList = new ArrayList<InOutCustomsBean>();
		int i = 0;
		BigDecimal div = new BigDecimal(10000);// 转化成：万美元
		for (InOutCustomsBean deal : list) {
			if (!"export".equals(isExport)) {// 导出数据，不光取前10
				if (i == 5) {
					break;// 只取top5
				}
			}
			i = i + 1;
			deal.setTotalAmount((deal.getTotalAmount().divide(div)).setScale(4, BigDecimal.ROUND_HALF_UP));//保留几位小数
			deal.setIndex(i);
			topList.add(deal);
		}
		return topList;
	}

	// ===============进出口分类统计：进出口口岸top======================

	//

	//

	// ===============进出口分类统计：进出口商品top======================
	public Response getInOutGoodsAmount(String timeType, String startDate,
			String endDate, String customsCode, String inout) {
		if (logger.isDebugEnabled()) {
			logger.debug("start getInOutGoodsAmount>>timeType: " + timeType
					+ " startDate:" + startDate + " endDate:" + endDate
					+ " customsCode:" + customsCode + " inout:" + inout);
		}
		if(null==customsCode||"all".equals(customsCode)){//当前端选择关区选全国时，这里设置为空字符，后面查询
			customsCode="";
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
		String title = "";
		String inout_type = "1";// 进出口类型：1:进口；2:出口
		if ("in".equals(inout)) {
			title = "进口商品TOP5";
			inout_type = "1";
		} else if ("out".equals(inout)) {
			title = "出口商品TOP5";
			inout_type = "2";
		}
		String startKey = inout_type + "_" + startDate + "_" + customsCode;
		String endKey = inout_type + "_" + toDate + "_" + customsCode;

		List<InOutGoodsBean> list;
		try {
			list = inOutDao.retrieveInOutGoodsAmountByPeriod(startKey, endKey,customsCode);
			List<InOutGoodsBean> toplist = new ArrayList<InOutGoodsBean>();
			// if ("week".equals(type)) {
			toplist = buildPeriodGoods(list, "not");
			// }
			// 条形图：展示top5
			
			// charts = buildCompanyAreaCharts(weekMap, title);
			areaBarCharts = buildAreaBarChartsGoods(toplist, title);
			areaBarCharts.setStatus(buildResponseResult(ServiceConst.SUCCESS_CODE,
					ServiceConst.SUCCESS_MSG));
			return Response.ok().entity(areaBarCharts).build();

		} catch (IOException e) {
			if (logger.isDebugEnabled()) {
				logger.debug("getInOutGoodsAmount error: " + e.getMessage());
			}
			areaBarCharts.setStatus(buildResponseResult(
					ServiceConst.SERVER_ERROR_CODE, ServiceConst.ERROR_MSG));
			return Response.serverError().entity(areaBarCharts).build();
		}

	}

	public Response getInOutGoodsAmountExport(String timeType,
			String startDate, String endDate, String customsCode, String inout) {
		if (logger.isDebugEnabled()) {
			logger.debug("start getInOutGoodsAmountExport>>timeType: "
					+ timeType + " startDate:" + startDate + " endDate:"
					+ endDate + " customsCode:" + customsCode + " inout:"
					+ inout);
		}
		if(null==customsCode||"all".equals(customsCode)){//当前端选择关区选全国时，这里设置为空字符，后面查询
			customsCode="";
		}
		ResultList<InOutGoodsBean> result = new ResultList<InOutGoodsBean>();
		boolean validRequest = (null != timeType && null != startDate && null != endDate);
		if (!validRequest) {
			result.setStatus(buildResponseResult(
					ServiceConst.INVADLID_REQUEST_CODE,
					ServiceConst.REQUEST_MSG));
			return Response.serverError().entity(result).build();
		}

		String toDate = endDate;// hbase查询：结束时间：后退一天
		toDate = CalendarUtils.nextDateStr(endDate);
		String inout_type = "1";// 进出口类型：1:进口；2:出口
		if ("in".equals(inout)) {
			inout_type = "1";
		} else if ("out".equals(inout)) {
			inout_type = "2";
		}
		String startKey = inout_type + "_" + startDate + "_" + customsCode;
		String endKey = inout_type + "_" + toDate + "_" + customsCode;

		List<InOutGoodsBean> list;
		try {
			list = inOutDao.retrieveInOutGoodsAmountByPeriod(startKey, endKey,customsCode);
			List<InOutGoodsBean> toplist = new ArrayList<InOutGoodsBean>();
			// if ("week".equals(type)) {
			toplist = buildPeriodGoods(list, "export");
			// }
			// 导出全部top
			 
//			result.setStatus(buildResponseResult(ServiceConst.SUCCESS_CODE,
//					ServiceConst.SUCCESS_MSG));
//			result.setList(toplist);
			return Response.ok().entity(toplist).build();// 导出返回List,
	 
		} catch (IOException e) {
			if (logger.isDebugEnabled()) {
				logger.debug("getInOutGoodsAmountExport error: " + e.getMessage());
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
	private AreaBarCharts buildAreaBarChartsGoods(List<InOutGoodsBean> toplist,
			String title) {
		AreaBarCharts charts = new AreaBarCharts();
		
		List<String> yAxisData = new ArrayList<String>();
		//
		List<BigDecimal> seriesData = new ArrayList<BigDecimal>();

		if (toplist.size() > 0) {//
			for (int i = 0; i < toplist.size(); i++) {
				yAxisData.add(toplist.get(i).getGoodsName());
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
	 * @return
	 * @throws IOException
	 */
	private List<InOutGoodsBean> buildPeriodGoods(List<InOutGoodsBean> list,
			String isExport) throws IOException {
		// 排序：从hbase里面查询出来的是乱序
		Collections.sort(list, new Comparator<InOutGoodsBean>() {
			public int compare(InOutGoodsBean arg0, InOutGoodsBean arg1) {
				return arg1.getTotalAmount().compareTo(arg0.getTotalAmount());
			}
		});

		List<InOutGoodsBean> topList = new ArrayList<InOutGoodsBean>();
		int i = 0;
		BigDecimal div = new BigDecimal(10000);// 转化成：万美元
		for (InOutGoodsBean deal : list) {
			if (!"export".equals(isExport)) {// 导出数据，不光取前10
				if (i == 5) {
					break;// 只取top5
				}
			}
			i = i + 1;
			deal.setTotalAmount((deal.getTotalAmount().divide(div)).setScale(4, BigDecimal.ROUND_HALF_UP));//保留几位小数
			deal.setIndex(i);
			topList.add(deal);
		}
		return topList;
	}

	// ===============进出口分类统计：进出口商品top======================

	private ResponseResult buildResponseResult(String code, String message) {
		ResponseResult result = new ResponseResult();
		result.setCode(code);
		result.setMessage(message);
		return result;
	}

}