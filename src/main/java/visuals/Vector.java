package visuals;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;

public class Vector {
    public Line line;
    public Dot beg, end;
    public Paint paint;
    public Vector(Dot beg, Dot end, Paint paint,Boolean dashed){
        this.beg = beg;
        this.end = end;
        this.paint = paint;
        drawLine(dashed);
    }
    private void drawLine(Boolean dashed){
        line = new Line();
        line.setStartX(this.beg.dot.getCenterX());
        line.setStartY(this.beg.dot.getCenterY());
        line.setEndX(this.end.dot.getCenterX());
        line.setEndY(this.end.dot.getCenterY());
        line.setStrokeWidth(5);
        if(dashed){
            line.setStrokeLineCap(StrokeLineCap.ROUND);
            line.getStrokeDashArray().addAll(10d, 10d);
        }
        line.setStroke(this.paint);
    }

}
