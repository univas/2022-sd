package br.edu.univas.si8.sd;

import java.io.*;
import java.net.Socket;

public class IOHelper {
	Socket sock;

	public IOHelper(Socket sock) throws IOException {
		this.sock = sock;
	}

	public void send(String objetc) throws IOException {
		ObjectOutputStream out = new ObjectOutputStream(sock.getOutputStream());
		out.writeObject(objetc);
		out.flush();
	}

	public String receive() throws IOException, ClassNotFoundException {
		ObjectInputStream in = new ObjectInputStream(sock.getInputStream());
		return (String) in.readObject();
	}
}
