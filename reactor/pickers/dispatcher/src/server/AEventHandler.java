package server;

import java.io.IOException;
import java.io.InputStream;
import java.util.StringTokenizer;

import server.dispatcher.EventHandler;

public class AEventHandler implements EventHandler {

	private AService service = new AService();


	@Override
	public void getHandle() {
		// TODO Auto-generated method stub
		
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
				
				@Override
				public void run() {
					String name;
					String age;
					
					String[] arr = new String[2];
					
					StringTokenizer token = new StringTokenizer(param, "|");
					int i=0;
					while(token.hasMoreTokens()) {
						arr[i] = token.nextToken();
						i++;
					}
					name = arr[0];
					age = arr[1];
					
					service.printService(name, age);
									
				}
			}.run();
	
	
	}

	
	
	
}
