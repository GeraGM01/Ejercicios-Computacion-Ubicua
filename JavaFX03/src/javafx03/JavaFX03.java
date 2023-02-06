package javafx03;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCombination;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import static javafx.scene.text.Font.font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class JavaFX03 extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        //Group root = new Group();
        Parent root;
        root = FXMLLoader.load(getClass().getResource("menu.fxml"));
        //creamos la escena
        Scene scene = new Scene(root);
        /*
        Text text = new Text();
        text.setText("Java FX");
        text.setX(50);
        text.setY(50);
        text.setFont(Font.font("Verdana", 50));
        text.setFill(Color.GREEN);
        
        root.getChildren().add(text);
        */
        /*
        Line line = new Line();
        line.setStartX(200);
        line.setStartY(200);
        line.setEndX(500);
        line.setEndY(200);
        line.setStrokeWidth(5);
        line.setStroke(Color.RED);
        line.setOpacity(0.5);
        line.setRotate(45);
        root.getChildren().add(line);*/
                
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}