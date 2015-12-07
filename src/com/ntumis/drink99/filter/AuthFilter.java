package com.ntumis.drink99.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class AuthFilter
 */
@WebFilter(filterName="AuthFilter")
public class AuthFilter implements Filter {

    public AuthFilter() {
        
    }
    
	public void destroy() {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession s = req.getSession();
		boolean isLogin = false;
		String user = "";
		if(s != null){
			Object username = s.getAttribute("user");
			if(username != null && !username.toString().equals("")){
				isLogin = true;
				user = username.toString();
			}
		}
		req.setAttribute("user", user);
		req.setAttribute("isLogin", isLogin);
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {

	}

}
