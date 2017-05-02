package org.jumao.bi.service.register;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


/**
 * 注册分析相关接口
 * @author chen qian
 */
@Path("/v1/register/")
@Produces(MediaType.APPLICATION_JSON)
public interface RegisterAnalysis {
	
	
	@GET
	@Path("/authUAreaDist")
	public Response getAuthUAreaDist();

	@GET
	@Path("/visaUAreaDist")
	public Response getVisaUAreaDist();

	@GET
	@Path("/payUAreaDist")
	public Response getPayUAreaDist();


}
