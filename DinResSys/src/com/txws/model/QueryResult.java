package com.txws.model;

import java.util.List;

/*��ѯ���*/
public class QueryResult<T> {
	private List<T> resultList;
	private int totalRecord;
	
	public List<T> getResultList() {
		return resultList;
	}
	public void setResultList(List<T> resultList) {
		this.resultList = resultList;
	}
	public int getTotalRecord() {
		return totalRecord;
	}
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}
}
