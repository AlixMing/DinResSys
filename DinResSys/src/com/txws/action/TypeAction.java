package com.txws.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.config.ParentPackage;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.txws.model.TypeTable;
import com.txws.service.interfaces.ITypeService;

@Controller
@Scope("prototype")
@ParentPackage(value = "struts-default")
public class TypeAction extends ActionSupport {

	private static final long serialVersionUID = 3318359614776620201L;

	@Resource(name = "typeService")
	private ITypeService typeService;

	private TypeTable type;
	private Object data = new Object();
	private Map<String, Object> dataMap = new HashMap<String, Object>();

	public TypeTable getType() {
		return type;
	}

	public void setType(TypeTable type) {
		this.type = type;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Map<String, Object> getDataMap() {
		return dataMap;
	}

	public void setDataMap(Map<String, Object> dataMap) {
		this.dataMap = dataMap;
	}

	public void addMenuType() throws IOException {
		/*dataMap.clear();
		
		try {
			typeService.addType(type);
		} catch (Exception e) {
			dataMap.put("status", 2);
			data = dataMap;
			return SUCCESS;
		}
		
		dataMap.put("status", 1);
		data = dataMap;
		return SUCCESS;*/
		HttpServletResponse response=ServletActionContext.getResponse();
		PrintWriter out = response.getWriter();  
		try {
			typeService.addType(type);
		} catch (Exception e) {
			String jsonString="{\"status\":\"2\"}";
			out.println(jsonString);  
		    out.flush();
		    out.close();
		}
		String jsonString="{\"status\":\"1\"}"; 
	    out.println(jsonString);  
	    out.flush();
	    out.close();
	}
}
