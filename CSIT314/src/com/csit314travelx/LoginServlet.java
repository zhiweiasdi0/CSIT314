package com.csit314travelx;

import java.io.IOException;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;





@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserDbUtil userDbutil;
	@Resource(name = "jdbc/csci314")
	private DataSource dataSource;
	
	
	@Override
	public void init() throws ServletException {

		super.init();

		// create our login db util .. pass in the conn pool/ datasource

		try {
			userDbutil = new UserDbUtil(dataSource);
		} catch (Exception exe) {
			throw new ServletException(exe);
		}
	
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		String command = request.getParameter("command");
		
		try 
		{
			switch(command)
			{
				case "LOGIN":
				LoginUsers(request,response);
				break;
					
				case "LOGOUT":
				LogoutUsers(request,response);
				break;
				
	
				default:

				
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	

		private void LogoutUsers(HttpServletRequest request, HttpServletResponse response) throws Exception
		{
			HttpSession session = request.getSession();
			session.removeAttribute("username");
			session.invalidate();
			
			response.sendRedirect("login.jsp");
		
		}
		private void LoginUsers(HttpServletRequest request, HttpServletResponse response) throws Exception 
		{
			
			String un = request.getParameter("username");
			String pwd = request.getParameter("password");
			
			if(un.equals("useradmin") && pwd.equals("password6"))
			{
				RequestDispatcher dispatcher = request.getRequestDispatcher("/welcome-useradmin.jsp");
				dispatcher.forward(request, response);
				
			}
			else
			{
				try {
					if(checkValid(un,pwd))
					{
						
						RequestDispatcher dispatcher = request.getRequestDispatcher("welcome.jsp");
						HttpSession session = request.getSession();
						session.setAttribute("username", un);
			
						dispatcher.forward(request, response);
					}
					else
					{
						response.sendRedirect("login.jsp");
					}
					
				} catch (SQLException e) {			
					e.printStackTrace();
				}
			}
	}


		

	
	private boolean checkValid(String username,String password) throws SQLException {
		

		boolean login = userDbutil.check(username, password);
		
		if(login)
		{
			
			return true;
		}
		else
		{
			return false;
		}
	}
}
