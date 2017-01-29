package com.supinfo.supsms.service;

import java.util.List;

import javax.ejb.Local;

import org.codehaus.jettison.json.JSONObject;

import com.supinfo.supsms.entities.Conversation;

@Local
public interface ConversationService
{
	public Conversation addConversationIfNotExists(JSONObject data);
	public List<Conversation> getAllConversationsByUser(Long id);
	public void deleteConversation(JSONObject data);
	public Conversation getConversationById(JSONObject data);
	void removeConversationByUserAndContact(JSONObject data);
}
