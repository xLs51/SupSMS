package com.supinfo.supsms.listener;

import javax.ejb.EJB;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.supinfo.supsms.service.InitService;
import com.supinfo.supsms.utils.PersistenceManager;

@WebListener
public class PersistenceAppListener implements ServletContextListener
{
	@EJB
	private InitService initService;
	
	public PersistenceAppListener()
	{

	}

	public void contextInitialized(ServletContextEvent arg0)
	{
		initService.initInvoices();
		initService.initAdmin();
		initService.initClients();
	}

	public void contextDestroyed(ServletContextEvent arg0)
	{
		PersistenceManager.closeEntityManagerFactory();
	}
}
