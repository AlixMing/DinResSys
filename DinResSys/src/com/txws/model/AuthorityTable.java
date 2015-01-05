package com.txws.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "authority")
public class AuthorityTable {
	/** @pdOid 2e03f6af-c4b7-4d3b-8067-750cd232eacc */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	/** @pdOid 422efc21-b072-4891-a756-5b6fcde80c95 */
	private java.lang.String autho;
	/**
	 * Ȩ�޶�Ӧ��URL·��
	 * 
	 * @pdOid be596efa-374a-42c6-b4fc-889cb9afab86
	 */
	private java.lang.String authoUrl;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public java.lang.String getAutho() {
		return autho;
	}

	public void setAutho(java.lang.String autho) {
		this.autho = autho;
	}

	public java.lang.String getAuthoUrl() {
		return authoUrl;
	}

	public void setAuthoUrl(java.lang.String authoUrl) {
		this.authoUrl = authoUrl;
	}

}
