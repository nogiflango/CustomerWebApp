package com.raj.jdbc;

import java.util.ArrayList;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.raj.customer.Customer;

public class Test {
	
	public static void main(String args[]) {
		ApplicationContext context = new  ClassPathXmlApplicationContext(new String[] {"SpringBean.xml"});
		DBProp db1 = (DBProp) context.getBean("DB1");
		System.out.println("USername =  "+db1.getUsername());
		System.out.println("Password =  "+db1.getPassword());
		System.out.println("DBUrl =  "+db1.getDburl());
		
		
		DBUtil dbutil1 = (DBUtil) context.getBean("DBUtil1");
		String username = dbutil1.getDbprop().getUsername();
		System.out.println(username);
		
		
		dbutil1.connectToDB();
		//dbutil1.deleteByName("Prajwal", "Goudar");
		//dbutil1.func("2");
		
		//dbutil1.createCustomer("2", "Shashank", "Gouru", "male", "January 3 2008", "2061 Waycross Rd", "Fremont", "CA", "94586");
		//dbutil1.deleteByName("Shahank", "Gouru");
		
		ArrayList<Customer> customers = dbutil1.queryByLastname("Gouru");
		System.out.println("Gouru live on "+customers.get(0).getStreet());
		dbutil1.closeDBConnection();
		//System.out.println("Are we connected to DB? "+dbutil1.isDBConnected());
	}

}
