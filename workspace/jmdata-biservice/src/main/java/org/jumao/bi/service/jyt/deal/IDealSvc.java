package org.jumao.bi.service.jyt.deal;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/v1/jyt/deal")
@Produces(MediaType.APPLICATION_JSON)
public interface IDealSvc {
    /**
     * operate:平台运营情况表.
     * 
     * @author Administrator
     * @date 2017年6月13日 下午1:12:04
     * @param startDate
     * @param endDate
     * @param platform
     * @return
     * @throws Exception
     */
    @GET
    @Path("/operate/{startDate}/{endDate}/{platform}/")
    public Response operate(@PathParam("startDate") String startDate, @PathParam("endDate") String endDate,
            @PathParam("platform") String platform) throws Exception;

    /**
     * translate:交易转化跟踪.
     * 
     * @author Administrator
     * @date 2017年6月13日 下午2:47:51
     * @param startDate
     * @param endDate
     * @param platform
     * @return
     * @throws Exception
     */
    @GET
    @Path("/translate/{startDate}/{endDate}/{platform}/")
    public Response translate(@PathParam("startDate") String startDate, @PathParam("endDate") String endDate,
            @PathParam("platform") String platform) throws Exception;

    /**
     * settle:结算方式占比.
     * 
     * @author Administrator
     * @date 2017年6月13日 下午3:21:46
     * @param startDate
     * @param endDate
     * @param platform
     * @return
     * @throws Exception
     */
    @GET
    @Path("/settle/{startDate}/{endDate}/{platform}/")
    public Response settle(@PathParam("startDate") String startDate, @PathParam("endDate") String endDate,
            @PathParam("platform") String platform) throws Exception;

    /**
     * lineType:运输方式
     * 
     * @author Administrator
     * @date 2017年6月13日 下午4:04:27
     * @param startDate
     * @param endDate
     * @param platform
     * @return
     * @throws Exception
     */
    @GET
    @Path("/lineType/{startDate}/{endDate}/{platform}/")
    public Response lineType(@PathParam("startDate") String startDate, @PathParam("endDate") String endDate,
            @PathParam("platform") String platform) throws Exception;

    @GET
    @Path("/orderSource/{startDate}/{endDate}/{platform}/")
    public Response orderSource(@PathParam("startDate") String startDate, @PathParam("endDate") String endDate,
            @PathParam("platform") String platform) throws Exception;

}
