package visuals;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


public class NotificationBoard {
    public StackPane pane = new StackPane();
    private final int rows, columns, size;
    public NotificationBoard(int rows, int columns, int size){
        pane.setVisible(false);
        pane.setPrefHeight(size*rows);
        pane.setPrefWidth(size*columns);
        this.rows = rows;
        this.columns = columns;
        this.size = size;
    }
    public void addNotification(String text){
        Rectangle rectangle = new Rectangle();
        rectangle.setX(3*(double)columns/8 * size);
        rectangle.setY(3*(double)rows/8 * size);
        rectangle.setHeight(5*size*(double)rows/8);
        rectangle.setWidth(5*size*(double)columns/8);
        rectangle.setFill(Color.GRAY);
        rectangle.setOpacity(0.7);
        Label label = new Label(text);
        label.setFont(Font.font("Purisa", FontWeight.BOLD, 40));
        pane.getChildren().addAll(rectangle, label);
        pane.setVisible(true);
    }
}
