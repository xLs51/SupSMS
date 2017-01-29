package com.supinfo.supsms.bean;

import javax.ejb.Stateless;

import org.codehaus.jettison.json.JSONObject;

import com.supinfo.supsms.dao.DaoFactory;
import com.supinfo.supsms.entities.User;
import com.supinfo.supsms.service.LoginService;

@Stateless
public class LoginServiceBean implements LoginService 
{
	@Override
	public User connect(JSONObject data) 
	{
		return DaoFactory.getUserDao().getUserByUsernameAndPassword(data);
	}
}
