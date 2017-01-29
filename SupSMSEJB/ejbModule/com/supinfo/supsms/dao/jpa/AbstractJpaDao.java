package com.supinfo.supsms.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.supinfo.supsms.dao.Dao;
import com.supinfo.supsms.utils.PersistenceManager;

public class AbstractJpaDao<T> implements Dao<T> 
{
	private Class<T> object;
	
	@PersistenceContext(unitName="SupSMS-PU")
	protected EntityManager em;
	
	protected AbstractJpaDao(Class<T> object) 
	{
		this.object = object;
	}

	@Override
	public T add(T newObject) 
	{
		em = PersistenceManager.getEntityManagerFactory().createEntityManager();
		
		// Create
		em.persist(newObject);
		return newObject;
	}

	@Override
	public void update(T object) 
	{
		em = PersistenceManager.getEntityManagerFactory().createEntityManager();
		
		// Update
		em.merge(object);
	}

	@Override
	public void remove(long id) 
	{
		em = PersistenceManager.getEntityManagerFactory().createEntityManager();
		
		// Delete
		em.remove(em.find(object, id));
	}

	@Override
	public T getById(long id) 
	{
		em = PersistenceManager.getEntityManagerFactory().createEntityManager();
		
		// Read
		return (T) em.find(object, id);
	}

	@Override
	public List<T> getAll() 
	{
		em = PersistenceManager.getEntityManagerFactory().createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();      
		CriteriaQuery<T> cq = cb.createQuery(object);
		Root<T> t = cq.from(object);
		cq.select(t);
		TypedQuery<T> q = em.createQuery(cq);
		List<T> list = q.getResultList();
		return list;
	}
}