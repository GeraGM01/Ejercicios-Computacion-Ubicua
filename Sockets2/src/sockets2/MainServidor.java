package sockets2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainServidor {

    public static void main(String[] args) {

        int puertoServidor = 6000;
        ArrayList<Thread> hilosServidor = new ArrayList();
        ServerSocket socketServidor;
        int numCliente = 0;
        try {
            //Punto de conexion del servidor(Socket en el puerto indicado en la maquina donde se ejecute el proceso)
            socketServidor = new ServerSocket(puertoServidor);  //Socket unico; OJO LO QUE CREEO SON MULTIPLES CLIENTES QUE SE CONECTAN AL SERVIDOR

            //Mensaje de arranque de la aplicacion
            System.out.println("SERVIDOR CONCURRENTE");
            System.out.println("--------------------");
            System.out.println("Servidor Iniciado");
            //System.out.println("Escuchando por el puerto %d.\n", socketServidor.getLocalPort());
            System.out.println("Esperando conexion con el cliente");

            while (numCliente < 50) {
                //quedamos a la espera(Escuchando) de que se realice una conexion con el socket del servido.
                //En el momento en que eso suceda se aceptara. mientras tanto, la ejecucion queda bloqueada
                //en espera a que se reciba esa peticion por parte de un cliente
                Socket clientSocket = socketServidor.accept(); //Acepto la conexion de cliente y creo un nuevo clienteSOcket

                //Interaccion del servidor con un cliente
                System.out.println("Conexion establecida con un cliente");  //Esto es para debbugiar

                //Creamos un nuevo hilo de ejecucion para servir a este nuevo cliente conectado
                Servidor hiloServidor = new Servidor(clientSocket, numCliente + 1); //Este es un objeto de tipo servidor y tambien de tipo Thread, le paso el cliente u el numero de cliente
                hilosServidor.add(hiloServidor); //lo agrego a la lista de servidores concurrentes
                numCliente++;

                //Lanzamos la ejecucion de ese nuevo hilo
                hiloServidor.start();

            }
            //Cuando se hayan aceptado el maximo de clientes cierro el socket
            socketServidor.close();

        } catch (IOException ex) {
            Logger.getLogger(MainServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("FIn de la ejecucion del servidor");
    }
}
