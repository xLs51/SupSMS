package com.supinfo.supsms.dao.jpa;

import java.util.List;

import javax.persistence.NoResultException;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.supinfo.supsms.dao.ContactDao;
import com.supinfo.supsms.entities.Contact;
import com.supinfo.supsms.utils.PersistenceManager;

public class JpaContactDao extends AbstractJpaDao<Contact> implements ContactDao
{
	public JpaContactDao() 
	{
		super(Contact.class);		
	}

	@Override
	public Contact checkIFPhoneNumberIsAContactOfUser(JSONObject data)
	{
		em = PersistenceManager.getEntityManagerFactory().createEntityManager();
		
		String phone = "";
		int userId = 0;
		try 
		{
			phone = data.getString("phone");
			userId = data.getInt("userId");
		} catch (JSONException e) 
		{
			e.printStackTrace();
		}
		
		try
		{
			return (Contact) em.createQuery("SELECT c FROM Contact c WHERE c.phone = '" + phone + "' AND c.user_owner.id = " + userId).getSingleResult();
		}
		catch(NoResultException e) 
		{
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Contact> getAllFromUser(Long id)
	{
		em = PersistenceManager.getEntityManagerFactory().createEntityManager();

		try
		{
			return (List<Contact>) em.createQuery("SELECT c FROM Contact c WHERE c.user_owner.id = " + id).getResultList();
		}
		catch(NoResultException e) 
		{
			return null;
		}
	}
}
