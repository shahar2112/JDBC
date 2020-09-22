package il.co.ilrd.JDBC;

import java.io.IOException;

//this will be the server class
public class DBObserverMain
{
		public static void main(String[] args) throws IOException, InterruptedException 
		{
			String src = "/var/log/syslog";
			String DB = "CrudDB";
			DBObserver filetest = new DBObserver(src, DB);
			
			Thread.sleep(1000);
			filetest.close();
		}

}

