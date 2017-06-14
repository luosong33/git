package org.jumao.bi.service.impl.jyt.overview;


import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONObject;
import org.jumao.bi.dao.jyt.JytOverviewDao;
import org.jumao.bi.entites.trade.register.vo.GroupByVo;
import org.jumao.bi.entites.trade.register.vo.TimeTotalVo;
import org.jumao.bi.service.impl.trade.register.ChartBasicService;
import org.jumao.bi.service.jyt.overview.JytOverviewAnalysis;
import org.jumao.bi.utis.GeneralUtils;
import org.jumao.bi.utis.LogUtils;
import org.jumao.bi.utis.StringUtils;
import org.jumao.bi.utis.constants.CN;
import org.jumao.bi.utis.constants.Key;
import org.jumao.bi.utis.exceptions.WrapEx;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.core.Response;
import java.util.List;


/**
 * @author chen qian
 */
public class JytOverviewAnalysisImpl extends ChartBasicService implements JytOverviewAnalysis {

    private Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private JytOverviewDao jytOverviewDao;


    @Override
    public Response getNumbersConfigable(String itemIds, String platform, String startDate, String endDate) throws Exception {
        try {
            LogUtils.writeLogs(logger, checkAndlogDateRange(platform, startDate, endDate));
            String[] idsArr = itemIds.split("-");
            JSONObject retJObj = new JSONObject();

            for (String ele : idsArr) {
                if (NumberUtils.isDigits(ele)) {
                    retJObj.put(ele, jytOverviewDao.getNumbersBy(ele));
                }
            }

            JSONObject resJObj = getRespResultJObj();
            resJObj.put(Key.DATA, retJObj);
            return Response.ok().entity(StringUtils.getUtf8Bytes(resJObj.toString())).build();
        } catch (Exception e) {
            throw GeneralUtils.getWrapEx(e);
        }
    }


    @Override
    public Response getLineChartByItemId(String itemId, String platform, String startDate, String endDate) throws Exception {
        try {
            LogUtils.writeLogs(logger, checkAndlogDateRange(platform, startDate, endDate));

            boolean accurateToHour = checkAccurateToHour(startDate, endDate);
            List<TimeTotalVo> list = jytOverviewDao.getLineChartByItemId(itemId, startDate, endDate, accurateToHour);
            return Response.ok().entity(getLineChartFromNewRegs(list, "", startDate, endDate, true, accurateToHour)).build();
        } catch (Exception e) {
            throw GeneralUtils.getWrapEx(e);
        }
    }


    @Override
    public Response getTransportLinePieChart(String platform, String startDate, String endDate) throws Exception {
        try {
            LogUtils.writeLogs(logger, checkAndlogDateRange(platform, startDate, endDate));

            List<GroupByVo> list = jytOverviewDao.getTransportLineBy(startDate, endDate);
            return Response.ok().entity(getPieChartFromGroupByVo(list, lineTypeNameMap)).build();
        } catch (Exception e) {
            throw GeneralUtils.getWrapEx(e);
        }
    }


    @Override
    public Response getRequireTop5BarChart(String platform, String startDate, String endDate) throws Exception {
        try {
            LogUtils.writeLogs(logger, checkAndlogDateRange(platform, startDate, endDate));

            List<GroupByVo> list = jytOverviewDao.getRequireTop5BarChartBy(startDate, endDate, Top_Ele5);
            return Response.ok().entity(getBarChartFromNewRegs(
                    list, getTypeNameMapFrom(list), CN.Require_Top5)).build();
        } catch (Exception e) {
            throw GeneralUtils.getWrapEx(e);
        }
    }


}
