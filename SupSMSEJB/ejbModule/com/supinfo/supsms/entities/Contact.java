
package com.supinfo.supsms.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Contact implements Serializable
{
	private static final long serialVersionUID = 8392515474609155308L;
	
	public static final String JSON_ID = "id";
	public static final String JSON_FIRSTNAME = "firstName";
	public static final String JSON_LASTNAME = "lastName";
	public static final String JSON_PHONENUMBER = "phone";
	public static final String JSON_EMAIL = "mail";
	public static final String JSON_USER_OWNER = "user";

	/////////////////////////////////////
	////	ATTRIBUTES
	/////////////////////////////////////
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@NotEmpty
	@Column(nullable=false)
	private String firstName;
	
	@NotEmpty
	@Column(nullable=false)
	private String lastName;
	
	@NotEmpty
	@Column(nullable=false)
	private String phone;
	
	@NotEmpty
	@Column(nullable=false)
	private String mail;
	
	@Column(nullable=true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date updated;

	@NotNull
	@ManyToOne
	@JoinColumn(name="USER_FK")
	private User user_owner;
	
	
	/////////////////////////////////////
	////	GETTERS AND SETTERS
	/////////////////////////////////////
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public Date getUpdated() {
		return updated;
	}
	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	public User getUser_owner() {
		return user_owner;
	}
	public void setUser_owner(User user_owner) {
		this.user_owner = user_owner;
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
			json.put(JSON_FIRSTNAME, this.getFirstName());
			json.put(JSON_LASTNAME, this.getLastName());
			json.put(JSON_PHONENUMBER, this.getPhone());
			json.put(JSON_EMAIL, this.getMail());
			json.put(JSON_USER_OWNER, this.getUser_owner().toJson());
		}
		catch (JSONException e)
		{
			e.printStackTrace();
		} 
		
		return json;
	}
	
	public static Contact toObject(JSONObject object)
	{
		Contact contact = new Contact();
		
		try 
		{
			contact.setId(object.getInt(JSON_ID));
			contact.setFirstName(object.getString(JSON_FIRSTNAME));
			contact.setLastName(object.getString(JSON_LASTNAME));
			contact.setPhone(object.getString(JSON_PHONENUMBER));
			contact.setMail(object.getString(JSON_EMAIL));
			contact.setUser_owner(User.toObject(object.getJSONObject(JSON_USER_OWNER)));
		}
		catch (JSONException e) 
		{
			e.printStackTrace();
		}
		
		return contact;
	}
}
