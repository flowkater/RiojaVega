package reactor.dispatcher;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;

import ractor.logger.Logger;
import reactor.factory.ProtocolFactory;

public class PoolDispatcher implements Dispatcher {

	static final String NUMTHREADS = "8";
	static final String THREADROP = "Threads";

	private int numThreads;

	public PoolDispatcher() {
		numThreads = Integer
				.parseInt(System.getProperty(THREADROP, NUMTHREADS));
	}

	@Override
	public void startDispatching(final ServerSocket servSock,
			final Logger logger, final Map<String, String> protocols) {
		for (int i = 0; i < (numThreads - 1); i++) {
			Thread thread = new Thread() {
				public void run() {
					dispatchLoop(servSock, logger, protocols);
				}
			};
			thread.start();
			logger.writeEntry("Created and started Thread = "
					+ thread.getName());
		}
		logger.writeEntry("Iterative server starting in main thread "
				+ Thread.currentThread().getName());

		dispatchLoop(servSock, logger, protocols);
	}

	private void dispatchLoop(ServerSocket servSock, Logger logger,
			Map<String, String> protocols) {
		while (true) {
			try {
				Socket clntSock = servSock.accept();
				InputStream in = clntSock.getInputStream();
				byte[] buffer = new byte[6];
				in.read(buffer);

				String header = buffer.toString();

				String protocolName = protocols.get(header);

				System.out.println(header);
				ProtocolFactory protoFactory = null;
				try {
					protoFactory = (ProtocolFactory) Class.forName(
							"reactor.factory" + protocolName
									+ "ProtocolFactory").newInstance();
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				Runnable protocol = protoFactory.createProtocol(clntSock,
						logger);

				protocol.run();
			} catch (IOException e) {
				logger.writeEntry("Exception = " + e.getMessage());
			}
		}
	}

}
