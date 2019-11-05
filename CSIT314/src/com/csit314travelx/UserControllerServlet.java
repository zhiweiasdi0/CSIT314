package com.csit314travelx;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;





@WebServlet("/UserControllerServlet")
public class UserControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	@Resource(name="jdbc/csci314")
	private DataSource dataSource;
	private UserDbUtil userDbUtil;
	
	
	
	@Override
	public void init() throws ServletException {
		
		super.init();
		
		try {
			userDbUtil = new UserDbUtil(dataSource);
		} catch (Exception exe) {
			throw new ServletException(exe);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String command = request.getParameter("command");
		
		if(command == null)
		{
			command = "LIST";
		}
		
		try 
		{
			switch(command)
			{
				case "LIST":
				listUsers(request,response);
				break;
					
				case "LOAD":
				loadUser(request,response);
				break;
				
				case "LOADUsername":
				loadUserName(request,response);
				break;
				
				case "LOAD2UPDATE":
				loadUserUpdate(request,response);
				break;
				
				case "SEARCH":
				searchUser(request,response);
				break;
					
				case "UPDATE":
				updateUser(request,response);
				break;
				
				case "DELETE":
				deleteUser(request,response);
				break;
					
				default:
				listUsers(request,response);
				
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}


	private void loadUserUpdate(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String userId = request.getParameter("userId");
		
		User theUser = userDbUtil.getUserById(userId);
		
		request.setAttribute("UPDATE_USER", theUser);
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/update-user.jsp");
		dispatcher.forward(request,response);
		
	}

	private void loadUserName(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		String userName = (String) session.getAttribute("username");
		
		int userid = userDbUtil.getIdWithUsername(userName);
		
		System.out.println(userid);
		String userId = Integer.toString(userid);
		
		
		User theUser = userDbUtil.getUserById(userId);
		
		request.setAttribute("THE_USER", theUser);
		
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/user-view.jsp");
		dispatcher.forward(request,response);
		
	}

	private void searchUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String searchCriteria = request.getParameter("SearchText");
		if(!searchCriteria.equals(""))
		{
			List<User> searchUsers = userDbUtil.searchUsers(searchCriteria);
			
			request.setAttribute("USER_LIST", searchUsers);

			// sent to JSP page(view)
			RequestDispatcher dispatcher = request.getRequestDispatcher("/user-list.jsp");
			dispatcher.forward(request, response);
			
			
		}
		else
		{
			listUsers(request, response);
		}
	
		
	}

	private void updateUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int id = Integer.parseInt(request.getParameter("userId"));
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String contactNumber = request.getParameter("contactNumber");

		// create a new user object
		User theUser = new User(id,username,password,firstName, lastName, email,contactNumber);

		// perform update on database
		userDbUtil.updateUser(theUser);

		// send them back to the "list users" page
		listUsers(request, response);
		
	}

	private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// read user id from form data
		String theUserId = request.getParameter("userId");
		
		System.out.println("IN SERVERT " + theUserId);
		// delete user from database
		userDbUtil.deleteUser(theUserId);
		// send them back to "list users" page
		
		listUsers(request, response);
		
	}

	private void loadUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		String userid = request.getParameter("userId");
		
		User theUser = userDbUtil.getUserById(userid);
		
		request.setAttribute("THE_USER", theUser);
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/user-view.jsp");
		dispatcher.forward(request,response);
	}

	private void AddUser(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	private void listUsers(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		List<User> userList = userDbUtil.getUserList();
		
		request.setAttribute("USER_LIST", userList);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/user-list.jsp");
		dispatcher.forward(request,response);
		
	}

}
