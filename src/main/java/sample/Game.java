package sample;

import logic.Move;
import visuals.Board;
import visuals.Dot;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Game {
    static Board board;
    static Queue<Dot> enabledDots = new LinkedList<>();
    static ArrayList<Move> gameHistory = new ArrayList<>();
    public static void makeMove(Dot dot) {
        Move move = new Move(gameHistory.get(gameHistory.size()-1));
        move.makeMove(dot);
        gameHistory.add(move);
        dot.dot.setDisable(false);
    }
    public static void nextMove(Dot dot){
        disableDots();
        if(gameHistory.isEmpty()){
            Move move = new Move(dot);
            move.makeMove(dot);
            gameHistory.add(move);
            changeDots(move, dot);
        }else{
            changeDots(gameHistory.get(gameHistory.size()-1), dot);
        }
    }
    public static void start(){
        setDots();
    }
    public static void disableDots(){
        while(!enabledDots.isEmpty()){
            Dot dot = enabledDots.remove();
            dot.dot.setDisable(true);
        }
    }
    public static void setDots(){
        Queue<Dot> exceptions = new LinkedList<>(board.finishDots);
        ArrayList<ArrayList<Dot>> dots = board.dotBoard.dots;
        for(ArrayList<Dot> row : dots){
            for(Dot dot : row){
                if(!exceptions.isEmpty() && dot == exceptions.peek()){
                    dot.dot.setDisable(false);
                    enabledDots.add(dot);
                    exceptions.remove();
                }
                else{
                    dot.dot.setDisable(true);
                }
            }
        }
    }
    public static void changeDots(Move move, Dot dot){
        int centerDotX = dot.x+ move.x;
        int centerDotY = dot.y + move.y;
        System.out.println("center " + centerDotX + " " + centerDotY);
        for(int i = -1; i <= 1; ++i){
            if(centerDotX + i >= 0 && centerDotX + i <= board.rows) {
                for (int j = -1; j <= 1; ++j) {
                    if(centerDotY + j >= 0 && centerDotY + j <= board.columns){
                        System.out.println("enabled " + (centerDotX + i) + " " + (centerDotY + j));
                        Dot d = board.dotBoard.dots.get(centerDotX + i).get(centerDotY + j);
                        d.dot.setDisable(false);
                        enabledDots.add(d);
                    }
                }
            }
        }
    }
}
