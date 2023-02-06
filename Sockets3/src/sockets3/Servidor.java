/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sockets3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gera
 */
public class Servidor extends Thread {

    private Socket socketCliente;

    //Flujo de salida a traves del cual enviaremos informacion al proceso del cliente
    //conectamos a traves del socket
    private DataOutputStream flujoEscrituraCliente;

    //Flujo de entrada a traves del cual recibiremos informacion desde el proceso del cliente
    DataInputStream flujoEntradaCliente;

    private int numCliente;
  
    private boolean continua;

    public Servidor(Socket socketCliente, int numCliente) throws IOException {
        this.socketCliente = socketCliente;

        //Flujo de salida a traves del cual enviaremos informacion al proceso del cliente 
        //conectado a traves del socket
        this.flujoEscrituraCliente = new DataOutputStream(this.socketCliente.getOutputStream());

        //flujo de entrada a traves del cual escribiremos la informacion desde el proceso del cliente
        this.flujoEntradaCliente = new DataInputStream(this.socketCliente.getInputStream());

        this.numCliente = numCliente;
        continua = true;
        
        
    }

    @Override
    public void run() {
        String resultado, peticionCliente = null, respuesta;

        System.out.println("Iniciado en el hilo del servidor ");
        try {
            while (continua == true) {
                peticionCliente = flujoEntradaCliente.readUTF();
                if (!peticionCliente.isEmpty()) {
                    System.out.println("Recibiendo del cliente: \n\t" + peticionCliente);

                    respuesta = calcularRespuesta(peticionCliente);
                    flujoEscrituraCliente.writeUTF(respuesta);
                }
            }

            //cerramos la comunicacion con el cliente
            flujoEscrituraCliente.close();
            flujoEscrituraCliente.close();
            this.socketCliente.close();
        } catch (IOException ex) {
            Logger.getLogger(MainServidor.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("Fin de la conexion con el cliente");
    }

    private String calcularRespuesta(String peticionCliente) {
        String letra = peticionCliente;
        String respuesta = "";
        
        while(true)
        {
            respuesta = letra;
            return respuesta;
            
        }
    }
}
