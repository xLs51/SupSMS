package com.supinfo.supsms.service;

import java.util.List;

import javax.ejb.Local;

import org.codehaus.jettison.json.JSONObject;

import com.supinfo.supsms.entities.SMS;

@Local
public interface SMSService
{
	public void addSMS(JSONObject data);
	public List<SMS> getAllSMSByConversation(JSONObject data);
	int getTotalNumberOfSMS();
}
