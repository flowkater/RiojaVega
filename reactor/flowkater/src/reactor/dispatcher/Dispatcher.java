package reactor.dispatcher;

import java.net.ServerSocket;
import java.util.Map;

import ractor.logger.Logger;

public interface Dispatcher {
	public void startDispatching(ServerSocket servSock, Logger logger,
			Map<String, String> protocols);
}
