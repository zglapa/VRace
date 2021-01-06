package visuals;

import handlers.DotHandler;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class Dot {
    public Circle dot;
    public int x;
    public int y;
    private Boolean clicked;
    private MouseEvent onEnter;
    public Dot(double size, int x, int y){
        this.x = x;
        this.y = y;
        dot = new Circle(size, Color.BLUE);
        dot.setStroke(Color.BLACK);
        dot.setStrokeWidth(0.125 * size);
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
