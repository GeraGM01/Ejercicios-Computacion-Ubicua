package javafx06;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Servidor extends Observable implements Runnable {

    private int puerto;
    private StringProperty mensajeRecibido = new SimpleStringProperty();

    public Servidor(int puerto) {
        this.puerto = puerto;
    }

    public final String getMensajeRecibido() {
        return mensajeRecibido.get();
    }

    public final void setMensajeRecibido(String value) {
        mensajeRecibido.set(value);
    }

    public StringProperty mensajeRecibidoProperty() {
        return mensajeRecibido;
    }

    @Override
    public void run() {

        ServerSocket servidor = null;
        Socket sc = null;
        DataInputStream in;

        try {
            //Creamos el socket del servidor
            servidor = new ServerSocket(puerto);
            System.out.println("Servidor iniciado");

            //Siempre estara escuchando peticiones
            while (true) {

                //Espero a que un cliente se conecte
                sc = servidor.accept();

                System.out.println("Cliente conectado");
                in = new DataInputStream(sc.getInputStream());

                //Leo el mensaje que me envia
                String mensaje = in.readUTF();

                System.out.println(mensaje);

                //Actualizamos la propiedad en la interfaz de usuario
                Platform.runLater(() -> setMensajeRecibido(mensaje));

                this.setChanged();
                this.notifyObservers(mensaje);
                this.clearChanged();

                //Cierro el socket
                sc.close();
                System.out.println("Cliente desconectado");

            }

        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
