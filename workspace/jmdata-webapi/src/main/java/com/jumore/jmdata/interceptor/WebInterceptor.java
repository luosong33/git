package com.jumore.jmdata.interceptor;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @author Wuzb
 * @since 2016-06-15
 */
public class WebInterceptor extends HandlerInterceptorAdapter {
	public static Log log = LogFactory.getLog(WebInterceptor.class);

	@Autowired
	private LocaleResolver localeResolver;

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		// Cookie cook = new Cookie("language", "en_US");
		// response.addCookie(cook);
		localeResolver.setLocale(request, response, Locale.CHINESE);

		return super.preHandle(request, response, handler);
	}

}
