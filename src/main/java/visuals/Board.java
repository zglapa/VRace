package visuals;

import handlers.BoundHandler;
import handlers.DotHandler;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.util.Pair;

import java.util.ArrayList;

public class Board {
    public StackPane backboard= new StackPane();
    public NotificationBoard notificationBoard;
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
        setNotificationBoard();
        backboard.setPrefHeight(SIZE * rows);
        backboard.setPrefWidth(SIZE * columns);
        backboard.getChildren().add(vectorboard);
//        vectorboard.setVisible(false);
        backboard.getChildren().add(dotBoard.dotboard);
        backboard.getChildren().add(notificationBoard.pane);
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
        this.track = new Track(TrackStyle.SQUARE, (double)columns*SIZE, (double)rows*SIZE, SIZE, finishDots.get(0));
        for(Pair<Shape, TrackElement> p : this.track.innerElements){
            vectorboard.getChildren().add(p.getKey());
        }
        for(Pair<Shape, TrackElement> p : this.track.outerElements){
            vectorboard.getChildren().add(p.getKey());
        }
        vectorboard.getChildren().add(this.track.finish);
        finishDots.removeIf(dot -> dot.dot.getCenterY() < this.track.finish.getStartY() || dot.dot.getCenterY() > this.track.finish.getEndY());
    }
    private void setNotificationBoard(){
        notificationBoard = new NotificationBoard(rows, columns, SIZE);

    }


}
