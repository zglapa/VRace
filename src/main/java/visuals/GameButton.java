package visuals;

import javafx.scene.control.Button;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class GameButton extends Button {
    public GameButton(double width, double height, String text, int size){
        super();
        this.setPrefWidth(width);
        this.setPrefHeight(height);
        this.setText(text);
        this.setFont(Font.font("msbm10", size));
        this.setShape(buttonShape(width,height));
        this.setOpacity(0.8);
    }
    public GameButton(double width, double height, String text){
        super();
        this.setPrefWidth(width);
        this.setPrefHeight(height);
        this.setText(text);
        this.setFont(Font.font("msbm10", 40));
        this.setShape(buttonShape(width,height));
        this.setOpacity(0.8);
    }
    private Rectangle buttonShape(double width, double height ){
        Rectangle rectangle = new Rectangle(width,height);
        rectangle.setArcHeight(2*height/3f);
        rectangle.setArcWidth(2*height/3f);
        return rectangle;
    }
}
