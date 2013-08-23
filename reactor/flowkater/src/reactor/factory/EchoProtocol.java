package reactor.factory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;

import ractor.logger.Logger;

public class EchoProtocol implements Runnable {
	static public final int BUFSIZE = 32;
	
	private Socket clntSock;
	private Logger logger;
	
	public EchoProtocol(Socket clntSock, Logger logger) {
		this.clntSock = clntSock;
		this.logger = logger;
	}

	@Override
	public void run() {
		ArrayList<String> entry = new ArrayList<String>();
		entry.add(clntSock.getInetAddress().getHostAddress() + " : " + clntSock.getPort());
		entry.add("Thread = " + Thread.currentThread().getName());
		
		try{
			InputStream in = clntSock.getInputStream();
			OutputStream out = clntSock.getOutputStream();
			
			int recvMsgSize;
			int totalBytesEchoed = 0;
			
			byte[] echoBuffer = new byte[BUFSIZE];
			// 클라이언트 연결이 끊길때까지 버퍼 받음 
			while((recvMsgSize = in.read(echoBuffer)) != -1){
				out.write(echoBuffer, 0, recvMsgSize);
				totalBytesEchoed += recvMsgSize;
			}
			entry.add("Client finished echoed " + totalBytesEchoed + " bytes");
		}catch(IOException e){
			entry.add("Exception = " + e.getMessage());
		}
		
		try{
			clntSock.close();
		}catch(IOException e){
			entry.add("Exception = " + e.getMessage());
		}

		logger.writeEntry(entry);
	}
}
