package com.raj.customer.customersbylastname;

import java.util.ArrayList;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.raj.customer.Customer;
import com.raj.jdbc.DBUtil;

public class Test {

	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext(new String[] {"SpringBean.xml"});
		DBUtil dbutil1 = (DBUtil) ac.getBean("DBUtil1");
		dbutil1.connectToDB();
		ArrayList<Customer> customersByLastname = dbutil1.queryByLastname("Goudar");
		customersByLastname.get(0).print();
		
	}

}
