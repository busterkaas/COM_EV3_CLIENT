package Logic;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class PCClient {
	DataInputStream dis;
	DataOutputStream dos;
	Socket socket;

	public PCClient(String serverIpAddress, int serverSocketNumber) throws UnknownHostException, IOException {
		socket = new Socket(serverIpAddress, serverSocketNumber);

		dis = new DataInputStream(socket.getInputStream());
		dos = new DataOutputStream(socket.getOutputStream());
	}

	public void closeClient() throws IOException {
		dis.close();
		dos.close();
		socket.close();
	}

	public String sendMessage(String message) throws IOException {
		dos.writeUTF(message);
		dos.flush();

		String ev3Message = "";
		if (dis.available() > 0) {
			ev3Message = dis.readUTF();
		}
		return ev3Message;
	}
}
