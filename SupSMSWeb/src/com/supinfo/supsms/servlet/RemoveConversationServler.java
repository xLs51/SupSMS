package com.supinfo.supsms.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.supinfo.supsms.service.ConversationService;

@WebServlet("/auth/removeConversation")
public class RemoveConversationServler extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private ConversationService conversationService;
	
    public RemoveConversationServler() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String conversationId = request.getParameter("conversation");
		
		try
		{
			conversationService.deleteConversation(new JSONObject().put("conversationId", Integer.parseInt(conversationId)));
			
			response.sendRedirect(request.getContextPath() + "/home");
		}
		catch (JSONException e)
		{
			e.printStackTrace();
		}
	}
}
