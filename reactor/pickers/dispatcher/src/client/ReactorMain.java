package client;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class ReactorMain {

	public static void main(String[] args) throws UnknownHostException,
			IOException, InterruptedException {
		while (true) {
			{
				Socket socket = new Socket("127.0.0.1", 5000);

				OutputStream out = socket.getOutputStream();

				out.write("0x5001|�뼺��|25".getBytes());

				socket.close();
			}
			{
				Socket socket = new Socket("127.0.0.1", 5000);

				OutputStream out = socket.getOutputStream();

				out.write("0x6001|���ѹα�|����".getBytes());

				socket.close();
			}
			
			Thread.sleep(1000);
		}
	}

}
