package server;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

import server.dispatcher.Demultiplexer;
import server.dispatcher.DispatcherConfig;
import server.dispatcher.DispatcherVO;
import server.dispatcher.HandleMap;
import server.dispatcher.IDispatcher;
import server.dispatcher.ProactorIOThread;
import server.dispatcher.ProactorThreadPool;

public class Proactor implements IDispatcher {

	private static HandleMap map;

	private Integer socketPort = null;

	public Proactor() {

		map = (new Demultiplexer()).select();

		handleEvent();

	}

	@Override
	public void handleEvent() {

		DispatcherConfig dispatcherConfig = new DispatcherConfig();

		DispatcherVO proactorConfig = dispatcherConfig
				.getDispatcherInfo("proactor");

		socketPort = proactorConfig.getPort();

		System.out.println(socketPort);
		
		// ���Ͽ� ��û�� ���� �� Thread Pool ���� Thead�� ���� ó���ϰ� �̺�Ʈ �ϷḦ ���� ��
		ProactorThreadPool threadPool = new ProactorThreadPool(5);
		
		
		ServerSocket serverSocket;
		
		
		try {
			serverSocket = new ServerSocket(socketPort);

			while (true) {

				Socket socket = serverSocket.accept();

				InputStream is = socket.getInputStream();

				byte[] buffer = new byte[6];
				is.read(buffer);
				String header = new String(buffer);

				// map.get(header).handleEvent(new String(buffer));
				
				synchronized (this) {
					
					ProactorIOThread ioThread =  threadPool.getThread();
					
					ioThread.setHandler(map.get(header), is).start();
				}
				

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
