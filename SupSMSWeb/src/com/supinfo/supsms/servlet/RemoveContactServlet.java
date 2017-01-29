package com.supinfo.supsms.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.supinfo.supsms.entities.Contact;
import com.supinfo.supsms.entities.User;
import com.supinfo.supsms.globals.Alert;
import com.supinfo.supsms.service.ContactService;
import com.supinfo.supsms.service.ConversationService;

@WebServlet("/auth/removeContact")
public class RemoveContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private ContactService contactService;
	
	@EJB
	private ConversationService conversationService;

    public RemoveContactServlet()
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Long id = Long.valueOf(request.getParameter("id"));
		Contact contact = contactService.getById(id);
		
		HttpSession session = request.getSession();
		
		if(contact != null)
		{
			contactService.remove(id);
			
			try {
				conversationService.removeConversationByUserAndContact(new JSONObject().put("userId", ((User)session.getAttribute("user")).getId()).put("phoneNumber", contact.getPhone()));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
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
