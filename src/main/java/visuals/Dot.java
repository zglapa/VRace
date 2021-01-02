package visuals;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class Dot {
    public Circle dot;
    public Dot(double size){
        dot = new Circle(size, Color.BLUE);
        dot.setStroke(Color.BLACK);
        dot.setStrokeWidth(1f);
        setAction();
    }
    private void setAction(){
        dot.setOnMouseEntered(new EventHandler< MouseEvent >(){

            @Override
            public void handle(MouseEvent mouseEvent) {
                dot.setStroke(Color.RED);
                dot.setStrokeWidth(2f);
            }
        });
        dot.setOnMouseExited(new EventHandler< MouseEvent >(){

            @Override
            public void handle(MouseEvent mouseEvent) {
                dot.setStroke(Color.BLACK);
                dot.setStrokeWidth(1f);
            }
        });
        dot.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(dot.getFill().equals(Color.BLUE)){
                    changeColor(Color.LIGHTGREEN);
                }
                else{
                    changeColor(Color.BLUE);
                }
            }
        });
    }
    public void changeColor(Paint paint) {
        dot.setFill(paint);
    }
    public void changeBorder(Paint paint) {
        dot.setStroke(paint);
    }
}
