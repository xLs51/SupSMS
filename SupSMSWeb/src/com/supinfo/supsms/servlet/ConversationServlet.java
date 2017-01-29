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
import com.supinfo.supsms.entities.SMS;
import com.supinfo.supsms.entities.User;
import com.supinfo.supsms.service.ContactService;
import com.supinfo.supsms.service.ConversationService;
import com.supinfo.supsms.service.SMSService;

@WebServlet("/auth/conversation")
public class ConversationServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	@EJB
	private SMSService smsService;
	
	@EJB
	private ConversationService conversationService;
	
	@EJB
	private ContactService contactService;
	
    public ConversationServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String conversationId = request.getParameter("id");
		
		try
		{
			List<SMS> sms = smsService.getAllSMSByConversation(new JSONObject().put("conversationId", conversationId));
			request.setAttribute("sms", sms);
			
			Conversation conversation = conversationService.getConversationById(new JSONObject().put("conversationId", Integer.parseInt(conversationId)));
			request.setAttribute("conversationNumber", conversation.getContactName());
			
			Contact contact = contactService.getContactByPhoneAndUser(new JSONObject().put("phone", conversation.getContactName()).put("userId", ((User)request.getSession().getAttribute("user")).getId()));
			if(contact != null)
				conversation.setContactName(contact.getFirstName() + " " + contact.getLastName());
			
			request.setAttribute("conversation", conversation);
		}
		catch (JSONException e)
		{
			e.printStackTrace();
		}
		
		request.setAttribute("pageTitle", "Conversation");
		request.getRequestDispatcher("/auth/conversation.jsp").forward(request, response);
	}
}
