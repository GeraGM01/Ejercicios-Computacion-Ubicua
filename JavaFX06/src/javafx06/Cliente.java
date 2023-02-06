package javafx06;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Platform;
import javafx.concurrent.Task;

public class Cliente extends Task<Void> {

    private int puerto;
    private String mensaje;

    public Cliente(int puerto, String mensaje) {
        this.puerto = puerto;
        this.mensaje = mensaje;
    }

    @Override
    protected Void call() throws Exception {
        // Host del servidor
        final String HOST = "127.0.0.1";
        // Puerto del servidor
        DataOutputStream out;

        try {
            // Creo el socket para conectarme con el servidor
            Socket sc = new Socket(HOST, puerto);

            out = new DataOutputStream(sc.getOutputStream());

            // Envio un mensaje al servidor
            out.writeUTF(mensaje);

            sc.close();

        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
