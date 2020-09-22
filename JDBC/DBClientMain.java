package il.co.ilrd.JDBC;

import java.io.IOException;

public class DBClientMain 
{
	public static void main(String[] args) throws IOException, InterruptedException 
	{
		String src = "/home/bla/shahar-maimon/java/myProjects/src/il/co/ilrd/JDBC/Test.txt";
		//String src = "/home/student/shahar-maimon/java/myProjects/src/il/co/ilrd/JDBC/Test.txt";
		
		DBObserverTCP dbObserver = new DBObserverTCP(src);
	}	

}
