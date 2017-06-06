package com.jumore.dove.demo.controller;

import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jumore.dove.aop.annotation.PublicMethod;
import com.jumore.dove.demo.common.util.SessionHelper;
import com.jumore.dove.demo.entity.DDUser;

@Controller
@RequestMapping(value = "")
public class IndexController {


    @RequestMapping(value = "index")
    public ModelAndView index(String returnUrl) throws Exception {
        ModelAndView mv = new ModelAndView();
        DDUser user = SessionHelper.getUser();
        if(user!=null){
            mv.addObject("username", "rename_me");
        }
        return mv;
    }

    @PublicMethod
    @RequestMapping(value = "releasenotes")
    public ModelAndView releasenotes() throws Exception {
        ModelAndView mv = new ModelAndView();
        InputStream in = this.getClass().getResourceAsStream("/version.txt");
        try{
            String version = IOUtils.toString(in);
            mv.addObject("svnVersion", version);
        }catch(Exception ex){
            
        }
        return mv;
    }
}
