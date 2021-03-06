package reactor.factory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;

import ractor.logger.Logger;

public class TimelimitEchoProtocol implements Runnable {
	private static final int BUFSIZE = 32;
	private static final String TIMELIMIT = "10000";
	private static final String TIMELIMITPROP = "Timelimit";

	private int timelimit;
	private Socket clntSock;
	private Logger logger;

	public TimelimitEchoProtocol(Socket clntSock, Logger logger) {
		this.clntSock = clntSock;
		this.logger = logger;
		timelimit = Integer.parseInt(System.getProperty(TIMELIMITPROP,
				TIMELIMIT));
	}

	@Override
	public void run() {
		ArrayList<String> entry = new ArrayList<String>();
		entry.add("Client address and port = "
				+ clntSock.getInetAddress().getHostAddress() + ":"
				+ clntSock.getPort());
		entry.add("Thread = " + Thread.currentThread().getName());

		try {
			InputStream in = clntSock.getInputStream();
			OutputStream out = clntSock.getOutputStream();

			int recvMsgSize;
			int totalBytesEchoed = 0;
			byte[] echoBuffer = new byte[BUFSIZE];
			long endTime = System.currentTimeMillis() + timelimit;
			int timeBoundMillis = timelimit;

			clntSock.setSoTimeout(timeBoundMillis);

			while ((timeBoundMillis > 0)
					&& ((recvMsgSize = in.read(echoBuffer)) != -1)) {
				out.write(echoBuffer, 0, recvMsgSize);
				totalBytesEchoed += recvMsgSize;
				timeBoundMillis = (int) (endTime - System.currentTimeMillis());
				clntSock.setSoTimeout(timeBoundMillis);
			}
			entry.add("Client finished echoed " + totalBytesEchoed + " bytes.");
		} catch (InterruptedIOException dummy) {
			entry.add("READ timed out");
		} catch (IOException e) {
			entry.add("Exception = " + e.getMessage());
		}

		try {
			clntSock.close();
		} catch (IOException e) {
			entry.add("Exception = " + e.getMessage());
		}
		logger.writeEntry(entry);
	}

}
