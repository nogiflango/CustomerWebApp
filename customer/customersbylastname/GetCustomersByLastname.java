package com.raj.customer.customersbylastname;

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


@WebServlet(urlPatterns = "/crm/customer/getcustomersbylastname")
public class GetCustomersByLastname extends HttpServlet{
	private DBUtil dbutil1 = null;
	
	
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
		if(dbutil1 == null) {
			setdbutil1();
		}
		
		String lastname =  req.getParameter("lastname");
		dbutil1.connectToDB();
		ArrayList<Customer> customersByLastname = dbutil1.queryByLastname(lastname);
		dbutil1.closeDBConnection();
		
		try {
			PrintWriter pw = resp.getWriter();
			//pw.println("<html>Hello from GetCustomersByLastname</html>");
			String[] dbColumns = dbutil1.allColumns();
			//writeTableToClient(pw, customersByLastname, dbColumns);
			String table = writeTableToClient2(customersByLastname, dbColumns);
			pw.println(table);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/*
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
		if(dbutil1 == null) {
			setdbutil1();
		}
		
		String lastname =  req.getParameter("lastname");
		dbutil1.connectToDB();
		ArrayList<Customer> customersByLastname = dbutil1.queryByLastname(lastname);
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
	
	*/
	
	private void writeTableToClient(PrintWriter pw, ArrayList<Customer> allCustomers, String[] dbColumns) {
		
		String tableStaticContents[] = tableStaticContents(dbColumns);
		pw.println(tableStaticContents[0]);
		System.out.println(tableStaticContents[0]);
		for(Customer c: allCustomers) {
			pw.println("<tr>\n");
			for(String content: getCustomerContents(c)) {
				pw.println("<td>"+content+"</td>\n");
				System.out.println("<td>"+content+"</td>\n");

			}
			
			pw.println("</tr>\n");
			System.out.println("</tr>\n");

		}
		pw.println(tableStaticContents[1]);
		System.out.println(tableStaticContents[1]);

	}
	
	private String writeTableToClient2(ArrayList<Customer> allCustomers, String[] dbColumns) {
		
		String table = "";
		
		String tableStaticContents[] = tableStaticContents(dbColumns);
		table+=tableStaticContents[0];
		for(Customer c: allCustomers) {
			table+="<tr>\n";
			for(String content: getCustomerContents(c)) {
				table+="<td>"+content+"</td>\n";
			}
			table+="</tr>\n";
		}
		
		table+=tableStaticContents[1];
		return table;
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
	
	private void setdbutil1() {
		ApplicationContext ac = new ClassPathXmlApplicationContext(new String[] {"SpringBean.xml"});
		dbutil1 = (DBUtil) ac.getBean("DBUtil1");
		
	}
	

}
