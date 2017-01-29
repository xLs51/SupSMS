package com.supinfo.supsms.servlet;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.supinfo.supsms.entities.Contact;
import com.supinfo.supsms.entities.User;
import com.supinfo.supsms.service.ContactService;

@WebServlet("/auth/contact")
public class ContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private ContactService contactService;
       
    public ContactServlet()
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Long id = ((User)request.getSession().getAttribute("user")).getId();
		List<Contact> contactList = contactService.getAllFromUser(id);
		
		request.setAttribute("pageTitle", "Manage your contacts");
		request.setAttribute("listContact", contactList);
		
		request.getRequestDispatcher("/auth/contact.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}

}
