package il.co.ilrd.JDBC.UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

import il.co.ilrd.JDBC.Parser;

public class UDPAdapter
{
	private static final int PORT = 9999;
	private InetAddress hostIP;
	
	public UDPAdapter()
	{
		try
		{
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
		
		byte[] wrappedData = Parser.buildData(data);
		int datagramBufferOffset = 0;
		int datagramBufferSize = wrappedData.length;
		// Creating a "server socket address" (object of server IP + server port).
		InetSocketAddress datagramDestinationDetails = new InetSocketAddress (hostIP, PORT);
		// Creating the datagram.
		DatagramPacket datagram = new DatagramPacket(wrappedData, 
				datagramBufferOffset, datagramBufferSize, datagramDestinationDetails);
		
		//creating the socket
		try (DatagramSocket socket = new DatagramSocket ();)
		{	
			socket.send (datagram); //sending the data
		} catch (IOException e) {e.printStackTrace(System.err);}
	}
}
