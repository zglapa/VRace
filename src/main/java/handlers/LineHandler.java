package handlers;

import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import javafx.util.Pair;
import boards.Board;
import visuals.TrackElement;
import visuals.Vector;

import java.awt.geom.Line2D;

public class LineHandler {
    public static Board board;
    public static boolean intersects(Vector vector, String line){
        return lineIntersection(vector, line);
    }
    private static boolean lineIntersection(Vector vector, String line){
        Line2D.Double vecGeom = new Line2D.Double(vector.line.getStartX(), vector.line.getStartY(), vector.line.getEndX(), vector.line.getEndY());
        Pair<Shape, TrackElement> p;
        if(line.equalsIgnoreCase("FINISH")){
            p = new Pair<>(board.track.finish, TrackElement.LINE);
        }
        else if(line.equalsIgnoreCase("CHECKPOINT")){
            p = new Pair<>(board.track.checkpoint, TrackElement.LINE);
        }
        else{
            return false;
        }
        return checkIntersection(vecGeom, p);
    }

    private static boolean checkIntersection(Line2D.Double vecGeom, Pair<Shape, TrackElement> p) {
        if(p.getValue() == TrackElement.LINE){
            Line l = (Line)(p.getKey());
            Line2D.Double trackLine = new Line2D.Double(l.getStartX(), l.getStartY(), l.getEndX(), l.getEndY());
            return trackLine.intersectsLine(vecGeom);
        }
        return false;
    }

}
