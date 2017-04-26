package org.jumao.bi.dao.baidu.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;
import org.jumao.bi.dao.baidu.IBaiduDao;
import org.jumao.bi.entites.baidu.CommonBean;
import org.jumao.bi.entites.baidu.DailyMetricBean;
import org.jumao.bi.entites.baidu.NOVisitorsBean;
import org.jumao.bi.entites.baidu.TopPageBean;
import org.jumao.bi.entites.baidu.VisitorsSourcesBean;
import org.jumao.commons.frameworks.jmframework.commutil.MRConstants;
import org.jumao.commons.frameworks.jmframework.hbaseutil.dao.IHBaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

@Repository("baiduDao")
public class BaiduDaoImpl implements IBaiduDao {

	@Autowired
	IHBaseDao hbasedao;

	public static final byte[] CF_NAME = Bytes.toBytes(MRConstants.TAB_CF);

	public List<DailyMetricBean> retrieveMetricDataByKeyRange(String startKey,
			String endKey) throws IOException {

		List<Result> results = hbasedao.getRows(
				MRConstants.JMBI_BAIDU_DAILY_STAT_TAB, startKey, endKey);

		byte[] platformIdField = Bytes.toBytes("platformId");
		byte[] dateField = Bytes.toBytes("date");
		byte[] pvField = Bytes.toBytes("pv");
		byte[] uvField = Bytes.toBytes("uv");
		byte[] ipsField = Bytes.toBytes("ips");
		// byte[] entrancesField = Bytes.toBytes("entrances");
		byte[] exitsField = Bytes.toBytes("exits");
		byte[] stayTimeField = Bytes.toBytes("stayTime");
		byte[] avgVisitPagesField = Bytes.toBytes("avgVisitPages");
		List<DailyMetricBean> dataResult = new ArrayList<DailyMetricBean>();

		for (Result result : results) {
			DailyMetricBean overview = new DailyMetricBean();
			if("--".equals(convertString(Bytes.toString(result.getValue(
					CF_NAME, pvField))))) {//无效数据，跳过
				continue;
			}
			overview.setDate(Bytes.toString(result.getValue(CF_NAME, dateField)));
			overview.setPlatform(Bytes.toString(result.getValue(CF_NAME,
					platformIdField)));
			overview.setPv(convertString(Bytes.toString(result.getValue(
					CF_NAME, pvField))));
			overview.setUv(convertString(Bytes.toString(result.getValue(
					CF_NAME, uvField))));
			overview.setIps(convertString(Bytes.toString(result.getValue(
					CF_NAME, ipsField))));
			// overview.setEntrances(convertString(Bytes.toString(result.getValue(CF_NAME,
			// entrancesField))));
			overview.setExits(convertString(Bytes.toString(result.getValue(
					CF_NAME, exitsField))));
			overview.setStayTime(convertString(Bytes.toString(result.getValue(
					CF_NAME, stayTimeField))));
			overview.setAvgVisitPages(convertString(Bytes.toString(result
					.getValue(CF_NAME, avgVisitPagesField))));
			dataResult.add(overview);
		}

		return dataResult;

	}

	private String convertString(String sourceStr) {

		return StringUtils.isNotBlank(sourceStr) ? sourceStr : "0"; // 赋默认值为0

	}

	public List<NOVisitorsBean> getONVisitorsByKeyRange(String startKey,
			String endKey) throws IOException {

		List<Result> results = hbasedao.getRows(
				MRConstants.JMBI_BAIDU_VISITOR_TAB, startKey, endKey);

		byte[] pvCountB = Bytes.toBytes("pvCount");
		byte[] visitorCountB = Bytes.toBytes("visitorCount");
		byte[] newVisitorCountB = Bytes.toBytes("newVisitorCount");
		byte[] avgVisitTimeB = Bytes.toBytes("avgVisitTime");
		byte[] avgVisitPageB = Bytes.toBytes("avgVisitPage");
		List<NOVisitorsBean> visitors = new ArrayList<NOVisitorsBean>();
		for (Result result : results) {
			NOVisitorsBean visitor = new NOVisitorsBean();
			
			String avgVisitTime = Bytes.toString(result.getValue(CF_NAME,
					avgVisitTimeB));
			if ("--".equals(avgVisitTime)) {//无效数据
				continue;
			}
			String newVisitorCount = Bytes.toString(result.getValue(CF_NAME,
					newVisitorCountB));
			String pvCount = Bytes.toString(result.getValue(CF_NAME, pvCountB));
			String visitorCount = Bytes.toString(result.getValue(CF_NAME,
					visitorCountB));
			String avgVisitPage = Bytes.toString(result.getValue(CF_NAME,
					avgVisitPageB));
			visitor.setAvgVisitPage(Double.parseDouble(avgVisitPage));
			visitor.setAvgVisitTime(Double.parseDouble(avgVisitTime));
			visitor.setNewVisitorCount(new Integer(newVisitorCount));
			visitor.setPvCount(new Integer(pvCount));
			visitor.setVisitorCount(new Integer(visitorCount));

			visitors.add(visitor);
		}

		return visitors;
	}

	public List<VisitorsSourcesBean> getVisitorsSourcesByKeyRange(
			String startKey, String endKey) throws IOException {

		List<Result> results = hbasedao.getRows(
				MRConstants.JMBI_BAIDU_SOURCES_TAB, startKey, endKey);
		List<VisitorsSourcesBean> visitorSources = new ArrayList<VisitorsSourcesBean>();
		byte[] dateB = Bytes.toBytes("date");
		byte[] valuesB = Bytes.toBytes("value");
//		Map<String, Integer> resultMap = new HashMap<String, Integer>();
		for (Result result : results) {
			
			String date = Bytes.toString(result.getValue(CF_NAME, dateB));
			String json = Bytes.toString(result.getValue(CF_NAME, valuesB));
			JSONObject job = JSON.parseObject(json);
			Set<Entry<String, Object>> set = job.entrySet();
			for (Entry<String, Object> entry : set) {
				VisitorsSourcesBean bean = new VisitorsSourcesBean();
				bean.setDate(date);
				bean.setName(entry.getKey());
				if("--".equals(entry.getValue().toString())) {//无效数据
					continue;
				}
				bean.setValue(Integer.parseInt(entry.getValue().toString()));
				visitorSources.add(bean);
			}
		}
//		Collection<Integer> sumS = resultMap.values();
//		Integer sum = new Integer(0);
//		for (Integer integer : sumS) {
//			sum += integer;
//		}
//		for (String name : resultMap.keySet()) {
//			VisitorsSourcesBean bean = new VisitorsSourcesBean();
//			bean.setName(name);
//			bean.setValue(resultMap.get(name).intValue()/** 1.0/sum */
//			);
//			visitorSources.add(bean);
//		}

		return visitorSources;
	}

	public List<CommonBean> getVisitorAreasByDate(String startKey, String endKey)
			throws IOException {

		List<Result> results = hbasedao.getRows(
				MRConstants.JMBI_BAIDU_AREAS_TAB, startKey, endKey);

		byte[] nameB = Bytes.toBytes("name");
		byte[] valueB = Bytes.toBytes("value");
		List<CommonBean> vistorAreas = new ArrayList<CommonBean>();
		for (Result result : results) {
			String name = Bytes.toString(result.getValue(CF_NAME, nameB));
			String value = Bytes.toString(result.getValue(CF_NAME, valueB));
			if("--".equals(value)) {//无效数据
				continue;
			}
			CommonBean areaBean = new CommonBean(name, value);
			vistorAreas.add(areaBean);
		}

		return vistorAreas;
	}

	public List<TopPageBean> getTopPageCountByKeyRange(String startKey,
			String endKey) throws IOException {

		List<Result> results = hbasedao.getRows(
				MRConstants.JMBI_BAIDU_TOP_PAGE_TAB, startKey, endKey);

		byte[] nameB = Bytes.toBytes("name");
		byte[] pvCountB = Bytes.toBytes("pvCount");
		byte[] existCountB = Bytes.toBytes("existCount");

		List<TopPageBean> topPages = new ArrayList<TopPageBean>();
		for (Result result : results) {
			String name = Bytes.toString(result.getValue(CF_NAME, nameB));
			String pvCount = Bytes.toString(result.getValue(CF_NAME, pvCountB));
			if("--".equals(pvCount)) {//无效数据
				continue;
			}
			String existCount = Bytes.toString(result.getValue(CF_NAME,
					existCountB));
			TopPageBean topBean = new TopPageBean(name, new BigDecimal(pvCount), new BigDecimal(existCount));
			topPages.add(topBean);
		}

		return topPages;
	}

}
