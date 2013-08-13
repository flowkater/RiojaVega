package multitasking;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class PoolDispatcher implements Dispatcher {

	static final String NUMTHREADS = "8";
	static final String THREADROP = "Threads";

	private int numThreads;

	public PoolDispatcher() {
		numThreads = Integer
				.parseInt(System.getProperty(THREADROP, NUMTHREADS));
	}

	@Override
	public void startDispatching(final ServerSocket servSock, final Logger logger,
			final ProtocolFactory protoFactory) {
		for (int i = 0; i < (numThreads - 1); i++) {
			Thread thread = new Thread(){
				public void run() {
					dispatchLoop(servSock, logger, protoFactory);
				}
			};
			thread.start();
			logger.writeEntry("Created and started Thread = " + thread.getName());
		}
		logger.writeEntry("Iterative server starting in main thread " + Thread.currentThread().getName());
		
		dispatchLoop(servSock, logger, protoFactory);
	}

	private void dispatchLoop(ServerSocket servSock, Logger logger,
			ProtocolFactory protoFactory) {
		while(true){
			try{
				Socket clntSock = servSock.accept();
				Runnable protocol = protoFactory.createProtocol(clntSock, logger);
				protocol.run();
			}catch(IOException e){
				logger.writeEntry("Exception = " + e.getMessage());
			}
		}
	}

}
