package com.supinfo.supsms.servlet;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.supinfo.supsms.entities.User;
import com.supinfo.supsms.service.UserService;

@WebServlet("/auth/admin/user")
public class AdminUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private UserService userService;
       
    public AdminUserServlet()
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		List<User> userList = userService.getAll();
		
		request.setAttribute("pageTitle", "Manage the users");
		request.setAttribute("listUser", userList);
		request.getRequestDispatcher("/auth/admin/user.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}

}
