package visuals;

import handlers.DotHandler;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class Dot {
    public Circle dot;
    private Boolean clicked;
    private MouseEvent onEnter;
    public Dot(double size){
        dot = new Circle(size, Color.BLUE);
        dot.setStroke(Color.BLACK);
        dot.setStrokeWidth(1f);
        this.clicked = false;
        setAction();
    }
    private void setAction(){
        dot.setOnMouseEntered(mouseEvent -> DotHandler.enterDot(this));
        dot.setOnMouseExited(mouseEvent -> DotHandler.exitDot(this));
        dot.setOnMouseClicked(mouseEvent -> {
            DotHandler.clickDot(this);
        });
    }
    public boolean ifClicked() {
        return this.clicked;
    }
    public void click(){
        this.clicked = !this.clicked;
    }

    public void changeColor(Paint paint) {
        dot.setFill(paint);
    }
    public void changeBorder(Paint paint) {
        dot.setStroke(paint);
    }

}
