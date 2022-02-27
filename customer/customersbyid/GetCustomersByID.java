package com.raj.customer.customersbyid;

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


@WebServlet(urlPatterns = "/crm/customer/getcustomersbyid")
public class GetCustomersByID extends HttpServlet{
	private DBUtil dbutil1 = null;

	
	private void setdbutil1() {
		ApplicationContext ac = new ClassPathXmlApplicationContext(new String[] {"SpringBean.xml"});
		dbutil1 = (DBUtil) ac.getBean("DBUtil1");
		
	}
	
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
		if(dbutil1 == null) {
			setdbutil1();
		}
		
		String custid = req.getParameter("custid");

		dbutil1.connectToDB();
		ArrayList<Customer> customersByID = dbutil1.queryByID(custid);
		dbutil1.closeDBConnection();
	
        Gson gson = new Gson();

		try {
			PrintWriter pw = resp.getWriter();
			String table = gson.toJson(customersByID);
			pw.println(table);
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
	

}
