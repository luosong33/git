package com.jumore.jmdata.controller.industry;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jumore.dove.controller.base.BaseController;
import com.jumore.jmdata.common.requests.CommResponse;
import com.jumore.jmdata.dao.entity.industry.Industry;
import com.jumore.jmdata.model.vo.industry.IndustryVO;
import com.jumore.jmdata.service.industry.IIndustryService;




/**
 * 行业分类
 * @author 1
 *
 */
@Controller
@RequestMapping("/v1/industry")
public class IndustryCateController extends BaseController {
	
	@Autowired
	IIndustryService industryService;

    /**
     * 获取行业分类字典信息
     * @param type
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/industryCate", method = RequestMethod.GET)
    @ResponseBody
    public CommResponse cateList(Long id,String source) throws Exception {
    	List<IndustryVO> result = industryService.getAllBulkIndustry(source);
        return CommResponse.success(result);
    }
    
    /**
     * 获取行业分类字典信息
     * @param type
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    @ResponseBody
    public CommResponse search(String keyword) throws Exception {
    	Industry result = industryService.searchIndustryInfo(keyword);
        return CommResponse.success(result);
    }

    /**
     * 获取行业分类字典信息
     * @param type
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/iec", method = RequestMethod.GET)
    @ResponseBody
    public CommResponse iec(String keyword) throws Exception {
    	List<IndustryVO> result = industryService.getIeCategories();
        return CommResponse.success(result);
    }
    
}
