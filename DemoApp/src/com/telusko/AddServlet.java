package com.telusko;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddServlet extends HttpServlet
{
	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException
	{
		
		System.out.println("Add Servlet Starts");

		String name = req.getParameter("num1");
		String name2 =req.getParameter("num2");
		
		PrintWriter out = res.getWriter();
		out.println("name is" + name);
		out.println("name is" + name2);
		
		
		
	}

}
