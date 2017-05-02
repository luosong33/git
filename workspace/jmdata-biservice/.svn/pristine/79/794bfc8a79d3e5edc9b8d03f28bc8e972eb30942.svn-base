package org.jumao.bi.service.impl.register;

import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import org.apache.log4j.Logger;
import org.jumao.bi.entites.register.AuthUAreaDist;
import org.jumao.bi.entites.register.PayUAreaDist;
import org.jumao.bi.entites.register.VisaUAreaDist;
import org.jumao.bi.service.register.RegisterAnalysis;
import org.jumao.bi.utis.GeneralUtils;
import org.jumao.bi.utis.ImpalaJDBC;
import org.jumao.bi.utis.LogUtils;

import java.util.List;

public class RegisterAnalysisImpl implements RegisterAnalysis {

	private Logger logger = Logger.getLogger(this.getClass());

	private ImpalaJDBC impalaJDBC = new ImpalaJDBC();
	private Gson gson = new Gson();

	private String START_TO = "start to";
	
	public Response getAuthUAreaDist() {
		LogUtils.writeLogs(logger, GeneralUtils.getCurrMethod(START_TO));

		String sql = "SELECT t.total,t.country,a.area_name as areaName,t.area as areaCode FROM " +
				"(SELECT c.country, c.area, count(DISTINCT c.id) as total " +
				"FROM default.jmbi_uc_company c where c.review_status=2 " +
				"GROUP BY c.country, c.area) t " +
				"LEFT JOIN default.jmbi_uc_area_cn a on t.area = a.area_code;";

		List<AuthUAreaDist> list = impalaJDBC.getImpalaData(new AuthUAreaDist(), sql);
		System.err.println(sql);

		for (AuthUAreaDist ele : list) {
			Long areaCode = ele.getAreacode();
			if (areaCode != null) {
				System.err.println(gson.toJson(ele));
				System.out.println();
			}
		}

		return Response.ok().entity("ok").build();
	}


	public Response getVisaUAreaDist() {
		LogUtils.writeLogs(logger, GeneralUtils.getCurrMethod(START_TO));

		String sql = "SELECT count(DISTINCT id) as total,country,area as areacode FROM default.jmbi_uc_company " +
				"WHERE signature_mark=1  group by country,area";
//		AND review_time <= '2017-5-1'" +
//		"AND review_time >= '2015-5-1'
		List<VisaUAreaDist> list = impalaJDBC.getImpalaData(new VisaUAreaDist(), sql);
		System.err.println(sql);

		for (VisaUAreaDist ele : list) {
			System.err.println(gson.toJson(ele));
			System.out.println();
		}

		return Response.ok().entity("ok").build();
	}

	public Response getPayUAreaDist() {
		LogUtils.writeLogs(logger, GeneralUtils.getCurrMethod(START_TO));

		String sql = "SELECT total, a.area_name as areaname,address_province as addressprovince " +
				"FROM (SELECT address_province,count(DISTINCT comp_id) as total FROM " +
				"default.jmbi_trade_company where pay_status=1 and address_province is not null " +
				"GROUP BY address_province) t LEFT JOIN default.jmbi_area a on t.address_province = CAST(a.area_code AS BIGINT);";

		List<PayUAreaDist> list = impalaJDBC.getImpalaData(new PayUAreaDist(), sql);
		System.err.println(sql);

		for (PayUAreaDist ele : list) {
			System.err.println(gson.toJson(ele));
			System.out.println();
		}
		return Response.ok().entity("ok").build();
	}


	public static void main(String[] args) {
		RegisterAnalysisImpl r = new RegisterAnalysisImpl();
		r.getPayUAreaDist();
	}

}
