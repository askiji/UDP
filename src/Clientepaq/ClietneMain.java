package Clientepaq;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ClietneMain {

	public static void main(String[] args) {
		DatagramSocket socket;
		InetAddress address;
		byte[] mensaje_bytes = new byte[256];
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String mensaje="";
		DatagramPacket paquete;
		// Params to recive package
		String mensaje_llegada; 
		byte[] mensaje_bytes_llegada = new byte[256];
		DatagramPacket paqueteLegada = new DatagramPacket(mensaje_bytes_llegada,256);
		try {
			socket = new DatagramSocket(6001);
			address=InetAddress.getByName("localhost");
			do {
				mensaje = in.readLine();
				mensaje_bytes = mensaje.getBytes();
				paquete = new DatagramPacket(mensaje_bytes,mensaje.length(),address,6000);
				System.out.println("Envio el paquete");
				socket.send(paquete);
				// Here we recive the package
				socket.receive(paqueteLegada);
				System.out.println("recibo el paquete");
				mensaje_llegada = new String(mensaje_bytes_llegada);
				System.out.println(mensaje_llegada);
//				mensaje_bytes_llegada = new byte[256];
//				socket.close();
			} while (true);
		} catch (Exception e) {
			System.err.println(e.getMessage());
			System.exit(1);
		}
	}

}
