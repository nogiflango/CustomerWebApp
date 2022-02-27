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


@WebServlet(urlPatterns = "/crm/customer/getcustomerjson")
public class GetCustomerJSONServlet extends HttpServlet{
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
		
		try {
			PrintWriter pw = resp.getWriter();
			
			String customerJsonStr="{"
					+ "  \"ID\": \"1\","
					+ "  \"firstname\": \"Rajendra\","
					+ "  \"lastname\": \"Goudar\","
					+ "  \"gender\": \"Male\","
					+ "  \"dob\": \"Dec 31 2001\",\n"
					+ "  \"street\": \"4223 SolarCir\","
					+ "  \"city\": \"Union City\","
					+ "  \"state\": \"CA\","
					+ "  \"zip\": \"94587\""
					+ "}";
		
	
			//resp.setContentType("application/json");
            Customer customer1 = new Customer("100","Prajwal1","Goudar","male", "May 29 2008", "4223 Solar Cir", "union city", "ca", "94587");
            Customer customer2 = new Customer("100","Prajwal2","Goudar","male", "May 29 2008", "4223 Solar Cir", "union city", "ca", "94587");
            ArrayList<Customer> listofCustomers = new ArrayList<Customer>();
            listofCustomers.add(customer1);
            listofCustomers.add(customer2);
            
            Gson gson = new Gson();
            String customerJSONStr2 = gson.toJson(listofCustomers);
            
            
			pw.write(customerJSONStr2);				
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
}
