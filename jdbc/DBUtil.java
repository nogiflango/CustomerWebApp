package com.raj.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;

import com.raj.customer.Customer;

public class DBUtil {
	
	
	private DBProp dbprop;
	private Connection conn;
	private Statement stmt;
	private ResultSet rs; 
	
	public DBProp getDbprop() {
		return dbprop;
	}
	
	public void setDbprop(DBProp dbprop) {
		this.dbprop = dbprop;
	}
	
	public void connectToDB() {
		try {			
			String dbURL = dbprop.getDburl();
			
			String username = dbprop.getUsername();
            String password = dbprop.getPassword();
			conn = DriverManager.getConnection(dbURL, username, password);
			if (conn != null) {
                System.out.println("Connected with the database!");
            }
					
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public void printAllCustomers() {
		String query = "select * from CUSTOMER";
	    try {
	    	stmt = conn.createStatement();
	        rs = stmt.executeQuery(query);
	        while (rs.next()) {
	          String custID = rs.getString("CUST_ID");
	          String firstname = rs.getString("FIRSTNAME");
	          String lastname = rs.getString("LASTNAME");
	          String gender = rs.getString("GENDER");
	          String dob = rs.getString("DOB");
	          String street = rs.getString("DOB");
	          String city = rs.getString("STREET");
	          String state = rs.getString("CITY");
	          String zip = rs.getString("ST");
	          System.out.println(custID + ", " + firstname + ", " + lastname + ", " + gender + ", " + dob 
	        		  + ", " + street + ", " + city + ", " + state + ", " + zip);
	          
	        }
	        stmt.close();
	        rs.close();
	        
	      } catch (SQLException e) {
	    	  e.printStackTrace();
	      }
	  
	}
	
	public ArrayList<Customer> queryAll() {
		 String query  = "select * from CUSTOMER";
		 try {
			 stmt = conn.createStatement();
			 rs = stmt.executeQuery(query);
			 ArrayList<Customer> customers = new ArrayList();
			 while(rs.next()) {
				  Customer c = new Customer();
				  c.setID(rs.getString("CUST_ID")); ;
				  c.setFirstname(rs.getString("FIRSTNAME")); 
				  c.setLastname(rs.getString("LASTNAME")); 
				  c.setGender(rs.getString("GENDER")); 
				  c.setDob(rs.getString("DOB"));
				  c.setStreet(rs.getString("STREET")); 
				  c.setCity(rs.getString("CITY")); 
				  c.setState(rs.getString("ST")); 
				  c.setZip(rs.getString("ZIP")); 
		          customers.add(c);
			 }
			 stmt.close();
	         rs.close();
	         
			 return customers;
			 
			 
		 } catch(SQLException e) {
			 e.printStackTrace();
		 }
		 return null;
		
	 }
	 
	 public ArrayList<Customer> queryByLastname(String lastname) {
		 String query  = "select * from CUSTOMER where LASTNAME='"+lastname+"'";
		 //System.out.println("Getting all info about  customers whose lastname is = "+lastname);
		 try {
			 stmt = conn.createStatement();
			 rs = stmt.executeQuery(query);
			 ArrayList<Customer> customers = new ArrayList();
			 while(rs.next()) {
				  Customer c = new Customer();
				  c.setID(rs.getString("CUST_ID")); ;
				  c.setFirstname(rs.getString("FIRSTNAME")); 
				  c.setLastname(rs.getString("LASTNAME")); 
				  c.setGender(rs.getString("GENDER")); 
				  c.setDob(rs.getString("DOB"));
				  c.setStreet(rs.getString("STREET")); 
				  c.setCity(rs.getString("CITY")); 
				  c.setState(rs.getString("ST")); 
				  c.setZip(rs.getString("ZIP")); 
		          customers.add(c);
			 }
			 stmt.close();
	         rs.close();

			 return customers;
			 
		 } catch(SQLException e) {
			 e.printStackTrace();
		 }
		 return null;
		
	 }
	 
	 public ArrayList<Customer> queryByID(String ID) {
		 String query  = "select * from CUSTOMER where CUST_ID='"+ID+"'";
		 //System.out.println("Getting all info about  customers whose lastname is = "+lastname);
		 try {
			 stmt = conn.createStatement();
			 rs = stmt.executeQuery(query);
			 ArrayList<Customer> customers = new ArrayList();
			 while(rs.next()) {
				  Customer c = new Customer();
				  c.setID(rs.getString("CUST_ID")); ;
				  c.setFirstname(rs.getString("FIRSTNAME")); 
				  c.setLastname(rs.getString("LASTNAME")); 
				  c.setGender(rs.getString("GENDER")); 
				  c.setDob(rs.getString("DOB"));
				  c.setStreet(rs.getString("STREET")); 
				  c.setCity(rs.getString("CITY")); 
				  c.setState(rs.getString("ST")); 
				  c.setZip(rs.getString("ZIP")); 
		          customers.add(c);
			 }
			 stmt.close();
	         rs.close();

			 return customers;
			 
		 } catch(SQLException e) {
			 e.printStackTrace();
		 }
		 return null;
		
	 }
	 
	 private String  getNextId() {
		 int count=0;
		 String sql="SELECT count(*) as CNT from CUSTOMER";
		 
		 return ""+count+1;
		 
	 }
	 
	 public String createCustomer(String ID, String firstname, String lastname, String gender, String dob, String street, String city, String state, String zip) {
		 
		 String generatedID=getNextId();
		 
		 String query = "insert into CUSTOMER(CUST_ID, FIRSTNAME, LASTNAME, GENDER, DOB, STREET, CITY, ST, ZIP) "
		 		+ "VALUES('"+ID+"','"+firstname+"','"+lastname+"','"+gender+"','"+dob+"','"+street+"','"+city+"','"+state+"','"+zip+"')";
		 //System.out.println("Query = "+query);
		 try {
			 stmt = conn.createStatement();
			 stmt.executeUpdate(query);
			 stmt.close();
			 
			 //System.out.println("Created cutstomer "+firstname+" "+lastname);
			 String success = "Created cutstomer "+firstname+" "+lastname;
			 return success;
			 
		 } catch(SQLException e) {
			 e.printStackTrace();
			 String error = "Failed to create cutstomer for "+firstname+" "+lastname;
			 return error;
		 }
		 	 
	 }
	 
	 
	 public String deleteByID(String ID) {
		 String query = "delete from Customer"+" where CUST_ID='"+ID+"'";
		 System.out.println("query="+query);
		 try {
			 stmt = conn.createStatement();
			 stmt.execute(query);
			 stmt.close();
		 } catch(SQLException e) {
			 e.printStackTrace();
		 }
		 return null;
		 
	 }
	 
	 
	 public String deleteByName(String firstname, String lastname) {
		 String query = "delete from Customer where FIRSTNAME='"+firstname+"'"+"and LASTNAME='"+lastname+"'";
		 System.out.println("query="+query);

		 try {
			 stmt = conn.createStatement();
			 stmt.executeUpdate(query);
			 stmt.close();
			 //System.out.println("Deleted "+firstname+" "+lastname+" from Customer DB");
			 
			 String success = "Deleted "+firstname+" "+lastname+" from Customer DB";
			 return success;
			 
		 } catch(SQLException e) {
			 e.printStackTrace();
			 String error = "Couldn't delete "+firstname+" "+lastname+" from Customer DB";
			 return error;
		 }
		 
	 }
	 
	 
	 public String updateCustomer(Map<String, String[]>  payload) {
		 
		 
		 String query = getUpdateQuery(payload);
		 System.out.println("query="+query);
		 try {
			 stmt = conn.createStatement();
			 stmt.executeUpdate(query);
			 stmt.close();
			 			 
		 }  catch(SQLException e) {
			 e.printStackTrace();
		 }
		 
		 return null;

	 }
	 
	 private String getUpdateQuery(Map<String, String[]> payload) {
		 String query = "UPDATE Customer SET ";
		 for(String updateColumn: payload.keySet()) {
			 if(!updateColumn.equals("CUST_ID")) {
				 String value= payload.get(updateColumn)[0];
				 if(!value.equals("")) {
					 query = query+updateColumn+"="+"'"+value+"',";
				 }
			 }
		 }
		 
		 query = query.substring(0, query.length()-1);
		 System.out.println("query first half="+query);
		 query= query +" WHERE CUST_ID='"+payload.get("CUST_ID")[0]+"'";
		 System.out.println("query final="+query);
		 return query;
		 
	 }
	 
	 /*
	 public String updateCustomer2(String ID, String updateVariable, String updateVal) {
		 String query = "delete from Customer where CUST_ID='"+ID+"'";
		 try {
			 stmt = conn.createStatement();
			 stmt.executeUpdate(query);
			 stmt.close();
			 
			 String success = "Updated "+updateVariable+"of customer with id"+" to "+updateVal;
			 return success;
			 
		 }  catch(SQLException e) {
			 e.printStackTrace();
			 return null;
		 }
		 
		 
	 }
	 */
	 
	 public String[] allColumns() {
		 String columns[] = new String[9];
		 columns[0] = "CUST_ID";
		 columns[1] = "FIRSTNAME";
		 columns[2] = "LASTNAME";
		 columns[3] = "GENDER";
		 columns[4] = "DOB";
		 columns[5] = "STREET";
		 columns[6] = "CITY";
		 columns[7] = "ST";
		 columns[8] = "ZIP";
		 return columns;		 
	 }
	 
	 public void closeDBConnection() {
		    try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	 }
	 
	 public boolean isDBConnected() {
		 try {
			return !conn.isClosed();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 return false;
	 }

}
