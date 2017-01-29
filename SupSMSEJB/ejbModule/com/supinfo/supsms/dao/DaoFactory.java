package com.supinfo.supsms.dao;

import com.supinfo.supsms.dao.jpa.JpaContactDao;
import com.supinfo.supsms.dao.jpa.JpaConversationDao;
import com.supinfo.supsms.dao.jpa.JpaInvoiceDao;
import com.supinfo.supsms.dao.jpa.JpaSMSDao;
import com.supinfo.supsms.dao.jpa.JpaUserDao;

public class DaoFactory 
{
	private DaoFactory()
	{
		
	}
	
	public static UserDao getUserDao()
	{
		return new JpaUserDao();
	}
	
	public static InvoiceDao getInvoiceDao()
	{
		return new JpaInvoiceDao();
	}
	
	public static ContactDao getContactDao()
	{
		return new JpaContactDao();
	}
	
	public static SMSDao getSMSDao()
	{
		return new JpaSMSDao();
	}
	
	public static ConversationDao getConversationDao()
	{
		return new JpaConversationDao();
	}
}
