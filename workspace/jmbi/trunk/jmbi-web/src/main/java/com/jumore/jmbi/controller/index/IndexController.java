package com.jumore.jmbi.controller.index;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jumore.dove.controller.base.BaseController;
import com.jumore.jmbi.common.util.Consts;

@Controller
public class IndexController extends BaseController {
    @RequestMapping("/index")
    public ModelAndView Index(HttpServletRequest request) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        ModelAndView mv = new ModelAndView();
        map.put("user", SecurityUtils.getSubject().getSession().getAttribute(Consts.Session_User_Key));
        mv.addAllObjects(map);
        return mv;
    }

    
    @RequestMapping("/index2")
    public ModelAndView Index2(HttpServletRequest request) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        ModelAndView mv = new ModelAndView();
        map.put("user", SecurityUtils.getSubject().getSession().getAttribute(Consts.Session_User_Key));
        mv.addAllObjects(map);
        return mv;
    }
    
}
