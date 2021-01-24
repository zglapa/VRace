package visuals;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class PlayerNameField extends StackPane {
    private final TextField nameField;
    private final Rectangle colorField;
    private final Label numberField;
    private final HBox hBox;
    public PlayerNameField(Paint paint, int index, int WIDTH, int HEIGHT){
        super();
        this.setPrefHeight(HEIGHT);
        this.setPrefWidth(WIDTH);
        hBox = new HBox();
        hBox.setSpacing(10);
        hBox.setAlignment(Pos.CENTER);
        Rectangle background = new Rectangle(WIDTH, HEIGHT);
        background.setFill(Color.WHITE);
        background.setOpacity(0.8);
        background.setArcWidth(HEIGHT/3);
        background.setArcHeight(HEIGHT/3);
        nameField = new TextField("Player " + index);
        nameField.setId("nameField");
        colorField = new Rectangle(HEIGHT*7f/8,HEIGHT*7f/8);
        colorField.setFill(paint);
        numberField = new Label("#" + index);
        numberField.setId("numberField");
        hBox.getChildren().addAll(numberField, colorField, nameField);
        this.getChildren().addAll(background, hBox);
    }
    public String getName(){
        return nameField.getText();
    }

}
