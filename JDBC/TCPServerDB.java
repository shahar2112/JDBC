package il.co.ilrd.JDBC;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServerDB
{
	private ServerSocket serverSocket;
	private static final int PORT = 9999;
	private boolean keepRunning;
	private CrudedDB serverDB;
	
	public TCPServerDB(String dBName)
	{
		try
		{
			//creates a server socket and binds it to the port number:
			serverSocket = new ServerSocket(PORT);
			//creating a new db if doesn't exist
			serverDB = new CrudedDB(dBName);
			keepRunning = true;
			
		} catch (IOException e) {e.printStackTrace();}	
	}
	
	public void startServer()
	{
		Thread thread = new RunThread();
		thread.start();
	}
	
	public void stopServer()
	{
		keepRunning = false;
	}
	
	class RunThread extends Thread
	{
		/*the max size of a packet data is 256
		 * and 5 more bytes for metadata = 261
		 */
		private int packetSize = 261;
		String data = null;
		
		
		@Override
		public void run() 
		{
			while(keepRunning)
			{
				// preparing an empty packet byte arr in order to receive the data from the client
				byte[] packetRecieved = new byte[packetSize];
				
				try
				(	Socket mySocket = serverSocket.accept();
					//InputStream to read data sent from the client 
					InputStream input = mySocket.getInputStream();)
				{
					input.read(packetRecieved);

					data = Parser.parseData(packetRecieved);
					if(data == null)
					{
						return;
					}
					// performing the right action according to the op char
					// 0- inserting to the database
					// 1- print to console
					operate();	
				}
				 catch (IOException e) {e.printStackTrace();}
			}
		}
		
		
		private void operate()
		{
			switch(data.charAt(0))
			{
				case '0':
					System.out.println("adding to db..");
					//add the data received to the database
					serverDB.create(data.substring(1));
					break;
				case '1':
					System.out.println(data.substring(1));
					break;
			}
		}
		
	}
}
