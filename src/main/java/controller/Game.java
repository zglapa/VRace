package controller;

import boards.Board;
import handlers.DotHandler;
import javafx.scene.paint.Color;
import javafx.util.Pair;
import logic.*;
import visuals.*;
import visuals.Vector;

import java.io.*;
import java.util.*;

public class Game {
    static Board board;
    static Queue<Dot> enabledDots;
    static HashMap<Player, ArrayList<Move>> gameHistory;
    public static ArrayList<Player> players;
    public static ArrayList<Player> finishedPlayers;
    public static HashMap<Player, Dot> playersDots;
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
        }
        if(v.outOfBounds){
            currentPlayer.setFinished();
            currentPlayer.setOutOfBounds();
        }
        if(v.ifFinished() && currentPlayer.ifCheckpoint()){
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
    public static void start(int playersNum, ArrayList<String> playerNames){
        numberOfPlayers = playersNum;
        numberOfFinishedPlayers = 0;
        enabledDots = new LinkedList<>();
        gameHistory = new HashMap<>();
        players = new ArrayList<>();
        finishedPlayers = new ArrayList<>();
        playersDots = new HashMap<>();
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
    @SuppressWarnings("unchecked")
    private static void finishTheGame(){
        HighScoreList score;
        ArrayList<Pair<String, Integer>> list;
        try(
                FileInputStream file = new FileInputStream("src/main/resources/highscore.out");
                ObjectInputStream in = new ObjectInputStream(file)
        ){

            score = (HighScoreList) in.readObject();
        }catch(IOException | ClassNotFoundException e){
            e.printStackTrace();
            score = new HighScoreList();
        }
        list = score.get(Sizes.getCOLUMNS());
        for(Player player : players){
            if(player.isOutOfBounds()) continue;

            for(int i = 0; i < list.size();++i){
                if(list.get(i).getValue() == null || player.getNumberOfMoves() < list.get(i).getValue()){
                    list.add(i, new Pair<>(player.getName(), player.getNumberOfMoves()));
                    break;
                }
            }
        }
        while(list.size() > 10){
            list.remove(list.size()-1);
        }
        try(
                FileOutputStream file = new FileOutputStream("src/main/resources/highscore.out");
                ObjectOutputStream out = new ObjectOutputStream(file)
        ){
            out.writeObject(score);
        }catch(IOException e){
            e.printStackTrace();
        }
        board.highScoreBoard.updateHighScore();
        board.finishGameBoard.setVisible(true);
        board.finishGameBoard.finishGame(players);
    }
}
