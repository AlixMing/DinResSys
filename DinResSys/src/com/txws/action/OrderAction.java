package com.txws.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.config.ParentPackage;
import org.apache.struts2.config.Result;
import org.apache.struts2.config.Results;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.txws.model.AddressTable;
import com.txws.model.MenuTable;
import com.txws.model.OrderMenuTable;
import com.txws.model.OrdersTable;
import com.txws.model.UserTable;
import com.txws.service.interfaces.IAddressService;
import com.txws.service.interfaces.IMenuService;
import com.txws.service.interfaces.IOrdersMenuService;
import com.txws.service.interfaces.IOrdersService;

@Controller
@Scope("prototype")
@ParentPackage(value = "struts-default")
@Results({ //@Result(name = "logSuccess", value = "pages/user!home", type = ServletActionRedirectResult.class),
	@Result(name = "successGetOrders", value = "index.jsp")
	}
)
public class OrderAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4909854934245282225L;

	private Object data = new Object();
	private Map<String, Object> dataMap = new HashMap<String, Object>();
	private List<Object> dataList = new ArrayList<>();
	private List<Object> orderList = new ArrayList<>();
	private String phone;
	private int addressID;
	private String remark;
	private Map<Integer, Integer> menus;
	private OrdersTable order;
	private int id;

	@Resource(name = "ordersService")
	private IOrdersService ordersService;
	@Resource(name = "addressService")
	private IAddressService addressService;
	@Resource(name = "menuService")
	private IMenuService menuService;
	@Resource(name = "ordersMenuService")
	private IOrdersMenuService ordersMenuService;

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

	public List<Object> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<Object> orderList) {
		this.orderList = orderList;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getAddressID() {
		return addressID;
	}

	public void setAddressID(int addressID) {
		this.addressID = addressID;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Map<Integer, Integer> getMenus() {
		return menus;
	}

	public void setMenus(Map<Integer, Integer> menus) {
		this.menus = menus;
	}

	public OrdersTable getOrder() {
		return order;
	}

	public void setOrder(OrdersTable order) {
		this.order = order;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	// OK
	public String commitOrderResult() {
		dataMap.clear();
		ActionContext ac = ActionContext.getContext();
		Map<String, Object> session = ac.getSession();

		OrdersTable ordersTable = new OrdersTable();
		int priceTotal = 0;

		for (Integer mapKey : menus.keySet()) {
			MenuTable menuTable = menuService.getMenuById(mapKey);
			priceTotal += (menus.get(mapKey) * menuTable.getPrice());
			menuTable.setOrderNum(menuTable.getOrderNum() + 1);
			menuService.updateMenu(menuTable);
		}
		AddressTable addressTable = addressService.loadAddress(addressID);
		ordersTable.setUserTable((UserTable) session.get("user"));
		ordersTable.setPhone(phone);
		ordersTable.setCreateTime(new Date());
		ordersTable.setPrice(priceTotal);
		ordersTable.setMessage(remark);
		ordersTable.setStatus("订单已提交");// 订单已提交、订单已确认、在送、送达
		ordersTable.setAddressTable(addressTable);

		// 保存order
		try {
			ordersTable = ordersService.addOrder(ordersTable);
			for (Integer mapKey : menus.keySet()) {
				OrderMenuTable orderMenuTable = new OrderMenuTable();
				orderMenuTable.setMenuId(mapKey);
				orderMenuTable.setNum(menus.get(mapKey));
				orderMenuTable.setOrderId(ordersTable.getId());
				ordersMenuService.addOrderMenuTable(orderMenuTable);
			}
		} catch (Exception e) {
			dataMap.put("status", 2);
			dataMap.put("status2", menus);
			data = dataMap;
			return SUCCESS;
		}

		dataMap.put("status", 1);
		dataMap.put("status2", menus);
		data = dataMap;

		return SUCCESS;
	}

	public String getAllOrder() {
		dataMap.clear();
		dataList.clear();
		ActionContext ac = ActionContext.getContext();
		Map<String, Object> session = ac.getSession();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");

		UserTable user = (UserTable) session.get("user");
		List<OrdersTable> ordersTables = ordersService.loadAllOrdersByUser(user
				.getId());

		for (OrdersTable ordersTable : ordersTables) {
			Map<String, Object> tempMap = new HashMap<>();
			List<Map<String, Object>> temp1 = new ArrayList<>();
			tempMap.put("id", ordersTable.getId());
			tempMap.put("date", formatter.format(ordersTable.getCreateTime()));
			tempMap.put("status", ordersTable.getStatus());
			List<MenuTable> menuTables = menuService
					.getMenuTablesByOrderId(ordersTable.getId());
			for (MenuTable menuTable : menuTables) {
				int num = ordersMenuService.getMenuNum(ordersTable.getId(),
						menuTable.getId());
				Map<String, Object> temp = new HashMap<>();
				temp.put("id", menuTable.getId());
				temp.put("item", menuTable.getItem());
				temp.put("price", (menuTable.getPrice() * num * menuTable.getDiscount())/100);
				temp.put("num", num);
				temp.put("saleNum", menuTable.getOrderNum());
				temp.put("type", menuTable.getTypeTable().getTypeName());
				temp1.add(temp);
			}
			tempMap.put("menus", temp1);
			dataList.add(tempMap);
		}
		dataMap.put("orders", dataList);
		data = dataMap;
		return SUCCESS;
	}

	@SuppressWarnings("finally")
	public String delete() {
		try {
			ordersMenuService.delOrderMenuTablesByOrderId(id);
			ordersService.delOrder(id);
		} catch (Exception e) {
			System.err.println(e.toString());
			e.printStackTrace();
		} finally {
			return "successDel";
		}
	}
	
	//OK
	public void changeStatus() throws IOException {
		HttpServletResponse response=ServletActionContext.getResponse();
		PrintWriter out = response.getWriter();  
		try {
			ordersService.updateStatus(order.getId(), order.getStatus());
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
		/*dataMap.clear();
		try {
			ordersService.updateStatus(order.getId(), order.getStatus());
		} catch (Exception e) {
			System.err.println(e.toString());
			dataMap.put("status", 2);
			data = dataMap;
			return SUCCESS;
		}
		dataMap.put("status", 1);
		data = dataMap;
		return SUCCESS;*/
	}
	
	public String getAllOrderAdmin(){
		List<OrdersTable> ordersTables = ordersService.loadAllOrders();

		for (OrdersTable ordersTable : ordersTables) {
			int totalPrice = 0;
			Map<String, Object> tempMap = new HashMap<>();
			List<Map<String, Object>> temp1 = new ArrayList<>();
			tempMap.put("id", ordersTable.getId());
			tempMap.put("phone", ordersTable.getPhone());
			tempMap.put("status", ordersTable.getStatus());
			tempMap.put("message", ordersTable.getMessage());
			tempMap.put("userName", ordersTable.getUserTable().getName());
			tempMap.put("address", ordersTable.getAddressTable().getAd());
			List<MenuTable> menuTables = menuService
					.getMenuTablesByOrderId(ordersTable.getId());
			for (MenuTable menuTable : menuTables) {
				int num = ordersMenuService.getMenuNum(ordersTable.getId(),
						menuTable.getId());
				int price = menuTable.getPrice() * num;
				totalPrice += price;
				Map<String, Object> temp = new HashMap<>();
				temp.put("id", menuTable.getId());
				temp.put("item", menuTable.getItem());
				temp.put("price", price);
				temp.put("num", num);
				temp.put("saleNum", menuTable.getOrderNum());
				temp.put("type", menuTable.getTypeTable().getTypeName());
				temp1.add(temp);
			}
			tempMap.put("menus", temp1);
			tempMap.put("totalPrice", totalPrice);
			orderList.add(tempMap);
		}
		return "successGetOrders";
	}

}
