package visuals;

import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.util.Pair;
import logic.Sizes;

import java.util.ArrayList;

public class Track {
    public ArrayList<Pair<Shape, TrackElement>> innerElements;
    public ArrayList<Pair<Shape, TrackElement>> outerElements;
    public Line finish;
    public Line checkpoint;
    public FinishLineDirection direction;
    private final double trackWidth;
    private double finishStartY;
    private double finishEndY;
    private double checkpointStartY;
    private double checkpointEndY;
    public Track(TrackStyle style, double width, double height, double dotDiff, Dot finishDot){
        innerElements = new ArrayList<>();
        outerElements = new ArrayList<>();
        trackWidth = Sizes.getTRACKWIDTH();
        drawSquare(width, height);
        drawFinishLine(finishDot);
        setCheckpoint(finishDot);

    }
    private void drawSquare(double width, double height){

        double endXInner = width - 3.5 * (width+1)/20;
        double endYInner = height - 3.5 * (height+1)/20;
        double endYOuter = height - (height+1)/20;
        double endXOuter = width - (width+1)/20;
        double startXInner = 3.5*(width+1)/20;
        double startYInner = 3.5*(height+1)/20;
        double startXOuter = (width+1)/20;
        double startYOuter = (height+1)/20;
        finishStartY = endYInner;
        finishEndY = endYOuter;
        checkpointStartY = startYOuter;
        checkpointEndY = startYInner;
        Line l1 = new Line(startXInner, startYInner, endXInner, startYInner);
        Line l2 = new Line(endXInner, startYInner, endXInner, endYInner);
        Line l3 = new Line(endXInner, endYInner, startXInner, endYInner);
        Line l4 = new Line(startXInner, endYInner, startXInner, startYInner);
        innerElements.add(new Pair<>(l1, TrackElement.LINE));
        innerElements.add(new Pair<>(l2, TrackElement.LINE));
        innerElements.add(new Pair<>(l3, TrackElement.LINE));
        innerElements.add(new Pair<>(l4, TrackElement.LINE));
        Line l5 = new Line(startXOuter, startYOuter, endXOuter, startYOuter);
        Line l6 = new Line(endXOuter, startYOuter, endXOuter, endYOuter);
        Line l7 = new Line(endXOuter, endYOuter, startXOuter, endYOuter);
        Line l8 = new Line(startXOuter, endYOuter, startXOuter, startYOuter);
        outerElements.add(new Pair<>(l5, TrackElement.LINE));
        outerElements.add(new Pair<>(l6, TrackElement.LINE));
        outerElements.add(new Pair<>(l7, TrackElement.LINE));
        outerElements.add(new Pair<>(l8, TrackElement.LINE));
        for(Pair<Shape, TrackElement> p : innerElements){
            if(p.getValue() == TrackElement.LINE){
                Line l = (Line)p.getKey();
                l.setStrokeWidth(trackWidth);
                l.setStroke(Color.WHITE);
            }
        }
        for(Pair<Shape, TrackElement> p : outerElements){
            if(p.getValue() == TrackElement.LINE){
                Line l = (Line)p.getKey();
                l.setStrokeWidth(trackWidth);
                l.setStroke(Color.WHITE);
            }
        }
    }
    private void drawFinishLine(Dot finishDot){
        this.direction = FinishLineDirection.RIGHT;
        this.finish = new Line();
        this.finish.setStroke(Color.WHITE);
        this.finish.setStartX(finishDot.getCenterX());
        this.finish.setStartY(finishStartY);
        this.finish.setEndX(finishDot.getCenterX());
        this.finish.setEndY(finishEndY);
        this.finish.setStrokeWidth(trackWidth);
    }
    private void setCheckpoint(Dot finishDot){
        this.checkpoint = new Line(finishDot.getCenterX(), checkpointStartY, finishDot.getCenterX(), checkpointEndY);
    }
}
