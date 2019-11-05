package com.csit314travelx;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class BookingDbUtil {

	private DataSource dataSource;
	
	public BookingDbUtil(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
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


	public void addBooking(int userid, int tourId,String status) throws Exception{
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			// get db connection
			myConn = dataSource.getConnection();
			
			// create sql for insert
			String sql = "insert into booking "
					   + "(userid, tourid, status) "
					   + "values (?, ?, ?)";
			
			myStmt = myConn.prepareStatement(sql);
			
			// set the param values for the booking
			myStmt.setInt(1, userid);
			myStmt.setInt(2, tourId);
			myStmt.setString(3, status);
			
			
			// execute sql insert
			myStmt.execute();
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, null);
		}
		
	}


	public List<Booking> getBookingList(int userid) throws Exception {
		
		List<Booking> bookList = new ArrayList<>();
		Booking theBooking = null;
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs =null;
		
		try
		{
			myConn = dataSource.getConnection();
			
			String sql = "select * from booking where userid=?";
			
			myStmt = myConn.prepareStatement(sql);
			myStmt.setInt(1, userid);
			
			myRs = myStmt.executeQuery();
			
			while(myRs.next())
			{
				int id = myRs.getInt("id");
				int userID = myRs.getInt("userid");
				int tourid = myRs.getInt("tourid");
				String status = myRs.getString("status");
				
				theBooking = new Booking (id,userID,tourid,status);
				bookList.add(theBooking);
			}
			return bookList;
			
		}
		finally
		{
			close(myConn,myStmt,myRs);
		}
		
		
	}

	
}
