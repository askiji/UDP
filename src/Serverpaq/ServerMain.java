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
		try {
			socket = new DatagramSocket(6000);
			
			do {
				// fill the package with the the reciving pacage
				socket.receive(paqueteLegada);
				System.out.println("Recivo el paquete");
				mensaje = new String(mensaje_bytes_llegada);
				System.out.println(mensaje);
				//Obtain port and Addres
				
				address = paqueteLegada.getAddress();
				// Now we send what we have recived
				System.out.println("Envio el mensaje");
				mensaje = mensaje.toUpperCase();
				paqueteSalida = new DatagramPacket(mensaje.getBytes(),mensaje.length(),address,6001);
				System.out.println("point a");
				socket.send(paqueteSalida);
				System.out.println("point b");
//				mensaje_bytes_llegada = new byte[256];
//				socket.close();
			} while (true);
		} catch (Exception e) {
			System.err.println(e.getMessage());
			System.exit(1);
		}

	}

}
