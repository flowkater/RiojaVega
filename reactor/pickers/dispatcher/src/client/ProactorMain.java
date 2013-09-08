package client;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class ProactorMain {
	public static void main(String[] args) throws UnknownHostException,
			IOException, InterruptedException {
		while (true) {
			{
				Socket socket = new Socket("127.0.0.1", 6000);

				OutputStream out = socket.getOutputStream();

				out.write("0x5001|노성현|25".getBytes());

				socket.close();
			}
			{
				Socket socket = new Socket("127.0.0.1", 6000);

				OutputStream out = socket.getOutputStream();

				out.write("0x6001|대한민국|서울".getBytes());

				socket.close();
			}

			Thread.sleep(500);
		}
	}
}
