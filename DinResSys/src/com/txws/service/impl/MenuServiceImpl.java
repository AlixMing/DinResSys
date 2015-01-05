package com.txws.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import com.txws.dao.interfaces.ICommonDAO;
import com.txws.model.ActivityTable;
import com.txws.model.MenuTable;
import com.txws.model.OrderMenuTable;
import com.txws.model.TypeTable;
import com.txws.service.interfaces.IMenuService;
import com.txws.util.PathUtils;
@Service("menuService")
public class MenuServiceImpl implements IMenuService {
	@Resource(name = "commonDAO")
	private ICommonDAO commonDAO;

	@Override
	public List<TypeTable> getMenuTypes() {
		List<TypeTable> list = new ArrayList<>();
		list = commonDAO.getObjects("TypeTable");
		return list;
	}

	@Override
	public List<TypeTable> getMenuByTypes(String typeId) {
		List<TypeTable> list = new ArrayList<>();
		list = commonDAO.getObjectsByKey(TypeTable.class, "typeId", typeId);
		return list;
	}

	@Override
	public List<Object> getAllMenu() {
		List<MenuTable> list = new ArrayList<>();
		list = commonDAO.getObjects("MenuTable");
		List<Object> dataList = new ArrayList<>();
		for (MenuTable menuTable : list) {
			Map<String, Object> dataMap = new HashMap<String, Object>();
			dataMap.put("id", menuTable.getId());
			dataMap.put("item", menuTable.getItem());
			dataMap.put("price", (menuTable.getPrice() * menuTable.getDiscount())/100);
			dataMap.put("saleNum", menuTable.getOrderNum());
			dataMap.put("type", menuTable.getTypeTable().getTypeName());
			if(menuTable.getIsInActivity() != 0){
				dataMap.put("hasPromotion", true);
			}else {
				dataMap.put("hasPromotion", false);				
			}
			dataList.add(dataMap);
		}
		return dataList;
	}

	@Override
	public List<? super String> getActivityMenuImg() {
		List<String> list = commonDAO.getPartialObjects("select picture from "
				+ "MenuTable where isInActivity = 1", 0, 4);
		return list;
	}

	@Override
	public MenuTable getMenuById(int id) {
		MenuTable menu = commonDAO.getObject(MenuTable.class, id);
		return menu;
	}

	@Override
	public List<MenuTable> getMenuTablesByOrderId(int orderId) {
		List<MenuTable> menuTables = new ArrayList<>();
		List<OrderMenuTable> orderMenuTables = commonDAO.getObjectsByKey(OrderMenuTable.class, "orderId", String.valueOf(orderId));
		for (OrderMenuTable orderMenuTable : orderMenuTables) {
			menuTables.add(commonDAO.getObject(MenuTable.class, orderMenuTable.getMenuId()));
		}
		return menuTables;
	}

	@Override
	public void deleteMenu(MenuTable menuTable) {
		System.out.println(menuTable.getId() + "............");
		commonDAO.delete(menuTable);
	}

	@Override
	public void updateMenu(MenuTable menuTable) {
		commonDAO.update(menuTable);
	}

	@Override
	public void removeActivity(int activityId) {
		List<MenuTable> menuList = commonDAO.getObjectsByKey(MenuTable.class, "activityId", String.valueOf(activityId));
		for (MenuTable menuTable : menuList) {
			menuTable.setActivityTable(null);
			menuTable.setIsInActivity(0);
		}
	}
	
	@Override
	public void addMenu(MenuTable menuTable, String typeName, String activityName, File menuImg) {
		TypeTable type = commonDAO.getObjectsByKey(TypeTable.class, "typeName", typeName).get(0);
		ActivityTable activity = null;
		if (!"不参与活动".equals(activityName)) {
			activity = commonDAO.getObjectsByKey(ActivityTable.class, "activityName", activityName).get(0);
			menuTable.setIsInActivity(1);
			menuTable.setDiscount(activity.getPromotion());
		}
		menuTable.setTypeTable(type);
		menuTable.setActivityTable(activity);
		commonDAO.save(menuTable);
		
		if (menuImg == null) {
			return;
		}
		String pictureName = menuTable.getId() + ".jpg";
		menuTable.setPicture(PathUtils.getMenuImgDirPath() + "/" + pictureName);
		File file = new File(PathUtils.getMenuImgStoreDir(), pictureName);
		try {
			FileUtils.copyFile(menuImg, file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateMenu(MenuTable menuTable, String typeName, String activityName, File menuImg) {
		int id = menuTable.getId();
		MenuTable menu = commonDAO.getObject(MenuTable.class, id);
		menu.setItem(menuTable.getItem());
		menu.setPrice(menuTable.getPrice());
		menu.setIsInActivity(menuTable.getIsInActivity());
		menu.setDescri(menuTable.getDescri());
		menu.setStatus(menuTable.getStatus());
		String pictureName = id + ".jpg";
		menu.setPicture(PathUtils.getMenuImgDirPath() + "/" + pictureName);
		
		TypeTable type = commonDAO.getObjectsByKey(TypeTable.class, "typeName", typeName).get(0);
		ActivityTable activity = null;
		if (!"不参与活动".equals(activityName)) {
			activity = commonDAO.getObjectsByKey(ActivityTable.class, "activityName", activityName).get(0);
			menu.setIsInActivity(1);
			menu.setDiscount(activity.getPromotion());
		}
		menu.setTypeTable(type);
		menu.setActivityTable(activity);
		commonDAO.update(menu);
		
		if (menuImg == null) {
			return;
		}
		File file = new File(PathUtils.getMenuImgStoreDir(), pictureName);
		try {
			FileUtils.copyFile(menuImg, file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<MenuTable> getAllMenus() {
		return commonDAO.getAllObjects(MenuTable.class);
	}
}
