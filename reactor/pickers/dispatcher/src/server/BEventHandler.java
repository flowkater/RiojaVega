package server;

import java.io.IOException;
import java.io.InputStream;
import java.util.StringTokenizer;

import server.dispatcher.EventHandler;

public class BEventHandler implements EventHandler {

	private BService service = new BService();


	@Override
	public void getHandle() {

	}

	@Override
	public void handleEvent(InputStream stream) {

		byte[] buffer = new byte[1024];
		
		try {
			stream.read(buffer);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		final String param = new String(buffer);
		
		
		new Runnable() {
			public void run() {

				String[] arr = new String[2];
				
				StringTokenizer token = new StringTokenizer(param, "|");
				int i=0;
				while(token.hasMoreTokens()) {
					arr[i] = token.nextToken();
					i++;
				}
				String nation = arr[0];
				String city = arr[1];
				
				
				service.printService(nation, city);

				
			}
		}.run();
		
		
	}


}
