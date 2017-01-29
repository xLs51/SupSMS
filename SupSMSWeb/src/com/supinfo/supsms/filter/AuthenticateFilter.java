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

@WebFilter(urlPatterns="/auth/*")
public class AuthenticateFilter implements Filter
{
	@Override
	public void destroy() 
	{

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain) throws IOException, ServletException 
	{
		// If user is logged
		if(((HttpServletRequest)request).getSession().getAttribute("user") != null) 
			chain.doFilter(request, response);
		else
			((HttpServletResponse)response).sendRedirect(((HttpServletRequest)request).getContextPath()+"/home");
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException 
	{

	}
}