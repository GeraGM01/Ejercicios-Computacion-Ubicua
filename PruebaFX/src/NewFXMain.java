/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Gera
 */
public class NewFXMain extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception{
        //StackPane root = new StackPane();
        StackPane root = FXMLLoader.load(getClass().getResource("/sample.fxml"));
        primaryStage.setTitle("Hello World!");
        Scene scene = new Scene(root, 300, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
        
        //root.getChildren().add(btn);
        
        //Scene scene = new Scene(root, 300, 250);
        
        //primaryStage.setTitle("Hello World!");
        //primaryStage.setScene(scene);
        //primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
