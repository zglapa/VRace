package handlers;

import javafx.geometry.Bounds;
import javafx.scene.shape.Shape;
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
        return !bOuter.contains(vectorBounds) || (bInner.intersects(vectorBounds));
    }
}
