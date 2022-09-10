package br.edu.univas.si8.sd;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class SocketObjectClient {

	public static void main(String[] args) {
		int port = 3134;

		try {
			Socket sock = new Socket("localhost", port); // cria um socket
			
			IOHelper ioHelper = new IOHelper(sock);

			Scanner sc = new Scanner(System.in);
			System.out.println("Digite alguma coisa (exit para sair): ");
			String msg = sc.next() + sc.nextLine();// le um texto do teclado
			while (!msg.equals("exit")) {
				// envia a msg para o socket via ObjectOutputStream

				ioHelper.send(msg);
				// recebe a resposta do socket via ObjectInputStream
				String resp = ioHelper.receive();
				if (resp != null) {
					System.out.println("Resposta: " + resp);
				}
				// le o proximo texto do teclado
				msg = sc.next() + sc.nextLine();
			}
			sock.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
