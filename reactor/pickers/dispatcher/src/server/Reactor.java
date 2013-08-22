package server;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

import server.dispatcher.Demultiplexer;
import server.dispatcher.DispatcherConfig;
import server.dispatcher.DispatcherVO;
import server.dispatcher.EventHandler;
import server.dispatcher.HandleMap;
import server.dispatcher.IDispatcher;

public class Reactor implements IDispatcher {

	private static HandleMap map;
	
	private Integer socketPort = null;
	
	public Reactor() {
		map =  (new Demultiplexer()).select(); 
		
		handleEvent();
		
	}
	
	@Override
	public void handleEvent()  {

		DispatcherConfig dispatcherConfig = new DispatcherConfig();
		
		DispatcherVO reactorConfig = dispatcherConfig.getDispatcherInfo("reactor");
		
		socketPort = reactorConfig.getPort();
		
		try {
			
			
			ServerSocket serverSocket = new ServerSocket(socketPort);

			while(true) {
			
			Socket socket = serverSocket.accept();
			
			InputStream is = socket.getInputStream();
			
			byte[] buffer = new byte[6];
			is.read(buffer);
			String header = new String( buffer );

//			map.get(header).handleEvent(new String(buffer));
			map.get(header).handleEvent(is);
			
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
	
	public void regesterHandler(String key, EventHandler handler){
		
		map.put(key, handler);
		
	}
	
	public void removeHandler(String key) {
		map.remove(key);
	}
	
}
