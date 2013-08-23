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

		// Ư�� ����, ��Ʈ�� ������ �� �ִ� ���� ����
		Socket socket = null;
		try {
			socket = new Socket(server, servPort);
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		p("������ �������Դϴ�. ���� ���ڿ��� �����ϴ�.");

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
		} // ���ڵ��� ���ڿ��� ������ ����

		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// ������ ���� ���� ���ڿ��� �����޴´�.
		int totalBytesRcvd = 0;
		int bytesRcvd = 0;
		while (totalBytesRcvd < byteBuffer.length) {
			/*
			 * read() 1) ���� ���� 2) ó������ ���� ����Ʈ�� �־�� �� ����Ʈ ������(offset) 3) ���ۿ� ������
			 * �ִ� ����Ʈ�� �ִ�
			 */
			try {
				if ((bytesRcvd = in.read(byteBuffer, totalBytesRcvd,
						byteBuffer.length - totalBytesRcvd)) == -1)
					throw new SocketException("������ ���� ����");
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
