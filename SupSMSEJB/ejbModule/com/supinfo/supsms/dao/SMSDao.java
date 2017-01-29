package com.supinfo.supsms.dao;

import java.util.List;

import org.codehaus.jettison.json.JSONObject;

import com.supinfo.supsms.entities.SMS;

public interface SMSDao extends Dao<SMS>
{
	List<SMS> getAllSMSByUser(JSONObject data);
	List<SMS> getAllSMSByConversation(JSONObject data);
}
