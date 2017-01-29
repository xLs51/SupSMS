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

@WebServlet("/auth/profile")
public class ProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB
	private UserService userService;
	
    public ProfileServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User actualUser = (User) session.getAttribute("user");
		
		String idString = request.getParameter("id");		
		if(idString != null)
		{
			int userId = Integer.parseInt(idString);
			 
			if(actualUser.getId() == userId || actualUser.getIsAdmin()) //we need to be a regular user wanting to edit our own profile or an Admin
			{
				User user = null;
				if (actualUser.getIsAdmin())
				{
					user = userService.getById((long)userId);
					if (user == null)
					{
						session.setAttribute(Alert.PRMTR_WARNING, "No user with this id is currently existing.");
						response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/auth/profile"));
						return;
					}
				}
				
				request.setAttribute("pageTitle", "Profile");
				user = userService.getById((long)userId);
				request.setAttribute("user", user);
				request.getRequestDispatcher("/auth/profile.jsp").forward(request, response);
				return;
			}			
		}
		else
		{
			request.setAttribute("pageTitle", "Profile");
			User user = userService.getById(actualUser.getId());
			request.setAttribute("user", user);
			request.getRequestDispatcher("/auth/profile.jsp").forward(request, response);
			return;
		}
	}

}