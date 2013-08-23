package reactor.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Client {
	final static int POOL = 8888;
	final static int THREADPER = 9999;

	public static void main(String[] args) {
		String server = "127.0.0.1";

		String word = "0x3333|Echo this";

		byte[] byteBuffer = word.getBytes();

		int servPort = THREADPER;

		// 특정 서버, 포트에 연결할 수 있는 소켓 생성
		Socket socket = null;
		try {
			socket = new Socket(server, servPort);
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		p("서버에 연결중입니다. 에코 문자열을 보냅니다.");

		InputStream in = null;
		try {
			in = socket.getInputStream();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		OutputStream out = null;
		try {
			out = socket.getOutputStream();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		try {
			out.write(byteBuffer);
		} catch (IOException e1) {
			e1.printStackTrace();
		} // 인코딩된 문자열을 서버로 전송

		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// 서버로 부터 같은 문자열을 돌려받는다.
		int totalBytesRcvd = 0;
		int bytesRcvd = 0;
		while (totalBytesRcvd < byteBuffer.length) {
			/*
			 * read() 1) 받을 버퍼 2) 처음으로 받은 바이트가 있어야 할 바이트 오프셋(offset) 3) 버퍼에 담을수
			 * 있는 바이트의 최대
			 */
			try {
				if ((bytesRcvd = in.read(byteBuffer, totalBytesRcvd,
						byteBuffer.length - totalBytesRcvd)) == -1)
					throw new SocketException("연결이 일찍 닫힘");
			} catch (SocketException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			totalBytesRcvd += bytesRcvd;
		}

		p("Recevied: " + new String(byteBuffer));

		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void p(String str) {
		System.out.println(str);
	}
}
