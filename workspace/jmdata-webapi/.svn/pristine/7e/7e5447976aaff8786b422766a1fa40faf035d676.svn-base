package com.jumore.jmdata.controller.common;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jumore.dove.controller.base.BaseController;

/**
 * 
 * 文件下载处理类
 * 
 * @author W 2012-3-27
 * 
 */
@Scope("prototype")
@Controller
@RequestMapping("/download")
public class DownLoadContorl extends BaseController {

    private static final int    UNSPECIFIED = -1;
    private static final int    MAC         = 0;
    private static final int    LINUX       = 1;
    private static final int    WINDOWS     = 2;
    private static final int    SOLARIS     = 3;
    private static final int    FREEBSD     = 4;
    private static int          osType;

    static {
        String osName = System.getProperty("os.name");
        if (osName.startsWith("Linux")) {
            osType = LINUX;
        } else if (osName.startsWith("Mac") || osName.startsWith("Darwin")) {
            osType = MAC;
        } else if (osName.startsWith("Windows")) {
            osType = WINDOWS;
        } else if (osName.startsWith("Solaris") || osName.startsWith("SunOS")) {
            osType = SOLARIS;
        } else if (osName.startsWith("FreeBSD")) {
            osType = FREEBSD;
        } else {
            osType = UNSPECIFIED;
        }
    }

    @RequestMapping(value = "/load")
    public ModelAndView down(HttpServletRequest request, HttpServletResponse response, String filePath, String orFileName) {
        filePath = filePath.substring(1, filePath.length());
        //System.out.println(filePath);

        filePath = request.getSession().getServletContext().getRealPath("/") + "/" + filePath;
        //System.out.println(filePath);

        filePath = filePath + "/" + orFileName;

        //System.out.println(filePath);

        File _file = new File(filePath);
        if (!_file.exists()) {
            //String message = "目标文件不存在!" + "  " + orFileName + "," + filePath;
            //System.out.println(message);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("errorMsg", "目标文件不存在!");
            return new ModelAndView("base/errorPage", map);
        } else {
            try {
                // 读文件
                //System.out.println(12222);
                InputStream fin = new BufferedInputStream(new FileInputStream(_file));
                byte[] buffer = new byte[fin.available()];
                fin.read(buffer);
                fin.close();
                response.reset();
                orFileName = getFileName(request, orFileName);
                //System.out.println(orFileName);
                response.setHeader("Content-Disposition", "attachment;filename=" + orFileName);
                response.setHeader("Content-Length", _file.length() + "");
                response.setContentType("application/octet-stream");
                OutputStream out = new BufferedOutputStream(response.getOutputStream());
                out.write(buffer);
                out.flush();
                out.close();
                //System.out.println(21212);
            } catch (Exception e) {
                logHelper.getBuilder().error("", e);
            }
            return null;
        }

    }

    public static String getFileName(HttpServletRequest request, String fileName) {
        String downloadName = fileName;
        try {
            String header = request.getHeader("User-Agent");
            // Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; FDM; CIBA)
            // Opera/9.60 (Windows NT 5.1; U; en) Presto/2.1.1
            // Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.0.6)
            // Gecko/2009011913 Firefox/3.0.6
            header = (header == null ? "" : header.toLowerCase());
            if (DownLoadContorl.isWindows()) {// Server is Windows
                if (header.indexOf("msie") > -1) {
                    downloadName = downloadName.replaceAll(" ", "20%");
                    downloadName = new String(downloadName.getBytes("gb2312"), "ISO8859-1");
                } else if (header.indexOf("mozilla") > -1) {
                    downloadName = downloadName.replaceAll(" ", "_");
                    downloadName = new String(downloadName.getBytes(), "ISO8859-1");
                } else if (header.indexOf("opera") > -1) {
                    downloadName = downloadName.replaceAll(" ", "_");
                    downloadName = new String(downloadName.getBytes(), "ISO8859-1");
                } else {
                    downloadName = downloadName.replaceAll(" ", "_");
                    downloadName = new String(downloadName.getBytes(), "ISO8859-1");
                }
            } else {// Server is Linux
                if (header.indexOf("msie") > -1) {
                    downloadName = URLEncoder.encode(downloadName, "UTF8");
                } else if (header.indexOf("mozilla") > -1) {
                    downloadName = downloadName.replaceAll(" ", "_");
                    downloadName = new String(downloadName.getBytes("UTF-8"), "ISO8859-1");
                } else if (header.indexOf("opera") > -1) {
                    downloadName = downloadName.replaceAll(" ", "_");
                    downloadName = new String(downloadName.getBytes(), "ISO8859-1");
                } else {
                    downloadName = downloadName.replaceAll(" ", "_");
                    downloadName = new String(downloadName.getBytes(), "ISO8859-1");
                }
            }
        } catch (Exception e) {
        }
        return downloadName;
    }

    private static final boolean isWindows() {
        return osType == WINDOWS;
    }

}
