package multitasking;

import java.net.ServerSocket;

public class ThreadMain {
	public static void main(String[] args) throws Exception {
		int servPort = 3333;
		String protocolName = "";
		String dispatcherName = "";
		String loggerName = "";

		ServerSocket servSock = new ServerSocket(servPort);
		Logger logger = (Logger) Class.forName(loggerName + "Logger")
				.newInstance();
		ProtocolFactory protoFactory = (ProtocolFactory) Class.forName(
				protocolName + "ProtocolFactory").newInstance();
		Dispatcher dispatcher = (Dispatcher) Class.forName(
				dispatcherName + "Dispatcher").newInstance();

		dispatcher.startDispatching(servSock, logger, protoFactory);
	}
}
