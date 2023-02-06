/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sockets2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Random;
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

    private String[] posiblesCadenas = {"Futbol", "Mesa", "Invitacion", "Clasificacion", "Esternomastoideo"};

    private String incognita;
    private String progreso;
    private int numFallos;
    private boolean acertado;

    public Servidor(Socket socketCliente, int numCliente) throws IOException {
        this.socketCliente = socketCliente;

        //Flujo de salida a traves del cual enviaremos informacion al proceso del cliente 
        //conectado a traves del socket
        this.flujoEscrituraCliente = new DataOutputStream(this.socketCliente.getOutputStream());

        //flujo de entrada a traves del cual escribiremos la informacion desde el proceso del cliente
        this.flujoEntradaCliente = new DataInputStream(this.socketCliente.getInputStream());

        this.numCliente = numCliente;

        Random rd = new Random();
        int n = rd.nextInt(5);
        incognita = posiblesCadenas[n];
        progreso = "" + incognita.charAt(0);
        for (int i = 1; i < incognita.length(); i++) {
            progreso = progreso + "-";
        }

        System.out.println("------->" + incognita);
        System.out.println("------->" + progreso);
        acertado = false;
        numFallos = 0;
    }

    @Override
    public void run() {
        String resultado, peticionCliente = null, respuesta;

        System.out.println("Iniciado juego en el hilo del servidor ");
        try {
            while (numFallos < 5 && acertado == false) {
                peticionCliente = flujoEntradaCliente.readUTF();
                if (!peticionCliente.isEmpty()) {
                    System.out.println("REcibiendo del cliente: \n\t" + peticionCliente);

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
        char letra = peticionCliente.charAt(0);
        boolean encontrado = false;

        String respuesta = "";
        for (int i = 0; i < this.incognita.length(); i++) {
            if (incognita.charAt(i) == letra) {
                progreso = progreso.substring(0, i) + letra + progreso.substring(i + 1);
                encontrado = true;
            }
        }

        if (encontrado) {
            if (incognita.equals(progreso) == true) {
                respuesta = "Has acertado la palabra. Fin del programa.-" + progreso;
                acertado = true;
            } else {
                respuesta = "Letra correcta.-" + progreso;
            }
        } else {
            numFallos++;
            if (numFallos < 5) {
                respuesta = "Letra incorrecta.-" + progreso;
            } else {
                respuesta = "Game over.-";
            }
        }
        return respuesta;
    }
}
