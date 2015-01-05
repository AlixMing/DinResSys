package com.txws.service.interfaces;

import java.io.File;
import java.util.List;

import com.txws.model.MenuTable;
import com.txws.model.TypeTable;


public interface IMenuService {
	List<TypeTable> getMenuTypes();
	List<TypeTable> getMenuByTypes(String typeId);
	List<Object> getAllMenu();
	List<? super String> getActivityMenuImg();
	MenuTable getMenuById(int id);
	List<MenuTable> getMenuTablesByOrderId(int orderId);
	void deleteMenu(MenuTable menuTable);
	void addMenu(MenuTable menuTable, String typeName, String activityName, File menuImg);
	void updateMenu(MenuTable menuTable);
	void removeActivity(int activityId);
	void updateMenu(MenuTable menuTable, String typeName, String activityName, File menuImg);
	List<MenuTable> getAllMenus();
}
