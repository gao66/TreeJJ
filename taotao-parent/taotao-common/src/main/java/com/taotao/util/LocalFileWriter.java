package com.taotao.util;

import java.io.BufferedOutputStream;
import java.io.File;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LocalFileWriter implements FileWriter {
	private static Logger logger = LoggerFactory.getLogger(LocalFileWriter.class);
	private String fileRootDirectory;
	private String url;
	
	public String getUrl() {
		if(url!=null)return url; 
		
		String host = System.getProperty("host");
		String port = System.getProperty("port");
		String path = System.getProperty("webappPath");
		url = "http://"+host+":"+port+"/"+path; 
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getFileRootDirectory() {
		return fileRootDirectory;
	}

	public void setFileRootDirectory(String fileRootDirectory) {
		this.fileRootDirectory = fileRootDirectory;
	}

	@Override
	public FileWriteResult write(byte[] bytes, String dir, String extName) {
		
		//获取文件将要保存的文件夹的绝对路径  
		String parentDir = fileRootDirectory+"/"+dir;
		//创建文件夹的file对象
		File parentFile = new File(parentDir);
		//判断这个文件夹是否存在，不存在的话就创建这样的文件夹
		if(parentFile.exists()==false) {
			parentFile.mkdirs();
		}
		//生成文件名
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss-");
		String fileName = format.format(date)+UUID.randomUUID().toString();
		fileName = fileName+"."+extName;
		
		//生成文件对象，用于写字节到文件中
		File target = new File(parentFile, fileName);
		FileWriteResult result = new FileWriteResult();
		try {
			//io流操作
			OutputStream out = new BufferedOutputStream(new FileOutputStream(target));
			out.write(bytes);
			out.close();
			result.setError(0);
			result.setUrl( this.getUrl()+dir+"/"+fileName );
			
		} catch (Exception e) {
			if(logger.isErrorEnabled())
				logger.error(e.getMessage());
			result.setError(1);
			result.setMessage(e.getMessage());
		}
		return result;
	}

}
