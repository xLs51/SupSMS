package com.supinfo.supsms.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class SMS implements Serializable
{
	private static final long serialVersionUID = 8392515474609155308L;
	
	public static final String JSON_ID = "id";
	public static final String JSON_SENDER = "sender";
	public static final String JSON_TEXT = "text";
	public static final String JSON_DATE = "date";
	public static final String JSON_CONVERSATION = "conversation";

	/////////////////////////////////////
	////	ATTRIBUTES
	/////////////////////////////////////
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@OneToOne
	@JoinColumn(name="sender_fk")
	private User sender;
	
	@NotEmpty
	@Column(nullable=false)
	private String text;
	
	@Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	
	@OneToOne
	@JoinColumn(name = "conversation_fk")
	private Conversation conversation;
	
	
	/////////////////////////////////////
	////	GETTERS AND SETTERS
	/////////////////////////////////////
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public User getSender() {
		return sender;
	}
	public void setSender(User sender) {
		this.sender = sender;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Conversation getConversation() {
		return conversation;
	}
	public void setConversation(Conversation conversation) {
		this.conversation = conversation;
	}
	
	
	/////////////////////////////////////
	////	JSON HANDLER
	/////////////////////////////////////
	public JSONObject toJson()
	{
		JSONObject json = new JSONObject();
		
		try
		{
			json.put(JSON_ID, this.getId());
			json.put(JSON_SENDER, this.getSender().toJson());
			json.put(JSON_TEXT, this.getText());
			json.put(JSON_DATE, this.getDate());
			json.put(JSON_CONVERSATION, this.getConversation().toJson());
		}
		catch (JSONException e)
		{
			e.printStackTrace();
		} 
		
		return json;
	}
	
	public static SMS toObject(JSONObject object)
	{
		SMS sms = new SMS();
		
		try 
		{
			sms.setId(object.getInt(JSON_ID));
			sms.setSender(User.toObject(object.getJSONObject(JSON_SENDER)));
			sms.setText(object.getString(JSON_TEXT));
			sms.setDate((Date)object.get(JSON_DATE));
			sms.setConversation(Conversation.toObject(object.getJSONObject(JSON_CONVERSATION)));
		}
		catch (JSONException e) 
		{
			e.printStackTrace();
		}
		
		return sms;
	}
}
