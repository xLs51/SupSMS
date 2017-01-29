package com.supinfo.supsms.dao;

import java.util.List;

import org.codehaus.jettison.json.JSONObject;

import com.supinfo.supsms.entities.Conversation;

public interface ConversationDao extends Dao<Conversation>
{
	Conversation addConversationIfNotExists(JSONObject data);
	List<Conversation> getAllConversationsByUser(Long id);
	void removeConversationsByUserAndContact(Long id, String phoneNumber);
}
