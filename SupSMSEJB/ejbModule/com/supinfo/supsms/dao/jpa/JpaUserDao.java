package com.supinfo.supsms.dao.jpa;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.supinfo.supsms.dao.UserDao;
import com.supinfo.supsms.entities.User;
import com.supinfo.supsms.utils.PersistenceManager;

public class JpaUserDao extends AbstractJpaDao<User> implements UserDao 
{
	public JpaUserDao() 
	{
		super(User.class);		
	}

	@Override
	public User getUserByUsernameAndPassword(JSONObject data) 
	{
		em = PersistenceManager.getEntityManagerFactory().createEntityManager();
		
		String username = null;
		String password = null;
		try 
		{
			username = data.getString("username");
			password = data.getString("password");
		} catch (JSONException e) 
		{
			e.printStackTrace();
		}
		
		try 
		{
			return (User) em.createQuery("SELECT u FROM User u WHERE u.username = '" + username + "' AND u.password = '" + password + "'").getSingleResult();
		} catch(NoResultException e) {
			return null;
		}
	}

	@Override
	public User getUserByUsername(JSONObject data) 
	{
		em = PersistenceManager.getEntityManagerFactory().createEntityManager();
		
		String username = null;
		try 
		{
			username = data.getString("username");
		} catch (JSONException e) 
		{
			e.printStackTrace();
		}
		
		try 
		{
			return (User) em.createQuery("SELECT u FROM User u WHERE u.username = '" + username + "'").getSingleResult();
		} catch(NoResultException e) 
		{
			return null;
		}
	}
	
	@Override
	public User getUserByPhoneNumber(JSONObject data) 
	{
		em = PersistenceManager.getEntityManagerFactory().createEntityManager();
		
		String phoneNumber = null;
		try 
		{
			phoneNumber = data.getString("phone");
		} catch (JSONException e) 
		{
			e.printStackTrace();
		}
		
		try
		{
			return (User) em.createQuery("SELECT u FROM User u WHERE u.phone = '" + phoneNumber + "'").getSingleResult();
		} catch(NoResultException e) 
		{
			return null;
		}
	}
	
	@Override
	public User getUserByMail(JSONObject data) 
	{
		em = PersistenceManager.getEntityManagerFactory().createEntityManager();
		
		String mail = null;
		try 
		{
			mail = data.getString("mail");
		} catch (JSONException e) 
		{
			e.printStackTrace();
		}
		
		try 
		{
			return (User) em.createQuery("SELECT u FROM User u WHERE u.mail = '" + mail + "'").getSingleResult();
		} catch(NoResultException e) 
		{
			return null;
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<User> getAllAdmins()
	{
		em = PersistenceManager.getEntityManagerFactory().createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<User> cq = cb.createQuery(User.class);
		Root<User> user = cq.from(User.class);
		cq.select(user).where(cb.isTrue((Expression)user.get("isAdmin"))); //only works when isAdmin can't be null or throw an exception : cb.equal(user.get("isAdmin"), true) 
		TypedQuery<User> q = em.createQuery(cq);
		List<User> list = q.getResultList();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUserLikeUsername(JSONObject data) {
		em = PersistenceManager.getEntityManagerFactory().createEntityManager();
		
		String search = null;
		try 
		{
			search = data.getString("search");
		} catch (JSONException e) 
		{
			e.printStackTrace();
		}
		
		try 
		{
			return (List<User>) em.createQuery("SELECT u FROM User u WHERE u.username LIKE '%"+ search +"%'").getResultList();
		} catch(NoResultException e) 
		{
			return null;
		}
	}
}
