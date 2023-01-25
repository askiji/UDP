package Serverpaq;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ServerMain {

	public static void main(String[] args) {
		DatagramSocket socket;
		// Open port in server
		// TODO hacer el codigo del mensage
		String mensaje; 
		byte[] mensaje_bytes_llegada = new byte[256];
		DatagramPacket paqueteLegada = new DatagramPacket(mensaje_bytes_llegada,256);
		//Params used to foward the message
		int puerto;
		InetAddress address;
		byte[] mensaje_bytes_salida = new byte[256];
		DatagramPacket paqueteSalida;
		// Frist message
		boolean firstMessage = true;
		try {
			socket = new DatagramSocket(6000);
			do {

				// fill the package with the the reciving pacage
				socket.receive(paqueteLegada);
				mensaje = new String(mensaje_bytes_llegada);
				System.out.println(mensaje);
				//Obtain port and Addres
				
				address = paqueteLegada.getAddress();
				if(firstMessage) {
					socket.send(new DatagramPacket(new String("Hola desde el servidor").getBytes(),24, address,6001));
					firstMessage = false;
				}
				// Now send recived package
				mensaje = mensaje.toUpperCase();
				paqueteSalida = new DatagramPacket(mensaje.getBytes(),mensaje.length(),address,6001);
				socket.send(paqueteSalida);
				// Clear buffer
				mensaje_bytes_llegada = new byte[256];
				paqueteLegada = new DatagramPacket(mensaje_bytes_llegada,256);
			} while (true);
		} catch (Exception e) {
			System.err.println(e.getMessage());
			System.exit(1);
		}

	}

}
