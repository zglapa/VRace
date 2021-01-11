package visuals;

import handlers.DotHandler;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.util.Pair;

import java.util.ArrayList;

public class Track {
    public ArrayList<Pair<Shape, TrackElement>> innerElements;
    public ArrayList<Pair<Shape, TrackElement>> outerElements;
    public Line finish;
    public FinishLineDirection direction;
    private final double trackWidth = 2f;
    private double finishStartY;
    private double finishEndY;
    public Track(TrackStyle style, double width, double height, double dotDiff, Dot finishDot){
        innerElements = new ArrayList<>();
        outerElements = new ArrayList<>();
        drawSquare(width, height);
//        drawOval(width, height);
        drawFinishLine(finishDot);

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
            }
        }
        for(Pair<Shape, TrackElement> p : outerElements){
            if(p.getValue() == TrackElement.LINE){
                Line l = (Line)p.getKey();
                l.setStrokeWidth(trackWidth);
            }
        }
    }
//    private void drawOval(double width, double height){
//        double endXInner = width - 3.5 * (width+1)/20;
//        double endYInner = height - 3.5 * (height+1)/20;
//        double endYOuter = height - (height+1)/20;
//        double endXOuter = width - (width+1)/20;
//        double startXInner = 3.5*(width+1)/20;
//        double startYInner = 3.5*(height+1)/20;
//        double startXOuter = (width+1)/20;
//        double startYOuter = (height+1)/20;
//        finishStartY = endYInner;
//        finishEndY = endYOuter;
//        Line l1 = new Line(startXInner, startYInner, endXInner - 3.5 * (width+1)/20, startYInner);
//        Line l2 = new Line(endXInner, startYInner + 3.5*(width+1)/20, endXInner, endYInner);
//        Line l3 = new Line(endXInner, endYInner, startXInner, endYInner);
//        Line l4 = new Line(startXInner, endYInner, startXInner, startYInner);
//        innerElements.add(new Pair<>(l1, TrackElement.LINE));
//        innerElements.add(new Pair<>(l2, TrackElement.LINE));
//        innerElements.add(new Pair<>(l3, TrackElement.LINE));
//        innerElements.add(new Pair<>(l4, TrackElement.LINE));
//        Line l5 = new Line(startXOuter, startYOuter, endXOuter, startYOuter);
//        Line l6 = new Line(endXOuter, startYOuter, endXOuter, endYOuter);
//        Line l7 = new Line(endXOuter, endYOuter, startXOuter, endYOuter);
//        Line l8 = new Line(startXOuter, endYOuter, startXOuter, startYOuter);
//        outerElements.add(new Pair<>(l5, TrackElement.LINE));
//        outerElements.add(new Pair<>(l6, TrackElement.LINE));
//        outerElements.add(new Pair<>(l7, TrackElement.LINE));
//        outerElements.add(new Pair<>(l8, TrackElement.LINE));
//        for(Pair<Shape, TrackElement> p : innerElements){
//            if(p.getValue() == TrackElement.LINE){
//                Line l = (Line)p.getKey();
//                l.setStrokeWidth(trackWidth);
//            }
//            if(p.getValue() == TrackElement.ARC){
//                Arc a = (Arc)p.getKey();
//                a.setStrokeWidth(trackWidth);
//            }
//        }
//        for(Pair<Shape, TrackElement> p : outerElements){
//            if(p.getValue() == TrackElement.LINE){
//                Line l = (Line)p.getKey();
//                l.setStrokeWidth(trackWidth);
//            }
//        }
//    }
    private void drawFinishLine(Dot finishDot){
        this.direction = FinishLineDirection.RIGHT;
        this.finish = new Line();
        System.out.println(finishStartY + " " + finishEndY);
        this.finish.setStartX(finishDot.dot.getCenterX());
        this.finish.setStartY(finishStartY);
        this.finish.setEndX(finishDot.dot.getCenterX());
        this.finish.setEndY(finishEndY);
        this.finish.setStrokeWidth(trackWidth);
    }
}
