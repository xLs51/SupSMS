package com.supinfo.supsms.servlet;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.supinfo.supsms.entities.User;
import com.supinfo.supsms.service.UserService;

@WebServlet("/auth/searchContact")
public class SearchContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private UserService userService;

    public SearchContactServlet()
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String searchByName = request.getParameter("searchByName");
		String searchById = request.getParameter("searchById");
		
		if(searchByName != null)
		{		
			try {
				List<User> listUser = userService.getUserLikeUsername(new JSONObject("{ search: "+ searchByName +" }"));
				
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				
				JSONArray jsonUser = new JSONArray();
				
				for (User users : listUser)
				{
					JSONObject json = new JSONObject();
					json.put("id", users.getId());
					json.put("username", users.getUsername());
					jsonUser.put(json);
				}
	
				response.getWriter().write(jsonUser.toString());
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		else if(searchById != null)
		{
			try {
				Long id = Long.valueOf(searchById);
				User user = userService.getById(id);
				
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				
				JSONObject json = new JSONObject();
				
				if(user != null)
				{			
					json.put("res", "1");
					json.put("firstname", user.getFirstName());
					json.put("lastname", user.getLastName());
					json.put("phone", user.getPhone());
					json.put("mail", user.getMail());
				}
				else
					json.put("res", "0");
				
				response.getWriter().write(json.toString());
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
	}

}
