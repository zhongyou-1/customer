package com.qidian.dao;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component("baseDAO")
@Qualifier("baseDAO")
public class BaseDAO {
	protected static Logger log = LoggerFactory.getLogger(BaseDAO.class);

	@PersistenceContext
	protected EntityManager manager;

	public <T> T findById(Class<T> entityClass, Object primaryKey) {
		T t = null;
		if (primaryKey == null) {
			log.debug(this.getClass().getName() + " findById()方法:primaryKey为空!");
			return null;
		}
		try {
			t = manager.find(entityClass, primaryKey);
		} catch (Exception e) {
			log.error("根据主键查找记录失败:", e);
		}
		return t;
	}

	public <T> List<T> findByIds(Class<T> entityClass, Collection<? extends Object> primaryKeys) {
		if (primaryKeys == null || primaryKeys.isEmpty()) {
			return Collections.emptyList();
		}
		List<T> list = new LinkedList<>();
		Object[] ids = primaryKeys.toArray();
		for (int i = 0; i < ids.length; i++) {
			list.add(manager.find(entityClass, ids[i]));
		}
		return list;
	}

	@Transactional
	public boolean merge(Object... objects) {
		if (objects == null) {
			return true;
		}
		try {
			for (Object object : objects) {
				manager.merge(object);
			}
		} catch (Exception e) {
			log.error("更新到数据库出错:", e);
			e.printStackTrace();
		} finally {
			manager.flush();
		}
		return true;
	}

	@Transactional
	public boolean persist(Object... objects) {
		if (objects == null) {
			return true;
		}
		try {
			for (Object object : objects) {
				manager.persist(object);
			}
		} catch (Exception e) {
			log.error("保存到数据库出错:", e);
			e.printStackTrace();
		} finally {
			manager.flush();
		}
		return true;
	}

	public <T> T readFirst(Class<T> cname, String jqpl, Object... params) {
		TypedQuery<T> query = manager.createQuery(jqpl, cname);
		if (params != null) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i + 1, params[i]);
			}
		}
		query.setMaxResults(1);
		List<T> list = query.getResultList();
		if (list == null || list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

	protected <T> T getFirstResult(TypedQuery<T> query) {
		query.setMaxResults(1);
		List<T> list = query.getResultList();
		if (list == null || list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

}
