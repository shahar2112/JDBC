package il.co.ilrd.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class IsolationLevel {

	public static void main(String[] args) throws SQLException 
	{
		Connection conn = null; // connection reference variable for getting connection
		String conUrl = "jdbc:mysql://localhost:3306/";
		String driverName = "com.mysql.jdbc.Driver";
		String databaseName = "Computer_firm";
		String usrName = "root";
		String usrPass = "12345678";
		
		try 
		{
			// Loading Driver
			Class.forName(driverName);
		} catch (ClassNotFoundException e) 
		{
			System.out.println(e.toString());
		}
		try 
		{
			// Getting Connection
			conn = DriverManager.getConnection(conUrl + databaseName, usrName,
					usrPass);
			{
				System.out.println("Transaction Isolation level= "
						+ conn.getTransactionIsolation());
			}
			
		} 
		catch (Exception e) 
		{
			System.out.println(e.toString());
		} 
		finally 
		{
			// Closing connection
			conn.close();
		}
	}

}
