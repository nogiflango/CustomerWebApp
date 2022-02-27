package com.raj.customer.deletecustomerbyid;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.google.gson.Gson;
import com.raj.customer.Customer;
import com.raj.jdbc.DBUtil;


@WebServlet(urlPatterns = "/crm/customer/deletecustomerbyid")

public class DeleteCustomerByID extends HttpServlet{
	
	private DBUtil dbutil1 = null;

	
	private void setdbutil1() {
		ApplicationContext ac = new ClassPathXmlApplicationContext(new String[] {"SpringBean.xml"});
		dbutil1 = (DBUtil) ac.getBean("DBUtil1");
		
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
		if(dbutil1 == null) {
			setdbutil1();
		}
		
		dbutil1.connectToDB();
		
		String ID = req.getParameter("CUST_ID");
		dbutil1.deleteByID(ID);
		dbutil1.closeDBConnection();
	
		
		try {
			PrintWriter pw = resp.getWriter();
			pw.println("Deleted customer succesfully!");
		} catch (IOException e) {

			e.printStackTrace();
		}
		
	}
	

}
