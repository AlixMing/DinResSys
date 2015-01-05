package com.txws.dao.interfaces;

import java.util.List;

public interface ICommonDAO {

	<T> T getObject(Class<T> clazz, Integer id);
	<T> void update(T obj);
	<T> void save(T obj);
	<T> void delete(T obj);
	<T> List<T> getObjects(String QueryName);
	<T> void deleteAll(List<T> list);
	<T> List<T> getObjectsByWhereSQL(String queryString);
	<T> List<T> getObjectsByKey(Class<T> clazz, String keyName, String KeyValue);
	<T> List<T> getObjectsByKeyandRequire(Class<T> clazz, String keyName, String KeyValue,String requirement);//yun 8_15 15:31
	<T> List<T> getObjectsandRequire(String queryType,String requirement);
	<T> List<T> getObjectsByKeys(T t);
	String shiftEnter(String srcStr); 
	<T> List<T> getPartialObjects(String hql, int first, int max);
	<T> List<T> getAllObjects(Class<T> entityClass);
}
