package com.txws.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.txws.dao.interfaces.ICommonDAO;

@Repository("commonDAO")
public class CommonDAOImpl extends BasicSupportDao implements ICommonDAO {
	private static final long serialVersionUID = 1744644549907514077L;
 
	@Override
	public <T> T getObject(Class<T> clazz, Integer id) {
		return this.getHibernateTemplate().get(clazz, id);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public <T> List<T> getObjects(String QueryName) {
		return this.getHibernateTemplate().find("from " + QueryName);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public <T> List<T> getObjectsandRequire(String queryType,String requirement) {
		return this.getHibernateTemplate().find("from " + queryType + requirement);
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> List<T> getObjectsByKeyandRequire(Class<T> clazz, String keyName, String KeyValue,String requirement) {
		System.out.println("CHECK:"+"from " +  clazz + " where " + keyName + "="+KeyValue + requirement);
		return this.getHibernateTemplate().find("from " +  clazz.getSimpleName() + " where " + keyName + "="+KeyValue + requirement);	
	}
	
	@Override
	public <T> void update(T obj) {
		
		this.getHibernateTemplate().update( obj );
	}

	@Override
	public <T> void save(T obj) {
		this.getHibernateTemplate().save( obj );
	}
	
	@Override
	public <T> void delete(T obj){
		this.getHibernateTemplate().delete(obj);
	}

	@Override
	public <T> void deleteAll(List<T> list){
		this.getHibernateTemplate().deleteAll(list);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getObjectsByKey(Class<T> clazz, String keyName,String KeyValue) {

		return this.getHibernateTemplate().find("from " + clazz.getSimpleName()+ " where " + keyName + "= '" + KeyValue +"'");
	}

	@Override
	public String shiftEnter(String srcStr) {

		System.out.println("srcStr: "+srcStr);
		String newStr = srcStr.replaceAll("\r\n", "<br>"); // �滻�س����з�
		newStr = newStr.replaceAll(" ", "&nbsp;");// �滻�ո��
		System.out.println("newStr: "+newStr);
		return newStr;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public <T> List<T> getObjectsByKeys(T t) {
		return this.getHibernateTemplate().findByExample(t);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getObjectsByWhereSQL(String queryString) {
		System.out.println(queryString);
		return this.getHibernateTemplate().find(queryString);
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> List<T> getPartialObjects(final String hql, final int first, final int max) {
		List<T> resultList = getHibernateTemplate().executeFind(new HibernateCallback<T>() {

			@Override
			public T doInHibernate(Session session) throws HibernateException,
					SQLException {
				Query query = session.createQuery(hql).setFirstResult(first).setMaxResults(max);
				return (T) query.list();
			}
		});
		return resultList;
	}

	@Override
	public <T> List<T> getAllObjects(Class<T> entityClass) {
		return this.getHibernateTemplate().loadAll(entityClass);
	}
}
