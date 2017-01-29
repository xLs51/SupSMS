package com.supinfo.supsms.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.supinfo.supsms.entities.User;

@WebFilter(urlPatterns="/auth/admin/*")
public class AuthenticateAdminFilter implements Filter
{
	@Override
	public void destroy() 
	{

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain) throws IOException, ServletException 
	{
		// Get the mail of the user logged
		HttpSession session = ((HttpServletRequest)request).getSession();
		User user = (User) session.getAttribute("user");
		
		// If user is logged
		if(session.getAttribute("user") != null && user.getIsAdmin()) 
			chain.doFilter(request, response);
		else
			((HttpServletResponse)response).sendRedirect(((HttpServletRequest)request).getContextPath()+"/home");
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException 
	{

	}
}