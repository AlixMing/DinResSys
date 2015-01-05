package com.txws.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/***********************************************************************
 * Module:  OrderMenuTable.java
 * Author:  Administrator
 * Purpose: Defines the Class OrderMenuTable
 ***********************************************************************/

/** @pdOid f9accbac-91b6-4555-b66e-2b00ffe01b20 */
@Entity
@Table(name = "ordermenu")
public class OrderMenuTable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	/**
	 * 菜品数量
	 * 
	 * @pdOid 3926da8a-bfcc-48fe-97dc-cef5ba2b6901
	 */
	private int num = 1;
	public int menuId;
	public int orderId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getMenuId() {
		return menuId;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

}