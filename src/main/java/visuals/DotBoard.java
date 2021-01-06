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
    public DotBoard(int rows, int columns, int size){
        dotboard.setPrefWidth(size * columns);
        dotboard.setPrefHeight(size * rows);
        for(int i = 0; i <= rows; ++i){
            dots.add(new ArrayList<Dot>());
            for(int j = 0; j <= columns; ++j){
                Dot dot = new Dot(3f, i ,j);
                dot.dot.setCenterY(size * i);
                dot.dot.setCenterX(size * j);
                dots.get(i).add(dot);
                dotboard.getChildren().add(dot.dot);
            }
        }
    }

}
