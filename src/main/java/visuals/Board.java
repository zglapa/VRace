package visuals;

import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class Board {
    public Pane backboard = new Pane();
    public ArrayList<Dot> dots = new ArrayList<>();
    public Board(int rows, int columns, int size){
        backboard.setPrefWidth(50 * columns);
        backboard.setPrefHeight(50 * rows);
        for(int i = 0; i < rows; ++i){
            for(int j = 0; j < columns; ++j){
                Dot dot = new Dot(5f);
                dot.dot.relocate(50 * i, 50 * j);
                dots.add(dot);
                backboard.getChildren().add(dot.dot);
            }
        }

    }
}
