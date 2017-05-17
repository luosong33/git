/**
 * 
 */
package com.jumore.jmbi.controller.common;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jumore.dove.controller.base.BaseController;
import com.jumore.dove.util.ParamMap;
import com.jumore.jmbi.common.exceptions.BizException;
import com.jumore.jmbi.common.requests.CommResponse;
import com.jumore.jmbi.common.util.Consts;
import com.jumore.jmbi.common.util.validation.Groups;
import com.jumore.jmbi.common.util.validation.ValidationUtils;
import com.jumore.jmbi.dao.entity.permission.Account;
import com.jumore.jmbi.dao.entity.permission.Department;
import com.jumore.jmbi.dao.entity.permission.Role;
import com.jumore.jmbi.model.param.permission.AccountQueryParam;
import com.jumore.jmbi.model.param.permission.DeptParam;
import com.jumore.jmbi.model.param.permission.PasswordParam;
import com.jumore.jmbi.model.param.permission.RoleParam;
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
        Account account = (Account) SecurityUtils.getSubject().getSession().getAttribute(Consts.Session_User_Key);
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
    public CommResponse updatePassword(PasswordParam param, boolean che) throws Exception {
        Account account = (Account) SecurityUtils.getSubject().getSession().getAttribute(Consts.Session_User_Key);
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
        accountService.reSetPassword(id);
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
    public ModelAndView list(Account queryParam) {
        ModelAndView model = new ModelAndView();
        model.addObject("deptList", accountService.listByParams(Department.class, ".DepartmentMapper.select", null));
        return model;
    }

    @RequestMapping(value = "/listData", method = RequestMethod.GET)
    public @ResponseBody PageResult<Account> listData(AccountQueryParam queryParam, String startDate, String endDate) {
        queryParam.setBdate(startDate);
        queryParam.setEdate(endDate);
        queryParam.setIsDel(0);
        queryParam.setRole(1);// 列表中只显示：普通管理员；不显示超：级管理员
        return accountService.queryPage(queryParam);
    }

    /**
     * 打开新增(编辑)页面
     *
     * @return
     * @throws Exception
     * @history
     */
    @RequestMapping(value = "/toEdit")
    @ResponseBody
    public CommResponse toEdit(String type, Integer id) throws Exception {
        Map<String, Object> data = new HashMap<String, Object>();
        if ("addRole_editRole".indexOf(type) > -1) {
            data.put("deptList", accountService.listByParams(Department.class, ".DepartmentMapper.select", null));
            if (id > 0) {
                data.put("role", accountService.get(Role.class, id));
            }
        } else if ("addUser_editUser".indexOf(type) > -1) {
            data.put("deptList", accountService.listByParams(Department.class, ".DepartmentMapper.select", null));
            int deptId = 0;
            if (id > 0) {
                Account account = accountService.get(Account.class, id);
                data.put("account", account);
                deptId = NumberUtils.toInt(account.getDepartment(), 0);
            }
            ParamMap paramMap = new ParamMap();
            paramMap.put("deptId", deptId);
            data.put("deptAndRoleList", accountService.listByParams(Department.class, ".RoleMapper.selectRolesByDeptId", paramMap));
        }
        return CommResponse.success(data);
    }

    /**
     * 功能说明：新增或修改
     * 
     * @param sopParam id为空时表示新增
     * @return
     * @throws BizException CommResponse
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public @ResponseBody CommResponse add(Account account, HttpServletResponse response) throws BizException {// @RequestParam(required
        Integer id = account.getId();
        if (id == null) {
            Account existOne = new Account();
            existOne.setAccount(account.getAccount());
            boolean isExist = (null != accountService.getByExample(existOne));
            if (!isExist) {
                account.setRole(1);
                accountService.add(account);
            } else {
                return CommResponse.failure("账号已存在，请修改重试！");
            }
        } else {
            accountService.update(account);
        }

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
    public CommResponse setStatus(Account account) throws Exception {
        accountService.update(account);
        return CommResponse.success();
    }

    /**
     * 部门：新增或修改
     * 
     * @param sopParam id为空时表示新增
     * @return
     * @throws BizException CommResponse
     */
    @RequestMapping(value = "/addDept", method = RequestMethod.POST)
    public @ResponseBody CommResponse addDept(Department department, HttpServletResponse response) throws BizException {// @RequestParam(required
        Account account = (Account) SecurityUtils.getSubject().getSession().getAttribute(Consts.Session_User_Key);
        department.setUserId(account.getId());
        accountService.save(department);
        return CommResponse.success();
    }

    @RequestMapping(value = "/listRole", method = RequestMethod.GET)
    public @ResponseBody PageResult<Role> listRole(RoleParam roleParam) {
        return accountService.getPageResult(Role.class, roleParam, ".RoleMapper.select");
    }

    @RequestMapping(value = "/listDept", method = RequestMethod.GET)
    public @ResponseBody PageResult<Department> listDept(DeptParam deptParam) {
        return accountService.getPageResult(Department.class, deptParam, ".DepartmentMapper.select");
    }

    /**
     * 删除
     *
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/delDept")
    @ResponseBody
    public CommResponse delDept(Integer id) throws Exception {
        accountService.deleteById(Department.class, id);
        return CommResponse.success();
    }

    /**
     * 角色：新增或修改
     * 
     * @param sopParam id为空时表示新增
     * @return
     * @throws BizException CommResponse
     */
    @RequestMapping(value = "/addRole", method = RequestMethod.POST)
    public @ResponseBody CommResponse addRole(Role role, HttpServletResponse response) throws BizException {// @RequestParam(required
        Integer id = role.getId();
        if (id != null) {
            accountService.update(role);
        } else {
            accountService.save(role);
        }

        return CommResponse.success();
    }

    /**
     * 角色：更改状态
     *
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/setRoleStatus")
    @ResponseBody
    public CommResponse setRoleStatus(Role role) throws Exception {
        accountService.update(role);
        return CommResponse.success();
    }

    /**
     * 角色：得到指定部门下的所有角色
     *
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getRolesByDeptId")
    @ResponseBody
    public CommResponse getRolesByDeptId(Integer deptId) throws Exception {
        ParamMap paramMap = new ParamMap();
        paramMap.put("deptId", deptId);
        paramMap.put("deptAndRoleList", accountService.listByParams(Department.class, ".RoleMapper.selectRolesByDeptId", paramMap));
        return CommResponse.success(paramMap);
    }

}
