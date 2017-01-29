package com.supinfo.supsms.service;

import java.util.List;

import javax.ejb.Local;

import org.codehaus.jettison.json.JSONObject;

import com.supinfo.supsms.entities.Contact;

@Local
public interface ContactService
{
	public Contact add(JSONObject data);
	public List<Contact> getAll();
	public void update(Contact contact);
	public void remove(long id);
	public Contact getById(long id);
	public Contact getContactByPhoneAndUser(JSONObject data);
	public List<Contact> getAllFromUser(Long id);
}
