package com.supinfo.supsms.service;

import java.util.List;

import javax.ejb.Local;

import org.codehaus.jettison.json.JSONObject;

import com.supinfo.supsms.entities.User;

@Local
public interface UserService
{
	public List<User> getAll();
	public User getById(Long id);
	public void remove(Long id);
	public int getTotalNumberOfUsers();
	public List<User> getUserLikeUsername(JSONObject data);
	public boolean getUserByPhoneNumber(JSONObject data);
	public void update(User user);
}
