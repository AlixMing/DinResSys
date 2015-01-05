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
@Table(name = "appraise")
public class AppraiseTable {
	/** @pdOid 654728c9-23c1-4233-b2d4-b45e5080c496 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	/** @pdOid 5d0e323b-5e1f-408b-94b2-c7f37e35ba84 */
	private java.util.Date praiseTime;
	/**
	 * �û��Բ˵������۵ȼ���1-5��
	 * 
	 * @pdOid 0e596695-2a92-4a80-b137-b2f78d81effb
	 */
	private int praiseLevel = 5;
	/**
	 * �û��Բ˵�����������
	 * 
	 * @pdOid ec6cca96-c5a3-4685-a77a-3067d03295e2
	 */
	private java.lang.String detail;

	/**
	 * @pdRoleInfo migr=no name=MenuTable assc=menuAppraiseReference mult=1..1
	 *             side=A
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "menuId")
	private MenuTable menuTable;
	/**
	 * @pdRoleInfo migr=no name=UserTable assc=userAppraiseReference mult=1..1
	 *             side=A
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	private UserTable userTable;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public java.util.Date getPraiseTime() {
		return praiseTime;
	}

	public void setPraiseTime(java.util.Date praiseTime) {
		this.praiseTime = praiseTime;
	}

	public int getPraiseLevel() {
		return praiseLevel;
	}

	public void setPraiseLevel(int praiseLevel) {
		this.praiseLevel = praiseLevel;
	}

	public java.lang.String getDetail() {
		return detail;
	}

	public void setDetail(java.lang.String detail) {
		this.detail = detail;
	}

	/** @pdGenerated default parent getter */
	public MenuTable getMenuTable() {
		return menuTable;
	}

	/**
	 * @pdGenerated default parent setter
	 * @param newMenuTable
	 */
	public void setMenuTable(MenuTable newMenuTable) {
		if (this.menuTable == null || !this.menuTable.equals(newMenuTable)) {
			if (this.menuTable != null) {
				@SuppressWarnings("unused")
				MenuTable oldMenuTable = this.menuTable;
				this.menuTable = null;
				//oldMenuTable.removeAppraiseTable(this);
			}
			if (newMenuTable != null) {
				this.menuTable = newMenuTable;
				//this.menuTable.addAppraiseTable(this);
			}
		}
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
				//oldUserTable.removeAppraiseTable(this);
			}
			if (newUserTable != null) {
				this.userTable = newUserTable;
				//this.userTable.addAppraiseTable(this);
			}
		}
	}

}
