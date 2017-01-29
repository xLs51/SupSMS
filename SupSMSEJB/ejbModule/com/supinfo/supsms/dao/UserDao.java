package com.supinfo.supsms.dao;

import java.util.List;

import org.codehaus.jettison.json.JSONObject;

import com.supinfo.supsms.entities.User;

public interface UserDao extends Dao<User>
{
	User getUserByUsernameAndPassword(JSONObject data);
	User getUserByUsername(JSONObject data);
	List<User> getAllAdmins();
	User getUserByPhoneNumber(JSONObject data);
	User getUserByMail(JSONObject data);
	List<User> getUserLikeUsername(JSONObject data);
}
