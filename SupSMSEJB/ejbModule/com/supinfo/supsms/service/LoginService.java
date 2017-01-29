package com.supinfo.supsms.service;

import javax.ejb.Local;

import org.codehaus.jettison.json.JSONObject;

import com.supinfo.supsms.entities.User;

@Local
public interface LoginService 
{
	public User connect(JSONObject data);
}
