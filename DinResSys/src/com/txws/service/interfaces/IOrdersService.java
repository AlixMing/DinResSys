package com.txws.service.interfaces;

import java.util.List;

import com.txws.model.OrdersTable;


public interface IOrdersService {
	
	OrdersTable addOrder(OrdersTable ordersTable);
	List<OrdersTable> loadAllOrdersByUser(int userId);
	void delOrder(int orderId);
	void updateStatus(int orderId, String status);
	List<OrdersTable> loadAllOrders();
}
