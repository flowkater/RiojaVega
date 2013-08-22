package server.dispatcher;

import java.io.InputStream;

public interface EventHandler {

	public void handleEvent(InputStream stream);
	
	public void getHandle();
	
}
