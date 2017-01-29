package com.supinfo.supsms.soap;

import java.util.List;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.supinfo.supsms.entities.Contact;
import com.supinfo.supsms.service.ContactService;

@WebService(name="ContactWebService", serviceName="ContactService")
public class ContactWebService {
	
	@EJB
	ContactService contactService;
	
	@WebMethod(operationName="getContactsByUser")
	public List<Contact> getAllContactsFromUser(@WebParam(name="id") Long id)
	{
		return contactService.getAllFromUser(id);
	}
}
