package il.co.ilrd.JDBC.UDP;

public class UDPServerMain 
{
	public static void main(String[] args) throws InterruptedException 
	{
		UDPServerDB serverDB = new UDPServerDB("CrudDBUDP");
		serverDB.startServer();
		
		//Thread.sleep(2000);
		//serverDB.stopServer();
	}
}
