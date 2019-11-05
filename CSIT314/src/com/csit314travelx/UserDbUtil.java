package com.csit314travelx;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;



public class UserDbUtil {

	private DataSource dataSource;
	
	
	public UserDbUtil(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}


	public List<User> getUserList() throws SQLException {
		
		List<User> userList = new ArrayList<>();
		User theUser = null;
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs =null;
		
		try
		{
			myConn = dataSource.getConnection();
			
			myStmt = myConn.createStatement();
			
			String sql = "select * from user";
			myRs = myStmt.executeQuery(sql);
			
			while(myRs.next())
			{
				int id = myRs.getInt("id");
				String username = myRs.getString("username");
				String password = myRs.getString("password");
				String firstName = myRs.getString("first_name");
				String lastName = myRs.getString("last_name");
				String email = myRs.getString("email");
				String contactnumber = myRs.getString("contact_number");
				
				theUser = new User(id,username,password,firstName,lastName,email,contactnumber);
				userList.add(theUser);
			}
			return userList;
			
		}
		finally
		{
			close(myConn,myStmt,myRs);
		}
	}


	private void close(Connection myConn, Statement myStmt, ResultSet myRs) {
		
		try
		{
			if(myRs != null)
			{
				myRs.close();
			}
			
			if(myStmt !=null)
			{
				myStmt.close();
			}
			if(myConn !=null)
			{
				myConn.close(); // doesn't really close it.. just puts back in connection pool 
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}


	public User getUserById(String userId) throws Exception {
		User theUser = null;
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int userid;
		
		
		
		try {
			// convert user id to int
			userid = Integer.parseInt(userId);
			
			// get connection to database
			myConn = dataSource.getConnection();
			
			// create sql to get selected user
			String sql = "select * from user where id=?";
			
			// create prepared statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setInt(1, userid);
			
			// execute statement
			myRs = myStmt.executeQuery();
			
			// retrieve data from result set row
			if (myRs.next()) {
				String userName = myRs.getString("username");
				String password = myRs.getString("password");
				String firstName = myRs.getString("first_name");
				String lastName = myRs.getString("last_name");
				String email = myRs.getString("email");
				String contactNumber = myRs.getString("contact_number");
				
				// use the userId during construction
				theUser = new User(userid,userName,password, firstName, lastName, email,contactNumber);
			}
			else {
				throw new Exception("Could not find user id: " + userid);
			}				
			
			return theUser;
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, myRs);
		}
	}


	public void deleteUser(String theUserId) throws Exception {
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		int userid;
		
		try {
			
			System.out.println(theUserId);
			// convert user id to int
			userid = Integer.parseInt(theUserId);
			
			// get connection to database
			myConn = dataSource.getConnection();
			
			// create sql to delete user
			String sql = "delete from user where id=?";
			
			// prepare statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setInt(1, userid);
			
			// execute sql statement
			myStmt.execute();
		}
		finally {
			// clean up JDBC code
			close(myConn, myStmt, null);
		}	
		
	}



	public void updateUser(User theUser) throws Exception{
	
		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			// get db connection
			myConn = dataSource.getConnection();
			
			// create SQL update statement
			String sql = "update user "
						+ "set username=?,password=?,first_name=?, last_name=?, email=?,contact_number=? "
						+ "where id=?";
			
			// prepare statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setString(1, theUser.getUsername());
			myStmt.setString(2, theUser.getPassword());
			myStmt.setString(3, theUser.getFirstName());
			myStmt.setString(4, theUser.getLastName());
			myStmt.setString(5, theUser.getEmail());
			myStmt.setString(6, theUser.getContactNumber());
			myStmt.setInt(7, theUser.getId());
			
			// execute SQL statement
			myStmt.execute();
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, null);
		}
		
	}


	public List<User> searchUsers(String searchCriteria) throws Exception {
		
		List<User> users = new ArrayList<>();
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		try
		{
			
			//get a connection 
			myConn = dataSource.getConnection();
			//create sql statements
			String sql = "select * from user where first_name LIKE '%" + searchCriteria + "%'";
			
			myStmt = myConn.prepareStatement(sql);
			//myStmt.setString(1, searchCriteria);
			myRs = myStmt.executeQuery();
			
			//process result set
			while(myRs.next())
			{
				int id = myRs.getInt("id");
				String username = myRs.getString("username");
				String password = myRs.getString("password");
				String firstName = myRs.getString("first_name");
				String lastName = myRs.getString("last_name");
				String email = myRs.getString("email");
				String contactNumber = myRs.getString("contact_number");
				
				//create new user objects
				User tempUser = new User(id,username,password,firstName,lastName,email,contactNumber);
				//add it to the list of users.
				users.add(tempUser);
			}
			//close JDBC objects
			
			return users;
		}
		finally
		{
			close(myConn,myStmt,myRs);
		}
	}


	public boolean check(String username, String password) throws SQLException 
	{
	
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		try
		{
			
			
			myConn = dataSource.getConnection();
			String sql = "select * from user where username=? and password =?";
			myStmt = myConn.prepareStatement(sql);
			myStmt.setString(1,username);
			myStmt.setString(2,password);
			myRs = myStmt.executeQuery();
			if(myRs.next())
			{
				
				return true;
			}
			else
			{
				return false;
			}
			
		}
		finally {
			// clean up JDBC code
			close(myConn, myStmt, null);
		}	


	}


	public User getUserByName(String userName) throws Exception {
	
	
		User theUser = null;
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int userid = 0;
		
		try {
			
			// get connection to database
			myConn = dataSource.getConnection();
			
			// create sql to get selected user
			String sql = "select * from user where username=?";
			
			// create prepared statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setString(1, userName);
			
			// execute statement
			myRs = myStmt.executeQuery();
			
			// retrieve data from result set row
			if (myRs.next()) {
				userid = myRs.getInt("id");
				String password = myRs.getString("password");
				String firstName = myRs.getString("first_name");
				String lastName = myRs.getString("last_name");
				String email = myRs.getString("email");
				String contactNumber = myRs.getString("contact_number");
				
				// use the userId during construction
				theUser = new User(userid,userName,password, firstName, lastName, email,contactNumber);
			}
			else {
				throw new Exception("Could not find user id: " + userid);
			}				
			
			return theUser;
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, myRs);
		}
		
	}


	public int getIdWithUsername(String userName) throws Exception{
		
		System.out.println("IN UserDbUtil" + userName);
		User theUser = null;
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int userid = 0;
		
		try {
			
			// get connection to database
			myConn = dataSource.getConnection();
			
			// create sql to get selected user
			String sql = "select id from user where username=?";
			
			// create prepared statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setString(1, userName);
			
			// execute statement
			myRs = myStmt.executeQuery();
			
			// retrieve data from result set row
			if (myRs.next()) {
				userid = myRs.getInt("id");
				
			
			}
			else {
				throw new Exception("Could not find user id: " + userid);
			}				
			
			return userid;
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, myRs);
		}
	}
	



}
