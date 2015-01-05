package com.txws.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.txws.dao.interfaces.ICommonDAO;
import com.txws.model.OrderMenuTable;
import com.txws.service.interfaces.IOrdersMenuService;
@Service("ordersMenuService")
public class OrderMenuServiceImpl implements IOrdersMenuService {
	@Resource(name = "commonDAO")
	private ICommonDAO commonDAO;
	
	@Override
	public void addOrderMenuTable(OrderMenuTable orderMenuTable) {
		commonDAO.save(orderMenuTable);
	}

	@Override
	public List<OrderMenuTable> loadOrderMenuTablesByOrderId(int orderId) {
		List<OrderMenuTable> list = commonDAO.getObjectsByKey(OrderMenuTable.class, "orderId", String.valueOf(orderId));
		return list;
	}

	@Override
	public void delOrderMenuTablesByOrderId(int orderId) {
		List<OrderMenuTable> list = commonDAO.getObjectsByKey(OrderMenuTable.class, "orderId", String.valueOf(orderId));
		commonDAO.deleteAll(list);
	}

	@Override
	public int getMenuNum(int orderId, int menuId) {
		List<OrderMenuTable> list = commonDAO.getObjectsByKeyandRequire(OrderMenuTable.class, "orderId", String.valueOf(orderId), " and menuId = " + menuId);
		return list.get(0).getNum();
	}

}
