package com.txws.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
public class OrdersTable {
	/** @pdOid 5b2ef31c-0e1f-4e31-9abf-6e4bd3ec16b1 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	/**
	 * �ܼۣ��µ�ʱ��������
	 * 
	 * @pdOid ddb7873b-98a3-488e-a221-4034701e429a
	 */
	private int price;
	
	private String phone;
	/**
	 * �µ�ʱ��
	 * 
	 * @pdOid 51a53f3d-498d-49d7-b37a-dc8cd3ab976e
	 */
	private java.util.Date createTime;
	/**
	 * ����״̬
	 * 
	 * @pdOid 1f0398e1-6549-4d66-a5fd-09dc2b890bc7
	 */
	private java.lang.String status;
	/**
	 * �û�����
	 * 
	 * @pdOid cdb23a12-0f91-4093-814c-16213aaa1f65
	 */
	private java.lang.String message;

	/**
	 * @pdRoleInfo migr=no name=UserTable assc=userOrderReference mult=1..1
	 *             side=A
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "userId")
	private UserTable userTable;
	/**
	 * @pdRoleInfo migr=no name=AddressTable assc=orderAddressReference
	 *             mult=1..1 side=A
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "adId")
	private AddressTable addressTable;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public java.util.Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}

	public java.lang.String getStatus() {
		return status;
	}

	public void setStatus(java.lang.String status) {
		this.status = status;
	}

	public java.lang.String getMessage() {
		return message;
	}

	public void setMessage(java.lang.String message) {
		this.message = message;
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
				@SuppressWarnings("unused")
				UserTable oldUserTable = this.userTable;
				this.userTable = null;
				//oldUserTable.removeOrdersTable(this);
			}
			if (newUserTable != null) {
				this.userTable = newUserTable;
				//this.userTable.addOrdersTable(this);
			}
		}
	}

	/** @pdGenerated default parent getter */
	public AddressTable getAddressTable() {
		return addressTable;
	}

	/**
	 * @pdGenerated default parent setter
	 * @param newAddressTable
	 */
	public void setAddressTable(AddressTable newAddressTable) {
		if (this.addressTable == null
				|| !this.addressTable.equals(newAddressTable)) {
			if (this.addressTable != null) {
				@SuppressWarnings("unused")
				AddressTable oldAddressTable = this.addressTable;
				this.addressTable = null;
				//oldAddressTable.removeOrdersTable(this);
			}
			if (newAddressTable != null) {
				this.addressTable = newAddressTable;
				//this.addressTable.addOrdersTable(this);
			}
		}
	}
/*
	public java.util.Collection<MenuTable> getReference_12() {
		if (Reference_12 == null)
			Reference_12 = new java.util.HashSet<MenuTable>();
		return Reference_12;
	}

	public java.util.Iterator getIteratorReference_12() {
		if (Reference_12 == null)
			Reference_12 = new java.util.HashSet<MenuTable>();
		return Reference_12.iterator();
	}

	public void setReference_12(java.util.Collection<MenuTable> newReference_12) {
		removeAllReference_12();
		for (java.util.Iterator iter = newReference_12.iterator(); iter
				.hasNext();)
			addReference_12((MenuTable) iter.next());
	}

	public void addReference_12(MenuTable newMenuTable) {
		if (newMenuTable == null)
			return;
		if (this.Reference_12 == null)
			this.Reference_12 = new java.util.HashSet<MenuTable>();
		if (!this.Reference_12.contains(newMenuTable)) {
			this.Reference_12.add(newMenuTable);
			newMenuTable.addReference_13(this);
		}
	}

	public void removeReference_12(MenuTable oldMenuTable) {
		if (oldMenuTable == null)
			return;
		if (this.Reference_12 != null)
			if (this.Reference_12.contains(oldMenuTable)) {
				this.Reference_12.remove(oldMenuTable);
				oldMenuTable.removeReference_13(this);
			}
	}

	public void removeAllReference_12() {
		if (Reference_12 != null) {
			MenuTable oldMenuTable;
			for (java.util.Iterator iter = getIteratorReference_12(); iter
					.hasNext();) {
				oldMenuTable = (MenuTable) iter.next();
				iter.remove();
				oldMenuTable.removeReference_13(this);
			}
		}
	}
*/
}
