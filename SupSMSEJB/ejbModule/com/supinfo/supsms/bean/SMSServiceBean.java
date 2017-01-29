package com.supinfo.supsms.bean;

import java.util.List;

import javax.ejb.Stateless;

import org.codehaus.jettison.json.JSONObject;

import com.supinfo.supsms.dao.DaoFactory;
import com.supinfo.supsms.entities.SMS;
import com.supinfo.supsms.service.SMSService;

@Stateless
public class SMSServiceBean implements SMSService
{
	@Override
	public void addSMS(JSONObject data)
	{
		DaoFactory.getSMSDao().add(SMS.toObject(data));
	}

	@Override
	public List<SMS> getAllSMSByConversation(JSONObject data)
	{	
		return DaoFactory.getSMSDao().getAllSMSByConversation(data);
	}
	
	@Override
	public int getTotalNumberOfSMS()
	{	
		return DaoFactory.getSMSDao().getAll().size();
	}
}
