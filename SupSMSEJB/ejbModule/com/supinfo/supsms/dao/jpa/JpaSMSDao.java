package com.supinfo.supsms.dao.jpa;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.supinfo.supsms.dao.SMSDao;
import com.supinfo.supsms.entities.Conversation;
import com.supinfo.supsms.entities.SMS;
import com.supinfo.supsms.utils.PersistenceManager;

public class JpaSMSDao extends AbstractJpaDao<SMS> implements SMSDao
{
	public JpaSMSDao() 
	{
		super(SMS.class);		
	}

	@Override
	public List<SMS> getAllSMSByUser(JSONObject data)
	{
		em = PersistenceManager.getEntityManagerFactory().createEntityManager();
		
		int userId = 0;
		try 
		{
			userId = data.getInt("userId");
		} catch (JSONException e) 
		{
			e.printStackTrace();
		}
		
		try
		{
			CriteriaBuilder cb = em.getCriteriaBuilder();      
			CriteriaQuery<SMS> cq = cb.createQuery(SMS.class);
			Root<SMS> sms = cq.from(SMS.class);
			cq.select(sms).where(cb.equal(sms.get("sender"), userId), cb.or(cb.equal(sms.get("receiver"), userId))).orderBy(cb.desc(sms.get("date")));
			TypedQuery<SMS> q = em.createQuery(cq);
			List<SMS> result = q.getResultList();
			return result;
		} catch(NoResultException e) 
		{
			return null;
		}
	}
	
	@Override
	public List<SMS> getAllSMSByConversation(JSONObject data)
	{
		em = PersistenceManager.getEntityManagerFactory().createEntityManager();
		
		int conversationId = 0;
		try 
		{
			conversationId = data.getInt("conversationId");
		} catch (JSONException e) 
		{
			e.printStackTrace();
		}
		
		try
		{
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<SMS> cq = cb.createQuery(SMS.class);
			Root<SMS> sms = cq.from(SMS.class);
			Join<SMS, Conversation> conv = sms.join("conversation", JoinType.LEFT);
			cq.select(sms).where(cb.equal(conv.get("id"), conversationId)).orderBy(cb.asc(sms.get("date")));
			TypedQuery<SMS> q = em.createQuery(cq);
			List<SMS> result = q.getResultList();
			return result;	
		} catch(NoResultException e) 
		{
			return null;
		}
	}
}
