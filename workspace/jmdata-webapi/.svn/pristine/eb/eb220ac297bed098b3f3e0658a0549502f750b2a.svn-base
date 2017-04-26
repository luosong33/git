package com.jumore.jmdata.controller.index;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jumore.dove.controller.base.BaseController;
import com.jumore.jmdata.common.util.Consts;

@Controller
public class IndexController extends BaseController {
    @RequestMapping("/v1/index")
    public ModelAndView Index(HttpServletRequest request) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        ModelAndView mv = new ModelAndView();
        map.put("user", SecurityUtils.getSubject().getSession().getAttribute(Consts.Session_User_Key));
        mv.addAllObjects(map);
        return mv;
    }

    /**
     * 显示每次版本发布信息
     * @return
     */
	@RequestMapping(value = "/releasenotes")
	public ModelAndView releasenotes() {
		InputStream in = this.getClass().getResourceAsStream("/version.txt");
		ModelAndView mv = new ModelAndView();
		try {
			String version = IOUtils.toString(in);
			mv.addObject("svnVersion", version);
		} catch (Exception ex) {

		}
		return mv;
	}
}
