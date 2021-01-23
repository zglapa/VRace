package visuals;

import handlers.DotHandler;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import logic.Player;
import logic.Sizes;

public class Dot{
    public int x;
    public int y;
    private Boolean clicked;
    private MouseEvent onEnter;
    private Boolean taken;
    private final Circle visibleDot;
    private final Circle clickableDot;
    public Dot(int x, int y){
        visibleDot = new Circle(Sizes.getSMALLDOTSIZE(), Color.LIGHTCYAN);
        clickableDot = new Circle(Sizes.getBIGDOTSIZE());
        this.x = x;
        this.y = y;
        visibleDot.setStroke(Color.WHITE);
        visibleDot.setStrokeWidth(0.125 * Sizes.getBIGDOTSIZE());
        this.clicked = false;
        this.taken = false;
        clickableDot.setOpacity(0);
        setAction();
    }
    private void setAction(){
        this.clickableDot.setOnMouseEntered(mouseEvent -> DotHandler.enterDot(this));
        this.clickableDot.setOnMouseExited(mouseEvent -> DotHandler.exitDot(this));
        this.clickableDot.setOnMouseClicked(mouseEvent -> {
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
        visibleDot.setFill(paint);
    }
    public void changeBorder(Paint paint) {
        if(paint != null){
            clickableDot.setOpacity(1);
            clickableDot.setStrokeWidth(Sizes.getSTROKEWIDTH());
            clickableDot.setStroke(paint);
        }
        else if(!ifTaken()){
            clickableDot.setStrokeWidth(0);
            clickableDot.setOpacity(0);
        }
    }
    public Circle getVisibleDot(){return visibleDot; }
    public Circle getClickableDot(){return clickableDot;}
    public void setCoordinates(double X, double Y){
        this.clickableDot.setCenterX(X);
        this.clickableDot.setCenterY(Y);
        visibleDot.setCenterX(X);
        visibleDot.setCenterY(Y);
    }
    public boolean ifTaken(){return this.taken;}
    public void setTaken(boolean taken, Player player){
        this.taken = taken;
        if(this.taken){
            this.clickableDot.setFill(player.getColor());
            this.clickableDot.setOpacity(1);
            this.clickableDot.setStroke(player.getColor());
        }
        else{
            this.clickableDot.setFill(Color.GRAY);
            this.clickableDot.setOpacity(0);
        }
    }
    public double getCenterX(){
        return clickableDot.getCenterX();
    }
    public double getCenterY(){
        return clickableDot.getCenterY();
    }
    public void setDisable(boolean disable){
        clickableDot.setDisable(disable);
    }

}
