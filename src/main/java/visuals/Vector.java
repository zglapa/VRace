package visuals;

import handlers.BoundHandler;
import handlers.LineHandler;
import javafx.animation.FillTransition;
import javafx.scene.effect.Effect;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;
import javafx.util.Duration;
import logic.Sizes;

public class Vector {
    public Line line;
    public Dot beg, end;
    public Paint paint;
    public boolean outOfBounds;
    private boolean finished;
    private boolean checkpoint;
    public Vector(Dot beg, Dot end, Paint paint,Boolean dashed){
        this.beg = beg;
        this.end = end;
        this.paint = paint;
        outOfBounds = false;
        finished = false;
        checkpoint = false;
        drawLine(dashed);
    }
    private void drawLine(Boolean dashed){
        line = new Line();
        line.setStartX(this.beg.getCenterX());
        line.setStartY(this.beg.getCenterY());
        line.setEndX(this.end.getCenterX());
        line.setEndY(this.end.getCenterY());
        line.setStrokeWidth(Sizes.getVECTORWIDTH());
        if(dashed){
            line.setStrokeLineCap(StrokeLineCap.ROUND);
            line.getStrokeDashArray().addAll(2d, 5d);
        }
        line.setStroke(this.paint);
        if(BoundHandler.intersects(this)){
            outOfBounds = true;
            line.setStroke(Color.RED);
        }
        if(LineHandler.intersects(this, "FINISH")){
            finished = true;
        }
        if(LineHandler.intersects(this, "CHECKPOINT")){
            checkpoint = true;
        }
    }
    public boolean ifFinished(){
        return finished;
    }
    public boolean ifCheckpoint(){
        return checkpoint;
    }
}
