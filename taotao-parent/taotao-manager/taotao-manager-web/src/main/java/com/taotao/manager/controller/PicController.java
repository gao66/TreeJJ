package com.taotao.manager.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.taotao.json.result.PicUploadResult;
import com.taotao.util.FileWriteResult;
import com.taotao.util.FileWriter;

@Controller
@RequestMapping("/pic")
public class PicController {

	@Autowired
	private FileWriter fileWriter;
	
	
	@PostMapping("/upload")
	@ResponseBody
	public PicUploadResult upload( @RequestParam("uploadFile")MultipartFile multipartFile, @RequestParam String dir) throws IOException {
		//System.out.println("multipartFile="+multipartFile);
		//  FileWriter是一个接口，它的实现类可以将文件写进磁盘，写进网络,写进数据库 
		//文件上传的名字  
		String fileName = multipartFile.getOriginalFilename();
		//取文件的扩展名
		String extName = fileName.substring(fileName.lastIndexOf(".")+1);
		//将上传的数据写到特定的目的地
		FileWriteResult result = fileWriter.write(multipartFile.getBytes(), dir,extName);
	
		//  error:  0是成功, 1是失败   
		//  url:    如果文件需要在网络上访问，则需要返回这个url
		//  path:   如果文件存在本系统的磁盘上，可以利用返回的这个真实的磁盘路径来处理这个文件
		//  message: 失败时的消息
		
		PicUploadResult r = new PicUploadResult();
		if(result.getError()==1) {
			r.setError(1);
			r.setMessage(result.getMessage());
			
		}else {
			r.setError(0);
			r.setUrl(result.getUrl());
		}
		return r;
	}
	
	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		/*
		
		String filename = multipartFile.getOriginalFilename();
		//  正常用户上传的图片资料应该放在文件服务器上，
		//  http://localhost:9090/${dir}/${filename}  才是文件的url
		
		// PicUploadResult:  error(0,1) ,  url:  ,   message:  
		
		//  fileName:  上传过来的文件名是:abc.png      
		//  服务器中存文件名时: realFileName =   abc-2018-08-10-16-23-21.png
		//  req.getServletContext().getRealPath()  
		String realPath = req.getServletContext().getRealPath("/");
		// realFilePath =  ${realPath}/image/abc-2018-08-10-16-23-21.png
		
		
		String webappPath = req.getServletContext().getContextPath(); 
		//  host:  
		String addr = req.getLocalAddr();
		
		int port = req.getLocalPort();
		// url:    http://${addr}:${port}/${webappPath}/${dir}/${realFileName}
		
		
		byte [] bts = multipartFile.getBytes();
		String path = "${realFilePath}";   // path  :  f:.....  
		String url = "?";  //url必须要让用户请求到的  http:  ...
		File file = new File(path);  
		new FileOutputStream(file).write(bts);  
		
		return url; 
		
		*/
	
	
}
