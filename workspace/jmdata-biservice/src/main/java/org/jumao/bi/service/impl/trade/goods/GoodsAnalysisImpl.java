package org.jumao.bi.service.impl.trade.goods;


import com.google.gson.Gson;
import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.jumao.bi.constant.ServiceConst;
import org.jumao.bi.entites.ResponseResult;
import org.jumao.bi.entites.trade.register.vo.GroupByVo;
import org.jumao.bi.entites.trade.register.vo.TimeTotalVo;
import org.jumao.bi.service.impl.trade.register.ChartBasicService;
import org.jumao.bi.service.trade.goods.GoodsAnalysis;
import org.jumao.bi.utis.LogUtils;
import org.jumao.bi.utis.RespUtils;
import org.jumao.bi.utis.StringUtils;
import org.jumao.bi.utis.constants.CN;
import org.jumao.bi.utis.constants.Key;

import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GoodsAnalysisImpl extends ChartBasicService implements GoodsAnalysis {

    private Logger logger = Logger.getLogger(this.getClass());
    private Gson gson = new Gson();



    public Response getNewRegLineChart(String platform, String startDate, String endDate) throws Exception {
        LogUtils.writeLogs(logger, checkAndlogDateRange(platform, startDate, endDate));

        boolean accurateToHour = checkAccurateToHour(startDate, endDate);
        List<TimeTotalVo> list = goodsDao.getNewlyIncrBy(platform, startDate, endDate, accurateToHour);
        return Response.ok().entity(getLineChartFromNewRegs(list, CN.Goods_Newlyincr, startDate, endDate, true, accurateToHour)).build();
    }


    public Response getIndustryPieChart(String platform, String startDate, String endDate) throws Exception {
        LogUtils.writeLogs(logger, checkAndlogDateRange(platform, startDate, endDate));

        List<GroupByVo> list = goodsDao.getIndustryPieBy(platform, startDate, endDate);
        return Response.ok().entity(getPieChartFromGroupByVo(list, industryIdNameMap)).build();
    }


    public Response getCategoryPieChart(String industryId, String platform, String startDate, String endDate) throws Exception {
        RespUtils.checkIndustryId(industryId);
        LogUtils.writeLogs(logger, checkAndlogDateRange(platform, startDate, endDate));

        List<GroupByVo> list = goodsDao.getCategoryPieBy(industryId, platform, startDate, endDate);
        List<GroupByVo> cateIdNameVos = goodsDao.getCateIdNameVosBy(industryId);
        return Response.ok().entity(getPieChartFromGroupByVo(list, getTypeNameMapFrom(cateIdNameVos))).build();
    }


    //这里的 industry 或 indus 全都是 行业中文名
    public Response getIndustryDiff(String platform, String startDate, String endDate) throws Exception {
        try {
            Map<String, Double> indusAvgMoneyMap = getIndusAvgMoneyMap(startDate, endDate);
            Map<String, Set<String>> indusLoginCompIdsMap = getIndustryLoginCompIdsMap(startDate, endDate);
            Map<String, Long> indusOrderCountsMap = getIndustryOrderCountsMap(indusLoginCompIdsMap);

            JSONArray resJArr = getResJArr(indusAvgMoneyMap, indusLoginCompIdsMap, indusOrderCountsMap);
            ResponseResult rr = new ResponseResult(ServiceConst.SUCCESS_CODE, ServiceConst.SUCCESS_MSG);
            JSONObject resJObj = new JSONObject(gson.toJson(rr));
            resJObj.put(Key.DATA, resJArr);

            return Response.ok().entity(StringUtils.getUtf8Bytes(resJObj.toString())).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



}
