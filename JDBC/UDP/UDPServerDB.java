package il.co.ilrd.JDBC.UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

import il.co.ilrd.JDBC.CrudedDB;
import il.co.ilrd.JDBC.Parser;


public class UDPServerDB
{
	private static final int PORT = 9999;
	private boolean keepRunning;
	private CrudedDB serverDB;
	
	public UDPServerDB(String dBName)
	{
		//creating a new db if doesn't exist
		serverDB = new CrudedDB(dBName);
		keepRunning = true;	
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
				// preparing an empty datagram packet in order to receive the data from the client
				byte[] DatagramBuffer = new byte [packetSize];
				int DatagramBufferOffset = 0;
				DatagramPacket requestDatagram = new DatagramPacket (DatagramBuffer, DatagramBufferOffset, packetSize);
				try 
				(	//creating the socket
					DatagramSocket socket = new DatagramSocket(PORT))
				{
					//read data sent from the client 
					socket.receive (requestDatagram);
					

					data = Parser.parseData(DatagramBuffer);
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
					System.out.println("adding to db");
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
