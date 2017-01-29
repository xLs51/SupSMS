package com.supinfo.supsms.bean;

import java.util.List;

import javax.ejb.Stateless;

import org.codehaus.jettison.json.JSONObject;

import com.supinfo.supsms.dao.DaoFactory;
import com.supinfo.supsms.entities.Contact;
import com.supinfo.supsms.service.ContactService;

@Stateless
public class ContactServiceBean implements ContactService
{
	@Override
	public Contact add(JSONObject data)
	{
		return DaoFactory.getContactDao().add(Contact.toObject(data));
	}
	
	@Override
	public List<Contact> getAll()
	{
		return DaoFactory.getContactDao().getAll();
	}
	
	@Override
	public void remove(long id)
	{
		DaoFactory.getContactDao().remove(id);
	}

	@Override
	public Contact getById(long id)
	{
		return DaoFactory.getContactDao().getById(id);
	}

	@Override
	public Contact getContactByPhoneAndUser(JSONObject data)
	{
		return DaoFactory.getContactDao().checkIFPhoneNumberIsAContactOfUser(data);
	}

	public void update(Contact contact)
	{
		DaoFactory.getContactDao().update(contact);
	}

	@Override
	public List<Contact> getAllFromUser(Long id)
	{
		return DaoFactory.getContactDao().getAllFromUser(id);
	}
}
