package com.jumore.jmdata.controller.banner;

import com.jumore.jmdata.model.vo.common.RespModel;
import com.jumore.jmdata.service.banner.IBannerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by root on 11/1/16.
 */
@Controller
@RequestMapping(value = "/v1/banner")
public class BannerController {

    @Resource
    private IBannerService bannerServiceImpl;

    /**
     *  load table data
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public RespModel list(String limit) {
        RespModel model = new RespModel();
        List<Map> fileList = bannerServiceImpl.getBannerAndFileList(limit);
        model.setRows(fileList);
        return model;
    }
}
