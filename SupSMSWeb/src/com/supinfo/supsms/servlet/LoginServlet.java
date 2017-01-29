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

import com.supinfo.supsms.entities.User;
import com.supinfo.supsms.globals.Alert;
import com.supinfo.supsms.service.LoginService;
import com.supinfo.supsms.utils.EncryptionServices;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private LoginService loginService;

	public LoginServlet() 
	{
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// User is already logged in, redirect to home page
		if(request.getSession().getAttribute("user") != null) 
		{
			response.sendRedirect(request.getContextPath());
			return;
		}
		request.setAttribute("pageTitle", "Login");
		request.getRequestDispatcher("/login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		HttpSession session = request.getSession();
		if (username != null && password != null) 
		{			
			JSONObject connectionData = new JSONObject();
			try 
			{
				connectionData.put("username", username);
				connectionData.put("password", EncryptionServices.encryptSHA1(password));
			} catch (JSONException e) 
			{
				e.printStackTrace();
			}
			User user = loginService.connect(connectionData);
			if (user != null)
			{
				session.setAttribute("user", user);
				response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/home"));
			}
			else //user is null
			{
				session.setAttribute(Alert.PRMTR_ERROR, "Authentication failed. Wrong password or unknown account.");
				request.setAttribute("pageTitle", "Login");
				request.getRequestDispatcher("/login.jsp").forward(request, response);
			}

		}
		else 
		{
			session.setAttribute(Alert.PRMTR_ERROR, "You didn't fill all the fields.");
			request.setAttribute("pageTitle", "Login");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}	
	}

}
