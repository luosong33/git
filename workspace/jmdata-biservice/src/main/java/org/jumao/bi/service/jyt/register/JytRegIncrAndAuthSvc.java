package org.jumao.bi.service.jyt.register;

import org.jumao.bi.utis.constants.JytUrl;
import org.jumao.bi.utis.constants.Key;
import org.jumao.bi.utis.constants.RegUrl;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


/**
 * 注册分析 日新增用户 和 认证分析 接口
 * @author chen qian
 */
@Path(JytUrl.V1_Jyt_Register)
@Produces(MediaType.APPLICATION_JSON)
public interface JytRegIncrAndAuthSvc {

	/**
	 * 新增注册数 折线图
	 */
	@GET
	@Path(RegUrl.Newlyincr_Linechart)
	public Response getNewRegLineChart(
            @PathParam(Key.Platform) String platform,
            @PathParam(Key.Start_Date) String startDate,
            @PathParam(Key.End_Date) String endDate) throws Exception;


    /**
     * 注册用户地域分布 中国地图
     */
    @GET
    @Path(JytUrl.Reg_Area_Dist)
    public Response getRegAreaDist(
            @PathParam(Key.Platform) String platform,
            @PathParam(Key.Start_Date) String startDate,
            @PathParam(Key.End_Date) String endDate) throws Exception;

    /**
     * 注册用户地域分布 top10 条形图
     */
    @GET
    @Path(JytUrl.Reg_Area_Dist_Top10)
    public Response getRegAreaDistTop10(
            @PathParam(Key.Platform) String platform,
            @PathParam(Key.Start_Date) String startDate,
            @PathParam(Key.End_Date) String endDate) throws Exception;

    /**
     * 新增注册数 饼图
     */
//    @GET
//    @Path(RegUrl.Newlyincr_Piechart)
//    public Response getNewRegPieChart(
//            @PathParam(Key.Platform) String platform,
//            @PathParam(Key.Start_Date) String startDate,
//            @PathParam(Key.End_Date) String endDate) throws Exception;

    /**
     * 新增注册数 饼图
     */
    @GET
    @Path(RegUrl.Newlyincr_Piechart)
    public Response getNewRegPieChart(
            @PathParam(Key.Platform) String platform,
            @PathParam(Key.Start_Date) String startDate,
            @PathParam(Key.End_Date) String endDate) throws Exception;


}
