package visuals;

import handlers.BoundHandler;
import handlers.DotHandler;
import handlers.LineHandler;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Shape;
import javafx.util.Pair;
import logic.Sizes;

import java.util.ArrayList;

public class Board {
    public StackPane backboard= new StackPane();
    public HighScoreBoard highScoreBoard;
    public DotBoard dotBoard;
    public Pane vectorboard = new Pane();
    public Vector hoverLine;
    public Track track;
    public ArrayList<Dot> finishDots = new ArrayList<>();
    public int rows;
    public int columns;
    public Board(int rows, int columns, int size, HighScoreBoard nb ){
        this.highScoreBoard = nb;
        this.rows = rows;
        this.columns = columns;
        setdotboard(rows, columns);
        setvectorboard(rows, columns);
        backboard.setPrefHeight(Sizes.getBOARDINDENT() * rows);
        backboard.setPrefWidth(Sizes.getBOARDINDENT() * columns);
        backboard.getChildren().add(vectorboard);
//        vectorboard.setVisible(false);
        backboard.getChildren().add(dotBoard);
        DotHandler.backboard = this;
        BoundHandler.board = this;
        LineHandler.board = this;
        setTrack(rows, columns);
    }
    private void setdotboard(int rows, int columns){
        dotBoard = new DotBoard(rows, columns, (int) Sizes.getBOARDINDENT());
        for(int i = 0; i <= rows; ++i){
            finishDots.add(dotBoard.dots.get(i).get(columns/2));
        }
        for(Dot d: finishDots){
            System.out.println(d.getCenterX() + " " + d.getCenterY());
        }
    }
    private void setvectorboard(int rows, int columns){
        vectorboard.setPrefHeight(Sizes.getBOARDINDENT()*rows);
        vectorboard.setPrefWidth(Sizes.getBOARDINDENT()*columns);
    }
    private void setTrack(int rows, int columns){
        this.track = new Track(TrackStyle.SQUARE, (double)columns*Sizes.getBOARDINDENT(), (double)rows*Sizes.getBOARDINDENT(), Sizes.getBOARDINDENT(), finishDots.get(0));
        for(Pair<Shape, TrackElement> p : this.track.innerElements){
            vectorboard.getChildren().add(p.getKey());
        }
        for(Pair<Shape, TrackElement> p : this.track.outerElements){
            vectorboard.getChildren().add(p.getKey());
        }
        vectorboard.getChildren().add(this.track.finish);
        finishDots.removeIf(dot -> dot.getCenterY() < this.track.finish.getStartY() || dot.getCenterY() > this.track.finish.getEndY());
    }



}
