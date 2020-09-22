package il.co.ilrd.JDBC.UDP;

import java.io.IOException;

public class UDPCLientMain {

	public static void main(String[] args) throws IOException, InterruptedException
	{
		String src = "/home/bla/shahar-maimon/java/myProjects/src/il/co/ilrd/JDBC/Test.txt";
		DBObserverUDP observerUDP = new DBObserverUDP(src);
	}

}
