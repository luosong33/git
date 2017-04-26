package cn.ls.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test1")//@RequestMapping标注在类上用来标识唯一一个Controller，起到命名空间的作用
public class TestController1 {
	
	@RequestMapping("/toAjax.do")
	public String toAjax(){
		return "ajax";
	}
}
