package org.jumao.bi.service.usertrace;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jumao.bi.entites.usertrace.UserTraces;

/**
 * 查询百度用户行为各种统计数据
 * 
 * @author 1
 *
 */
@Path("/v1/usertrace/")
@Produces(MediaType.APPLICATION_JSON)
public interface IUserTraceSvc {

	/**
	 * 添加用户平台轨迹信息
	 * @return
	 */
	@POST
	@Path("/")
	public Response addUserTraceInfo(UserTraces userTraces);
	



}
