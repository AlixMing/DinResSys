package com.txws.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.txws.dao.interfaces.ICommonDAO;
import com.txws.model.OrdersTable;
import com.txws.service.interfaces.IOrdersService;
@Service("ordersService")
public class OrdersServiceImpl implements IOrdersService {

	@Resource(name = "commonDAO")
	private ICommonDAO commonDAO;

	@Override
	public OrdersTable addOrder(OrdersTable ordersTable) {
		commonDAO.save(ordersTable);
		return commonDAO.getObjectsByKeys(ordersTable).get(0);
	}

	@Override
	public List<OrdersTable> loadAllOrdersByUser(int userId) {
		List<OrdersTable> ordersTables = commonDAO.getObjectsByKey(OrdersTable.class, "userId", String.valueOf(userId));
		return ordersTables;
	}

	@Override
	public void delOrder(int orderId) {
		OrdersTable ordersTable = commonDAO.getObject(OrdersTable.class, orderId);
		commonDAO.delete(ordersTable);
	}

	@Override
	public void updateStatus(int orderId, String status) {
		OrdersTable ordersTable = commonDAO.getObject(OrdersTable.class, orderId);
		ordersTable.setStatus(status);
		commonDAO.update(ordersTable);
	}

	@Override
	public List<OrdersTable> loadAllOrders() {
		List<OrdersTable> list = commonDAO.getAllObjects(OrdersTable.class);
		return list;
	}
}
