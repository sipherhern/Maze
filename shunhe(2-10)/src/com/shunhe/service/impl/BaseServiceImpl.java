package com.shunhe.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.hql.ast.QuerySyntaxException;
import org.springframework.beans.factory.annotation.Autowired;

import com.shunhe.service.BaseService;

public class BaseServiceImpl<T> implements BaseService<T> {

	// ����ע��,���� SessionFactory
	@Autowired
	private SessionFactory sessionFactory;

	
	/**
	 * �½�һ��Session���󣬸�Session��������һ�����̣߳��ܹ��ֶ���������
	 * 
	 * @return
	 */
	public Session openSession(){
		return sessionFactory.openSession();
	}
	
	
	/**
	 * ��ȡ��ǰSession����
	 * 
	 * @return
	 */
	public Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	/**
	 * �������
	 */
	public void save(T entity) {
		this.getCurrentSession().save(entity);
	}

	/**
	 * ɾ������
	 */
	public void delete(T entity){
		this.getCurrentSession().delete(entity);
	}

	/**
	 * ����
	 */
	public void update(T entity) {
		this.getCurrentSession().update(entity);
	}

	/**
	 * �ϲ�
	 */
	public void merge(T entity) {
		this.getCurrentSession().merge(entity);
	}

	/**
	 * ʹ��Persist�����������
	 */
	@Override
	public void persist(T entity) {
		this.persist(entity);
	}
	
	
	
	/**
	 * ��������
	 */
	public void saveOrUpdate(T entity) {
		this.getCurrentSession().saveOrUpdate(entity);
	}

	/**
	 * ����ID���ض���
	 */
	@SuppressWarnings("unchecked")
	public T load(Class<T> c, Serializable id) {
		return (T) this.getCurrentSession().load(c, id);
	}

	/**
	 * ����ID��ȡ����
	 */
	@SuppressWarnings("unchecked")
	public T get(Class<T> c, Serializable id) {
		return (T) this.getCurrentSession().get(c, id);
	}

	/**
	 * ����HQL����ȡ����
	 */
	public T get(String hql) {
		return this.get(hql, null);
	}

	/**
	 * ����HQL���Ͳ�����ȡ����
	 */
	public T get(String hql, Map<String, Object> params) {
		// ��ѯ�����Ľ����
		List<T> list = this.find(hql, params);
		// �������ǿ�
		if (list != null && list.size() > 0) {
			// ���ص�һ������
			return list.get(0);
		}
		return null;
	}

	/**
	 * ����HQL��ѯ�����
	 * 
	 * @param hql
	 * @return
	 */
	public List<T> find(String hql) {
		return find(hql, null);
	}

	/**
	 * ����HQL��ѯ�����н����
	 * 
	 * @param hql
	 * @return
	 */
	public Iterator<T> findByCache(String hql) {
		return findByCache(hql, null);
	}
	/**
	 * ʹ��HQL��ѯ���󼯺�,ע:
	 * �����'SQL��'��ͷ��ʹ��SQL��䣬��������list<List>
	 * �����'SQM��'��ͷ��ʹ��SQL��䣬��������list<Map>
	 * �����'HQM��'��ͷ��ʹ��HQL��䣬��������list<Map>
	 */
	@SuppressWarnings("unchecked")
	public List<T> find(String hql, Map<String, Object> params) {
		Query query = null;
		List<T> list = new ArrayList<T>();
		boolean matcherSQL = hql.startsWith("SQL:");
		boolean matcherSQLToMap = hql.startsWith("SQM:");
		boolean matcherHQLToMap = hql.startsWith("HQM:");
		try{
			//ƥ����SQL:��ͷ���������
			if(matcherSQL == true)
			{
				hql = hql.substring(4);
				query= this.getCurrentSession().createSQLQuery(hql);
			}
			//ƥ����MQL:��ͷ���������
			else if (matcherSQLToMap == true)
			{
				hql = hql.substring(4);
				query = this.getCurrentSession().createSQLQuery(hql).setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			}	
			//ƥ����HQM:��ͷ���������
			else if (matcherHQLToMap == true)
			{
				hql = hql.substring(4);
				query = this.getCurrentSession().createQuery(hql).setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			}
			else
			{
				query = this.getCurrentSession().createQuery(hql);
			}
		}
		catch(QuerySyntaxException e){
			this.getCurrentSession().close();
			e.printStackTrace();
		}
		// ��������ǿ�
		if (params != null && params.size() > 0) {
			// ���ò���
			this.setParameters(query, params);
		}
		// ���ز�ѯ���
		try{
			list = query.list();
		}
		catch(Exception e){
			this.getCurrentSession().close();
			e.printStackTrace();
		}
		return list;
	}



	@SuppressWarnings("unchecked")
	public Iterator<T> findByCache(String hql, Map<String, Object> params) {
		// ����Query
		Query query = this.getCurrentSession().createQuery(hql);

		// ��������ǿ�
		if (params != null && params.size() > 0) {
			// ���ò���
			this.setParameters(query, params);
		}
		// ���ز�ѯ���
		return query.iterate();
	}

	/**
	 * ʹ��HQL��ѯ��ҳ�Ľ�����������'SQL:'��ͷ��ʹ��SQL���
	 */
	@SuppressWarnings("unchecked")
	public List<T> find(String hql, int page, int rows,
			Map<String, Object> params ) {
		Query query = null;
		boolean matcherSQL = hql.startsWith("SQL:");
		boolean matcherSQLToMap = hql.startsWith("SQM:");
		boolean matcherHQLToMap = hql.startsWith("HQM:");
		try{
			//ƥ����SQL:��ͷ���������
			if(matcherSQL == true)
			{
				hql = hql.substring(4);
				query= this.getCurrentSession().createSQLQuery(hql);
			}
			//ƥ����MQL:��ͷ���������
			else if (matcherSQLToMap == true)
			{
				hql = hql.substring(4);
				query = this.getCurrentSession().createSQLQuery(hql).setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			}	
			//ƥ����HQM:��ͷ���������
			else if (matcherHQLToMap == true)
			{
				hql = hql.substring(4);
				query = this.getCurrentSession().createQuery(hql).setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			}
			else
			{
				query = this.getCurrentSession().createQuery(hql);
			}
		}
		catch(QuerySyntaxException e){
			e.printStackTrace();
		}
		// ��������ǿ�
		if (params != null && params.size() > 0) {
			// ���ò���
			this.setParameters(query, params);
		}

		// ��ѯ���������
		return query.setFirstResult((page - 1) * rows).setMaxResults(rows)
				.list();
	}

	/**
	 * ��ҳ�Ľ����,ͨ������
	 */
	@SuppressWarnings("unchecked")
	public Iterator<T> findByCache(String hql, int page, int rows,
			Map<String, Object> params) {
		// ����Query
		Query query = this.getCurrentSession().createQuery(hql);
		// ��������ǿ�
		if (params != null && params.size() > 0) {
			// ���ò���
			this.setParameters(query, params);
		}
		// ��ѯ���������
		return query.setFirstResult((page - 1) * rows).setMaxResults(rows)
				.iterate();
	}

	/**
	 * �޲����� Select count(*) from
	 */
	public Long count(String hql) {
		// ͨ����һ������ʵ��^_^
		return this.count(hql, null);
	}

	/**
	 * �������� Select count(*) from
	 */
	public Long count(String hql, Map<String, Object> params) {

		// �����ݹ�����hql���ǰ׺����
		hql = "select count(*) " + hql;

		// ����Query
		Query query = this.getCurrentSession().createQuery(hql);

		// ��������ǿ�
		if (params != null && params.size() > 0) {
			// ���ò���
			this.setParameters(query, params);
		}

		// ���ز�ѯ���
		return (Long) query.uniqueResult();
	}

	/**
	 * ִ��HQL
	 */
	public Integer executeHql(String hql) {
		// ͨ����һ������ʵ��^_^
		return this.executeHql(hql, null);
	}

	/**
	 * ִ��HQL
	 */
	public Integer executeHql(String hql, Map<String, Object> params) {

		// ����Query
		Query query = this.getCurrentSession().createQuery(hql);

		// ���������Ϊnull
		if (params != null && params.size() > 0) {
			// ���ò���
			this.setParameters(query, params);
		}

		// ִ��HQL
		return query.executeUpdate();
	}

	/**
	 * ��Query��ֵ�ķ���
	 * 
	 * @param query
	 *            Hibernate Query����
	 * @param params
	 *            Map���ϲ���
	 */
	private void setParameters(Query query, Map<String, Object> params) {

		// ��ȡ����Key
		Set<String> keys = params.keySet();

		// ѭ��ȡֵ,��ֵ
		for (String key : keys) {

			// ��ȡ�������еĶ���
			Object obj = params.get(key);

			// �жϲ�������
			if (obj instanceof Collection<?>) {// �Ǽ���
				// System.out.println("����Ϊ���ϵĴ�СΪ:" + ((Collection<?>)
				// obj).size());
				query.setParameterList(key, (Collection<?>) obj);
			} else if (obj instanceof Object[]) {// ������
				// System.out.println("����Ϊ����Ĵ�СΪ:" + ((Object[]) obj).length);
				query.setParameterList(key, (Object[]) obj);
			} else {// ��ͨ����
				// System.out.println("��ͨ����");
				query.setParameter(key, obj);
			}
		}
	}

	@Override
	public boolean deleteById(String Id) {
		T t = get(Id);
		if (t == null) {
			return false;
		}
		delete(t);
		return true;
	}

	/**
	 * ��ҳ
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<T> getAllRecordAndDivPage(String hql, int offset, int length) {
		List<T> list = new ArrayList<T>();
		Query query = getCurrentSession().createQuery(hql);
		query.setFirstResult(offset);
		query.setMaxResults(length);
		list = query.list();
		return list;
	}

	@Override
	public int getAllRowCount(String hql) {
		return getCurrentSession().createQuery(hql).list().size();
	}



	
		
	
	
	@SuppressWarnings({ "unchecked", "hiding" })
	public <T> List<T> getList(Class<T> c){
		try{
		return this.sessionFactory.getCurrentSession().createQuery("from "+c.getName()).list();
		}
		catch(Exception e){
			
			System.out.println("!!!");
			e.printStackTrace();
			
		}
		return null;
	}
	
	
	
	
	public <T> List<T> getListHaveParameter(final Class<T> c,final Map map,final String lastHql){
				return this.sessionFactory.getCurrentSession().createQuery("from "+c.getName()+" where "+changeMapParameter(map)+" "+lastHql).setProperties(map).list();
	}
	
	public String changeMapParameter(Map map){
		int i=0;
		String r="";
		 for (Object key : map.keySet()) {
			 if(i==0)
			  r=r+key.toString()+"=:"+ key.toString();
			 else
			  r=r+" and "+key.toString()+"=:"+ key.toString();	 
		     i++;
		 }
		return r;
	}
	
	
	public <T> List<T> getListHaveParameterAndPage(final Class<T> c,final Map map,final String lastHql,final int first,final int max){
				return this.sessionFactory.getCurrentSession().createQuery("from "+c.getName()+" where "+changeMapParameter(map)+" "+lastHql).setProperties(map).setFirstResult(first).setMaxResults(max).list();
	}
	
	
	/**
	 * �������
	 */
	public Integer save2(T entity) {
		return (Integer)this.getCurrentSession().save(entity);
	}
	
	
	public <T> Integer getEntityCount(final T c){
	     Session arg0=this.getCurrentSession();
				Long l= (Long)arg0.createQuery("select count(*) from "+ c.getClass().getName()).uniqueResult();
				return l.intValue();
		 
	}
	
	public <T> Integer getEntityCountHaveParam(final T c,final Map<String,Object> map){
		Session arg0=this.getCurrentSession();
				Long l= (Long)arg0.createQuery("select count(*) from "+c.getClass().getName()+" where "+changeMapParameter(map)).setProperties(map).uniqueResult();
				return l.intValue();
	}
	
	public <T> Integer getEntityCountHaveParam(final T c,final Map<String,Object> map,final String lastsql){
		Session arg0=this.getCurrentSession();
				Long l= (Long)arg0.createQuery("select count(*) from "+c.getClass().getName()+" where "+changeMapParameter(map)+" "+lastsql).setProperties(map).uniqueResult();
				return l.intValue();
	}
	
	
	
}
