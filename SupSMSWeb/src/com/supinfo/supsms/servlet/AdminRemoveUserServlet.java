package com.supinfo.supsms.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.supinfo.supsms.entities.User;
import com.supinfo.supsms.globals.Alert;
import com.supinfo.supsms.service.UserService;

@WebServlet("/auth/admin/removeUser")
public class AdminRemoveUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private UserService userService;
       
    public AdminRemoveUserServlet()
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
		User user = userService.getById(id);
		
		HttpSession session = request.getSession();
		
		if(user != null)
		{
			userService.remove(id);
			response.sendRedirect(request.getContextPath()+"/auth/admin/user");
		}	
		else
		{
			session.setAttribute(Alert.PRMTR_ERROR, "The user doesn't exist !");
			request.setAttribute("pageTitle", "Users");
			request.getRequestDispatcher("/auth/admin/user.jsp").forward(request, response);
		}
	}

}
