package com.jumore.jmdata.controller.es;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jumore.dove.controller.base.BaseController;
import com.jumore.jmdata.model.param.es.SearchCollectEntity;
import com.jumore.jmdata.model.vo.common.RespModel;
import com.jumore.jmdata.service.es.ISearchCollectService;

/**
 * Created by Administrator on 2017/4/6.
 */

@Controller()
@RequestMapping("/v1/es")
public class SearchKeywordCollectController  extends BaseController{

    @Autowired
    private ISearchCollectService esSearchService;


    @RequestMapping(value = "/searchKeywordCollect")
    @ResponseBody
    public RespModel searchKeywordCollect(String agrs) throws Exception {
    	RespModel model = new RespModel();
       SearchCollectEntity entity = new SearchCollectEntity();
        entity.setAuthor(agrs);
        esSearchService.mianService(entity);
        model.setCode(4);
        return model;
    }

}
