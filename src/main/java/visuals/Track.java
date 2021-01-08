package visuals;

import handlers.DotHandler;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;

public class Track {
    public Path inner;
    public Path outer;
    public Line finish;
    public double innerLength;
    public double innerHeight;
    public double outerHeight;
    public double outerLength;
    public double startXInner;
    public double startYInner;
    public double startXOuter;
    public double startYOuter;
    public Track(TrackStyle style, double width, double height, double dotDiff, Dot finishDot){
        drawOval(width, height);
        drawFinishLine(finishDot);
    }
    private void drawOval(double width, double height){
        innerLength = width - 7 * (width+1)/20;
        innerHeight = height - 7 * (height+1)/20;
        outerHeight = height - 2 * (height+1)/20;
        outerLength = width - 2 * (width+1)/20;
        startXInner = 3.5*(width+1)/20;
        startYInner = 3.5*(height+1)/20;
        startXOuter = (width+1)/20;
        startYOuter = (height+1)/20;
        this.inner = new Path(new MoveTo(startXInner, startYInner),
                new LineTo(startXInner + innerLength, startYInner),
                new LineTo(startXInner + innerLength, startYInner + innerHeight),
                new LineTo(startXInner, startYInner + innerHeight),
                new LineTo(startXInner, startYInner));
        this.inner.setStrokeWidth(2f);
        this.outer = new Path(new MoveTo(startXOuter, startYOuter),
                new LineTo(startXOuter + outerLength, startYOuter),
                new LineTo(startXOuter + outerLength, startYOuter + outerHeight),
                new LineTo(startXOuter, startYOuter + outerHeight),
                new LineTo(startXOuter, startYOuter));
        this.outer.setStrokeWidth(2f);
    }
    private void drawFinishLine(Dot finishDot){
        this.finish = new Line();
        this.finish.setStartX(finishDot.dot.getCenterX());
        this.finish.setStartY(startYInner + innerHeight);
        this.finish.setEndX(finishDot.dot.getCenterX());
        this.finish.setEndY(startYOuter + outerHeight);
        this.finish.setStrokeWidth(2f);
    }
}
