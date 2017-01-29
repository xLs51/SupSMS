package com.supinfo.supsms.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Invoice implements Serializable
{
	private static final long serialVersionUID = 1L;

	public static final String JSON_ID = "id";
	public static final String JSON_NAME = "name";
	public static final String JSON_PRICE = "price";
	public static final String JSON_NBOFSMS = "nbOfSMS";
	public static final String JSON_DURATION = "duration";

	
	/////////////////////////////////////
	////	ATTRIBUTES
	/////////////////////////////////////
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@NotEmpty
	@Column(unique=true, nullable=false)
	private String name;
	
	@Column(nullable=false)
	private int price;
	
	@Column(nullable=false)
	private int nbOfSMS;
	
	@Column(nullable=false)
	private int duration;
	
	
	/////////////////////////////////////
	/////	GETTERS AND SETTERS
	/////////////////////////////////////
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getNbOfSMS() {
		return nbOfSMS;
	}
	public void setNbOfSMS(int nbOfSMS) {
		this.nbOfSMS = nbOfSMS;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
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
			json.put(JSON_NAME, this.getName());
			json.put(JSON_PRICE, this.getPrice());
			json.put(JSON_NBOFSMS, this.getNbOfSMS());
			json.put(JSON_DURATION, this.getDuration());
		}
		catch (JSONException e)
		{
			e.printStackTrace();
		} 
		return json;
	}

	public static Invoice toObject(JSONObject object)
	{
		Invoice invoice = new Invoice();
		try 
		{
			invoice.setId(object.getInt(JSON_ID));
			invoice.setName(object.getString(JSON_NAME));
			invoice.setPrice(object.getInt(JSON_PRICE));
			invoice.setNbOfSMS(object.getInt(JSON_NBOFSMS));
			invoice.setDuration(object.getInt(JSON_DURATION));
		}
		catch (JSONException e) 
		{
			e.printStackTrace();
		}
		return invoice;
	}
}
