package com.shunhe.service;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;


@Service
public interface BaseService<T> {
	/**
	 * ����һ������
	 * 
	 * @param entity
	 */
	public void save(T entity);

	/**
	 * ɾ��һ������
	 * 
	 * @param entity
	 */
	public void delete(T entity);

	/**
	 * ����һ������
	 * 
	 * @param entity
	 */
	public void update(T entity);

	/**
	 * �ϲ�һ������
	 * 
	 * @param entity
	 */
	public void merge(T entity);

	/**
	 * ��ӻ���¶���
	 * 
	 * @param entity
	 */
	public void saveOrUpdate(T entity);

	/**
	 * ����һ������
	 * 
	 * @param c
	 * @param id
	 * @return
	 */
	public T load(Class<T> c, Serializable id);

	/**
	 * ��ȡһ������
	 * 
	 * @param c
	 * @param id
	 * @return
	 */
	public T get(Class<T> c, Serializable id);
	
	
	/**
	 * ��ȡһ������
	 * 
	 * @param hql
	 * @return
	 */
	public T get(String hql);

	/**
	 * ��ȡһ������
	 * 
	 * @param hql
	 * @param params
	 * @return
	 */
	public T get(String hql, Map<String, Object> params);

	/**
	 * ����HQL����ѯ�����
	 * 
	 * @param hql
	 * @return
	 */
	public List<T> find(String hql);

	/**
	 * ����HQL����ѯ�����еĽ����
	 * 
	 * @param hql
	 * @return
	 */
	public Iterator<T> findByCache(String hql);
	
	
	/**
	 * ��ѯ���󼯺�
	 * 
	 * @param hql
	 * @param params
	 * @return
	 */
	public List<T> find(String hql, Map<String, Object> params);
	
	/**
	 * ��ѯ���󼯺�(�����ȡ)
	 * 
	 * @param hql
	 * @param params
	 * @return
	 */
	public Iterator<T> findByCache(String hql, Map<String, Object> params);
	/**
	 * ��ѯ���󼯺�
	 * 
	 * @param hql
	 * @param page
	 * @param rows
	 * @param params
	 * @return
	 */
	public List<T> find(String hql, int page, int rows,
			Map<String, Object> params);

	/**
	 * Select count(*) from
	 * 
	 * @param hql
	 * @return
	 */
	
	public Iterator<T> findByCache(String hql, int page, int rows,
			Map<String, Object> params);
	
	/**
	 * Select count(*) from cache
	 * 
	 * @param hql
	 * @return
	 */
	public Long count(String hql);

	/**
	 * Select count(*) from
	 * 
	 * @param hql
	 * @param params
	 * @return
	 */
	public Long count(String hql, Map<String, Object> params);

	/**
	 * ִ��HQL���
	 * 
	 * @param hql
	 * @return
	 */
	public Integer executeHql(String hql);

	/**
	 * ִ��HQL���
	 * 
	 * @param hql
	 * @param params
	 * @return
	 */
	public Integer executeHql(String hql, Map<String, Object> params)
			throws Exception;
	
	
    /**
     * <����IDɾ������>
     * @param Id ʵ��id
     * @return �Ƿ�ɾ���ɹ�
     * 
     */
    public boolean deleteById(String Id);
    
 
    
	/**
	 * @author 
	 * @param hql
	 *            ��ѯȫ����Ϣ��HQL��䣬��֧��ԭ��SQL������ʹ��createQuery���������Բ�֧��ԭ����SQL��䣻
	 *            �����ʹ��ԭ����SQL����Ҫ�޸�DaoImpl�е�ʵ��(��createQuery�����޸�ΪcreateSQLQuery)��
	 *            ��������ʹ��ͨ�õ�DAO���޸ĺ��ѯ�Ľ������Object���ͣ���ҳ��(view��)�޷�ʹ�ö���ģ�ͻ�ȡ
	 *            ���������ֵ�����ÿ��ʵ���඼���Լ���Dao��DaoImpl�㣬�򲻴���������⡣
	 * 
	 * @param offset
	 *            ��ǰҳ����ʼ��¼��
	 * @param length
	 *            ��ǰҳ��Ҫ��ʾ�ļ�¼�����������ȡ�
	 * */
	public List<T> getAllRecordAndDivPage(final String hql, final int offset,
			final int length);

	/**
	 * ��ѯ���м�¼������
	 * */
	public int getAllRowCount(String hql);
	/**
	 * �־û�һ�����󣬸ö��󲻻��Լ��������ݿ��У�ֱ������flush����
	 * 
	 * @param entity
	 */
	public void persist(T entity);

}
