
package com.supinfo.supsms.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Conversation implements Serializable
{
	private static final long serialVersionUID = 8392515474609155308L;
	
	public static final String JSON_ID = "id";
	public static final String JSON_CONTACTNAME = "contactName";
	public static final String JSON_USER = "user";

	/////////////////////////////////////
	////	ATTRIBUTES
	/////////////////////////////////////
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@NotEmpty
	@Column(nullable=false)
	private String contactName;
	
	@OneToOne
	@JoinColumn(name="user_fk")
	private User user;

	@OneToMany(mappedBy="conversation", orphanRemoval = true, cascade=CascadeType.REMOVE)
	private Collection<SMS> conversationSMS;
	
	
	/////////////////////////////////////
	////	GETTERS AND SETTERS
	/////////////////////////////////////
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public Collection<SMS> getConversationSMS() {
		return conversationSMS;
	}
	public void setConversationSMS(Collection<SMS> conversationSMS) {
		this.conversationSMS = conversationSMS;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
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
			json.put(JSON_CONTACTNAME, this.getContactName());
			json.put(JSON_USER, this.getUser().toJson());
		}
		catch (JSONException e)
		{
			e.printStackTrace();
		} 
		
		return json;
	}
	
	public static Conversation toObject(JSONObject object)
	{
		Conversation conversation = new Conversation();
		
		try 
		{
			conversation.setId(object.getInt(JSON_ID));
			conversation.setContactName(object.getString(JSON_CONTACTNAME));
			conversation.setUser(User.toObject(object.getJSONObject(JSON_USER)));
		}
		catch (JSONException e) 
		{
			e.printStackTrace();
		}
		
		return conversation;
	}
}
