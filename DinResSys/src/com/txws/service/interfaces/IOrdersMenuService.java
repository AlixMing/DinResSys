package com.txws.service.interfaces;

import java.util.List;

import com.txws.model.OrderMenuTable;

public interface IOrdersMenuService {
	void addOrderMenuTable(OrderMenuTable orderMenuTable);
	List<OrderMenuTable> loadOrderMenuTablesByOrderId(int orderId);
	void delOrderMenuTablesByOrderId(int orderId);
	int getMenuNum(int orderId, int menuId);
}
