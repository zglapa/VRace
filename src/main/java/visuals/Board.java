package visuals;

import handlers.DotHandler;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Board {
    public StackPane backboard= new StackPane();
    public DotBoard dotBoard;
    public Pane vectorboard = new Pane();
    public Vector hoverLine;
    public Track track;
    public Board(int rows, int columns, int size){
        setdotboard(rows, columns);
        setvectorboard(rows, columns);
        backboard.setPrefHeight(50 * rows);
        backboard.setPrefWidth(50 * columns);
        backboard.getChildren().add(vectorboard);
//        vectorboard.setVisible(false);
        backboard.getChildren().add(dotBoard.dotboard);
        DotHandler.backboard = this;
        this.track = new Track(TrackStyle.OVAL, (double)columns*49, (double)rows*49);
        vectorboard.getChildren().add(this.track.path);
    }
    private void setdotboard(int rows, int columns){
        dotBoard = new DotBoard(rows, columns);

    }
    private void setvectorboard(int rows, int columns){
        vectorboard.setPrefHeight(50*rows);
        vectorboard.setPrefWidth(50*columns);
    }

}
