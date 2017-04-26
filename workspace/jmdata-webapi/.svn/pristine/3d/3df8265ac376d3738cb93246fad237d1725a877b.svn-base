package com.jumore.jmdata.controller.keyword;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.jumore.jmdata.model.param.keyword.KeywordParam;
import com.jumore.jmdata.model.vo.common.PageRespModel;
import com.jumore.jmdata.service.keyword.IKeywordCloudService;

@Controller
@RequestMapping("/v1/keyword")
public class KeywordController {
	@Autowired
	IKeywordCloudService keywordCloudService;

	/**
	 * load table data
	 * 
	 * @param param
	 * @return
	 */
	@RequestMapping("/list")
	@ResponseBody
	public PageRespModel list(KeywordParam param) {
		param.setEnabled(0);
		return keywordCloudService.getKeywordList(param);
	}
}
