package com.supinfo.supsms.service;

import java.util.List;

import javax.ejb.Local;

import org.codehaus.jettison.json.JSONObject;

import com.supinfo.supsms.entities.Invoice;
import com.supinfo.supsms.entities.User;

@Local
public interface RegisterService 
{
	public boolean isUserExisting(JSONObject data);
	public User registerUser(JSONObject data);
	List<Invoice> getAllInvoices();
	Invoice getSelectedInvoice(int invoiceId);
	boolean isPhoneNumberExisting(JSONObject data);
	boolean isMailExisting(JSONObject data);
}
