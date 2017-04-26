package cn.ls.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class MyInterceptor implements HandlerInterceptor {

	/**
	 * 执行时机：页面解析完成之后，主要做运行期监控的作用
	 */
	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception ex)
			throws Exception {
		System.out.println("afterCompletion...");
		System.out.println(ex.getMessage());
		System.out.println("====================");

	}

	/**
	 * 执行时机：Controller执行完成并且视图解析器解析页面之前
	 */
	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView mv) throws Exception {
		System.out.println("postHandler...");
		Map<String, Object> map = mv.getModel();
		map.put("test", "append something...");
	}

	/**
	 * 执行时机：在Controller执行之前执行
	 * 返回值是Boolean：ture代表放行，false不能访问Controller
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object arg2) throws Exception {
		//第一步获得权限点：通常是一个url
		
		//第二步通过request拿到user从而拿到角色并且拿到权限集合
		
		//判断权限集合中是否存在获得的权限点,如果存在就放行返回true，如果不存在就重定向返回false
		System.out.println("preHandler...");
		
		return true;
	}

}
