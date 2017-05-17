/**
 * 
 */
package com.jumore.jmbi.controller;

import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jumore.jmbi.common.requests.CommResponse;
import com.jumore.jmbi.common.util.Consts;

/** 
*登陆控制器
* @author: luochao
* @since: 2016年8月1日  下午1:25:54
* @history:
*/
@Controller
public class LoginController {

    /**
     * 登陆页
     *
     * @param request
     * @return 
     * @history
     */
    @RequestMapping(value = "/login")
    public ModelAndView login(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        return mv;
    }

    /**
     * 登陆
     *
     * @param username
     * @param password
     * @throws Exception 
     * @history
     */
    @RequestMapping(value = "/doLogin")
    @ResponseBody
    public CommResponse doLogin(String username, String password) throws Exception {
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            SecurityUtils.getSubject().login(token);
            return CommResponse.success(4);
        } catch (Exception ex) {

            return CommResponse.failure(ex.getCause().getMessage());
        }
    }

    /**
     * 登出
     *
     * @return
     * @throws Exception 
     * @history
     */
    @RequestMapping(value = "/doLogout")
    public String doLogout() throws Exception {
        SecurityUtils.getSubject().logout();
        SecurityUtils.getSubject().getSession().removeAttribute(Consts.Session_User_Key);
        return "redirect:/login";
    }
    
    
    @RequestMapping(value = "/releasenotes")
    public ModelAndView releasenotes(){
        InputStream in = this.getClass().getResourceAsStream("/version.txt");
        ModelAndView mv = new ModelAndView();
        try{
            String version = IOUtils.toString(in);
            mv.addObject("svnVersion", version);
        }catch(Exception ex){
            
        }
     return mv;
    }
}
