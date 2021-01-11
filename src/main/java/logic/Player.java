package logic;

import javafx.scene.paint.Paint;

public class Player {
    private final int index;
    private final Paint color;
    private int numberOfMoves;
    public Player(int i, Paint p){
        this.index = i;
        this.color = p;
        numberOfMoves = 0;
    }
    public void increaseNumberOfMoves(){
        numberOfMoves++;
    }
    public int getIndex(){
        return index;
    }
    public Paint getColor(){
        return color;
    }

}
