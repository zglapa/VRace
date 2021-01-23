package sample;

import handlers.DotHandler;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.util.Pair;
import logic.Move;
import logic.Paints;
import logic.Player;
import visuals.Board;
import visuals.Dot;
import visuals.PlayerColor;
import visuals.Vector;

import java.io.*;
import java.util.*;

public class Game {
    static Board board;
    static Queue<Dot> enabledDots = new LinkedList<>();
    static HashMap<Player, ArrayList<Move>> gameHistory = new HashMap<>();
    public static ArrayList<Player> players = new ArrayList<>();
    public static ArrayList<Player> finishedPlayers = new ArrayList<>();
    public static HashMap<Player, Dot> playersDots = new HashMap<>();
    public static Player currentPlayer;
    static int currentPlayerIndex;
    static int numberOfPlayers;
    static int numberOfFinishedPlayers;
    public static void makeMove(Dot dot, Vector v) {
        playersDots.get(currentPlayer).setTaken(false,currentPlayer);
        playersDots.replace(currentPlayer, dot);
        playersDots.get(currentPlayer).setTaken(true,currentPlayer);
        Move lastMove = gameHistory.get(currentPlayer).get(gameHistory.get(currentPlayer).size()-1);
        Move move = new Move(lastMove);
        move.makeMove(dot);
        gameHistory.get(currentPlayer).add(move);
        dot.setDisable(false);
        currentPlayer.increaseNumberOfMoves();
        if(v.ifCheckpoint()){
            currentPlayer.setCheckpoint();
            System.out.println(currentPlayerIndex + " checkpoint");
        }
        if(v.outOfBounds){
            currentPlayer.setFinished();
        }
        if(v.ifFinished() && currentPlayer.ifCheckpoint()){
            System.out.println(currentPlayerIndex + " finished");
            currentPlayer.setFinished();
        }
        changePlayer();
    }
    private static void changePlayer(){
        currentPlayerIndex = (currentPlayerIndex+1)%players.size();
        currentPlayer = players.get(currentPlayerIndex);
        int i = 0;
        while(currentPlayer.isFinished() && i < numberOfPlayers + 1){
            currentPlayerIndex = (currentPlayerIndex+1)%players.size();
            currentPlayer = players.get(currentPlayerIndex);
            i++;
        }
        if(i>=numberOfPlayers) {
            System.out.println("Disabling all");
            board.dotBoard.setDisable(true);
            finishTheGame();
        }
        if(playersDots.containsKey(currentPlayer)) {
            DotHandler.clickDot(playersDots.get(currentPlayer));
        }
        else {
            setDots();
        }
    }
    private static Move makeZeroMove(Player player, Dot dot){
        Move move = new Move(dot);
        move.makeMove(dot);
        playersDots.put(player, dot);
        gameHistory.get(currentPlayer).add(move);
        return move;
    }
    private static void setZeroDots(Move move, Dot dot){
        int centerDotX = dot.x + move.x;
        int centerDotY = dot.y + move.y;
        for (int j = -1; j <= 1; ++j) {
            if(centerDotY + j >= 0 && centerDotY + j <= board.columns){
                Dot d = null;
                switch (board.track.direction){
                    case UP:
                        d = board.dotBoard.dots.get(centerDotX - 1).get(centerDotY + j);
                        break;
                    case DOWN:
                        d = board.dotBoard.dots.get(centerDotX + 1).get(centerDotY + j);
                        break;
                    case LEFT:
                        d = board.dotBoard.dots.get(centerDotX + j).get(centerDotY - 1);
                        break;
                    case RIGHT:
                        d = board.dotBoard.dots.get(centerDotX + j).get(centerDotY + 1);
                        break;
                    default:
                        break;
                }
                assert d != null;
                d.setDisable(false);
                enabledDots.add(d);
            }
        }
    }
    public static void selectDot(Dot dot){
        disableDots();
        dot.changeBorder(Color.WHITE);
        // Player's first selected dot
        if(gameHistory.get(currentPlayer).isEmpty()){
            dot.setTaken(true, currentPlayer);
            Move move = makeZeroMove(currentPlayer, dot);
            setZeroDots(move, dot);
        }
        // Player's next selected dot
        else{
            playersDots.replace(currentPlayer, dot);
            changeDots(gameHistory.get(currentPlayer).get(gameHistory.get(currentPlayer).size()-1), dot);
        }
    }
    public static void start(int players, ArrayList<String> playerNames){
        numberOfPlayers = players;
        numberOfFinishedPlayers = 0;
        currentPlayer = setPlayers(numberOfPlayers, playerNames);
        setDots();
    }
    public static void disableDots(){
        while(!enabledDots.isEmpty()){
            Dot dot = enabledDots.remove();
            dot.setDisable(true);
        }
    }
    public static void setDots(){
        Queue<Dot> exceptions = new LinkedList<>(board.finishDots);
        ArrayList<ArrayList<Dot>> dots = board.dotBoard.dots;
        for(ArrayList<Dot> row : dots){
            for(Dot dot : row){
                if(!exceptions.isEmpty() && dot == exceptions.peek()){
                    dot.setDisable(false);
                    enabledDots.add(dot);
                    exceptions.remove();
                }
                else{
                    dot.setDisable(true);
                }
            }
        }
    }
    public static void changeDots(Move move, Dot dot){
        int centerDotX = dot.x+ move.x;
        int centerDotY = dot.y + move.y;
        for(int i = -1; i <= 1; ++i){
            if(centerDotX + i >= 0 && centerDotX + i <= board.rows) {
                for (int j = -1; j <= 1; ++j) {
                    if(centerDotY + j >= 0 && centerDotY + j <= board.columns){
                        Dot d = board.dotBoard.dots.get(centerDotX + i).get(centerDotY + j);
                        if(!d.ifTaken()){
                            d.setDisable(false);
                        }

                        enabledDots.add(d);
                    }
                }
            }
        }
    }
    public static Player setPlayers(int numberOfPlayers, ArrayList<String> playerNames) {
        Player firstPlayer = null;
        for (int i = 0; i < numberOfPlayers; ++i) {
            Player player = new Player(i, Paints.get(i), playerNames.get(i));
            if (firstPlayer == null) {
                firstPlayer = player;
            }
            players.add(player);
            gameHistory.put(player, new ArrayList<>());
        }
        return firstPlayer;
    }
    private static void finishTheGame(){
        ArrayList<Pair<String, Integer>> score;
        try(
                FileInputStream file = new FileInputStream("src/main/resources/highscore.out");
                ObjectInputStream in = new ObjectInputStream(file);
        ){
            score = (ArrayList<Pair<String, Integer>>) in.readObject();
        }catch(IOException | ClassNotFoundException e){
            e.printStackTrace();
            score = new ArrayList<>();
        }
        for(Player player : players){
            for(int i = 0; i < score.size();++i){
                if(score.get(i).getValue() == null || player.getNumberOfMoves() < score.get(i).getValue()){
                    score.add(i, new Pair<>(player.getName(), player.getNumberOfMoves()));
                    break;
                }
            }
        }
        while(score.size() > 10){
            score.remove(score.size()-1);
        }
        try(
                FileOutputStream file = new FileOutputStream("src/main/resources/highscore.out");
                ObjectOutputStream out = new ObjectOutputStream(file);
        ){
            out.writeObject(score);
        }catch(IOException e){
            e.printStackTrace();
        }

    }
}
