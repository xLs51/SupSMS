package com.supinfo.supsms.bean;

import java.util.List;

import javax.ejb.Stateless;

import org.codehaus.jettison.json.JSONObject;

import com.supinfo.supsms.dao.DaoFactory;
import com.supinfo.supsms.entities.User;
import com.supinfo.supsms.service.UserService;

@Stateless
public class UserServiceBean implements UserService
{
	@Override
	public List<User> getAll()
	{
		return DaoFactory.getUserDao().getAll();
	}

	@Override
	public User getById(Long id)
	{
		return DaoFactory.getUserDao().getById(id);
	}

	@Override
	public void remove(Long id)
	{
		DaoFactory.getUserDao().remove(id);
	}

	@Override
	public int getTotalNumberOfUsers()
	{
		return DaoFactory.getUserDao().getAll().size();
	}

	@Override
	public List<User> getUserLikeUsername(JSONObject data) 
	{
		return DaoFactory.getUserDao().getUserLikeUsername(data);
	}

	@Override
	public boolean getUserByPhoneNumber(JSONObject data) 
	{
		if (DaoFactory.getUserDao().getUserByPhoneNumber(data) != null)
			return true;
		
		return false;
	}
	
	@Override
	public void update(User user) 
	{
		DaoFactory.getUserDao().update(user);
	}
}
