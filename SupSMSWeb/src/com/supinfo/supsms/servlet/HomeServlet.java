package com.supinfo.supsms.servlet;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.supinfo.supsms.entities.Contact;
import com.supinfo.supsms.entities.Conversation;
import com.supinfo.supsms.entities.User;
import com.supinfo.supsms.service.ContactService;
import com.supinfo.supsms.service.ConversationService;
import com.supinfo.supsms.service.SMSService;
import com.supinfo.supsms.service.UserService;

@WebServlet("/home")
public class HomeServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	@EJB
	private ConversationService conversationService;

	@EJB
	private ContactService contactService;
	
	@EJB
	private SMSService smsService;
	
	@EJB
	private UserService userService;

    public HomeServlet()
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		if(request.getSession().getAttribute("user") == null)
		{
			request.setAttribute("numberOfSMS", smsService.getTotalNumberOfSMS());
			request.setAttribute("numberOfUsers", userService.getTotalNumberOfUsers());
		}
		else
		{
			try
			{
				List<Conversation> conversations = conversationService.getAllConversationsByUser(((User)request.getSession().getAttribute("user")).getId());
				for(Conversation conversation : conversations)
				{
					Contact contact = contactService.getContactByPhoneAndUser(new JSONObject().put("phone", conversation.getContactName()).put("userId", ((User)request.getSession().getAttribute("user")).getId()));
					if(contact != null)
						conversation.setContactName(contact.getFirstName() + " " + contact.getLastName());
				}
				request.setAttribute("conversations", conversations);
			}
			catch (JSONException e)
			{
				e.printStackTrace();
			}
		}
		
		request.setAttribute("pageTitle", "Home");
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}
}
