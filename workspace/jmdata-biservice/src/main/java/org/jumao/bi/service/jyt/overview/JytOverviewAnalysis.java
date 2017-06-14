package org.jumao.bi.service.jyt.overview;

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
 * 总览相关接口
 * @author chen qian
 */
@Path(JytUrl.V1_Jyt_Overview)
@Produces(MediaType.APPLICATION_JSON)
public interface JytOverviewAnalysis {

	/**
	 * 可配置的几个总览数字
	 */
	@GET
	@Path(JytUrl.Numbers_Configable)
	public Response getNumbersConfigable(
			@PathParam(Key.Item_Ids) String itemIds,
            @PathParam(Key.Platform) String platform,
            @PathParam(Key.Start_Date) String startDate,
            @PathParam(Key.End_Date) String endDate) throws Exception;

	/**
	 * 总览折线图
	 */
	@GET
	@Path(JytUrl.Linechart_By_Itemid)
	public Response getLineChartByItemId(
			@PathParam(Key.Item_Id) String itemId,
			@PathParam(Key.Platform) String platform,
			@PathParam(Key.Start_Date) String startDate,
			@PathParam(Key.End_Date) String endDate) throws Exception;


	/**
	 * 总览物流线路
	 */
	@GET
	@Path(JytUrl.Transport_Line_Piechart)
	public Response getTransportLinePieChart(
			@PathParam(Key.Platform) String platform,
			@PathParam(Key.Start_Date) String startDate,
			@PathParam(Key.End_Date) String endDate) throws Exception;


	/**
	 * 需求发布数 top5
	 */
	@GET
	@Path(JytUrl.Require_Top5_Barchart)
	public Response getRequireTop5BarChart(
			@PathParam(Key.Platform) String platform,
			@PathParam(Key.Start_Date) String startDate,
			@PathParam(Key.End_Date) String endDate) throws Exception;

}
