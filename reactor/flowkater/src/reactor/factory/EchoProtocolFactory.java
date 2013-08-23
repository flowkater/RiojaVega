package reactor.factory;

import java.net.Socket;

import ractor.logger.Logger;

public class EchoProtocolFactory implements ProtocolFactory{
	@Override
	public Runnable createProtocol(Socket clntSock, Logger logger) {
		return new EchoProtocol(clntSock, logger);
	}
}
