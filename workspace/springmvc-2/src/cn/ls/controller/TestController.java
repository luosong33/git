package cn.ls.controller;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.ls.model.Person;
import cn.ls.model.User;

@Controller
@RequestMapping("/test")//@RequestMapping标注在类上用来标识唯一一个Controller，起到命名空间的作用
public class TestController {
	
	
	
	/**
	 * 
	 * desc：返回值String:ModelAndView中的View，要返回的是viewName，依然需要视图解析器的解析
	 * author:任亮
	 * mail:renliangjava@163.com
	 * 班级：1219
	 */
	@RequestMapping("/hello.do")
	public String hello(){
		System.out.println("===============hello.do================");
		return "index";
	}
	
	@RequestMapping("/toAjax.do")
	public String toAjax(){
		return "ajax";
	}
	
	/**
	 * 
	 * desc：参数列表里支持HttpServletRequest HttpServletResponse， HttpSession
	 * author:任亮
	 * mail:renliangjava@163.com
	 * 班级：1219
	 */
	@RequestMapping("/toPerson.do")
	public String toPerson(HttpServletRequest request){
		String name = request.getParameter("name");
		System.out.println(name);
		return "index";
	}
	
	/**
	 * 
	 * desc：零散的对参数的接收，在参数列表中直接定义要接收的参数名，参数名和请求的实参名字要一致
	 * author:任亮
	 * mail:renliangjava@163.com
	 * 班级：1219
	 */
	@RequestMapping("/toPerson1.do")
	public String toPerson1(String name, Integer id, Integer age, Integer gender, Date birthday){
		System.out.println(name + "  "+ id + "  "+ age +  "  "+gender + " "+birthday);
		return "index";
	}
	
	/**
	 * 
	 * desc：多选的接收，直接在参数列表定义数组即可，参数名和请求的实参名字要一致
	 * author:任亮
	 * mail:renliangjava@163.com
	 * 班级：1219
	 */
	@RequestMapping("/toPerson2.do")
	public String toPerson2(String[] name){
		for(String myName : name){
			System.out.println(myName);
		}
		return "index";
	}
	
	/**
	 * 
	 * desc：对于多选接收，如果后台在参数列表中定义的不是数组，是字符串就会以“,”把每一项给连接起来
	 * author:任亮
	 * mail:renliangjava@163.com
	 * 班级：1219
	 */
	@RequestMapping("/toPerson3.do")
	public String toPerson3(String name){
		System.out.println(name);
		return "index";
	}
	
	
	@RequestMapping("/toPerson4.do")
	public String toPerson4(Person person){
		System.out.println(person);
		return "index";
	}
	
	/**
	 * 
	 * desc：当request请求访问的时候springmvc会把参数列表中的每一个自定义实体类创建一个对象，
	 * 根据请求中的实参的名字来进行匹配，如果匹配成功，就把值注入，无论参数列表中有多少个对象都可以注入
	 * author:任亮
	 * mail:renliangjava@163.com
	 * 班级：1219
	 */
	@RequestMapping("/toPerson5.do")
	public String toPerson5(Person person, User user, String name){
		System.out.println(person);
		System.out.println(user);
		System.out.println(name);
		return "index";
	}
	
	/**
	 * 
	 * desc：在参数列表中定义request把值带到页面上
	 * 不建议使用
	 * author:任亮
	 * mail:renliangjava@163.com
	 * 班级：1219
	 */
	@RequestMapping("/toPerson6.do")
	public String toPerson6(HttpServletRequest request) throws Exception{
		Person person = new Person();
		person.setId(1);
		person.setName("liuneng");
		person.setGender(1);
		person.setAge(39);
		String str = "1998-01-04";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = format.parse(str);
		person.setBirthday(date);
		request.setAttribute("p", person);
		return "return";
	}
	/**
	 * 
	 * desc：Map在参数列表中的含义是ModelAndView中的Map，存储数据用。
	 * 不建议用
	 * author:任亮
	 * mail:renliangjava@163.com
	 * 班级：1219
	 */
	@RequestMapping("/toPerson7.do")
	public String toPerson7(Map<String, Object> map) throws Exception{
		
		Person person = new Person();
		person.setId(1);
		person.setName("liuneng");
		person.setGender(1);
		person.setAge(39);
		String str = "1998-01-04";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = format.parse(str);
		person.setBirthday(date);
		//相当于request.addAttribute("p", person);
		map.put("p", person);
		return "return";
	}
	
	/**
	 * 
	 * desc：把返回值设置成ModelAndView，使用ModelAndView("return", map)
	 * author:任亮
	 * mail:renliangjava@163.com
	 * 班级：1219
	 */
	@RequestMapping("/toPerson8.do")
	public ModelAndView toPerson8() throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		Person person = new Person();
		person.setId(1);
		person.setName("liuneng");
		person.setGender(1);
		person.setAge(39);
		String str = "1998-01-04";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = format.parse(str);
		person.setBirthday(date);
		//相当于request.addAttribute("p", person);
		map.put("p", person);
		return new ModelAndView("return", map);
	}
	
	/**
	 * 
	 * desc：在参数列表中定义Model
	 * author:任亮
	 * mail:renliangjava@163.com
	 * 班级：1219
	 */
	@RequestMapping("/toPerson9.do")
	public String toPerson9(Model model) throws Exception{
		Person person = new Person();
		person.setId(1);
		person.setName("liuneng");
		person.setGender(1);
		person.setAge(39);
		String str = "1998-01-04";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = format.parse(str);
		person.setBirthday(date);
		//相当于request.addAttribute("p", person);
		model.addAttribute("p", person);
		return "return";
	}
	@RequestMapping("/toPerson10.do")
	public String toPerson10(Model model) throws Exception{
		List<Person> pList = new ArrayList<Person>();
		for(int i = 1; i < 10; i++){
			Person person = new Person();
			person.setId(i);
			person.setName("liuneng");
			person.setGender(1);
			person.setAge(39+i);
			String str = "1998-0"+i+"-04";
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date date = format.parse(str);
			person.setBirthday(date);
			pList.add(person);
		}
		//相当于request.addAttribute("p", person);
		model.addAttribute("pList", pList);
		return "return";
	}
	
	@RequestMapping("/toPerson11.do")
	public void toPerson11(String name, HttpServletResponse response) throws Exception{
		String result = "hello "+name;
		response.getWriter().write(result);
	}
	
	/**
	 * 
	 * desc：ajax访问，返回值void确定不跳页面，参数列表定义PrintWriter用于数据回写
	 * 建议使用
	 * author:任亮
	 * mail:renliangjava@163.com
	 * 班级：1219
	 */
	@RequestMapping("/toPerson12.do")
	public void toPerson12(String name, PrintWriter out){
		String result = "hello "+name;
		out.write(result);
	}
	
	/**
	 * 
	 * desc：默认情况下是同一个Controller内部重定向
	 * redirect:加上同一个Controller内部的requestMapping的值，不要忘记.do
	 * author:任亮
	 * mail:renliangjava@163.com
	 * 班级：1219
	 */
	@RequestMapping("/toRedirectAjax.do")
	public String toRedirectAjax(){
		return "redirect:toAjax.do";
	}
	/**
	 * 
	 * desc：不同Controller之间重定向redirect:/加上要重定向到的地址,注意：一定要有/
	 * author:任亮
	 * mail:renliangjava@163.com
	 * 班级：1219
	 */
	@RequestMapping("/toRedirectAjax1.do")
	public String toRedirectAjax1(){
		return "redirect:/test1/toAjax.do";
	}
	
	
	
	/**
	 * 
	 * desc：@InitBinder做数据绑定用
	 * author:任亮
	 * mail:renliangjava@163.com
	 * 班级：1219
	 */
	@InitBinder
	public void initBinder(ServletRequestDataBinder binder){
		binder.registerCustomEditor(Date.class, 
				new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
	}
	
	
	

}
