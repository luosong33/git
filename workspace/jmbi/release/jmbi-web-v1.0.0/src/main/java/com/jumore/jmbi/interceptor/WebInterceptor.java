package com.jumore.jmbi.interceptor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Wuzb
 * @since 2016-06-15
 */
public class WebInterceptor extends HandlerInterceptorAdapter{
    public  static Log log    = LogFactory.getLog(WebInterceptor.class);

    /**
     * ftp file address
     */
//    @Value("${web.image.path}")
//    private String webImagePath;
//
//    @Value("${web.image.view.path}")
//    private String webImageViewPath;

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
//        if(modelAndView != null){
//            modelAndView.addObject("webImagePath", webImagePath);
//            modelAndView.addObject("webImageViewPath", webImageViewPath);
//        }
    }
}
