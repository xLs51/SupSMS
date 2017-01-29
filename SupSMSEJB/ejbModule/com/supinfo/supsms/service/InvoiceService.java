package com.supinfo.supsms.service;

import java.util.List;

import javax.ejb.Local;

import com.supinfo.supsms.entities.Invoice;

@Local
public interface InvoiceService
{
	public List<Invoice> getAll();
}
