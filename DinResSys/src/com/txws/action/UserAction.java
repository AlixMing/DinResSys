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

import com.txws.model.AddressTable;
import com.txws.model.UserTable;
import com.txws.service.interfaces.IUserService;
import com.txws.util.MD5;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Controller
@Scope("prototype")
@ParentPackage(value = "struts-default")
@Results({
	@Result(name = "successLogin", value = "order!getAllOrderAdmin", type = ServletActionRedirectResult.class),
	@Result(name = "failLogin", value = "login.html"),
	@Result(name = "successDel", value = "user!getAllUser", type = ServletActionRedirectResult.class),
	@Result(name = "successGetUser", value = "user.jsp")
})
public class UserAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2761694876354016692L;

	@Resource(name = "userService")
	private IUserService userService;
	private UserTable user;
	private AddressTable address;
	private Object data;
	private Map<String, Object> dataMap = new HashMap<String, Object>();
	private List<Object> dataList = new ArrayList<>();
	private List<UserTable> userList = new ArrayList<>();
	private int id;

	public UserTable getUser() {
		return user;
	}

	public void setUser(UserTable user) {
		this.user = user;
	}

	public AddressTable getAddress() {
		return address;
	}

	public void setAddress(AddressTable address) {
		this.address = address;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Map<String, Object> getDataMap() {
		return dataMap;
	}

	public List<Object> getDataList() {
		return dataList;
	}

	public void setDataList(List<Object> dataList) {
		this.dataList = dataList;
	}

	public void setDataMap(Map<String, Object> dataMap) {
		this.dataMap = dataMap;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<UserTable> getUserList() {
		return userList;
	}

	public void setUserList(List<UserTable> userList) {
		this.userList = userList;
	}

	@Override
	public String execute() {
		user = userService.loadUser(2);
		return "success";
	}

	// OK
	public String getLoginStatus() {
		dataMap.clear();

		ActionContext ac = ActionContext.getContext();
		Map<String, Object> session = ac.getSession();
		if (session.containsKey("user")) {
			user = (UserTable) session.get("user");
			if (user != null) {
				dataMap.put("status", 1);
			} else
				dataMap.put("status", 2);
		} else {
			dataMap.put("status", 2);
		}

		data = dataMap;
		return SUCCESS;
	}

	// OK
	public String register() {
		dataMap.clear();

		ActionContext ac = ActionContext.getContext();
		Map<String, Object> session = ac.getSession();

		user.setPassword(MD5.UseMD5(user.getName() + user.getPassword()));

		try {
			user.getAddressTable().add(address);
			address.setUserTable(user);

			if (!userService.addUser(user)) {
				dataMap.put("status", 2);
			} else {
				dataMap.put("status", 1);
			}
		} catch (Exception e) {
			dataMap.put("status", 3);
			session.put("user", null);
			return SUCCESS;
		}

		session.put("user", user);
		data = dataMap;
		return SUCCESS;
	}

	// OK
	public String getUserInfo() {
		dataMap.clear();
		ActionContext ac = ActionContext.getContext();
		Map<String, Object> session = ac.getSession();
		List<Map<String, Object>> list = new ArrayList<>();

		user = (UserTable) session.get("user");

		user = userService.loadUser(user.getId());

		dataMap.put("phone", user.getTel());

		for (AddressTable addressTable : user.getAddressTable()) {
			if (addressTable.getIsDefault() == 1) {
				Map<String, Object> temp = new HashMap<>();
				temp.put("id", addressTable.getId());
				temp.put("address", addressTable.getAd());
				temp.put("isDefault", true);
				list.add(temp);
				break;
			}
		}
		for (AddressTable addressTable : user.getAddressTable()) {
			if (addressTable.getIsDefault() != 1) {
				Map<String, Object> temp = new HashMap<>();
				temp.put("id", addressTable.getId());
				temp.put("address", addressTable.getAd());
				temp.put("isDefault", false);
				list.add(temp);
			}
		}

		dataMap.put("addresses", list);

		data = dataMap;
		return SUCCESS;
	}

	// OK
	public String login() {
		dataMap.clear();
		ActionContext ac = ActionContext.getContext();
		Map<String, Object> session = ac.getSession();

		user.setPassword(MD5.UseMD5(user.getName() + user.getPassword()));

		try {
			user = userService.login(user);
			if (user != null)
				dataMap.put("status", 1);
			else
				dataMap.put("status", 2);
		} catch (Exception e) {
			dataMap.put("status", 3);
			session.put("user", null);
			return SUCCESS;
		}

		session.put("user", user);

		data = dataMap;
		return SUCCESS;
	}
	
	public String adminLogin() {
		ActionContext ac = ActionContext.getContext();
		Map<String, Object> session = ac.getSession();
		user.setPassword(MD5.UseMD5(user.getName() + user.getPassword()));
		
		try {
			user = userService.login(user);
		} catch (Exception e) {
			session.put("user", null);
			return "failLogin";
		}
		if(user == null){
			session.put("user", null);
			return "failLogin";
		}else {
			session.put("user", user);
			return "successLogin";
		}
	}

	@SuppressWarnings("finally")
	public String delete() {
		try {
			userService.delUser(id);
		} catch (Exception e) {
			System.err.println(e.toString());
			e.printStackTrace();
		} finally {
			return "successDel";
		}
	}
	
	//OK
	public void changeUserInfo() throws IOException{
		HttpServletResponse response=ServletActionContext.getResponse();
		PrintWriter out = response.getWriter();  
		String name = user.getName();
		String pw = user.getPassword();
		try {
			user = userService.loadUser(user.getId());
			user.setName(name);
			user.setPassword(MD5.UseMD5(name + pw));
			userService.updateUser(user);
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
		String name = user.getName();
		String pw = user.getPassword();
		try {
			user = userService.loadUser(user.getId());
			user.setName(name);
			user.setPassword(MD5.UseMD5(name + pw));
			userService.updateUser(user);
		} catch (Exception e) {
			dataMap.put("status", 2);
			data = dataMap;
			return SUCCESS;
		}
		dataMap.put("status", 1);
		data = dataMap;
		return SUCCESS;*/
	}
	
	public String getAllUser(){
		userList = userService.loadAllUser();
		for (UserTable userTable : userList) {
			System.out.println(userTable.getId() + ":" + userTable.getName() + ":" + userTable.getPassword());
		}
		return "successGetUser";
	}
}
