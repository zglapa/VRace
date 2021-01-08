package handlers;

import javafx.geometry.Bounds;
import javafx.scene.shape.Line;
import javafx.scene.shape.PathElement;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.util.Pair;
import visuals.Board;
import visuals.Vector;


public class BoundHandler {
    public static Board board;
    public static boolean intersects(Vector vector){
        Bounds bInner = board.track.inner.getLayoutBounds();
        Bounds bOuter = board.track.outer.getLayoutBounds();
        Bounds vectorBounds = vector.line.getLayoutBounds();
//        System.out.println(bInner);
//        System.out.println(vectorBounds);
        if(bOuter.contains(vectorBounds) && !bInner.intersects(vectorBounds)){
            return false;
        }
        return preciseIntersects(vector);
    }
    private static boolean preciseIntersects(Vector vector){
        Pair<Double, Double> fun;
        double lengthX;
        double lengthY;
        try{
            fun = func(vector.line);
            lengthX = vector.line.getEndX() - vector.line.getStartX();
            lengthY = vector.line.getEndY() - vector.line.getStartY();
        }catch (StraightLineException e){
            lengthY = vector.line.getEndY() - vector.line.getStartY();
            double chunk = lengthY/1000;
            for(int i = 0; i < 1000; ++i){
                double y = i*chunk + vector.line.getStartY();
                double x = vector.line.getStartX();
                if(board.track.inner.contains(x,y) || !board.track.outer.contains(x,y)){
                    System.out.println(x + " " + y);
                    if((x > board.track.startXInner && x < board.track.startXInner + board.track.innerLength) && (y > board.track.startYInner && y < board.track.startYInner+board.track.innerHeight)){
                        return true;
                    }
                }
            }
            return false;
        }

        if(Math.abs(lengthX) > Math.abs(lengthY)){
            double chunk = lengthX/1000;
            for(int i = 0; i < 1000; ++i){
                double x = i*chunk + vector.line.getStartX();
                double y = fun.getKey()*x + fun.getValue();
                if(board.track.inner.contains(x,y) || !board.track.outer.contains(x,y)){
                    System.out.println(x + " " + y);
                    if((x > board.track.startXInner && x < board.track.startXInner + board.track.innerLength) && (y > board.track.startYInner && y < board.track.startYInner+board.track.innerHeight)){
                        return true;
                    }
                }
            }
        }
        else{
            double chunk = lengthY/1000;
            for(int i = 0; i < 1000; ++i){
                double y = i*chunk + vector.line.getStartY();
                double x = (y-fun.getValue())/fun.getKey();
                if(board.track.inner.contains(x,y) || !board.track.outer.contains(x,y)){
                    System.out.println(x + " " + y);
                    if((x > board.track.startXInner && x < board.track.startXInner + board.track.innerLength) && (y > board.track.startYInner && y < board.track.startYInner+board.track.innerHeight)){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    private static Pair<Double, Double> func(Line line) throws StraightLineException {
        if(line.getStartX() == line.getEndX()){
            throw new StraightLineException();
        }
        double a = (line.getEndY()-line.getStartY())/(line.getEndX()-line.getStartX());
        double b = line.getEndY() - a * line.getEndX();
        return new Pair<>(a, b);
    }
    private static class StraightLineException extends Exception{}
}
