package com.txws.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * �û���ַ�?����ÿ���û��ж����ַ
 * 
 * @pdOid e6445ad3-bcec-4e0f-bac4-bb488732289b
 */
@Entity
@Table(name = "address")
public class AddressTable {
	/** @pdOid 69b74cc8-cdc3-4a9c-b047-3bf27eeae4da */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	/**
	 * ��ַ��Ϣ
	 * 
	 * @pdOid 0f7572ad-be43-4f40-baec-54875fb62db7
	 */
	@Column(name = "ad")
	private java.lang.String ad;
	/**
	 * �Ƿ�Ĭ��ֵ
	 * 
	 * @pdOid b725f5c3-e6a7-482c-b932-900bdc13c343
	 */
	private int isDefault = 0;

	/**
	 * @pdRoleInfo migr=no name=OrdersTable assc=orderAddressReference
	 *             coll=java.util.Collection impl=java.util.HashSet mult=0..*
	 */
	// @OneToMany(mappedBy = "addressTable", cascade = { CascadeType.REMOVE },
	// fetch = FetchType.LAZY)
	// public java.util.Collection<OrdersTable> ordersTable;
	/**
	 * @pdRoleInfo migr=no name=UserTable assc=userAddressReference mult=1..1
	 *             side=A
	 */
	@ManyToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	private UserTable userTable;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public java.lang.String getAd() {
		return ad;
	}

	public void setAd(java.lang.String address) {
		this.ad = address;
	}

	public int getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(int isDefault) {
		this.isDefault = isDefault;
	}

	/** @pdGenerated default parent getter */
	public UserTable getUserTable() {
		return userTable;
	}

	/**
	 * @pdGenerated default parent setter
	 * @param newUserTable
	 */
	public void setUserTable(UserTable newUserTable) {
		if (this.userTable == null || !this.userTable.equals(newUserTable)) {
			if (this.userTable != null) {
				UserTable oldUserTable = this.userTable;
				this.userTable = null;
				oldUserTable.removeAddressTable(this);
			}
			if (newUserTable != null) {
				this.userTable = newUserTable;
				this.userTable.addAddressTable(this);
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
			newOrdersTable.setAddressTable(this);
		}
	}

	public void removeOrdersTable(OrdersTable oldOrdersTable) {
		if (oldOrdersTable == null)
			return;
		if (this.ordersTable != null)
			if (this.ordersTable.contains(oldOrdersTable)) {
				this.ordersTable.remove(oldOrdersTable);
				oldOrdersTable.setAddressTable((AddressTable) null);
			}
	}

	public void removeAllOrdersTable() {
		if (ordersTable != null) {
			OrdersTable oldOrdersTable;
			for (java.util.Iterator iter = getIteratorOrdersTable(); iter
					.hasNext();) {
				oldOrdersTable = (OrdersTable) iter.next();
				iter.remove();
				oldOrdersTable.setAddressTable((AddressTable) null);
			}
		}
	}
*/
}
