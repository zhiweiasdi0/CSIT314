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




@WebServlet("/TourControllerServlet")
public class TourControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Resource(name="jdbc/csci314")
	private DataSource dataSource;
	private TourDbUtil tourDbUtil;
	private UserDbUtil userDbUtil;
	private BookingDbUtil bookingDbUtil;
	
	@Override
	public void init() throws ServletException {
		
		super.init();
		
		try {
			tourDbUtil = new TourDbUtil(dataSource);
			userDbUtil = new UserDbUtil(dataSource);
			bookingDbUtil = new BookingDbUtil(dataSource);
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
				listTours(request,response);
				break;
				
				case "ADD":
				addTour(request,response);
				break;
				
				case "BOOK":
				bookTour(request,response);
				break;
					
				case "LOAD":
				loadTour(request,response);
				break;
				
				case "LOADBOOKING":
				loadBooking(request,response);
				break;
				
				case "LOAD2UPDATE":
				loadTourUpdate(request,response);
				break;
				
				case "SEARCH":
				searchTour(request,response);
				break;
					
				case "UPDATE":
				updateTour(request,response);
				break;
				
				case "DELETE":
				deleteTour(request,response);
				break;
					
				default:
				listTours(request,response);
				
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}


	private void loadBooking(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		String userName = (String) session.getAttribute("username");
		

		int userid = userDbUtil.getIdWithUsername(userName);
		
		
		List<Booking> bookList = bookingDbUtil.getBookingList(userid);
		
		request.setAttribute("BOOK_LIST", bookList);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/book-list.jsp");
		dispatcher.forward(request,response);
		
		
	}


	private void bookTour(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		
		HttpSession session = request.getSession();
		String userName = (String) session.getAttribute("username");
		

		int userid = userDbUtil.getIdWithUsername(userName);
		
		int tourId = Integer.parseInt(request.getParameter("tourId"));
		
		String status = "NEW BOOKING";
		bookingDbUtil.addBooking(userid,tourId,status);
		
		
	}


	private void loadTourUpdate(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String tourId = request.getParameter("tourId");
		
		Tour theTour = tourDbUtil.getTourById(tourId);
		
		request.setAttribute("UPDATE_TOUR", theTour);
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/update-tour.jsp");
		dispatcher.forward(request,response);
		
	}


	private void addTour(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		String userName = (String) session.getAttribute("username");
		

		int tourguide_userid = userDbUtil.getIdWithUsername(userName);
		//System.out.println("IN ADD TOUR" + tourguide_userid);
		
		// read tour info from form data
		String title = request.getParameter("title");
		String description = request.getParameter("description");
		String nationality = request.getParameter("nationality");
//		int tourguide_id = Integer.parseInt(request.getParameter("tourguide_id"));
		String route_start = request.getParameter("route_start");
		String route_end = request.getParameter("route_end");
		String date = request.getParameter("date");
		String capacity = request.getParameter("capacity");
		String max_capacity = request.getParameter("max_capacity");
		String rating = request.getParameter("rating");
		String delete_ind = request.getParameter("delete_ind");
		
		// create a new tour object
		Tour theTour = new Tour
				(title, description, nationality,tourguide_userid,route_start,
			    route_end,date,capacity,max_capacity,rating,delete_ind);

		// add the tour to the database
		tourDbUtil.addTour(theTour);

		// send back to main page (the tour list)
		listTours(request, response);
		
	}


	private void deleteTour(HttpServletRequest request, HttpServletResponse response) throws Exception {
		

		
	}


	private void updateTour(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		String userName = (String) session.getAttribute("username");
		
		int tourguide_userid = userDbUtil.getIdWithUsername(userName);
		//System.out.println("IN ADD TOUR" + tourguide_userid);
		
		
		int id = Integer.parseInt(request.getParameter("tourId"));
		String title = request.getParameter("title");
		String description = request.getParameter("description");
		String nationality = request.getParameter("nationality");
		//int tourguide_id = Integer.parseInt(request.getParameter("userId"));
		String route_start = request.getParameter("route_start");
		String route_end = request.getParameter("route_end");
		String date = request.getParameter("date");
		String capacity = request.getParameter("capacity");
		String max_capacity = request.getParameter("max_capacity");
		String rating = request.getParameter("rating");
		String delete_ind = request.getParameter("delete_ind");

		Tour theTour = new Tour
				(id,title, description, nationality,tourguide_userid,route_start,
			    route_end,date,capacity,max_capacity,rating,delete_ind);

		// add the tour to the database
		tourDbUtil.updateTour(theTour);
		
		// send them back to the "list tours" page
		listTours(request, response);
		
	}


	private void searchTour(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String searchCriteria = request.getParameter("SearchText");
		if(!searchCriteria.equals(""))
		{
			List<Tour> searchTours = tourDbUtil.searchTours(searchCriteria);
			
			
			request.setAttribute("TOUR_LIST", searchTours);

			// sent to JSP page(view)
			RequestDispatcher dispatcher = request.getRequestDispatcher("/tour-list.jsp");
			dispatcher.forward(request, response);
			
			
		}
		else
		{
			listTours(request, response);
		}
		
	}


	private void loadTour(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String tourId = request.getParameter("tourId");
		
		Tour theTour = tourDbUtil.getTourById(tourId);
		
		request.setAttribute("THE_TOUR", theTour);
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/tour-view.jsp");
		dispatcher.forward(request,response);
		
	}


	private void listTours(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		List<Tour> tourList = tourDbUtil.getTourList();
		
		request.setAttribute("TOUR_LIST", tourList);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/tour-list.jsp");
		dispatcher.forward(request,response);
		
	}

}
