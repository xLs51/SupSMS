package com.supinfo.supsms.service;

import javax.ejb.Local;

@Local
public interface InitService
{
	public void initInvoices();
	public void initAdmin();
	public void initClients();
}
