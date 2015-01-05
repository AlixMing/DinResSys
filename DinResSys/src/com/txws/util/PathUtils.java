package com.txws.util;

import java.io.IOException;
import java.util.Properties;

import org.apache.struts2.ServletActionContext;

/**
 * 分别在eclipse和tomcat的server.xml添加
 * <Context path="/DinResSys/img/menu" docBase="D:/DinResSys/img/menu" /> 
 */
public class PathUtils {
	private static Properties prop = new Properties();
	
	static {
		try {
			prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("path.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String getMenuImgStoreDir() {
		return prop.getProperty("menuImgStoreDir");
	}
	
	public static String getMenuImgDirRealPath() {
		String path = ServletActionContext.getServletContext().getRealPath("/");
		String webDir = path + "/" + prop.getProperty("menuImgWebDir"); 
		return webDir;
	}
	
	public static String getMenuImgDirPath() {
		return prop.getProperty("menuImgWebDir");
	}
}
