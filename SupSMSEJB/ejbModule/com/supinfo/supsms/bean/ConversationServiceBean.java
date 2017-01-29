package com.supinfo.supsms.bean;

import java.util.List;

import javax.ejb.Stateless;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.supinfo.supsms.dao.DaoFactory;
import com.supinfo.supsms.entities.Conversation;
import com.supinfo.supsms.service.ConversationService;

@Stateless
public class ConversationServiceBean implements ConversationService
{
	@Override
	public Conversation addConversationIfNotExists(JSONObject data)
	{
		return DaoFactory.getConversationDao().addConversationIfNotExists(data);
	}
	
	@Override
	public List<Conversation> getAllConversationsByUser(Long id)
	{
		return DaoFactory.getConversationDao().getAllConversationsByUser(id);
	}

	@Override
	public void deleteConversation(JSONObject data)
	{
		try
		{
			DaoFactory.getConversationDao().remove(data.getInt("conversationId"));
		}
		catch (JSONException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public Conversation getConversationById(JSONObject data)
	{
		try
		{
			return DaoFactory.getConversationDao().getById(data.getInt("conversationId"));
		}
		catch (JSONException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public void removeConversationByUserAndContact(JSONObject data)
	{
		try
		{
			DaoFactory.getConversationDao().removeConversationsByUserAndContact(data.getLong("userId"), data.getString("phoneNumber"));
		}
		catch (JSONException e)
		{
			e.printStackTrace();
		}
	}
}
