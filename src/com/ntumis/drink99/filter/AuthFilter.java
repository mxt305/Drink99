package com.ntumis.drink99.filter;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.ntumis.drink99.dao.DBConnector;
import com.ntumis.drink99.dao.UserDAO;
import com.ntumis.drink99.entity.User;

/**
 * Servlet Filter implementation class AuthFilter
 */
@WebFilter(filterName = "AuthFilter")
public class AuthFilter implements Filter {
	protected Connection conn;
	
	public AuthFilter() {
		conn = DBConnector.createConnection();
	}
	
	@Override
	protected void finalize() throws Throwable {
		if(conn != null && !conn.isClosed()){
			conn.close();
		}
	}
	
	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession s = req.getSession();
		boolean isLogin = false;
		//String user = "";
		if (s != null) {
			/*Object username = s.getAttribute("user");
			if (username != null && !username.toString().equals("")) {
				isLogin = true;
				user = username.toString();
			}*/
			try {
				Object mUid = s.getAttribute("user");
				int uid = Integer.parseInt(mUid.toString());
				User u = getUserdata(uid);
				isLogin = true;
				req.setAttribute("user", u);
			} catch (Exception e) {
			}

		}
		//req.setAttribute("user", user);
		req.setAttribute("isLogin", isLogin);
		chain.doFilter(request, response);
	}

	private User getUserdata(int id) {
		UserDAO dUser = new UserDAO(conn);
		User u = dUser.queryById(id);
		return u;
	}

	public void init(FilterConfig fConfig) throws ServletException {

	}

}
