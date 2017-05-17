package org.jumao.bi.service.impl.trade.operate;

import org.apache.log4j.Logger;

import org.jumao.bi.constant.ServiceConst;
import org.jumao.bi.dao.trade.operate.IOperationGoodsDao;
import org.jumao.bi.entites.ParamBean;
import org.jumao.bi.entites.ResponseResult;
import org.jumao.bi.entites.operate.GoodsCateResponse;
import org.jumao.bi.entites.operate.GoodsInfo;
import org.jumao.bi.service.trade.operate.IOperationGoodsSvc;
import org.jumao.bi.utis.LogUtils;
import org.jumao.bi.utis.PlatFormUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.ws.rs.core.Response;

/**
 * Created by Administrator on 2017/5/8.
 */
public class OperationGoodsSvcImpl implements IOperationGoodsSvc {

	@Autowired
    private IOperationGoodsDao operationGoodsDao;
    private Logger logger = Logger.getLogger(this.getClass());
    
    public Response getGoodsCate(String platform, String startDate, String endDate) throws Exception{
    	LogUtils.writeLogs(logger, "get goods cate start.");

        GoodsCateResponse response = new GoodsCateResponse();
        
        String nStartDate = new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("yyyyMMdd").parse(startDate));
        String nEndDate = new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("yyyyMMdd").parse(endDate));
        
        String nPlatform = PlatFormUtil.getPlatformV(platform);
    	List<GoodsInfo> goodsInfos = operationGoodsDao.getGoodsCate(new ParamBean(nPlatform, nStartDate, nEndDate));
        OperationGoodsSvcHelper.buildGoodsPieChart(goodsInfos, response);
        
        response.setStatus(new ResponseResult(ServiceConst.SUCCESS_CODE, ServiceConst.SUCCESS_MSG));

        LogUtils.writeLogs(logger, "end goods cat.");
        return Response.ok().entity(response).build();
        
    }

}
