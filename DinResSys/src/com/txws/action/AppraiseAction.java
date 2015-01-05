package com.txws.action;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.config.ParentPackage;
import org.apache.struts2.config.Result;
import org.apache.struts2.config.Results;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.txws.model.AppraiseTable;
import com.txws.model.MenuTable;
import com.txws.model.UserTable;
import com.txws.service.interfaces.IAppraiseService;
import com.txws.service.interfaces.IMenuService;
import com.txws.service.interfaces.IUserService;

@Controller
@Scope("prototype")
@ParentPackage(value = "struts-default")
@Results({ @Result(name = "success", value = "index.jsp"), })
public class AppraiseAction extends ActionSupport {

	private static final long serialVersionUID = -4420479385334433523L;
	@Resource(name = "appraiseService")
	private IAppraiseService appraiseService;
	@Resource(name = "userService")
	private IUserService userService;
	@Resource(name = "menuService")
	private IMenuService menuService;

	private Map<String, Object> dataMap = new HashMap<>();
	private AppraiseTable appraise;
	private MenuTable menu;
	private int menuId;

	public String getAppraiseByMenuID() {
		dataMap = appraiseService.getAppraiseByMenuID(menu.getId());
		return SUCCESS;
	}

	public Map<String, Object> getDataMap() {
		return dataMap;
	}

	public void setDataMap(Map<String, Object> dataMap) {
		this.dataMap = dataMap;
	}

	public MenuTable getMenu() {
		return menu;
	}

	public void setMenu(MenuTable menu) {
		this.menu = menu;
	}

	public AppraiseTable getAppraise() {
		return appraise;
	}

	public void setAppraise(AppraiseTable appraise) {
		this.appraise = appraise;
	}

	public int getMenuId() {
		return menuId;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	// OK
	public String addAppraise() {
		dataMap.clear();
		ActionContext ac = ActionContext.getContext();
		Map<String, Object> session = ac.getSession();

		UserTable userTable = (UserTable) session.get("user");
		if (userTable == null) {
			dataMap.put("status", 3);
			return SUCCESS;
		} else {
			try {
				appraise.setMenuTable(menuService.getMenuById(menuId));
				appraise.setUserTable(userService.loadUser(userTable.getId()));
				appraise.setPraiseTime(new Date());
				appraiseService.addAppraise(appraise);
			} catch (Exception e) {
				System.err.println(e.toString());
				dataMap.put("status", 2);
				return SUCCESS;
			}
			dataMap.put("status", 1);
			return SUCCESS;
		}
	}
}
