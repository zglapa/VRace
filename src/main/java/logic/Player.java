package logic;

import javafx.scene.paint.Paint;

import java.util.Comparator;

public class Player {
    private final int index;
    private final Paint color;
    private int numberOfMoves;
    private boolean checkpoint;
    private boolean finished;
    private boolean outOfBounds;
    private final String name;
    public Player(int i, Paint p, String name){
        this.index = i;
        this.color = p;
        numberOfMoves = 0;
        checkpoint = false;
        finished = false;
        this.name = name;
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
    public String getName(){
        return name;
    }

    public boolean isOutOfBounds() {
        return outOfBounds;
    }

    public void setOutOfBounds() {
        this.outOfBounds = true;
    }

    public static class PlayerSort implements Comparator<Player>{
        public int compare(Player a, Player b){
            if(!a.isOutOfBounds() && !b.isOutOfBounds()){
                return Integer.compare(a.getNumberOfMoves(), b.getNumberOfMoves());
            }
            else if(!a.isOutOfBounds()){
                return -1;
            }
            else if(!b.isOutOfBounds()){
                return 1;
            }
            else{
                return Integer.compare(b.getNumberOfMoves(), a.getNumberOfMoves());
            }

        }
    }
}

