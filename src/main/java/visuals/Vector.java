package visuals;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class Vector {
    public Line line;

    public Vector(Dot beg, Dot end){
        line = new Line();
        line.setStartX(beg.dot.getCenterX());
        line.setStartY(beg.dot.getCenterY());
        line.setEndX(end.dot.getCenterX());
        line.setEndY(end.dot.getCenterY());
//        line.startXProperty().bind(beg.dot.centerXProperty().add(beg.dot.translateXProperty()));
//        line.startYProperty().bind(beg.dot.centerYProperty().add(beg.dot.translateYProperty()));
//        line.endYProperty().bind(end.dot.centerYProperty().add(end.dot.translateYProperty()));
//        line.endXProperty().bind(end.dot.centerXProperty().add(end.dot.translateXProperty()));
        line.setFill(Color.BLACK);
        line.setStroke(Color.BLACK);
        line.setStrokeWidth(10f);
    }

}
