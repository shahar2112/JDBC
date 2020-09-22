package il.co.ilrd.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import il.co.ilrd.FileMonitor.CrudRepository;

public class CrudedDB implements CrudRepository<String, Integer>
{
	private String DBName = null;
	private String DBUrl = "jdbc:mysql://localhost:3306/";
	private String usrName = "root";
	private String usrPass = "12345678";
			
	public CrudedDB(String DBName)
	{
		this.DBName = DBName;
		
		try
		(
				// Getting Connection to driver
				Connection conn = DriverManager.getConnection(DBUrl , usrName, usrPass);
				//create a query
				Statement stmt = conn.createStatement();
		)
		{
			String createDB = "CREATE DATABASE IF NOT EXISTS " + DBName ;
			String useDB = "USE " + DBName;
			String createTable = "CREATE TABLE IF NOT EXISTS Logger " +
					"(change_id INT AUTO_INCREMENT PRIMARY KEY, " +
					"created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
					"changes VARCHAR(1000) )" ;
			System.out.println("Database and table created successfully...");
			//execute a query
			stmt.executeUpdate(createDB);
			stmt.executeUpdate(useDB);
			stmt.executeUpdate(createTable);
		}  
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}

	
	/* The method gets the new entry (new change) and writes it into the DB,
	 * return the id number of the change - the change row index */
	@Override
	public Integer create(String data)
	{
		try
		(
				// Getting Connection to driver
				Connection conn = DriverManager.getConnection(DBUrl + DBName , usrName, usrPass);
				//create a query
				Statement stmt = conn.createStatement();
		)
		{
			String insertChange = "INSERT INTO Logger(changes) " +
								"VALUES(' " + data + "')";
			System.out.println(data);	
			//execute a query
			stmt.executeUpdate(insertChange);
		}  
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return 0;
	}
	
	@Override
	public void close(){}

	@Override
	public String read(Integer id){return null;}

	@Override
	public void update(Integer id, String data) {}

	@Override
	public void delete(Integer id) {}
}	
