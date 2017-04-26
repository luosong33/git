package com.jumore.jmdata.controller.help;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jumore.dove.controller.base.BaseController;
import com.jumore.dove.util.ParamMap;
import com.jumore.jmdata.common.exceptions.BizException;
import com.jumore.jmdata.common.requests.CommResponse;
import com.jumore.jmdata.common.util.ParamUtil;
import com.jumore.jmdata.dao.entity.help.HelpCategories;
import com.jumore.jmdata.model.param.BaseParam.SortType;
import com.jumore.jmdata.model.param.help.HelpCategoriesQueryParam;
import com.jumore.jmdata.model.vo.PageResult;
import com.jumore.jmdata.service.help.IHelpCategoriesService;

/**
 * 
 * @author liquan
 *
 * @since: 2016年9月7日 下午2:16:23
 * @history:
 */
@Controller()
@RequestMapping("/v1/helpCategories")
public class HelpCategoriesController extends BaseController {

	@Autowired
	IHelpCategoriesService categoriesService;
 

	/**
	 * 查询善行列表
	 *
	 * @param queryParam
	 * @return
	 */
	@RequestMapping(value = "/list")
	public @ResponseBody ModelAndView list(HelpCategories queryParam) {
		ModelAndView model = new ModelAndView();
		return model;
	}

	/**
	 * 查询善行分页列表
	 *
	 * @param request
	 * @param activityQueryParam
	 * @return 善行列表
	 */
	@RequestMapping(value = "/listData")
	public @ResponseBody PageResult<HelpCategories> listData(
			HelpCategoriesQueryParam queryParam) {
		queryParam.addSort("sort", SortType.ASC);
		// queryParam.setDelflag(0);// 只查询未删除的记录
		return categoriesService.queryHelpCategoriesPage(queryParam);
	}



	/**
	 * 查询善行分页列表
	 *
	 * @param request
	 * @param activityQueryParam
	 * @return 善行列表
	 */
	@RequestMapping(value = "/listBottomData")
	public @ResponseBody CommResponse listBottomData(HelpCategories cate) {
		cate.setBottomShow(1);// 在底部显示的：帮助分类
		// queryParam.addSort("createtime", SortType.DESC);
		// queryParam.setDelflag(0);// 只查询未删除的记录
		ParamMap paramMap = ParamUtil.getParamMap(cate);
		List<HelpCategories> list =	categoriesService.listByParams(HelpCategories.class, ".HelpCategoriesMapper.selectFirstHelpByCate", paramMap);
		 //List<Object> list = categoriesService.listByExample(cate);
		 return CommResponse.success(list);
	}

	/**
	 * 行愿无尽：编辑界面
	 * 
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/edit")
	public @ResponseBody ModelAndView edit(Integer id) {
		ModelAndView model = new ModelAndView();
		if (null != id) {
			HelpCategories categories = categoriesService
					.getHelpCategoriesInfo(id);
			model.addObject("cate", categories);
		}
		return model;
	}

	/**
	 * 功能说明：新增或修改
	 * 
	 * @param sopParam
	 *            id为空时表示新增
	 * @return
	 * @throws BizException
	 *             CommResponse
	 */
	@RequestMapping(value = "/addOrUpdate")
	public @ResponseBody CommResponse addOrUpdate(HelpCategories categories)
			throws BizException {

		if (categories.getId() == null) {
			categoriesService.addHelpCategories(categories);
		} else {
			categoriesService.updateHelpCategories(categories);
		}
		return CommResponse.success();
	}

	/**
	 * 删除
	 *
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/delete")
	@ResponseBody
	public CommResponse delete(Integer id) throws Exception {
		categoriesService.deleteById(HelpCategories.class, id);
		return CommResponse.success();
	}

	// /**
	// * 设置：发布状态
	// *
	// * @param id
	// * @return
	// * @throws Exception
	// */
	// @RequestMapping(value = "/setStatus")
	// @ResponseBody
	// public CommResponse setStatus(long id, int status) throws Exception {
	// return CommResponse.success(categoriesService.setStatus(id, status));
	// }

}
