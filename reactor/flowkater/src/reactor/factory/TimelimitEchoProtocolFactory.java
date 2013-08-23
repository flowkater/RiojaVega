package reactor.factory;

import java.net.Socket;

import ractor.logger.Logger;

public class TimelimitEchoProtocolFactory implements ProtocolFactory {

	@Override
	public Runnable createProtocol(Socket clntSock, Logger logger) {
		return new TimelimitEchoProtocol(clntSock, logger);
	}

}
