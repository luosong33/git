package com.jumore.jmdata.controller.ads;

import com.jumore.jmdata.dao.entity.common.File;
import com.jumore.jmdata.dao.entity.industry.Industry;
import com.jumore.jmdata.model.param.ads.AdsParam;
import com.jumore.jmdata.model.param.common.FileParam;
import com.jumore.jmdata.model.vo.common.PageRespModel;
import com.jumore.jmdata.model.vo.common.RespModel;
import com.jumore.jmdata.service.ads.IAdsService;
import com.jumore.jmdata.service.common.IFileService;
import com.jumore.jmdata.service.industry.IIndustryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by root on 11/1/16.
 */
@Controller
@RequestMapping(value = "/v1/ads")
public class AdsController {

    @Resource
    private IAdsService adsServiceImpl;

    /**
     *  load table data
     * @param industryCode
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public RespModel list(String industryCode) {
        RespModel model = new RespModel();
        Map map = adsServiceImpl.getAdsByIndustryCode(industryCode);
        model.setRows(map);
        return model;
    }

}
