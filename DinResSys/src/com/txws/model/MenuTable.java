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
@Table(name = "menu")
public class MenuTable {
	/** @pdOid ed9257dd-9258-48e2-a8ff-4ff437c81bbf */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	/**
	 * �˵������
	 * 
	 * @pdOid 3d0f3be0-3103-4057-b724-de968de5023d
	 */
	private java.lang.String item;
	/**
	 * �˵���۸�
	 * 
	 * @pdOid 9892e320-2ef5-439c-9063-c519842a94be
	 */
	private int price;
	/**
	 * �Ƿ�����˵���
	 * 
	 * @pdOid 2cc01ad7-78be-480c-b953-01f98f6efe2e
	 */
	private int isHot = 0;
	/** @pdOid 6aaf1f7d-685b-472a-9763-f7fb05e40472 */
	private int orderNum = 0;
	/** @pdOid 1ad506e9-93e8-41a7-8ce7-cc0238a982d8 */
	private int praiseNum = 0;
	/**
	 * �˵�״̬��������0������1��
	 * 
	 * @pdOid 62104d3a-d5d8-481c-9d81-9580b77dc006
	 */
	private int status = 1;
	/**
	 * �Ƿ�������û��0����1��
	 * 
	 * @pdOid ba4c8232-f086-4829-92f6-4465c125b220
	 */
	private int isInActivity = 0;
	/** @pdOid 0c1d5b02-9bba-49ac-9296-a2dd8f6f6be4 */
	private int discount = 100;
	/** @pdOid 41d13cdb-95b2-42b1-a271-4d1f389e73c9 */
	private java.lang.String picture;
	/**
	 * �˵�����
	 * 
	 * @pdOid 33edef61-524a-4285-9927-8ff2bbd76177
	 */
	private java.lang.String descri;

	/**
	 * @pdRoleInfo migr=no name=AppraiseTable assc=menuAppraiseReference
	 *             coll=java.util.Collection impl=java.util.HashSet mult=0..*
	 */
	//@OneToMany(mappedBy = "menuTable", cascade = { CascadeType.REMOVE },fetch = FetchType.LAZY)
	//private java.util.Collection<AppraiseTable> appraiseTable;

	/** @pdRoleInfo migr=no name=TypeTable assc=reference11 mult=0..1 side=A */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "typeId")
	private TypeTable typeTable;
	/** @pdRoleInfo migr=no name=ActivityTable assc=reference14 mult=0..1 side=A */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "activityId")
	private ActivityTable activityTable;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public java.lang.String getItem() {
		return item;
	}

	public void setItem(java.lang.String item) {
		this.item = item;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getIsHot() {
		return isHot;
	}

	public void setIsHot(int isHot) {
		this.isHot = isHot;
	}

	public int getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}

	public int getPraiseNum() {
		return praiseNum;
	}

	public void setPraiseNum(int praiseNum) {
		this.praiseNum = praiseNum;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getIsInActivity() {
		return isInActivity;
	}

	public void setIsInActivity(int isInActivity) {
		this.isInActivity = isInActivity;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public java.lang.String getPicture() {
		return picture;
	}

	public void setPicture(java.lang.String picture) {
		this.picture = picture;
	}

	public java.lang.String getDescri() {
		return descri;
	}

	public void setDescri(java.lang.String descri) {
		this.descri = descri;
	}
/*
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
			newAppraiseTable.setMenuTable(this);
		}
	}

	public void removeAppraiseTable(AppraiseTable oldAppraiseTable) {
		if (oldAppraiseTable == null)
			return;
		if (this.appraiseTable != null)
			if (this.appraiseTable.contains(oldAppraiseTable)) {
				this.appraiseTable.remove(oldAppraiseTable);
				oldAppraiseTable.setMenuTable((MenuTable) null);
			}
	}

	public void removeAllAppraiseTable() {
		if (appraiseTable != null) {
			AppraiseTable oldAppraiseTable;
			for (java.util.Iterator iter = getIteratorAppraiseTable(); iter
					.hasNext();) {
				oldAppraiseTable = (AppraiseTable) iter.next();
				iter.remove();
				oldAppraiseTable.setMenuTable((MenuTable) null);
			}
		}
	}
*/
/*
	public java.util.Collection<OrdersTable> getReference_13() {
		if (Reference_13 == null)
			Reference_13 = new java.util.HashSet<OrdersTable>();
		return Reference_13;
	}

	public java.util.Iterator getIteratorReference_13() {
		if (Reference_13 == null)
			Reference_13 = new java.util.HashSet<OrdersTable>();
		return Reference_13.iterator();
	}

	public void setReference_13(
			java.util.Collection<OrdersTable> newReference_13) {
		removeAllReference_13();
		for (java.util.Iterator iter = newReference_13.iterator(); iter
				.hasNext();)
			addReference_13((OrdersTable) iter.next());
	}

	public void addReference_13(OrdersTable newOrdersTable) {
		if (newOrdersTable == null)
			return;
		if (this.Reference_13 == null)
			this.Reference_13 = new java.util.HashSet<OrdersTable>();
		if (!this.Reference_13.contains(newOrdersTable)) {
			this.Reference_13.add(newOrdersTable);
			newOrdersTable.addReference_12(this);
		}
	}

	public void removeReference_13(OrdersTable oldOrdersTable) {
		if (oldOrdersTable == null)
			return;
		if (this.Reference_13 != null)
			if (this.Reference_13.contains(oldOrdersTable)) {
				this.Reference_13.remove(oldOrdersTable);
				oldOrdersTable.removeReference_12(this);
			}
	}

	public void removeAllReference_13() {
		if (Reference_13 != null) {
			OrdersTable oldOrdersTable;
			for (java.util.Iterator iter = getIteratorReference_13(); iter
					.hasNext();) {
				oldOrdersTable = (OrdersTable) iter.next();
				iter.remove();
				oldOrdersTable.removeReference_12(this);
			}
		}
	}
*/
	/** @pdGenerated default parent getter */
	public TypeTable getTypeTable() {
		return typeTable;
	}

	/**
	 * @pdGenerated default parent setter
	 * @param newTypeTable
	 */
	public void setTypeTable(TypeTable newTypeTable) {
		if (this.typeTable == null || !this.typeTable.equals(newTypeTable)) {
			if (this.typeTable != null) {
				TypeTable oldTypeTable = this.typeTable;
				this.typeTable = null;
				oldTypeTable.removeMenuTable(this);
			}
			if (newTypeTable != null) {
				this.typeTable = newTypeTable;
				this.typeTable.addMenuTable(this);
			}
		}
	}

	/** @pdGenerated default parent getter */
	public ActivityTable getActivityTable() {
		return activityTable;
	}

	/**
	 * @pdGenerated default parent setter
	 * @param newActivityTable
	 */
	public void setActivityTable(ActivityTable newActivityTable) {
		if (this.activityTable == null
				|| !this.activityTable.equals(newActivityTable)) {
			if (this.activityTable != null) {
				ActivityTable oldActivityTable = this.activityTable;
				this.activityTable = null;
				oldActivityTable.removeMenuTable(this);
			}
			if (newActivityTable != null) {
				this.activityTable = newActivityTable;
				this.activityTable.addMenuTable(this);
			}
		}
	}

}
