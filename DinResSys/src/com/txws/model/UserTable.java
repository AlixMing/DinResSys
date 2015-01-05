package com.txws.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class UserTable {
	/** @pdOid 3ed67683-f2e5-4493-822e-fa2895d4c762 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	/**
	 * �û��ǳ�
	 * 
	 * @pdOid b05cc0fc-796c-421d-b4c0-277a2afa9aa7
	 */
	private java.lang.String name;
	/**
	 * �û�����
	 * 
	 * @pdOid ee9f90ad-736c-4254-bdc4-8b075d9fc2cd
	 */
	private java.lang.String password;
	/**
	 * �û��ֻ�ţ����ڶ����ϵ���ϵ��ʽ
	 * 
	 * @pdOid 99cf7223-81cb-44dd-aa16-7a77904aa8fc
	 */
	private java.lang.String tel;
	/**
	 * �û��ȼ���0��?������Ա��1���˵�������Ա��2�����ͨ�û�
	 * 
	 * @pdOid 83a6ecbf-27ca-4078-a5a9-5e8ece608bf5
	 */
	private int authoLevel = 2;

	/**
	 * @pdRoleInfo migr=no name=AddressTable assc=userAddressReference
	 *             coll=java.util.Collection impl=java.util.HashSet mult=0..*
	 */
	@OneToMany(mappedBy = "userTable", cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	private java.util.Collection<AddressTable> addressTable;
	/**
	 * @pdRoleInfo migr=no name=OrdersTable assc=userOrderReference
	 *             coll=java.util.Collection impl=java.util.HashSet mult=0..*
	 */
	// @OneToMany(mappedBy = "userTable", cascade = { CascadeType.REMOVE },
	// fetch = FetchType.LAZY)
	// public java.util.Collection<OrdersTable> ordersTable;

	/**
	 * @pdRoleInfo migr=no name=AppraiseTable assc=userAppraiseReference
	 *             coll=java.util.Collection impl=java.util.HashSet mult=0..*
	 */
	// @OneToMany(mappedBy = "userTable", cascade = { CascadeType.REMOVE },
	// fetch = FetchType.LAZY)
	// public java.util.Collection<AppraiseTable> appraiseTable;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public java.lang.String getName() {
		return name;
	}

	public void setName(java.lang.String name) {
		this.name = name;
	}

	public java.lang.String getPassword() {
		return password;
	}

	public void setPassword(java.lang.String password) {
		this.password = password;
	}

	public java.lang.String getTel() {
		return tel;
	}

	public void setTel(java.lang.String tel) {
		this.tel = tel;
	}

	public int getAuthoLevel() {
		return authoLevel;
	}

	public void setAuthoLevel(int authoLevel) {
		this.authoLevel = authoLevel;
	}
	
	public java.util.Collection<AddressTable> getAddressTable() {
		if (addressTable == null)
			addressTable = new java.util.HashSet<AddressTable>();
		return addressTable;
	}
	
	@SuppressWarnings("rawtypes")
	public java.util.Iterator getIteratorAddressTable() {
		if (addressTable == null)
			addressTable = new java.util.HashSet<AddressTable>();
		return addressTable.iterator();
	}
	
	@SuppressWarnings("rawtypes")
	public void setAddressTable(
			java.util.Collection<AddressTable> newAddressTable) {
		removeAllAddressTable();
		for (java.util.Iterator iter = newAddressTable.iterator(); iter
				.hasNext();)
			addAddressTable((AddressTable) iter.next());
	}
	
	public void addAddressTable(AddressTable newAddressTable) {
		if (newAddressTable == null)
			return;
		if (this.addressTable == null)
			this.addressTable = new java.util.HashSet<AddressTable>();
		if (!this.addressTable.contains(newAddressTable)) {
			this.addressTable.add(newAddressTable);
			newAddressTable.setUserTable(this);
		}
	}
	
	public void removeAddressTable(AddressTable oldAddressTable) {
		if (oldAddressTable == null)
			return;
		if (this.addressTable != null)
			if (this.addressTable.contains(oldAddressTable)) {
				this.addressTable.remove(oldAddressTable);
				oldAddressTable.setUserTable((UserTable) null);
			}
	}
	
	@SuppressWarnings("rawtypes")
	public void removeAllAddressTable() {
		if (addressTable != null) {
			AddressTable oldAddressTable;
			for (java.util.Iterator iter = getIteratorAddressTable(); iter
					.hasNext();) {
				oldAddressTable = (AddressTable) iter.next();
				iter.remove();
				oldAddressTable.setUserTable((UserTable) null);
			}
		}
	}
/*
	public java.util.Collection<OrdersTable> getOrdersTable() {
		if (ordersTable == null)
			ordersTable = new java.util.HashSet<OrdersTable>();
		return ordersTable;
	}

	public java.util.Iterator getIteratorOrdersTable() {
		if (ordersTable == null)
			ordersTable = new java.util.HashSet<OrdersTable>();
		return ordersTable.iterator();
	}

	public void setOrdersTable(java.util.Collection<OrdersTable> newOrdersTable) {
		removeAllOrdersTable();
		for (java.util.Iterator iter = newOrdersTable.iterator(); iter
				.hasNext();)
			addOrdersTable((OrdersTable) iter.next());
	}

	public void addOrdersTable(OrdersTable newOrdersTable) {
		if (newOrdersTable == null)
			return;
		if (this.ordersTable == null)
			this.ordersTable = new java.util.HashSet<OrdersTable>();
		if (!this.ordersTable.contains(newOrdersTable)) {
			this.ordersTable.add(newOrdersTable);
			newOrdersTable.setUserTable(this);
		}
	}

	public void removeOrdersTable(OrdersTable oldOrdersTable) {
		if (oldOrdersTable == null)
			return;
		if (this.ordersTable != null)
			if (this.ordersTable.contains(oldOrdersTable)) {
				this.ordersTable.remove(oldOrdersTable);
				oldOrdersTable.setUserTable((UserTable) null);
			}
	}

	public void removeAllOrdersTable() {
		if (ordersTable != null) {
			OrdersTable oldOrdersTable;
			for (java.util.Iterator iter = getIteratorOrdersTable(); iter
					.hasNext();) {
				oldOrdersTable = (OrdersTable) iter.next();
				iter.remove();
				oldOrdersTable.setUserTable((UserTable) null);
			}
		}
	}


	public java.util.Collection<AppraiseTable> getAppraiseTable() {
		if (appraiseTable == null)
			appraiseTable = new java.util.HashSet<AppraiseTable>();
		return appraiseTable;
	}

	public java.util.Iterator getIteratorAppraiseTable() {
		if (appraiseTable == null)
			appraiseTable = new java.util.HashSet<AppraiseTable>();
		return appraiseTable.iterator();
	}

	public void setAppraiseTable(
			java.util.Collection<AppraiseTable> newAppraiseTable) {
		removeAllAppraiseTable();
		for (java.util.Iterator iter = newAppraiseTable.iterator(); iter
				.hasNext();)
			addAppraiseTable((AppraiseTable) iter.next());
	}

	public void addAppraiseTable(AppraiseTable newAppraiseTable) {
		if (newAppraiseTable == null)
			return;
		if (this.appraiseTable == null)
			this.appraiseTable = new java.util.HashSet<AppraiseTable>();
		if (!this.appraiseTable.contains(newAppraiseTable)) {
			this.appraiseTable.add(newAppraiseTable);
			newAppraiseTable.setUserTable(this);
		}
	}

	public void removeAppraiseTable(AppraiseTable oldAppraiseTable) {
		if (oldAppraiseTable == null)
			return;
		if (this.appraiseTable != null)
			if (this.appraiseTable.contains(oldAppraiseTable)) {
				this.appraiseTable.remove(oldAppraiseTable);
				oldAppraiseTable.setUserTable((UserTable) null);
			}
	}

	public void removeAllAppraiseTable() {
		if (appraiseTable != null) {
			AppraiseTable oldAppraiseTable;
			for (java.util.Iterator iter = getIteratorAppraiseTable(); iter
					.hasNext();) {
				oldAppraiseTable = (AppraiseTable) iter.next();
				iter.remove();
				oldAppraiseTable.setUserTable((UserTable) null);
			}
		}
	}
*/
}
