package com.raj.customer.getall;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.google.gson.Gson;
import com.raj.customer.Customer;
import com.raj.jdbc.DBUtil;


@WebServlet(urlPatterns = "/crm/customer/entries")
public class GetCustomerAllServlet extends HttpServlet{
	
	private DBUtil dbutil1 = null;
	
	private void setdbutil1() {
		ApplicationContext ac = new ClassPathXmlApplicationContext(new String[] {"SpringBean.xml"});
		dbutil1 = (DBUtil) ac.getBean("DBUtil1");
	}
	/*

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
		if(dbutil1 == null) {
			setdbutil1();
		}
		dbutil1.connectToDB();
		ArrayList<Customer> allCustomers = dbutil1.queryAll();
		String[] dbColumns = dbutil1.allColumns();
		dbutil1.closeDBConnection();
		
		try {
			PrintWriter pw = resp.getWriter();
			writeTableToClient(pw, allCustomers,dbColumns);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	*/
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
		if(dbutil1 == null) {
			setdbutil1();
		}
		
		dbutil1.connectToDB();
		ArrayList<Customer> customersByLastname = dbutil1.queryAll();
		dbutil1.closeDBConnection();
	
        Gson gson = new Gson();

		try {
			PrintWriter pw = resp.getWriter();
			String table = gson.toJson(customersByLastname);
			pw.println(table);
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
	
	private void writeTableToClient(PrintWriter pw, ArrayList<Customer> allCustomers, String[] dbColumns) {
	
		String tableStaticContents[] = tableStaticContents(dbColumns);
		pw.println(tableStaticContents[0]);
		
		for(Customer c: allCustomers) {
			pw.println("<tr>\n");
			for(String content: getCustomerContents(c)) {
				pw.println("<td>"+content+"</td>\n");
			}
			pw.println("</tr>\n");
		}
		pw.println(tableStaticContents[1]);
	}
	
	private String[] tableStaticContents(String[] dbColumns) {
		String tableStaticContents[] = new String[2];
		
		String beginning = "<html> \n<style>\n"
				+ "table, th, td {\nborder:1px solid black;\n"
				+ "}\n</style>\n<body>\n"
				+ "<h2>All Customers</h2>\n"
				+ "<table style=\"width:100%\">\n"
				+"<tr>\n";		
		for(String column: dbColumns) {
			beginning += "    <th>"+column+"</th>\n";
		}
		beginning += "  </tr>\n";
		
		String end = "</table>\n"
				+ "</body>\n"
				+ "</html>";
		tableStaticContents[0] = beginning;
		tableStaticContents[1] = end;
		return tableStaticContents;
	}
	
	private String[] getCustomerContents(Customer c){
		String[] contents = new String[9];
		contents[0] = c.getID();
		contents[1] = c.getFirstname();
		contents[2] = c.getLastname();
		contents[3] = c.getGender();
		contents[4] = c.getDob();
		contents[5] = c.getStreet();
		contents[6] = c.getCity();
		contents[7] = c.getState();
		contents[8] = c.getZip();
		return contents;
	}
	
	/*
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
		String id = req.getParameter("ID");
		String firstname = req.getParameter("firstname");
		String lastname = req.getParameter("lastname");
		String gender = req.getParameter("gender");
		String dob = req.getParameter("dob");
		String street = req.getParameter("street");
		String city = req.getParameter("city");
		String state = req.getParameter("state");
		String zip = req.getParameter("zip");

		if(dbutil1 == null) {
			setdbutil1();
		}
		dbutil1.connectToDB();
		String msg = dbutil1.createCustomer(id, firstname, lastname, gender, dob, street, city, state, zip);	
		try {
			PrintWriter pw = resp.getWriter();
			pw.println(msg);
			dbutil1.closeDBConnection();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	*/
	


}
