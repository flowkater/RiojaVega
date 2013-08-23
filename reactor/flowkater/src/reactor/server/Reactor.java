package reactor.server;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import ractor.logger.FileLogger;
import ractor.logger.Logger;
import reactor.dispatcher.Dispatcher;
import reactor.factory.EchoProtocolFactory;
import reactor.factory.ProtocolFactory;
import reactory.simpleclass.Dispatchers;

public class Reactor implements Runnable {
	final static int POOL = 8888;
	final static int THREADPER = 9999;

	Map<String, String> protocols = new HashMap<String, String>();
	Map<Integer, String> dispatchers = new HashMap<Integer, String>();
	List<Dispatcher> dispatcherList = new ArrayList<Dispatcher>();

	public Reactor() {
		Demultiplexer demultiplexer = new Demultiplexer();
		protocols = demultiplexer.select();
		System.out.println(protocols);

		Serializer serializer = new Persister();
		File source = new File("DispatcherConfig.xml");
		Dispatchers dispatchConfigs = new Dispatchers();

		try {
			dispatchConfigs = serializer.read(Dispatchers.class, source);
		} catch (Exception e) {
			e.printStackTrace();
		}

		dispatchers = dispatchConfigs.getDispatchers();
		System.out.println(dispatchers);

		Logger logger = null;
		try {
			logger = new FileLogger("logger.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}

		String dispatcherName = dispatchers.get(THREADPER);
		ServerSocket servSock = null;
		try {
			servSock = new ServerSocket(THREADPER);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Dispatcher dispatcher = null;
		try {
			dispatcher = (Dispatcher) Class.forName(
					"reactor.dispatcher." + dispatcherName + "Dispatcher")
					.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		dispatcher.startDispatching(servSock, logger, protocols);
	}

	@Override
	public void run() {

	}

	public static void main(String[] args) {
		Reactor reactor = new Reactor();
	}
}
