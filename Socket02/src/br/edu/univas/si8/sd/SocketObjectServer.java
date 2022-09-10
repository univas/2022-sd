package br.edu.univas.si8.sd;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketObjectServer {

	public static void main(String[] args) {
		System.out.println("Iniciando servidor.");
		int port = 3134;
		int count = 0;
		ServerSocket server;
		try {
			// cria um socket para ficar escutando
			server = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
			return;
		} 
		while (true) {
			try {
				System.out.println("Aceitando a conexao [" + count++ + "]");
				Socket sock = server.accept();
				IOHelper ioHelper = new IOHelper(sock);
				
				String source = sock.getInetAddress().getHostAddress();
				System.out.println("Lendo os dados de " + source);
				String buffer = ioHelper.receive();
				// processamento qualquer do pedido
				String resposta = "Ola " + buffer.toString();
				ioHelper.send(resposta);
				sock.close();
			} catch (Exception ioe) {
				ioe.printStackTrace();
			}
		}
		//server.close();
	}
}
