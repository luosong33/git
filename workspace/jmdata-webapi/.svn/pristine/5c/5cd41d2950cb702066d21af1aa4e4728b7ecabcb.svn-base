package com.jumore.jmdata.controller.common;

import com.jumore.dove.controller.base.BaseController;
import com.jumore.jmdata.common.util.properties.PropertiesBean;
import com.jumore.jmdata.service.common.IFileService;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/v1/file")
public class FileController extends BaseController {

	@Autowired
    private IFileService fileServiceImpl;
    
    @Autowired
    private PropertiesBean propertiesBean;

    @RequestMapping(value = "/uploader")
    public ModelAndView Index(HttpServletRequest request) throws Exception {
        ModelAndView mv = new ModelAndView();
        return mv;
    }

    /**
     * 下载网络文件
     * @param response
     * @throws MalformedURLException
     */
    @RequestMapping(value = "/download")
    public void downloadNet(String reportId, HttpServletResponse response) throws IOException {
        Map<String,String> map = this.getFileNameAndPath(reportId);
        String fileName = (String) map.get("fileName");
        String filePath = (String) map.get("filePath");
        filePath = propertiesBean.getWebImageViewPath() + filePath;

        URLConnection conn = null;
        InputStream input = null;
        OutputStream output = null;

        try {
            response.reset();
            URL url = new URL(filePath);
            conn = url.openConnection();
            input = conn.getInputStream();
            output = new BufferedOutputStream(response.getOutputStream());
            // 先通知浏览器以下载的方式打开文件（即弹出下载框）,再读取文件
            response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            int n = 0;
            byte[] buffer = new byte[1024];
            while ((n = input.read(buffer)) > -1) {
                output.write(buffer, 0, n);
            }
            output.flush();
            response.setHeader("Content-Length", conn.getContentLength() + "");
            response.setHeader("Content-Type", "application/octet-stream;charset=UTF-8");
            response.flushBuffer();
        } catch (MalformedURLException e) {
            response.setHeader("Content-Type", "text/html;charset=UTF-8");
            response.getWriter().write("文件不存在!");
        } catch (IOException e) {
            response.setHeader("Content-Type", "text/html;charset=UTF-8");
            response.getWriter().write("文件不存在!");
        } finally {
            if(input != null) {
                input.close();
            }
            if(output != null) {
                output.close();
            }
        }
    }

    /**
     * 获取文件名和路径
     * @param reportId
     * @return
     */
    private Map<String,String> getFileNameAndPath(String reportId) {
        Map<String,String> map = new HashMap<String,String>();
        com.jumore.jmdata.dao.entity.common.File myFile = fileServiceImpl.getFileByPkValue(Long.valueOf(reportId), "JSJ_REPORT");
        String path = myFile.getFilePath();
        String name = myFile.getFileName();
        if(StringUtils.isNotEmpty(name)) {
            String[] nameArr = name.trim().split(",");
            String[] pathArr = path.trim().split(",");
            String fileName = "";
            String filePath = "";
            if(pathArr != null && pathArr.length > 0) {
                if(pathArr.length == 1) {
                    filePath = pathArr[0];
                    fileName = nameArr[0];
                } else {
                    filePath = pathArr[1];
                    fileName = nameArr[1];
                }
            }
            map.put("fileName", fileName);
            map.put("filePath", filePath);
            return map;
        } else {
            map.put("fileName", null);
            map.put("filePath", null);
            return map;
        }
    }
}
