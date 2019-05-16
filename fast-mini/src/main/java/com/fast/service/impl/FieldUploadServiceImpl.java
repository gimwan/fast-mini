package com.fast.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Service;

import com.fast.base.Result;
import com.fast.service.IFieldUploadService;

/**
 * 文件上传
 * @author J
 *
 */
@Service
public class FieldUploadServiceImpl implements IFieldUploadService, Serializable {
	
	private static final long serialVersionUID = 71148004875517941L;

	/**
	 * 上传图片
	 */
	@SuppressWarnings("deprecation")
	@Override
	public Result uploadImage(HttpServletRequest request) throws Exception {
		Result result = new Result();
		
		try {
	        //获取文件需要上传到的路径
			String path = request.getRealPath("/uploadimages") + "/";
	        File dir = new File(path);
	        if (!dir.exists()) {
	            dir.mkdir();
	        }
	        System.out.println("path=" + path);
	
	        request.setCharacterEncoding("utf-8");  //设置编码
	        //获得磁盘文件条目工厂
	        DiskFileItemFactory factory = new DiskFileItemFactory();
	
	        //如果没以下两行设置的话,上传大的文件会占用很多内存，
	        //设置暂时存放的存储室,这个存储室可以和最终存储文件的目录不同
	        /**
	         * 原理: 它是先存到暂时存储室，然后再真正写到对应目录的硬盘上，
	         * 按理来说当上传一个文件时，其实是上传了两份，第一个是以 .tem 格式的
	         * 然后再将其真正写到对应目录的硬盘上
	         */
	        factory.setRepository(dir);
	        //设置缓存的大小，当上传文件的容量超过该缓存时，直接放到暂时存储室
	        factory.setSizeThreshold(1024 * 1024);
	        //高水平的API文件上传处理
	        ServletFileUpload upload = new ServletFileUpload(factory);
        
            List<FileItem> list = upload.parseRequest(request);
            FileItem picture = null;
            for (FileItem item : list) {
                //获取表单的属性名字
                String name = item.getFieldName();
                //如果获取的表单信息是普通的 文本 信息
                if (item.isFormField()) {
                    //获取用户具体输入的字符串
                    String value = item.getString();
                    request.setAttribute(name, value);
                    System.out.println("name=" + name + ",value=" + value);
                } else {
                    picture = item;
                }
            }

            //自定义上传图片的名字
            Date date = new Date();
            String fileName = String.valueOf(date.getTime()) + ".png";
            String destPath = path + fileName;
            System.out.println("destPath=" + destPath);
            
            // 域名
            String scheme = request.getScheme();
            String serverName = request.getServerName();
            int serverPort = request.getServerPort();
            String contextPath = request.getContextPath();
            String domain = scheme + "://" + serverName + ":" + serverPort + contextPath;
            
            String imageUrls = domain + "/uploadimages/" + fileName;
            
            System.out.println(imageUrls);

            //真正写到磁盘上
            File file = new File(destPath);
            OutputStream out = new FileOutputStream(file);
            InputStream in = picture.getInputStream();
            int length = 0;
            byte[] buf = new byte[1024];
            // in.read(buf) 每次读到的数据存放在buf 数组中
            while ((length = in.read(buf)) != -1) {
                //在buf数组中取出数据写到（输出流）磁盘上
                out.write(buf, 0, length);
            }
            in.close();
            out.close();
            
            result.setErrcode(0);
            result.setData(imageUrls);
            result.setMessage("上传成功");
        } catch (FileUploadException fe) {
        	fe.printStackTrace();
           result.setMessage(fe.getMessage());
        } catch (Exception e) {
        	e.printStackTrace();
        	result.setMessage(e.getMessage());
        }
        
        return result;
    }

}