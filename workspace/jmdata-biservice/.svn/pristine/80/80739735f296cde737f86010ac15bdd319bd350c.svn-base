package org.jumao.bi.dao.jdt.inout.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;
import org.jumao.bi.dao.jdt.inout.IInOutDao;
import org.jumao.bi.entites.InOutCountryBean;
import org.jumao.bi.entites.InOutCustomsBean;
import org.jumao.bi.entites.InOutGoodsBean;
import org.jumao.bi.utis.DesensitizationUtils;
import org.jumao.commons.frameworks.jmframework.hbaseutil.dao.IHBaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("inOutDao")
public class InOutDaoImpl implements IInOutDao {

	@Autowired
	IHBaseDao hbasedao;

	/**
	 * 进出口分类统计：进出口贸易国别top
	 */
	private static final String IN_OUT_COUNTRY_TABLE = "jmbi:inOutCountryAmount";

	/**
	 * 进出口分类统计：进出口口岸top
	 */
	private static final String IN_OUT_CUSTOMS_TABLE = "jmbi:inOutCustomsAmount";

	/**
	 * 进出口分类统计：进出口商品top
	 */
	private static final String IN_OUT_GOODS_TABLE = "jmbi:inOutGoodsAmount";

	// =====================================

	private static final byte[] TABLE_CF = "info".getBytes();

	private static final BigDecimal zero = new BigDecimal(0);

	// ===============进出口分类统计：进出口贸易国别top======================
	public List<InOutCountryBean> retrieveInOutCountryAmountByPeriod(
			String fromKey, String endKey, String customsCode)
			throws IOException {

		List<Result> results = new ArrayList<Result>();
		if (isNotNull(customsCode)) {
			List<String> arr = new ArrayList<String>();
			arr.add("info" + "," + "customsCode" + "," + customsCode);
			results = hbasedao.getRowsByKeyAndFilterLike(IN_OUT_COUNTRY_TABLE,
					fromKey, endKey, arr);
		} else {
			results = hbasedao.getRows(IN_OUT_COUNTRY_TABLE, fromKey, endKey);
		}

		// 在这里累加合并：减少数据量
		Map<String, InOutCountryBean> map = new HashMap<String, InOutCountryBean>();
		for (Result result : results) {
			InOutCountryBean company = convertInOutCountryBy(result);
			// companyList.add(company);
			// 国家编码不为空
			if (null != company && isNotNull(company.getCountryCode())) {
				if (map.containsKey(company.getCountryCode())) {// 已经存在的国家：累计其：金额
					InOutCountryBean value = map.get(company.getCountryCode());
					value.setTotalAmount(value.getTotalAmount()
							.add(company.getTotalAmount()));// 保留4位小数
//							.setScale(4, BigDecimal.ROUND_HALF_UP)
					map.put(company.getCountryCode(), value);
				} else {
					company.setTotalAmount(company.getTotalAmount());
//					.setScale(4,BigDecimal.ROUND_HALF_UP));// 保留4位小数
					map.put(company.getCountryCode(), company);
				}
			}
		}
		List<InOutCountryBean> companyList = new ArrayList<InOutCountryBean>();
		for (Entry<String, InOutCountryBean> entry : map.entrySet()) {
			companyList.add(entry.getValue());
		}
		return companyList;
	}

	/**
	 * 进出口分类统计：进出口贸易国别top
	 * 
	 * @param result
	 * @return
	 */
	private InOutCountryBean convertInOutCountryBy(Result result) {
		InOutCountryBean bean = new InOutCountryBean();
		String rewKey = Bytes.toString(result.getRow());
		String countryName = Bytes.toString(result.getValue(TABLE_CF,
				Bytes.toBytes("countryName")));
		String totalAmount = Bytes.toString(result.getValue(TABLE_CF,
				Bytes.toBytes("totalAmount")));
		//
		String[] strs = rewKey.split("_", -1);

		String customs_code = strs[2];
		String trading_country = strs[3];
		bean.setCustomsCode(customs_code);
		bean.setCountryCode(trading_country);
		bean.setCountryName(countryName);
		// 订单金额
		if (null == totalAmount || totalAmount.trim().length() == 0) {
			bean.setTotalAmount(zero);
		} else {
			bean.setTotalAmount(new BigDecimal(totalAmount));
		}
		System.out.println(customs_code+"||"+trading_country+"||"+countryName+"||"+bean.getTotalAmount());
		return bean;
	}

	// ===============进出口分类统计：进出口贸易国别top======================

	// ===============进出口分类统计：进出口口岸top======================
	public List<InOutCustomsBean> retrieveInOutCustomsAmountByPeriod(
			String fromKey, String endKey, String customsCode)
			throws IOException {

		List<Result> results = new ArrayList<Result>();
		if (isNotNull(customsCode)) {
			List<String> arr = new ArrayList<String>();
			arr.add("info" + "," + "customsCode" + "," + customsCode);
			results = hbasedao.getRowsByKeyAndFilterLike(IN_OUT_CUSTOMS_TABLE,
					fromKey, endKey, arr);
		} else {
			results = hbasedao.getRows(IN_OUT_CUSTOMS_TABLE, fromKey, endKey);
		}
//		List<Result> results = hbasedao.getRows(IN_OUT_CUSTOMS_TABLE, fromKey,
//				endKey);
		// 在这里累加合并：减少数据量
		Map<String, InOutCustomsBean> map = new HashMap<String, InOutCustomsBean>();
		for (Result result : results) {
			InOutCustomsBean company = convertInOutCustomsBy(result);
			// companyList.add(company);
			// 海关/关区编码不为空
			if (null != company && isNotNull(company.getCustomsCode())) {
				if (map.containsKey(company.getCustomsCode())) {// 已经存在的海关/关区：累计其：金额
					InOutCustomsBean value = map.get(company.getCustomsCode());
					value.setTotalAmount(value.getTotalAmount()
							.add(company.getTotalAmount()));
//							.setScale(4, BigDecimal.ROUND_HALF_UP)// 保留4位小数
					map.put(company.getCustomsCode(), value);
				} else {
					company.setTotalAmount(company.getTotalAmount());
//							.setScale(4,BigDecimal.ROUND_HALF_UP)// 保留4位小数
					map.put(company.getCustomsCode(), company);
				}
			}
		}
		List<InOutCustomsBean> companyList = new ArrayList<InOutCustomsBean>();
		for (Entry<String, InOutCustomsBean> entry : map.entrySet()) {
			companyList.add(entry.getValue());
		}
		return companyList;
	}

	/**
	 * 进出口分类统计：进出口海关/关区top
	 * 
	 * @param result
	 * @return
	 */
	private InOutCustomsBean convertInOutCustomsBy(Result result) {
		InOutCustomsBean bean = new InOutCustomsBean();
		String rewKey = Bytes.toString(result.getRow());
		String customsName = Bytes.toString(result.getValue(TABLE_CF,
				Bytes.toBytes("customsName")));
		String totalAmount = Bytes.toString(result.getValue(TABLE_CF,
				Bytes.toBytes("totalAmount")));
		//
		String[] strs = rewKey.split("_", -1);

		String customs_code = strs[2];
		// String trading_country = strs[3];
		bean.setCustomsCode(customs_code);
		// bean.setCountryCode(trading_country);
		bean.setCustomsName(customsName);
		// 订单金额
		if (null == totalAmount || totalAmount.trim().length() == 0) {
			bean.setTotalAmount(zero);
		} else {
			bean.setTotalAmount(new BigDecimal(totalAmount));
		}
		return bean;
	}

	// ===============进出口分类统计：进出口口岸top======================

	// ===============进出口分类统计：进出口商品top======================
	public List<InOutGoodsBean> retrieveInOutGoodsAmountByPeriod(
			String fromKey, String endKey, String customsCode)
			throws IOException {

		List<Result> results = new ArrayList<Result>();
		if (isNotNull(customsCode)) {
			List<String> arr = new ArrayList<String>();
			arr.add("info" + "," + "customsCode" + "," + customsCode);
			results = hbasedao.getRowsByKeyAndFilterLike(IN_OUT_GOODS_TABLE,
					fromKey, endKey, arr);
		} else {
			results = hbasedao.getRows(IN_OUT_GOODS_TABLE, fromKey, endKey);
		}
//		List<Result> results = hbasedao.getRows(IN_OUT_GOODS_TABLE, fromKey,
//				endKey);
		// 在这里累加合并：减少数据量
		Map<String, InOutGoodsBean> map = new HashMap<String, InOutGoodsBean>();
		for (Result result : results) {
			InOutGoodsBean company = convertInOutGoodsBy(result);
			// companyList.add(company);
			// 商品码不为空
			if (null != company && isNotNull(company.getGoodsCode())) {
				if (map.containsKey(company.getGoodsCode())) {// 已经存在的商品：累计其：金额
					InOutGoodsBean value = map.get(company.getGoodsCode());
					value.setTotalAmount(value.getTotalAmount()
							.add(company.getTotalAmount()));
//							.setScale(4, BigDecimal.ROUND_HALF_UP)// 保留4位小数
					map.put(company.getGoodsCode(), value);
				} else {
					company.setTotalAmount(company.getTotalAmount());
//					.setScale(4, BigDecimal.ROUND_HALF_UP)// 保留4位小数
					map.put(company.getGoodsCode(), company);
				}
			}
		}
		List<InOutGoodsBean> companyList = new ArrayList<InOutGoodsBean>();
		for (Entry<String, InOutGoodsBean> entry : map.entrySet()) {
			companyList.add(entry.getValue());
		}
		return companyList;
	}

	/**
	 * 进出口分类统计：进出口商品top
	 * 
	 * @param result
	 * @return
	 */
	private InOutGoodsBean convertInOutGoodsBy(Result result) {
		InOutGoodsBean bean = new InOutGoodsBean();
		String rewKey = Bytes.toString(result.getRow());
		String goodsName = Bytes.toString(result.getValue(TABLE_CF,
				Bytes.toBytes("goodsName")));
		String totalAmount = Bytes.toString(result.getValue(TABLE_CF,
				Bytes.toBytes("totalAmount")));
		//
		String[] strs = rewKey.split("_", -1);

		String customs_code = strs[2];
		String hs_no = strs[3];//商品hs编码
		bean.setCustomsCode(customs_code);
		bean.setGoodsCode(hs_no);
		bean.setGoodsName(goodsName);
		// 订单金额
		if (null == totalAmount || totalAmount.trim().length() == 0) {
			bean.setTotalAmount(zero);
		} else {
			bean.setTotalAmount(new BigDecimal(totalAmount));
		}
		return bean;
	}

	// ===============进出口分类统计：进出口商品top======================

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

}