package com.csit314travelx;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class TourDbUtil {

	private DataSource dataSource;
	
	public TourDbUtil(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}

	public List<Tour> getTourList() throws Exception{
		
		List<Tour> tourList = new ArrayList<>();
		Tour theTour = null;
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs =null;
		
		try
		{
			myConn = dataSource.getConnection();
			
			myStmt = myConn.createStatement();
			
			String sql = "select * from tour";
			myRs = myStmt.executeQuery(sql);
			
			while(myRs.next())
			{
				int id = myRs.getInt("id");
				String title = myRs.getString("title");
				String description = myRs.getString("description");
				String nationality = myRs.getString("nationality");
				int tourguide_id = myRs.getInt("tourguide_id");
				String route_start = myRs.getString("route_start");
				String route_end = myRs.getString("route_end");
				String date = myRs.getString("date");
				String capacity = myRs.getString("capacity");
				String max_capacity = myRs.getString("max_capacity");
				String rating = myRs.getString("rating");
				String delete_ind = myRs.getString("delete_ind");
				
				theTour = new Tour
						(id,title,description,nationality,tourguide_id,
					     route_start,route_end,date,capacity,max_capacity,rating,delete_ind);
				tourList.add(theTour);
			}
			return tourList;
			
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

	public Tour getTourById(String tourId) throws Exception {
		
		Tour theTour = null;
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int tourid;
		
		try {
			// convert tour id to int
			tourid = Integer.parseInt(tourId);
			
			// get connection to database
			myConn = dataSource.getConnection();
			
			// create sql to get selected tour
			String sql = "select * from tour where id=?";
			
			// create prepared statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setInt(1, tourid);
			
			// execute statement
			myRs = myStmt.executeQuery();
			
			// retrieve data from result set row
			if (myRs.next()) {
				int id = myRs.getInt("id");
				String title = myRs.getString("title");
				String description = myRs.getString("description");
				String nationality = myRs.getString("nationality");
				int tourguide_id = myRs.getInt("tourguide_id");
				String route_start = myRs.getString("route_start");
				String route_end = myRs.getString("route_end");
				String date = myRs.getString("date");
				String capacity = myRs.getString("capacity");
				String max_capacity = myRs.getString("max_capacity");
				String rating = myRs.getString("rating");
				String delete_ind = myRs.getString("delete_ind");
				
				// use the TourId during construction
				theTour = new Tour
						(id,title,description,nationality,tourguide_id,
					     route_start,route_end,date,capacity,max_capacity,rating,delete_ind);
			}
			else {
				throw new Exception("Could not find tour id: " + tourid);
			}				
			
			return theTour;
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, myRs);
		}
	}

	public void addTour(Tour theTour) throws Exception {
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			// get db connection
			myConn = dataSource.getConnection();
			
			// create sql for insert
			String sql = "insert into tour "
					   + "(title, description, nationality, "
					   + "tourguide_id, route_start, route_end, "
					   + "date, capacity, max_capacity, "
					   + "rating, delete_ind) "
					   + "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			myStmt = myConn.prepareStatement(sql);
			
			// set the param values for the tour
			myStmt.setString(1, theTour.getTitle());
			myStmt.setString(2, theTour.getDescription());
			myStmt.setString(3, theTour.getNationality());
			myStmt.setInt(4, theTour.getTourguide_id());
			myStmt.setString(5, theTour.getRoute_start());
			myStmt.setString(6, theTour.getRoute_end());
			myStmt.setString(7, theTour.getDate());
			myStmt.setString(8, theTour.getCapacity());
			myStmt.setString(9, theTour.getMax_capacity());
			myStmt.setString(10, theTour.getRating());
			myStmt.setString(11, theTour.getDelete_ind());
			
			// execute sql insert
			myStmt.execute();
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, null);
		}
		
	}

	public List<Tour> searchTours(String searchCriteria) throws Exception {
		
		List<Tour> tours = new ArrayList<>();
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		try
		{
			
			//get a connection 
			myConn = dataSource.getConnection();
			//create sql statements
			String sql = "select * from tour where nationality LIKE '%" + searchCriteria + "%'";
			
			myStmt = myConn.prepareStatement(sql);
			//myStmt.setString(1, searchCriteria);
			myRs = myStmt.executeQuery();
			
			//process result set
			while(myRs.next())
			{
				int id = myRs.getInt("id");
				String title = myRs.getString("title");
				String description = myRs.getString("description");
				String nationality = myRs.getString("nationality");
				int tourguide_id = myRs.getInt("tourguide_id");
				String route_start = myRs.getString("route_start");
				String route_end = myRs.getString("route_end");
				String date = myRs.getString("date");
				String capacity = myRs.getString("capacity");
				String max_capacity = myRs.getString("max_capacity");
				String rating = myRs.getString("rating");
				String delete_ind = myRs.getString("delete_ind");
				
				//create new tour objects
				Tour tempTour = new Tour
								(id,title,description,nationality,tourguide_id,
							     route_start,route_end,date,capacity,max_capacity,rating,delete_ind);
				//add it to the list of tours.
				tours.add(tempTour);
			}
			//close JDBC objects
			
			return tours;
		}
		finally
		{
			close(myConn,myStmt,myRs);
		}
	}

	public void updateTour(Tour theTour) throws Exception {
		
		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			// get db connection
			myConn = dataSource.getConnection();
			
			// create SQL update statement
			String sql = "update tour "
						+ "set title=?,description=?,nationality=?, tourguide_id=?, route_start=?,route_end=?"
						+ ",date=?,capacity=?, max_capacity=?, rating=?,delete_ind=? "
						+ "where id=?";
			
			// prepare statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setString(1, theTour.getTitle());
			myStmt.setString(2, theTour.getDescription());
			myStmt.setString(3, theTour.getNationality());
			myStmt.setInt(4, theTour.getTourguide_id());
			myStmt.setString(5, theTour.getRoute_start());
			myStmt.setString(6, theTour.getRoute_end());
			myStmt.setString(7, theTour.getDate());
			myStmt.setString(8, theTour.getCapacity());
			myStmt.setString(9, theTour.getMax_capacity());
			myStmt.setString(10, theTour.getRating());
			myStmt.setString(11, theTour.getDelete_ind());
			myStmt.setInt(12, theTour.getId());
			
			// execute SQL statement
			myStmt.execute();
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, null);
		}
		
	}
}
