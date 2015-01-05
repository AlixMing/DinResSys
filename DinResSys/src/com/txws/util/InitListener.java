package com.txws.util;

import java.io.File;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class InitListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		File dir = new File(PathUtils.getMenuImgStoreDir());
		if (!dir.exists()) {
			dir.mkdirs();
		}
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		
	}
}
