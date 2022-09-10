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
					String source = sock.getInetAddress().getHostAddress();
					System.out.println("Lendo os dados de " + source);
					StringBuffer buffer = readData(sock);
					//System.out.println("Dados recebidos: " + buffer.toString());
					// processamento qualquer do pedido
					String resposta = "Ola " + buffer.toString();
					writeData(sock, resposta);
					//System.out.println("Fechando a conexao.");
					sock.close();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}
			//server.close();
	}

	private StringBuffer readData(Socket sock) throws IOException {
		StringBuffer buffer = new StringBuffer();
		InputStream in = sock.getInputStream(); // le os dados a partir do inputStream
		byte[] dados = new byte[10]; // buffer de leitura de tamanho fixo
		int qtd = in.read(dados); // le 10 bytes
		while (qtd > 0) {
			buffer.append(new String(dados, 0, qtd));
			qtd = in.available(); // verifica se existe dados para ler
			if (qtd > 0) { // isto evita ficar preso no read bloqueante
				qtd = in.read(dados); // lê 10 bytes
			}
		}
		return buffer;
	}

	private void writeData(Socket sock, String resposta) throws IOException {
		OutputStream out = sock.getOutputStream();
		System.out.println("Enviando a resposta: " + resposta);
		out.write(resposta.getBytes()); // envia dados através do outputStream
		out.flush();
	}
}
