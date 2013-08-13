package multitasking;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;

public class TimeProtocolFactory implements ProtocolFactory {

	@Override
	public Runnable createProtocol(final Socket clntSock, final Logger logger) {
		return new Runnable(){
			@Override
			public void run() {
				TimeProtocolFactory.handClient(clntSock, logger);
			}
		};
	}

	private static void handClient(Socket clntSock, Logger logger) {
		ArrayList<String> entry = new ArrayList<String>();
		entry.add("Client address and port = " + clntSock.getInetAddress().getHostAddress() + ":" + clntSock.getPort());
		
		entry.add("Thread = " + Thread.currentThread().getName());
		
		try{
			clntSock.getOutputStream().write((new Date() + "\n").getBytes());
		}catch(IOException e){
			entry.add("Exception = " + e.getMessage());
		}
		
		try{
			clntSock.close();
		}catch(IOException e){
			entry.add("Exception = " + e.getMessage());
		}
		
		logger.writeEntry(entry);
	}

}
