package il.co.ilrd.JDBC;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCPAdapter 
{
	private static final int PORT = 9999;
	InetAddress hostIP;
	
	public TCPAdapter()
	{
		try {
			hostIP = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {e.printStackTrace();}
	}
	
	void operateData(String data)
	{
		if(data == null || data.length() == 0)
		{
			return;
		}
		
		data.replaceAll("[\\n]", "");
		
		try (
			// Creating a clientSocket and connect it to the serverAdress and port
			Socket clientsocket = new Socket(hostIP, PORT);
			OutputStream output = clientsocket.getOutputStream())
		{
			byte[] wrappedData = Parser.buildData(data);
			
			//write to the socket
			output.write(wrappedData);
			
		} catch (IOException e) {e.printStackTrace();}
	}
}
