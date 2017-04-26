/**
 * 
 */
package com.jumore.jmbi.controller.common;

import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jumore.dove.controller.base.BaseController;
import com.jumore.jmbi.common.exceptions.BizException;
import com.jumore.jmbi.common.requests.CommResponse;
import com.jumore.jmbi.common.util.Consts;
import com.jumore.jmbi.common.util.validation.Groups;
import com.jumore.jmbi.common.util.validation.ValidationUtils;
import com.jumore.jmbi.dao.entity.permission.Account;
import com.jumore.jmbi.model.param.permission.AccountQueryParam;
import com.jumore.jmbi.model.param.permission.PasswordParam;
import com.jumore.jmbi.model.vo.PageResult;
import com.jumore.jmbi.service.permission.AccountService;

/**
 * 我的账号
 * 
 * @author: luochao
 * @since: 2016年7月29日 上午10:36:54
 * @history:
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

	/** 用户服务 */
	@Autowired
	private AccountService accountService;

	/*
	 * @Resource private SOAService soaService;
	 */

	/**
	 * 管理员重置密码
	 *
	 * @param id
	 * @return
	 * @throws Exception
	 * @history
	 */
	@RequestMapping(value = "/password")
	public ModelAndView password(Long id) throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.addObject("id", id);
		return mv;
	}
	
	/**
	 * 管理员重置密码
	 *
	 * @param id
	 * @return
	 * @throws Exception
	 * @history
	 */
	@RequestMapping(value = "/user")
	public ModelAndView user() throws Exception {
		ModelAndView mv = new ModelAndView();
		Account account = (Account) SecurityUtils.getSubject().getSession()
				.getAttribute(Consts.Session_User_Key);
		mv.addObject("account", account);
		return mv;
	}

	/**
	 * 修改个人密码
	 *
	 * @param param
	 * @return
	 * @throws Exception
	 * @history
	 */
	@RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
	@ResponseBody
	public CommResponse updatePassword(PasswordParam param, boolean che)
			throws Exception {
		Account account = (Account) SecurityUtils.getSubject().getSession()
				.getAttribute(Consts.Session_User_Key);
		if (account != null) {
			param.setId(account.getId());
			ValidationUtils.validateEntityThrows(param, Groups.update.class);
			accountService.updatePassword(param, true);
		}

		return CommResponse.success();
	}

	/**
	 * 重置密码
	 *
	 * @param param
	 * @return
	 * @throws Exception
	 * @history
	 */
	@RequestMapping(value = "/reSetPassword", method = RequestMethod.POST)
	@ResponseBody
	public CommResponse reSetPassword(Integer id) throws Exception {
		Account account = (Account) SecurityUtils.getSubject().getSession()
				.getAttribute(Consts.Session_User_Key);
		if (account != null) {

			accountService.reSetPassword(id, account);
		}

		return CommResponse.success();
	}

	/**
	 * 打开账户列表页面
	 * 
	 * @param queryParam
	 * @return
	 * @history:
	 */
	@RequestMapping(value = "/list")
	public @ResponseBody ModelAndView list(Account queryParam) {
		ModelAndView model = new ModelAndView();
		// HelpCategories cate = new HelpCategories();
		// cate.setIsDel(0);
		// List<Object> list = categoriesService.listByExample(cate);
		// model.addObject("list", list);
		return model;
	}

	@RequestMapping(value = "/listData", method = RequestMethod.POST)
	public @ResponseBody PageResult<Account> listData(
			AccountQueryParam queryParam, String startDate, String endDate) {
		// queryParam.addSort("createtime", SortType.DESC);
		// queryParam.setDelflag(0);// 只查询未删除的记录

		// if(isNotNull(startDate)){//查询开始时间
		// queryParam.setBdate(DateUtils.parseDate(startDate,
		// DateUtils.DAY_FORMAT));
		// }
		// if(isNotNull(endDate)){//查询结束时间
		// queryParam.setEdate(DateUtils.parseDate(endDate,
		// DateUtils.DAY_FORMAT));
		// }
		queryParam.setBdate(startDate);
		queryParam.setEdate(endDate);
		queryParam.setIsDel(0);
		queryParam.setRole(1);//列表中只显示：普通管理员；不显示超：级管理员
		return accountService.queryPage(queryParam);
	}

	/**
	 * 打开新增页面
	 *
	 * @return
	 * @throws Exception
	 * @history
	 */
	@RequestMapping(value = "/edit")
	public ModelAndView edit(Long id) throws Exception {
		ModelAndView mv = new ModelAndView();
//		mv.addObject("id", id);
		return mv;
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
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public @ResponseBody CommResponse add(Account account,
			HttpServletResponse response) throws BizException {// @RequestParam(required
		Account existOne = new Account();
		existOne.setAccount(account.getAccount());
		boolean isExist = (null != accountService.getByExample(existOne));
		if (!isExist) {
			accountService.add(account);			
			return CommResponse.success();
		} else {
			return CommResponse.failure("账号已存在，请修改重试！");
		}
						
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
		accountService.deleteById(id);
		return CommResponse.success();
	}

	/**
	 * 设置：发布状态
	 *
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/setStatus")
	@ResponseBody
	public CommResponse setStatus(Integer id, int status) throws Exception {
		return CommResponse.success(accountService.setStatus(id, status));
	}

}
