package visuals;

import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;

public class Track {
    Path path;
    public Track(TrackStyle style, double width, double height){
        drawOval(width, height);
    }
    private void drawOval(double width, double height){
        this.path = new Path(new MoveTo(3 * (width+1)/20, 3* (height+1)/20),
                new LineTo(width - 3 * (width+1)/20, 3* (height+1)/20),
                new LineTo(width - 3 * (width+1)/20, height - 3* (height+1)/20),
                new LineTo(3 * (width+1)/20, height - 3* (height+1)/20),
                new LineTo(3 * (width+1)/20, 3* (height+1)/20));
    }
}
