package com.txws.test;

import java.util.List;
import java.util.Map;

import org.springframework.context.support.ClassPathXmlApplicationContext;






import com.txws.model.AppraiseTable;
import com.txws.service.interfaces.IAppraiseService;


public class Test {
	public static void main(String[] args) {
		
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		IAppraiseService service = (IAppraiseService) ctx.getBean("appraiseService");
		Map<String, Object> map = service.getAppraiseByMenuID(1);
		@SuppressWarnings("unchecked")
		List<AppraiseTable> list = (List<AppraiseTable>) map.get("appraises");
		for (AppraiseTable appraiseTable : list) {
			System.out.println(appraiseTable.getDetail());
		}
//		System.out.println(map);
		
//		System.out.println(service.getClass());
		
//		IMenuService service = (IMenuService) ctx.getBean("menuService");
//		List<String> list = service.getActivityMenuImg();
//		for (String string : list) {
//			System.out.println(string);
//		}
		
		ctx.destroy();
	}
}
