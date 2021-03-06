package org.jumao.bi.component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.jumao.bi.constant.ServiceConst;
import org.jumao.bi.dao.AbstractBaseDao;
import org.jumao.bi.entites.ParamBean;
import org.jumao.bi.entites.ResponseResult;
import org.jumao.bi.utis.PlatFormUtil;
import org.jumao.bi.utis.SpringContextUtil;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseDataExtract implements IDataExtract{
	
	@Autowired
    private AbstractBaseDao baseDao;
	private Logger logger = Logger.getLogger(this.getClass());
	/**
	 * 处理sql传入参数
	 */
	public void dealSqlParam(ParamBean param,List<String> transList) throws ParseException{
		for (String tranParam : transList){
			if ("platform".equalsIgnoreCase(tranParam)){
				String platForm = PlatFormUtil.getPlatformV(param.getPlatform());
		        if (platForm==null) {
		        	platForm = "0"; 
		        }
		        param.setPlatform(platForm);
			} else if("startDate".equalsIgnoreCase(tranParam)){
				String startDate = new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("yyyyMMdd").parse(param.getStartDate()));
				param.setStartDate(startDate);	
				//throw new ParseException(tranParam, 0);
			} else if("endDate".equalsIgnoreCase(tranParam)){
				String endDate = new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("yyyyMMdd").parse(param.getEndDate()));
				param.setEndDate(endDate);
			}
		}
	}
	/**
	 * 替换sql里的占位变量为具体的值
	 * @param sql
	 * @param param
	 * @param transList
	 * @return
	 * @throws Exception
	 */
	public String dealSqlWithParam(String sql,ParamBean param,List<String> transList) throws Exception{
		this.dealSqlParam(param,transList);
		return sql.replaceAll(":platform", param.getPlatform())
		.replaceAll(":startDate", param.getStartDate())
		.replaceAll(":endDate", param.getEndDate())
		.replaceAll(":itemId", param.getItemId());
	}
	
	private Response buildResonse(BaseResponse response){
		response.setStatus(new ResponseResult(ServiceConst.SUCCESS_CODE, ServiceConst.SUCCESS_MSG));
        return Response.ok().entity(response).build();
	}
	/**
	 * 处理数据库返回数据
	 * @param baseInfos
	 */
	public  void dealReslutData(List<BaseInfo> baseInfos){
		// 转换行业id为行业名称
		for (BaseInfo baseInfo:baseInfos){
			String industryId = baseInfo.getItemName();
			String industryName = PlatFormUtil.getPlatformV(industryId);
			baseInfo.setItemName(industryName);
		}
	}
	/**
	 * 设置环境
	 * @param param
	 * @param transPlatIdFlag
	 * @param sql
	 * @param seriesName
	 * @param returnSeriesType
	 * @param returnItemIdType
	 */
	public void setContext(ParamBean param,List<String> transList,Boolean transPlatIdFlag,
			String sql,String seriesName,Class<?> returnSeriesType,
			Class<?> returnItemIdType, ComponentContext context){
//		List<String> trasList = new ArrayList<String>();
//    	trasList.add("startDate");
//    	trasList.add("endDate");
    	context.setTransParam(transList);
    	context.setParamBean(param);
    	context.setDealPlatIdFlag(transPlatIdFlag);
    	context.setSql(sql);
    	Map<String,String> utilMap = new HashMap<String,String>();
    	utilMap.put("seriesName", seriesName);
    	context.setUntilMap(utilMap);
    	context.setReturnType(returnSeriesType);
    	context.setItemIdtype(returnItemIdType);
	}
	@Override
	public  Response extractDataAndBuildChart(BaseDaoCallback callBack,
			BaseChartBuilder baseChartBuilder, ComponentContext context) throws Exception {
		String realSql = dealSqlWithParam(context.getSql(),context.getParamBean(),context.getTransParam());
		logger.info("query sql: "+realSql);
		List<BaseInfo> baseInfos = callBack.getListBySql(baseDao, realSql);
		if(context.getDealPlatIdFlag()) {
			dealReslutData(baseInfos);
		}
        // 不要使用Autowired注入，否则原型没有用
        BaseResponse response = SpringContextUtil.getBean(BaseResponse.class);
        baseChartBuilder.buildData(baseInfos,response,context);
        return buildResonse(response);
	}
	
	@Override
	public  Response extractDataAndBuildChartBySqlMap(BaseDaoCallback callBack, Map<String,String> sqlMap,
			BaseChartBuilder baseChartBuilder, ComponentContext context) throws Exception{
		Map<String,String> realSqlMap = new HashMap<String,String>();
		for (Entry<String, String> entry : sqlMap.entrySet()) {
			String realSql = dealSqlWithParam(entry.getValue(),context.getParamBean(),context.getTransParam());
			realSqlMap.put(entry.getKey(), realSql);
			logger.info("query sql: "+realSql);
		}
		List<BaseInfo> baseInfos = callBack.getListBySqlMap(baseDao, realSqlMap);
        BaseResponse response = SpringContextUtil.getBean(BaseResponse.class);
        baseChartBuilder.buildData(baseInfos,response,context);
        return buildResonse(response);
	}
}
