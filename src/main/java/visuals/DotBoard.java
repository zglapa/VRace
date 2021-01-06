package visuals;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class DotBoard {
    public Pane dotboard = new Pane();
    public ArrayList<ArrayList<Dot>> dots = new ArrayList<>();
    public Queue<Dot> clickedDots = new LinkedList<>();
    public Dot hoveredDot;
    public DotBoard(int rows, int columns){
        dotboard.setPrefWidth(50 * columns);
        dotboard.setPrefHeight(50 * rows);
        for(int i = 0; i < rows; ++i){
            dots.add(new ArrayList<Dot>());
            for(int j = 0; j < columns; ++j){
                Dot dot = new Dot(5f);
                dot.dot.setCenterY(50 * i);
                dot.dot.setCenterX(50 * j);
                dots.get(i).add(dot);
                dotboard.getChildren().add(dot.dot);
            }
        }
    }

}
