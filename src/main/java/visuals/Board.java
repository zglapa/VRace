package visuals;

import handlers.BoundHandler;
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
    public ArrayList<Dot> finishDots = new ArrayList<>();
    public int rows;
    public int columns;
    public final int SIZE = 17;
    public Board(int rows, int columns, int size){
        this.rows = rows;
        this.columns = columns;
        setdotboard(rows, columns);
        setvectorboard(rows, columns);
        backboard.setPrefHeight(SIZE * rows);
        backboard.setPrefWidth(SIZE * columns);
        backboard.getChildren().add(vectorboard);
//        vectorboard.setVisible(false);
        backboard.getChildren().add(dotBoard.dotboard);
        DotHandler.backboard = this;
        BoundHandler.board = this;
        setTrack(rows, columns);
    }
    private void setdotboard(int rows, int columns){
        dotBoard = new DotBoard(rows, columns, SIZE);
        for(int i = 0; i <= rows; ++i){
            finishDots.add(dotBoard.dots.get(i).get(columns/2));
        }
    }
    private void setvectorboard(int rows, int columns){
        vectorboard.setPrefHeight(SIZE*rows);
        vectorboard.setPrefWidth(SIZE*columns);
    }
    private void setTrack(int rows, int columns){
        this.track = new Track(TrackStyle.OVAL, (double)columns*SIZE, (double)rows*SIZE, SIZE, finishDots.get(0));
        vectorboard.getChildren().addAll(this.track.inner, this.track.outer, this.track.finish);
        finishDots.removeIf(dot -> dot.dot.getCenterY() < this.track.finish.getStartY() || dot.dot.getCenterY() > this.track.finish.getEndY());
    }


}
