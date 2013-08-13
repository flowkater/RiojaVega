package multitasking;

import java.net.Socket;

public class EchoProtocolFactory implements ProtocolFactory{
	@Override
	public Runnable createProtocol(Socket clntSock, Logger logger) {
		return new EchoProtocol(clntSock, logger);
	}
}
