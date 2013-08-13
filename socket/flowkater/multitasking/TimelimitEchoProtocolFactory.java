package multitasking;

import java.net.Socket;

public class TimelimitEchoProtocolFactory implements ProtocolFactory {

	@Override
	public Runnable createProtocol(Socket clntSock, Logger logger) {
		return new TimelimitEchoProtocol(clntSock, logger);
	}

}
