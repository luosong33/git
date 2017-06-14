package org.jumao.bi.service.impl.jyt.register;

import com.google.gson.Gson;
import org.apache.log4j.Logger;
import org.jumao.bi.dao.jyt.JytRegIncrAndAuthDao;
import org.jumao.bi.dao.trade.TradeRegisterDao;
import org.jumao.bi.entites.trade.register.vo.GroupByVo;
import org.jumao.bi.entites.trade.register.vo.TimeTotalVo;
import org.jumao.bi.service.impl.trade.register.ChartBasicService;
import org.jumao.bi.service.jyt.register.JytRegIncrAndAuthSvc;
import org.jumao.bi.utis.GeneralUtils;
import org.jumao.bi.utis.LogUtils;
import org.jumao.bi.utis.constants.CN;
import org.jumao.bi.utis.constants.Table;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.core.Response;
import java.util.List;

/**
 * @author chen qian
 */
public class JytRegIncrAndAuthSvcImpl extends ChartBasicService implements JytRegIncrAndAuthSvc {

    private Logger logger = Logger.getLogger(this.getClass());
    private Gson gson = new Gson();

    @Autowired
    private TradeRegisterDao tradeRegisterDao;

    @Autowired
    private JytRegIncrAndAuthDao jytRegIncrAndAuthDao;


    /**
     */
    public Response getNewRegLineChart(String platform, String startDate, String endDate) throws Exception {
        try {
            LogUtils.writeLogs(logger, checkAndlogDateRange(platform, startDate, endDate));

            boolean accurateToHour = checkAccurateToHour(startDate, endDate);
            List<TimeTotalVo> list = tradeRegisterDao.getNewlyIncrBy(
                    Table.Lg_Base_Company_User, platform, startDate, endDate, accurateToHour);
            return Response.ok().entity(
                    getLineChartFromNewRegs(list, CN.Reg_Newlyincr, startDate, endDate, true, accurateToHour)).build();
        } catch (Exception e) {
            throw GeneralUtils.getWrapEx(e);
        }
    }

    /**
     */
    public Response getRegAreaDist(String platform, String startDate, String endDate) throws Exception {
        try {
            LogUtils.writeLogs(logger, checkAndlogDateRange(platform, startDate, endDate));

            List<GroupByVo> list = jytRegIncrAndAuthDao.getRegAreaDist(startDate, endDate, 0);
            return Response.ok().entity(getPieChartFromGroupByVo(list, getProvinceCodeNameMap(list))).build();
        } catch (Exception e) {
            throw GeneralUtils.getWrapEx(e);
        }
    }

    @Override
    public Response getRegAreaDistTop10(String platform, String startDate, String endDate) throws Exception {
        return null;
    }


    /**
     */
    public Response getNewRegPieChart(String platform, String startDate, String endDate) throws Exception {
        try {
            LogUtils.writeLogs(logger, checkAndlogDateRange(platform, startDate, endDate));

            List<GroupByVo> list = tradeRegisterDao.getDataSrcIncrBy(Table.Lg_Base_Company_User, platform, startDate, endDate);
            return Response.ok().entity(getPieChartFromGroupByVo(list, dataSrcNameMap)).build();
        } catch (Exception e) {
            throw GeneralUtils.getWrapEx(e);
        }
    }

}

