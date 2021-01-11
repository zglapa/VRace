package handlers;

import javafx.geometry.Bounds;
import javafx.scene.shape.Line;
import javafx.scene.shape.PathElement;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.util.Pair;
import visuals.Board;
import visuals.TrackElement;
import visuals.Vector;
import java.awt.geom.*;

public class BoundHandler {
    public static Board board;
    public static boolean intersects(Vector vector){
        return lineIntersection(vector);
    }
    public static boolean lineIntersection(Vector vector){
        Line2D.Double vecGeom = new Line2D.Double(vector.line.getStartX(), vector.line.getStartY(), vector.line.getEndX(), vector.line.getEndY());
        for(Pair<Shape, TrackElement> p : board.track.innerElements){
            if (checkIntersection(vecGeom, p)) return true;

        }
        for(Pair<Shape, TrackElement> p : board.track.outerElements){
            if (checkIntersection(vecGeom, p)) return true;

        }
        return false;
    }

    private static boolean checkIntersection(Line2D.Double vecGeom, Pair<Shape, TrackElement> p) {
        if(p.getValue() == TrackElement.LINE){
            Line l = (Line)(p.getKey());
            Line2D.Double trackLine = new Line2D.Double(l.getStartX(), l.getStartY(), l.getEndX(), l.getEndY());
            return trackLine.intersectsLine(vecGeom);
        }
        return false;
    }

//    private static Pair<Double, Double> func(Line line) throws StraightLineException {
//        if(line.getStartX() == line.getEndX()){
//            throw new StraightLineException();
//        }
//        double a = (line.getEndY()-line.getStartY())/(line.getEndX()-line.getStartX());
//        double b = line.getEndY() - a * line.getEndX();
//        return new Pair<>(a, b);
//    }
//    private static class StraightLineException extends Exception{}
}
