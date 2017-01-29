package com.supinfo.supsms.servlet;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.supinfo.supsms.entities.Invoice;
import com.supinfo.supsms.service.InvoiceService;

@WebServlet("/offers")
public class OfferServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private InvoiceService invoiceService;
       
    public OfferServlet()
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		List<Invoice> invoiceList = invoiceService.getAll();
		
		request.setAttribute("pageTitle", "Offers");
		request.setAttribute("listOffers", invoiceList);
		request.getRequestDispatcher("/offers.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request,response);
	}

}
