package logic;

import javafx.scene.paint.Paint;

public class Player {
    private final int index;
    private final Paint color;
    private int numberOfMoves;
    private int penalty;
    private boolean checkpoint;
    private boolean finished;
    public Player(int i, Paint p){
        this.index = i;
        this.color = p;
        numberOfMoves = 0;
        checkpoint = false;
        finished = false;
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
    public int getNumberOfMoves(){return numberOfMoves;}
    public boolean ifCheckpoint(){
        return checkpoint;
    }
    public void setCheckpoint(){
        checkpoint = true;
    }
    public boolean isFinished(){
        return finished;
    }
    public void setFinished(){
        finished = true;
    }
}
