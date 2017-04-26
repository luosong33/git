package cn.ls.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import cn.ls.model.Person;

@Controller
@RequestMapping("/upload")//@RequestMapping标注在类上用来标识唯一一个Controller，起到命名空间的作用
public class UploadController {
	
	@RequestMapping("/uploadPic.do")
	public String uploadPic(Person person, HttpServletRequest request) throws Exception{
		//强制转换request
		MultipartHttpServletRequest mr =  (MultipartHttpServletRequest) request;
		//接收文件
		CommonsMultipartFile cfile =  (CommonsMultipartFile) mr.getFile("pic");
		//转换成字节数组
		byte[] bfile = cfile.getBytes();
		
		//文件的命名规则：当前上传文件的时间最小精度加上三位随机数
		String fileName = "";
		fileName = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		Random random = new Random();
		for(int i = 0 ; i < 3 ;i++){
			fileName = fileName + random.nextInt(10);
		}
		//xxxxx.jpg
		String origFileName = cfile.getOriginalFilename();
		String suffix = origFileName.substring(origFileName.lastIndexOf("."));
		String path = request.getSession().getServletContext().getRealPath("/");
		OutputStream out = new FileOutputStream(new File(path+"/upload/"+fileName+suffix));
		out.write(bfile);
		out.flush();
		out.close();
		return "success";
	}
	
	@RequestMapping("/toForm")
	public String toForm(){
		return "form";
	}
	
	@InitBinder
	public void initBinder(ServletRequestDataBinder binder){
		binder.registerCustomEditor(Date.class, 
				new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
	}
}
