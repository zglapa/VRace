package visuals;
import javafx.geometry.Pos;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class DotBoard extends StackPane{
    public ArrayList<ArrayList<Dot>> dots = new ArrayList<>();
    public Queue<Dot> clickedDots = new LinkedList<>();
    public Dot hoveredDot;
    public Pane visiblePane;
    public Pane clickablePane;
    public DotBoard(int rows, int columns, int size){
        visiblePane = new Pane();
        clickablePane = new Pane();
        this.setPrefWidth(size * columns);
        this.setPrefHeight(size * rows);
        visiblePane.setPrefWidth(size * columns);
        visiblePane.setPrefHeight(size * rows);
        clickablePane.setPrefWidth(size * columns);
        clickablePane.setPrefHeight(size * rows);
        for(int i = 0; i <= rows; ++i){
            dots.add(new ArrayList<>());
            for(int j = 0; j <= columns; ++j){
                Dot dot = new Dot(i ,j);
                dot.setCoordinates(size * j, size * i);
                dots.get(i).add(dot);
                clickablePane.getChildren().add(dot.getClickableDot());
                visiblePane.getChildren().add(dot.getVisibleDot());
            }
        }
        this.getChildren().add(visiblePane);
        this.getChildren().add(clickablePane);
        this.setAlignment(Pos.CENTER);

    }

}
