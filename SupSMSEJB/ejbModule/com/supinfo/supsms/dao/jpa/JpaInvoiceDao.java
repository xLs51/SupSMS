package com.supinfo.supsms.dao.jpa;

import com.supinfo.supsms.dao.InvoiceDao;
import com.supinfo.supsms.entities.Invoice;

public class JpaInvoiceDao extends AbstractJpaDao<Invoice> implements InvoiceDao 
{
	public JpaInvoiceDao() 
	{
		super(Invoice.class);		
	}
}
