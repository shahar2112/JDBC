package il.co.ilrd.JDBC;


public class Parser 
{
	private static final byte STX = 0x02;
	private static final byte ETX = 0x03;
	
	public static byte[] buildData(String data)
	{	
		/* the first byte in the data we receive from user is the
		 * operation he wants to perform
		 */
		byte[] dataByteArr = data.substring(1).getBytes();
		
		int dataLength = dataByteArr.length;
		
		/*the length of the byte array will be data length and 5 more
		 * for start, op, len, end and lrc 
		 */
		byte[] bytearr = new byte[dataLength + 5];

		bytearr[0] = STX; //STX -symbols the start of the msg
		
		bytearr[1] = (byte)data.charAt(0); //operation code
		
		bytearr[2] = (byte)dataLength; //length of the actual data
		
		int i = 0;
		
		for(i = 0; i < dataLength; ++i)
		{
			bytearr[i + 3] = dataByteArr[i];
		}
		bytearr[i + 3] = ETX; //ETX - this symbols the end
		bytearr[i + 4] = lrc(bytearr);

		return bytearr;
	}
	
	public static String parseData(byte[] packet)
	{
		int i;
		int PACKET_SIZE = 261;
		
		// reaching the start byte
		for (i=0; packet[i] != (byte)STX & i < PACKET_SIZE; i++);
		
		if (PACKET_SIZE == i)
		{
			return null;
		}
		
		// parsing the op code
		String op = ""  + (char)packet[++i];
		
		// parsing the length
		int length = (int)packet[++i];
		
		// parsing the data
		String data = new String(packet, ++i, length);
		
		// security check of lrc
		if (packet[packet.length-1] != lrc(packet))
		{
			return null;
		}
		
		return op + data;
	}
	
	
	
	private static byte lrc(byte[] arr) //lrc- returns a signature for the data
	{
		byte lrc = 0;
		for(int i = 0; i < arr.length -1; ++i)
		{
			lrc ^= arr[i];
		}
		return lrc;
	}

}
