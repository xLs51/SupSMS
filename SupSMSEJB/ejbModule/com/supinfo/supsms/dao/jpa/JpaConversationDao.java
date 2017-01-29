package com.supinfo.supsms.dao.jpa;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.supinfo.supsms.dao.ConversationDao;
import com.supinfo.supsms.dao.DaoFactory;
import com.supinfo.supsms.entities.Conversation;
import com.supinfo.supsms.entities.SMS;
import com.supinfo.supsms.entities.User;
import com.supinfo.supsms.utils.PersistenceManager;

public class JpaConversationDao extends AbstractJpaDao<Conversation> implements ConversationDao 
{
	public JpaConversationDao() 
	{
		super(Conversation.class);		
	}
	
	@Override
	public Conversation addConversationIfNotExists(JSONObject data)
	{
		em = PersistenceManager.getEntityManagerFactory().createEntityManager();
		
		String contact = "";
		String user = "";
		try 
		{
			contact = data.getString("contact");
			user = data.getString("user");
		}
		catch (JSONException e) 
		{
			e.printStackTrace();
		}
		
		try
		{
			CriteriaBuilder cb = em.getCriteriaBuilder();      
			CriteriaQuery<Conversation> cq = cb.createQuery(Conversation.class);
			Root<Conversation> conv = cq.from(Conversation.class);
			cq.select(conv).where(cb.equal(conv.get("contactName"), contact), cb.and(cb.equal(conv.get("user").get("phone"), user)));
			TypedQuery<Conversation> q = em.createQuery(cq);
			Conversation result = q.getSingleResult();
			return result;
		}
		catch(NoResultException e) 
		{
			Conversation conversation = new Conversation();
			conversation.setContactName(contact);
			
			try {
				User targetUser = DaoFactory.getUserDao().getUserByPhoneNumber(new JSONObject().put("phone", user));
				if(targetUser == null)
					return null;
				
				conversation.setUser(targetUser);
			} catch (JSONException e1) {
				e1.printStackTrace();
			}
			
			return add(conversation);
		}
	}
	
	@Override
	public void removeConversationsByUserAndContact(Long id, String phoneNumber)
	{
		em = PersistenceManager.getEntityManagerFactory().createEntityManager();
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Conversation> cq = cb.createQuery(Conversation.class);
		Root<Conversation> conv = cq.from(Conversation.class);
		cq.select(conv).where(cb.equal(conv.get("user").get("id"), id), cb.and(cb.equal(conv.get("contactName"), phoneNumber)));
		TypedQuery<Conversation> q = em.createQuery(cq);
		List<Conversation> list = q.getResultList();
		
		for(Conversation conversation : list)
		{
			CriteriaQuery<SMS> sms = cb.createQuery(SMS.class);
			Root<SMS> smsRoot = cq.from(SMS.class);
			sms.select(smsRoot).where(cb.equal(smsRoot.get("conversation").get("id"), conversation.getId()));
			TypedQuery<SMS> smsQuery = em.createQuery(sms);
			List<SMS> smsList = smsQuery.getResultList();
			
			for(SMS s : smsList)
				em.remove(s);
			
			em.remove(conversation);
		}
	}
	
	@Override
	public List<Conversation> getAllConversationsByUser(Long id)
	{
		em = PersistenceManager.getEntityManagerFactory().createEntityManager();
		
		try
		{
			CriteriaBuilder cb = em.getCriteriaBuilder();      
			CriteriaQuery<Conversation> cq = cb.createQuery(Conversation.class);
			Root<Conversation> conv = cq.from(Conversation.class);
			cq.select(conv).where(cb.equal(conv.get("user").get("id"), id));
			TypedQuery<Conversation> q = em.createQuery(cq);
			List<Conversation> list = q.getResultList();
			return list;
		}
		catch(NoResultException e) 
		{
			return null;
		}
	}
}
