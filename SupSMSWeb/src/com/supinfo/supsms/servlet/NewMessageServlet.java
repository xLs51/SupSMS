package com.supinfo.supsms.servlet;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.supinfo.supsms.entities.Contact;
import com.supinfo.supsms.entities.Conversation;
import com.supinfo.supsms.entities.SMS;
import com.supinfo.supsms.entities.User;
import com.supinfo.supsms.globals.Alert;
import com.supinfo.supsms.service.ContactService;
import com.supinfo.supsms.service.ConversationService;
import com.supinfo.supsms.service.SMSService;

@WebServlet("/auth/newMessage")
public class NewMessageServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	/*	JMS*/
	@Resource(mappedName = "SupSMSConnection")
	private ConnectionFactory connectionFactory;
	@Resource(mappedName = "SupSMSDestination")
	private Queue queue;
	
	@EJB
	private ConversationService conversationService;
	
	@EJB
	private SMSService SMSService;
	
	@EJB
	private ContactService contactService;
       
    public NewMessageServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Long id = ((User)request.getSession().getAttribute("user")).getId();
		List<Contact> contactList = contactService.getAllFromUser(id);
		
		request.setAttribute("pageTitle", "Send a message");
		request.setAttribute("listContact", contactList);
		
		request.getRequestDispatcher("/auth/newMessage.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String contact = request.getParameter("contact");
		String message = request.getParameter("message");

		HttpSession session = request.getSession();
		if (contact != null && message != null) 
		{			
			JSONObject data = new JSONObject();
			try
			{
				data.put("contact", ((User)session.getAttribute("user")).getPhone());
				data.put("user", contact);
				
				Conversation conversation = conversationService.addConversationIfNotExists(data);
				
				if(conversation == null)
				{
					session.setAttribute(Alert.PRMTR_ERROR, "This user doesn't exist.");
					request.setAttribute("pageTitle", "Send a Message");
					request.getRequestDispatcher("/auth/newMessage.jsp").forward(request, response);
					return;
				}
				
				SMS sms = new SMS();
				sms.setText(message);
				sms.setConversation(conversation);
				sms.setDate(Calendar.getInstance().getTime());
				sms.setSender((User)session.getAttribute("user"));
				
				SMSService.addSMS(sms.toJson());
				
				data.put("contact", contact);
				data.put("user", ((User)session.getAttribute("user")).getPhone());
				
				conversation = conversationService.addConversationIfNotExists(data);
				
				sms = new SMS();
				sms.setConversation(conversation);
				sms.setDate(Calendar.getInstance().getTime());
				sms.setSender((User)session.getAttribute("user"));
				sms.setText(message);
				
				SMSService.addSMS(sms.toJson());
				
				
				/*	JMS	*/
				Connection cnx = connectionFactory.createConnection();
				Session jmsSession = cnx.createSession(false, Session.AUTO_ACKNOWLEDGE);

				MessageProducer producer = jmsSession.createProducer(queue);

				TextMessage jmsMessage = jmsSession.createTextMessage();
				jmsMessage.setText(((User)session.getAttribute("user")).getPhone() + "\n\r" + message + "\n\r" + contact);
				producer.send(jmsMessage);			    
				cnx.close();
				
				response.sendRedirect(request.getContextPath() + "/auth/conversation?id=" + conversation.getId());
			}
			catch (JSONException e) 
			{
				e.printStackTrace();
			}
			catch (JMSException e)
			{
				e.printStackTrace();
			}
		}
		else 
		{
			session.setAttribute(Alert.PRMTR_ERROR, "Vous n'avez pas renseigné tous les champs.");
			request.setAttribute("pageTitle", "Login");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}	
	}
}
