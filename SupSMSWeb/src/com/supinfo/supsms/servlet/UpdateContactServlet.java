package com.supinfo.supsms.servlet;

import java.io.IOException;
import java.util.Calendar;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.supinfo.supsms.entities.Contact;
import com.supinfo.supsms.globals.Alert;
import com.supinfo.supsms.globals.InputName;
import com.supinfo.supsms.service.ContactService;

@WebServlet("/auth/updateContact")
public class UpdateContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private ContactService contactService;
       
    public UpdateContactServlet()
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Long id = Long.valueOf(request.getParameter("id"));
		Contact contact = contactService.getById(id);
		
		HttpSession session = request.getSession();
		
		if(contact != null)
		{
			request.setAttribute("contact", contact);
			request.setAttribute("pageTitle", "Update Contact");
			request.getRequestDispatcher("/auth/updateContact.jsp").forward(request, response);
		}
		else
		{
			session.setAttribute(Alert.PRMTR_ERROR, "The contact doesn't exist !");
			request.setAttribute("pageTitle", "Contacts");
			request.getRequestDispatcher("/auth/contact.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Long id = Long.valueOf(request.getParameter("id"));
		String firstname = request.getParameter(InputName.PRMTR_FIRSTNAME);
		String lastname = request.getParameter(InputName.PRMTR_LASTNAME);
		String mail = request.getParameter(InputName.PRMTR_MAIL);
		
		Contact contact = contactService.getById(id);
		
		HttpSession session = request.getSession();
		
		if(contact != null)
		{
			contact.setFirstName(firstname);
			contact.setLastName(lastname);
			contact.setMail(mail);
			contact.setUpdated(Calendar.getInstance().getTime());
			
			contactService.update(contact);
			response.sendRedirect(request.getContextPath()+"/auth/contact");
		}
		else
		{
			session.setAttribute(Alert.PRMTR_ERROR, "The contact doesn't exist !");
			request.setAttribute("pageTitle", "Contacts");
			request.getRequestDispatcher("/auth/contact.jsp").forward(request, response);
		}	
	}

}
