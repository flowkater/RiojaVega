package multitasking;

import java.net.ServerSocket;

public interface Dispatcher {
	public void startDispatching(ServerSocket servSock, Logger logger,
			ProtocolFactory protoFactory);
}
