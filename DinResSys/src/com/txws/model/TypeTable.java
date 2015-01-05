package com.txws.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "types")
public class TypeTable {
	/** @pdOid 1535f0d1-7c29-457a-86e3-3a9377e129f9 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	/** @pdOid 20b20792-abbd-4bd9-9e41-6a0b49012af3 */
	private java.lang.String typeName;

	/**
	 * @pdRoleInfo migr=no name=MenuTable assc=reference11
	 *             coll=java.util.Collection impl=java.util.HashSet mult=1..*
	 */
	@OneToMany(mappedBy = "typeTable", fetch = FetchType.LAZY)
	private java.util.Collection<MenuTable> menuTable;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public java.lang.String getTypeName() {
		return typeName;
	}

	public void setTypeName(java.lang.String typeName) {
		this.typeName = typeName;
	}

	/** @pdGenerated default getter */
	public java.util.Collection<MenuTable> getMenuTable() {
		if (menuTable == null)
			menuTable = new java.util.HashSet<MenuTable>();
		return menuTable;
	}

	/** @pdGenerated default iterator getter */
	@SuppressWarnings("rawtypes")
	public java.util.Iterator getIteratorMenuTable() {
		if (menuTable == null)
			menuTable = new java.util.HashSet<MenuTable>();
		return menuTable.iterator();
	}

	/**
	 * @pdGenerated default setter
	 * @param newMenuTable
	 */
	@SuppressWarnings("rawtypes")
	public void setMenuTable(java.util.Collection<MenuTable> newMenuTable) {
		removeAllMenuTable();
		for (java.util.Iterator iter = newMenuTable.iterator(); iter.hasNext();)
			addMenuTable((MenuTable) iter.next());
	}

	/**
	 * @pdGenerated default add
	 * @param newMenuTable
	 */
	public void addMenuTable(MenuTable newMenuTable) {
		if (newMenuTable == null)
			return;
		if (this.menuTable == null)
			this.menuTable = new java.util.HashSet<MenuTable>();
		if (!this.menuTable.contains(newMenuTable)) {
			this.menuTable.add(newMenuTable);
			newMenuTable.setTypeTable(this);
		}
	}

	/**
	 * @pdGenerated default remove
	 * @param oldMenuTable
	 */
	public void removeMenuTable(MenuTable oldMenuTable) {
		if (oldMenuTable == null)
			return;
		if (this.menuTable != null)
			if (this.menuTable.contains(oldMenuTable)) {
				this.menuTable.remove(oldMenuTable);
				oldMenuTable.setTypeTable((TypeTable) null);
			}
	}

	/** @pdGenerated default removeAll */
	@SuppressWarnings("rawtypes")
	public void removeAllMenuTable() {
		if (menuTable != null) {
			MenuTable oldMenuTable;
			for (java.util.Iterator iter = getIteratorMenuTable(); iter
					.hasNext();) {
				oldMenuTable = (MenuTable) iter.next();
				iter.remove();
				oldMenuTable.setTypeTable((TypeTable) null);
			}
		}
	}

}
