package logic;

import visuals.Dot;

public class Move {
    public Move lastMove;
    public int x;
    public int y;
    public Dot endDot;
    public Dot begDot;
    public Move(Dot dot){
        this.lastMove = null;
        this.begDot = dot;
        this.x = 0;
        this.y = 0;
    }
    public Move(Move lastMove){
        this.lastMove = lastMove;
        this.begDot = lastMove.endDot;
    }
    public void makeMove(Dot dot){
        this.endDot = dot;
        this.x = endDot.x - begDot.x;
        this.y = endDot.y - begDot.y;
        System.out.println("move " + this.x + " " + this.y);

    }
}
