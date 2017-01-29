package com.supinfo.supsms.bean;

import javax.ejb.Stateless;

import org.codehaus.jettison.json.JSONObject;

import com.supinfo.supsms.dao.DaoFactory;
import com.supinfo.supsms.entities.Invoice;
import com.supinfo.supsms.entities.User;
import com.supinfo.supsms.service.InitService;

@Stateless
public class InitServiceBean implements InitService
{
	@Override
	public void initInvoices()
	{
		if(DaoFactory.getInvoiceDao().getAll().size() == 0) //if we don't already have got a some invoices in base
		{
			Invoice firstInvoice = new Invoice();
			firstInvoice.setName("First");
			firstInvoice.setPrice(5);
			firstInvoice.setNbOfSMS(100);
			firstInvoice.setDuration(30);

			DaoFactory.getInvoiceDao().add(firstInvoice);

			Invoice secondInvoice = new Invoice();
			secondInvoice.setName("Second");
			secondInvoice.setPrice(20);
			secondInvoice.setNbOfSMS(-1);
			secondInvoice.setDuration(30);

			DaoFactory.getInvoiceDao().add(secondInvoice);
		}
	}

	@Override
	public void initAdmin() 
	{
		if(DaoFactory.getUserDao().getAllAdmins().size() == 0) //if we don't already have got at least one administrator
		{
			User firstAdmin = new User();
			firstAdmin.setAdmin(true);
			firstAdmin.setUsername("Admin");
			firstAdmin.setPassword("f1c4324718a174cb4b98445496c49b715eaed3f9"); //12345678
			firstAdmin.setCreditCard("0");
			firstAdmin.setFirstName("Admin");
			firstAdmin.setLastName("Admin");
			firstAdmin.setMail("Admin@Admin.Admin");
			firstAdmin.setPhone("0606060605");
			firstAdmin.setInvoice(DaoFactory.getInvoiceDao().getAll().get(0)); //we set an arbitrary invoice
			
			DaoFactory.getUserDao().add(firstAdmin);
		}
	}
	
	@Override
	public void initClients()
	{
		User firstUser = new User();
		firstUser.setAdmin(false);
		firstUser.setUsername("FirstUser");
		firstUser.setPassword("f1c4324718a174cb4b98445496c49b715eaed3f9"); //12345678
		firstUser.setCreditCard("0");
		firstUser.setFirstName("FirstUser");
		firstUser.setLastName("FirstUser");
		firstUser.setMail("FirstUser@FirstUser.FirstUser");
		firstUser.setPhone("0606060606");
		firstUser.setInvoice(DaoFactory.getInvoiceDao().getAll().get(0)); //we set an arbitrary invoice
		
		JSONObject firstUserData = firstUser.toJson();
		if (DaoFactory.getUserDao().getUserByUsername(firstUserData) == null) //if we don't already have got a user registered with this username 
		{
			DaoFactory.getUserDao().add(firstUser);
		}
		
		User secondUser = new User();
		secondUser.setAdmin(false);
		secondUser.setUsername("SecondUser");
		secondUser.setPassword("f1c4324718a174cb4b98445496c49b715eaed3f9"); //12345678
		secondUser.setCreditCard("0");
		secondUser.setFirstName("SecondUser");
		secondUser.setLastName("SecondUser");
		secondUser.setMail("SecondUser@SecondUser.SecondUser");
		secondUser.setPhone("0606060607");
		secondUser.setInvoice(DaoFactory.getInvoiceDao().getAll().get(0)); //we set an arbitrary invoice
		
		JSONObject secondUserData = secondUser.toJson();
		if (DaoFactory.getUserDao().getUserByUsername(secondUserData) == null) //if we don't already have got a user registered with this username 
		{
			DaoFactory.getUserDao().add(secondUser);
		}
	}
}
