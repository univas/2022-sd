package br.edu.univas.si8.sd;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {

	public static void main(String[] args) {
		new SocketServer().runServer();
	}

	public void runServer() {
			System.out.println("Iniciando servidor.");
			int port = 3134;
			StringBuffer buffer = new StringBuffer(); // buffer geral
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
					System.out.println("Aceitando a conexao.");
					Socket sock = server.accept();
					System.out.println("Lendo os dados.");
					readData(buffer, sock);
					System.out.println("Dados recebidos: " + buffer.toString());
					// processamento qualquer do pedido
					String resposta = "Ola " + buffer.toString();
					writeData(sock, resposta);
					System.out.println("Fechando a conexao.");
					sock.close();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}
			//server.close();
	}

	private void readData(StringBuffer buffer, Socket sock) throws IOException {
		InputStream in = sock.getInputStream(); // le os dados a partir do inputStream
		byte[] dados = new byte[10]; // buffer de leitura de tamanho fixo
		int qtd = in.read(dados); // le 10 bytes
		while (qtd > 0) {
			buffer.append(new String(dados, 0, qtd));
			qtd = in.available(); // verifica se existe dados para ler
			if (qtd > 0) { // isto evita ficar preso no read bloqueante
				qtd = in.read(dados); // l� 10 bytes
			}
		}
	}

	private void writeData(Socket sock, String resposta) throws IOException {
		OutputStream out = sock.getOutputStream();
		System.out.println("Enviando a resposta: " + resposta);
		out.write(resposta.getBytes()); // envia dados atrav�s do outputStream
		out.flush();
	}
}
