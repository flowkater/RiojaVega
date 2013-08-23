package reactor.dispatcher;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;

import ractor.logger.Logger;
import reactor.factory.ProtocolFactory;

public class ThreadPerDispatcher implements Dispatcher {

	@Override
	public void startDispatching(ServerSocket servSock, Logger logger,
			Map<String, String> protocols) {
		while (true) {
			try {
				Socket clntSock = servSock.accept();
				InputStream in = clntSock.getInputStream();
				byte[] buffer = new byte[6];
				in.read(buffer);

				String header = new String(buffer);

				String protocolName = protocols.get(header);
				
				System.out.println(header);

				ProtocolFactory protoFactory = null;
				try {
					protoFactory = (ProtocolFactory) Class
							.forName("reactor.factory." + protocolName
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
				Thread thread = new Thread(protocol);
				thread.start();
				logger.writeEntry("Created and started Thread = "
						+ thread.getName());
			} catch (IOException e) {
				logger.writeEntry("Exception = " + e.getMessage());
			}
		}
	}
}
