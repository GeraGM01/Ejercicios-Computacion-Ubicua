package sockets2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainCliente extends Thread {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("PROGRAMA CLIENTE INICIADO..");

        Socket cliente = null;
        //Se crea el flujo de entrada al servidor
        DataInputStream flujoEntrada = null;

        //Se crea el flujo de salida al servidor
        DataOutputStream flujoSalida = null;
        String respuesta = "";
        String letra;

        try {
            cliente = new Socket("localhost", 6000);

            flujoSalida = new DataOutputStream(cliente.getOutputStream());
            flujoEntrada = new DataInputStream(cliente.getInputStream());

            while (respuesta.contains("Game over") == false && respuesta.contains("Haz acertado la palabra") == false) //El servidor me devuelve esos mensajes
            {
                //Envio un saludo al servidor
                System.out.println("Dame la palabra");
                //leo la letra
                letra = sc.nextLine();
                //se la mando al servidor y calcula si la acepta o no la acepta
                flujoSalida.writeUTF(letra);
                //me devuelve una respuesta 
                respuesta = flujoEntrada.readUTF();
                //El servidor envia el mensaje de recibido y muestro la respuesta
                System.out.println("Reibiendo del servidor: \n\t" + respuesta);

            }

        } catch (IOException ex) {
            Logger.getLogger(MainCliente.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Cerrar sockets
        try {
            flujoEntrada.close();
            flujoSalida.close();
            cliente.close();
        } catch (IOException io) {
            io.printStackTrace();
        }

    }
}
