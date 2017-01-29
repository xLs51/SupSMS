package com.supinfo.supsms.dao;

import java.util.List;

import org.codehaus.jettison.json.JSONObject;

import com.supinfo.supsms.entities.Contact;

public interface ContactDao extends Dao<Contact>
{
	Contact checkIFPhoneNumberIsAContactOfUser(JSONObject data);
	List<Contact> getAllFromUser(Long id);
}
