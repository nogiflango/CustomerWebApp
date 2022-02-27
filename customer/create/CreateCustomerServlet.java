package com.raj.customer.create;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.raj.customer.Customer;
import com.raj.jdbc.DBUtil;


@WebServlet(urlPatterns = "/crm/customer/create")
public class CreateCustomerServlet extends HttpServlet{
	
	private DBUtil dbutil1 = null;
	
	private void setdbutil1() {
		ApplicationContext ac = new ClassPathXmlApplicationContext(new String[] {"SpringBean.xml"});
		dbutil1 = (DBUtil) ac.getBean("DBUtil1");
	}
	
	
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


}
