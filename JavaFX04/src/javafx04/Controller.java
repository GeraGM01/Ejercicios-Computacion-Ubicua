
package javafx04;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.shape.Circle;

public class Controller {
    @FXML
    
    private Circle myCircle;
    private double x, y;
    
    public void up(ActionEvent e)
    {
        myCircle.setCenterY(y-= 5);
    }
    
    public void down(ActionEvent e)
    {
         myCircle.setCenterY(y+= 5);
    }
    
    public void right(ActionEvent e)
    {
         myCircle.setCenterX(x+= 5);
    }
    
    public void left(ActionEvent e)
    {
         myCircle.setCenterX(x-= 5);
    }
}
