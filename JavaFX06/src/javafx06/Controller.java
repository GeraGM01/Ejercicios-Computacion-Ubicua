
package javafx06;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.shape.Circle;

public class Controller{
    @FXML
    private TextField txt;
    @FXML
    private TextArea txtArea;
    
    
    public void enviarMsj(ActionEvent e)
    {
        //Obtengo el texto que escribio el usuario en el textField
        
        int puerto = 5000;
        String texto = txt.getText();
        System.out.println(texto);

        Cliente clienteTask = new Cliente(puerto, texto);
        Thread hilo = new Thread(clienteTask);
        hilo.start();
        
    }
    
    public void imprimirMsj(String mensaje)
    {
        txtArea.setText(mensaje);
    }
   
}
