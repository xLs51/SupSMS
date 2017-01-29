package com.supinfo.supsms.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class User implements Serializable
{
	private static final long serialVersionUID = 8392515474609155308L;

	public static final String JSON_ID = "id";
	public static final String JSON_USERNAME = "username";
	public static final String JSON_PASSWORD = "password";
	public static final String JSON_FIRSTNAME = "firstName";
	public static final String JSON_LASTNAME = "lastName";
	public static final String JSON_PHONENUMBER = "phone";
	public static final String JSON_CREDITCARD = "creditCard";
	public static final String JSON_EMAIL = "mail";
	public static final String JSON_ISADMIN = "isAdmin";
	public static final String JSON_INVOICE = "invoice";

	/////////////////////////////////////
	////	ATTRIBUTES
	/////////////////////////////////////
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@NotEmpty
	@Column(unique=true, nullable=false)
	private String username;

	@NotEmpty
	@Column(nullable=false)
	private String password;
	
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
	private String creditCard;
	
	@NotEmpty
	@Column(nullable=false)
	private String mail;
	
	@Column(nullable=false)
	private boolean isAdmin;

	@ManyToOne
	@JoinColumn(name="INVOICE_FK")
	private Invoice invoice;
	
	
	/////////////////////////////////////
	////	GETTERS AND SETTERS
	/////////////////////////////////////
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	public String getCreditCard() {
		return creditCard;
	}
	public void setCreditCard(String creditCard) {
		this.creditCard = creditCard;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public boolean getIsAdmin() {
		return isAdmin;
	}
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	public Invoice getInvoice() {
		return invoice;
	}
	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
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
			json.put(JSON_USERNAME, this.getUsername());
			json.put(JSON_PASSWORD, this.getPassword());
			json.put(JSON_FIRSTNAME, this.getFirstName());
			json.put(JSON_LASTNAME, this.getLastName());
			json.put(JSON_PHONENUMBER, this.getPhone());
			json.put(JSON_CREDITCARD, this.getCreditCard());
			json.put(JSON_EMAIL, this.getMail());
			json.put(JSON_ISADMIN, this.getIsAdmin());
			json.put(JSON_INVOICE, this.getInvoice().toJson());
		}
		catch (JSONException e)
		{
			e.printStackTrace();
		} 
		return json;
	}

	public static User toObject(JSONObject object)
	{
		User user = new User();
		try 
		{
			user.setId(object.getInt(JSON_ID));
			user.setUsername(object.getString(JSON_USERNAME));
			user.setPassword(object.getString(JSON_PASSWORD));
			user.setFirstName(object.getString(JSON_FIRSTNAME));
			user.setLastName(object.getString(JSON_LASTNAME));
			user.setPhone(object.getString(JSON_PHONENUMBER));
			user.setCreditCard(object.getString(JSON_CREDITCARD));
			user.setMail(object.getString(JSON_EMAIL));
			user.setMail(object.getString(JSON_ISADMIN));
			user.setInvoice(Invoice.toObject(object.getJSONObject(JSON_INVOICE)));
		}
		catch (JSONException e) 
		{
			e.printStackTrace();
		}
		return user;
	}
}
