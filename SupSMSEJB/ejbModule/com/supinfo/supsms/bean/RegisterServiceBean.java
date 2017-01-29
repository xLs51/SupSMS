package com.supinfo.supsms.bean;

import java.util.List;

import javax.ejb.Stateless;

import org.codehaus.jettison.json.JSONObject;

import com.supinfo.supsms.dao.DaoFactory;
import com.supinfo.supsms.entities.Invoice;
import com.supinfo.supsms.entities.User;
import com.supinfo.supsms.service.RegisterService;

@Stateless
public class RegisterServiceBean implements RegisterService
{
	@Override
	public boolean isUserExisting(JSONObject data)
	{
		if (DaoFactory.getUserDao().getUserByUsername(data) != null)
			return true;
		
		return false;
	}
	
	@Override
	public boolean isPhoneNumberExisting(JSONObject data)
	{
		if (DaoFactory.getUserDao().getUserByPhoneNumber(data) != null)
			return true;
		
		return false;
	}
	
	@Override
	public boolean isMailExisting(JSONObject data)
	{
		if (DaoFactory.getUserDao().getUserByMail(data) != null)
			return true;
		
		return false;
	}

	@Override
	public User registerUser(JSONObject data) 
	{
		return DaoFactory.getUserDao().add(User.toObject(data));
	}
	
	@Override
	public List<Invoice> getAllInvoices()
	{
		return DaoFactory.getInvoiceDao().getAll();	
	}
	
	@Override
	public Invoice getSelectedInvoice(int invoiceId)
	{
		return DaoFactory.getInvoiceDao().getById(invoiceId);
	}
}
