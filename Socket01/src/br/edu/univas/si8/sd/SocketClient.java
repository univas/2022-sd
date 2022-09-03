package br.edu.univas.si8.sd;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class SocketClient {

	public static void main(String[] args) {
		new SocketClient().runClient();
	}

	public void runClient() {
		String mensagem = "Roberto";
		System.out.println("Iniciando o cliente.");
		int port = 3134;
		try {
			Socket sock = new Socket("localhost", port); // cria um socket
			writeData(sock, mensagem);
			StringBuffer buffer = readData(sock);
			System.out.println("Resposta do servidor: " + buffer.toString());
			sock.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private StringBuffer readData(Socket sock) throws IOException {
		// buffer de leitura de tamanho fixo
		byte[] dados = new byte[10];
		StringBuffer buffer = new StringBuffer();
		// recebe e imprime os dados
		InputStream in = sock.getInputStream();
		int qtd = in.read(dados); // le 10 bytes
		while (qtd > 0) {
			buffer.append(new String(dados, 0, qtd));
			qtd = in.available(); // verifica se existe dados para ler
			if (qtd > 0) {
				qtd = in.read(dados); // lê 10 bytes
			}
		}
		return buffer;
	}

	private void writeData(Socket sock, String mensagem) throws IOException {
		OutputStream out = sock.getOutputStream();
		System.out.println("Enviando dados.");
		out.write(mensagem.getBytes()); // envia dados
		out.flush();
	}
}
