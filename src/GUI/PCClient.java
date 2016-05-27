package GUI;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import Helpers.ComSupport;
import IHelpers.ICommunicationObserver;

public class PCClient {
	DataInputStream dis;
	DataOutputStream dos;
	Socket socket;
	
	private ComSupport com;

	public PCClient(String serverIpAddress, int serverSocketNumber) throws UnknownHostException, IOException {
		socket = new Socket(serverIpAddress, serverSocketNumber);

		dis = new DataInputStream(socket.getInputStream());
		dos = new DataOutputStream(socket.getOutputStream());
	}
	
	public void setupMsgRecieving(ICommunicationObserver obs){
		com = new ComSupport(dis, obs);
		com.start();
	}

	public void closeClient() throws IOException {
		dis.close();
		dos.close();
		socket.close();
	}

	public void sendMessage(String message) throws IOException {
		dos.writeUTF(message);
		dos.flush();

	}
}
