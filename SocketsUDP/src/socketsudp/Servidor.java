/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package socketsudp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gera
 */
public class Servidor {

    public static void main(String[] args) {

        final int PUERTO = 5000;
        byte[] buffer = new byte[1024];

        try {
            System.out.println("Iniciado el servidor UDP");
            DatagramSocket socketUDP = new DatagramSocket(PUERTO);
            while (true) {
                //Creamos la peticion
                DatagramPacket peticion = new DatagramPacket(buffer, buffer.length);

                //El usuario se conecta con nosotros con esta peticion
                socketUDP.receive(peticion);
                System.out.println("Recibo la informacion del cliente");

                //el mensaje que nos ha dicho el cliente lo vamos a guardar
                String mensaje = new String(peticion.getData());
                System.out.println(mensaje);

                //Ahora obtenemos el puerto del cliente que esta en el paquete de la peticion
                int puertoCliente = peticion.getPort();
                InetAddress direccion = peticion.getAddress();

                mensaje = "Hola mundo desde el servidor";
                buffer = new byte[1024];
                buffer = mensaje.getBytes();

                //Creamos otro paquete con la respuesta del servidor al cliente
                DatagramPacket respuesta = new DatagramPacket(buffer, buffer.length, direccion, puertoCliente);

                System.out.println("Envio la informacion al cliente");
                //ENviamos la respuesta
                socketUDP.send(respuesta);
            }

        } catch (SocketException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);

        }

    }
}
