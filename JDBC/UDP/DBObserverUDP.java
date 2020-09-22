package il.co.ilrd.JDBC.UDP;

import java.io.IOException;

import il.co.ilrd.FileMonitor.MonitorLog;	
import il.co.ilrd.Observer.Dispatcher;
import il.co.ilrd.Observer.Observer;

public class DBObserverUDP
{
	private MonitorLog monitorLog;
	private Observer<String> observer;
	
	public DBObserverUDP(String src) throws IOException, InterruptedException 
	{
		monitorLog = new MonitorLog(src);
		observer = new ObservedDB(monitorLog.getDispatcher());
		observer.subscribe();
		monitorLog.startLog();
	}
	
	/* closing the monitor = src file and watchservice
	 * and the observer = dest file
	 */
	public void close() 
	{
		monitorLog.closeLog();
		observer.unsubscribe();
		observer.StopUpdate(null);
	}
	
	
	 /* A inner class that observed the src file and calls the CrudedDB to
	 * update or stop update if needed.
	 *  The class observes a source file and writes changes to the DB.
	 *   This class extends the abstract class observer. */
	private class ObservedDB extends Observer<String>
	{
		private UDPAdapter clientUDPAdapter;
		
		private ObservedDB(Dispatcher<String> dispatcher) throws IOException 
		{
			super(dispatcher);
			clientUDPAdapter = new UDPAdapter();
		}
		
		@Override
		public void StopUpdate(String data) 
		{
			
		}
		
		@Override
		public void update(String data) 
		{
			clientUDPAdapter.operateData(data);
		}
	}
}
