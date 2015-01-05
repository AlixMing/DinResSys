
package com.txws.action;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.config.ParentPackage;
import org.apache.struts2.config.Result;
import org.apache.struts2.config.Results;
import org.apache.struts2.dispatcher.ServletActionRedirectResult;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.txws.model.ActivityTable;
import com.txws.model.MenuTable;
import com.txws.model.TypeTable;
import com.txws.service.interfaces.IActivityService;
import com.txws.service.interfaces.IAppraiseService;
import com.txws.service.interfaces.IMenuService;
import com.txws.service.interfaces.ITypeService;

@Controller
@Scope("prototype")
@ParentPackage(value = "struts-default")
@Results({
	@Result(name = "success", value = "menu!getAllMenus", type = ServletActionRedirectResult.class), 
	@Result(name = "successGetMe", value = "menu.jsp"),
	@Result(name="successDel", value = "menu!getAllMenus", type = ServletActionRedirectResult.class)
})
public class MenuAction extends ActionSupport {
	
	private static final long serialVersionUID = 7293136559505921937L;
	
	@Resource(name="menuService")
	private IMenuService menuService;
	@Resource(name="activityService")
	private IActivityService activityService;
	@Resource(name="typeService")
	private ITypeService typeService;
	@Resource(name="appraiseService")
	private IAppraiseService appraiseService;

	private Object data = new Object();
	Map<String, Object> dataMap = new HashMap<String, Object>();
	List<Object> dataList = new ArrayList<>();
	private MenuTable menu;
	private List<Object> menuList = new ArrayList<>();
	private List<TypeTable> typeList = new ArrayList<>();
	private List<ActivityTable> activityTables = new ArrayList<>();
	private int id;
	private String typeName;
	private String activityName;
	private File menuImg;
	
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

	public MenuTable getMenu() {
		return menu;
	}

	public void setMenu(MenuTable menu) {
		this.menu = menu;
	}

	public List<Object> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<Object> menuList) {
		this.menuList = menuList;
	}

	public List<TypeTable> getTypeList() {
		return typeList;
	}

	public void setTypeList(List<TypeTable> typeList) {
		this.typeList = typeList;
	}

	public List<ActivityTable> getActivityTables() {
		return activityTables;
	}

	public void setActivityTables(List<ActivityTable> activityTables) {
		this.activityTables = activityTables;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public File getMenuImg() {
		return menuImg;
	}

	public void setMenuImg(File menuImg) {
		this.menuImg = menuImg;
	}

	//OK
	public String getAllMenuType(){
		List<TypeTable> list = menuService.getMenuTypes();
		for (TypeTable types : list) {
			dataList.add(types.getTypeName());
		}
		data = dataList;
		return SUCCESS;
	}
	
	//OK
	public String getAllMenu(){
		dataMap.put("menus", menuService.getAllMenu());
		data = dataMap;
		return SUCCESS;
	}
	
	public String getActivityMenuImg() {
		@SuppressWarnings("unchecked")
		List<Object> list = (List<Object>) menuService.getActivityMenuImg();
		data = list;
		return SUCCESS;
	}
	
	public String deleteMenu() {
		int menuId = 19;
		try {
			MenuTable menuTable = menuService.getMenuById(menuId);
			menuService.deleteMenu(menuTable);
			appraiseService.deleteAppraiseByMenu(menuId);
		} catch (Exception e) {
			System.err.println(e.toString());
			dataMap.put("status", 2);
			data = dataMap;
			return SUCCESS;
		}
		dataMap.put("status", 1);
		data = dataMap;
		return SUCCESS;
	}
	
	@SuppressWarnings("finally")
	public String delete(){
		try {
			appraiseService.deleteAppraiseByMenu(id);
			menu = menuService.getMenuById(id);
			menuService.deleteMenu(menu);
		} catch (Exception e) {
			System.err.println(e.toString());
			e.printStackTrace();
		}finally{
			return "successDel";
		}
	}
	
	public String addMenu() {
		menuService.addMenu(menu,typeName, activityName, menuImg);
		dataMap.put("status", 1);
		data = dataMap;
		return SUCCESS;
	}
	
	public String changeMenu() {
		menuService.updateMenu(menu,typeName, activityName, menuImg);
		dataMap.put("status", 1);
		data = dataMap;
		return SUCCESS;
	}
	
	public String getAllMenus(){
		List<MenuTable> me = menuService.getAllMenus();
		for (MenuTable menu: me) {
			Map<String, Object> tempMap = new HashMap<>();
			tempMap.put("id", menu.getId());
			tempMap.put("item", menu.getItem());
			tempMap.put("dec", menu.getDescri());
			tempMap.put("img", menu.getPicture());
			tempMap.put("dis", menu.getDiscount());
			tempMap.put("price", menu.getPrice());
			tempMap.put("salenum", menu.getOrderNum());
			tempMap.put("type", menu.getTypeTable().getTypeName());
			tempMap.put("activity", menu.getActivityTable() == null?"无":menu.getActivityTable().getActivityName());
			menuList.add(tempMap);
		}
		typeList = typeService.getAllType();
		activityTables = activityService.loadAllActivity();
		return "successGetMe";
	}
}
