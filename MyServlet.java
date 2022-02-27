package com.raj;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/path1/hello/welcome")


public class MyServlet extends HttpServlet{
	String str_table="<!DOCTYPE html>\n"
			+ "<html>\n"
			+ "<style>\n"
			+ "table, th, td {\n"
			+ "  border:1px solid black;\n"
			+ "}\n"
			+ "</style>\n"
			+ "<body>\n"
			+ "\n"
			+ "<h2>A basic HTML table</h2>\n"
			+ "\n"
			+ "<table style=\"width:100%\">\n"
			+ "  <tr>\n"
			+ "    <th>Company</th>\n"
			+ "    <th>Contact</th>\n"
			+ "    <th>Country</th>\n"
			+ "  </tr>\n"
			+ "  <tr>\n"
			+ "    <td>Alfreds Futterkiste</td>\n"
			+ "    <td>Maria Anders</td>\n"
			+ "    <td>Germany</td>\n"
			+ "  </tr>\n"
			+ "  <tr>\n"
			+ "    <td>Centro comercial Moctezuma</td>\n"
			+ "    <td>Francisco Chang</td>\n"
			+ "    <td>Mexico</td>\n"
			+ "  </tr>\n"
			+ "</table>";
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
		
		try {
			String val_header = req.getHeader("Accept");
			String method = req.getMethod();
			PrintWriter pw = resp.getWriter();
			String firstname = req.getParameter("fname");
			String lastname = req.getParameter("lname");
		    pw.println("Hello "+firstname+" "+lastname+" I will create your info in DB");
			pw.println("THis is GET method= "+req.getMethod());
			//pw.println("hello from MyServlet ");
			//pw.println("Value of Header = "+val_header);
			 //pw.println(str_table);
			
			//pw.println("Method = "+method);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
		try {

		String firstname = req.getParameter("fname");
		String lastname = req.getParameter("lname");
		PrintWriter pw = resp.getWriter();
		pw.println("THis is POST method= "+req.getMethod());
	    pw.println("Hello "+firstname+" "+lastname+" I will create your info in DB");

		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) {
		try {

		String firstname = req.getParameter("fname");
		String lastname = req.getParameter("lname");
		PrintWriter pw = resp.getWriter();
		pw.println("THis is PUT method= "+req.getMethod());
	    pw.println("Hello "+firstname+" "+lastname+" I will modify your input in DB");

		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
		try {

		String firstname = req.getParameter("fname");
		String lastname = req.getParameter("lname");
		PrintWriter pw = resp.getWriter();
		pw.println("THis is DELETE method= "+req.getMethod());
	    pw.println("Hello "+firstname+" "+lastname+" I will delere your input in DB");

		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}


}
