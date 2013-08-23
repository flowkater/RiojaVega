package reactor.factory;

import java.net.Socket;

import ractor.logger.Logger;

public interface ProtocolFactory {
	public Runnable createProtocol(Socket clntSock, Logger logger);
}
