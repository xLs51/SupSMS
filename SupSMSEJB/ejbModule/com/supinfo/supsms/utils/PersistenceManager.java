package com.supinfo.supsms.utils;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

public class PersistenceManager 
{
	@PersistenceContext(unitName="SupSMS-PU")
	private static EntityManagerFactory emf;
	
	private PersistenceManager() {}
	
	public static EntityManagerFactory getEntityManagerFactory()
	{
		if(emf != null && emf.isOpen())
			emf.close();

		emf = Persistence.createEntityManagerFactory("SupSMS-PU");
		return emf;
	}
	
	public static void closeEntityManagerFactory()
	{
		if(emf != null && emf.isOpen())
			emf.close();
	}
}
