package il.co.ilrd.JDBC;

import java.io.IOException;

public class DBServerMain 
{
	public static void main(String[] args) throws IOException, InterruptedException 
	{
		String DB = "CrudDBTCP";
		TCPServerDB filetest = new TCPServerDB(DB);
		filetest.startServer();
		
		//Thread.sleep(2000);
		//System.out.println("stopping the server");
		//filetest.stopServer();
	}

}
