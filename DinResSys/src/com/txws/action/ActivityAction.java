package com.txws.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.config.ParentPackage;
import org.apache.struts2.config.Result;
import org.apache.struts2.config.Results;
import org.apache.struts2.dispatcher.ServletActionRedirectResult;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.txws.model.ActivityTable;
import com.txws.service.interfaces.IActivityService;
import com.txws.service.interfaces.IMenuService;

@Controller
@Scope("prototype")
@ParentPackage(value = "struts-default")
@Results({ 
	@Result(name = "successDel", value = "activity!getAllActivity", type = ServletActionRedirectResult.class),
	@Result(name = "successGetAc", value = "activity.jsp")
	}
)
public class ActivityAction extends ActionSupport {
	private static final long serialVersionUID = 6887701169383402623L;

	@Resource(name = "activityService")
	private IActivityService activityService;
	@Resource(name = "menuService")
	private IMenuService menuService;

	private Object data = new Object();
	private Map<String, Object> dataMap = new HashMap<String, Object>();
	private List<Object> dataList = new ArrayList<>();

	private List<ActivityTable> activityList = new ArrayList<>();
	private ActivityTable activity;
	
	private int id;

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

	public List<Object> getDataList() {
		return dataList;
	}

	public void setDataList(List<Object> dataList) {
		this.dataList = dataList;
	}

	public List<ActivityTable> getActivityList() {
		return activityList;
	}

	public void setActivityList(List<ActivityTable> activityList) {
		this.activityList = activityList;
	}

	public ActivityTable getActivity() {
		return activity;
	}

	public void setActivity(ActivityTable activity) {
		this.activity = activity;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void saveActivity() throws IOException {
		HttpServletResponse response=ServletActionContext.getResponse();
		PrintWriter out = response.getWriter();
		try {
			activityService.addActivity(activity);
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
/*		try {
			activityService.addActivity(activity);
		} catch (Exception e) {
			dataMap.put("status", 2);
			data = dataMap;
			return SUCCESS;
		}
		dataMap.put("status", 1);
		data = dataMap;
		return SUCCESS;
*/	}

	public void changeActivity() throws IOException {
		HttpServletResponse response=ServletActionContext.getResponse();
		PrintWriter out = response.getWriter();  
		try {
			activityService.updateActivity(activity);
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
		/*try {
			activityService.updateActivity(activity);
		} catch (Exception e) {
			dataMap.put("status", 2);
			data = dataMap;
			return SUCCESS;
		}
		dataMap.put("status", 1);
		data = dataMap;
		return SUCCESS;*/
	}

	@SuppressWarnings("finally")
	public String delete() {
		try {
			menuService.removeActivity(id);
			activityService.delActivity(id);
		} catch (Exception e) {
			System.err.println(e.toString());
			e.printStackTrace();
		} finally {
			return "successDel";
		}
	}

	//OK
	public String getAllActivity() {
		activityList = activityService.loadAllActivity();
		return "successGetAc";
	}
}
