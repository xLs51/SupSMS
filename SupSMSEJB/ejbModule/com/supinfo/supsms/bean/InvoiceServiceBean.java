package com.supinfo.supsms.bean;

import java.util.List;

import javax.ejb.Stateless;

import com.supinfo.supsms.dao.DaoFactory;
import com.supinfo.supsms.entities.Invoice;
import com.supinfo.supsms.service.InvoiceService;

@Stateless
public class InvoiceServiceBean implements InvoiceService
{
	@Override
	public List<Invoice> getAll()
	{
		return DaoFactory.getInvoiceDao().getAll();
	}
}
