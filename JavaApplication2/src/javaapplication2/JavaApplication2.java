package javaapplication2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCombination;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class JavaApplication2 extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Group root = new Group();
        Image icon = new Image("icono.png");  //Ruta del png del icono
        Scene scene = new Scene(root, Color.BLACK);
        stage.setTitle("Computacion Ubicua");  //Agregamos nombre a la ventana
        stage.getIcons().add(icon); //Seleccionamos un icono definido por nosotros para la ventana
        //Definimos el tamaño del icono
        stage.setWidth(640);
        stage.setHeight(340);
        stage.setResizable(false);  //No permite modificar el tamaño de la ventana
        stage.setX(50);
        stage.setY(50);
        stage.setFullScreen(true);
        stage.setFullScreenExitHint("Press Q to exit full screen");
        stage.setScene(scene);
        stage.show();  //Muestra los objetos que estan en la escena del escenario
    }

    public static void main(String[] args) {
        launch(args);
    }

}