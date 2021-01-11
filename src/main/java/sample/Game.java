package sample;

import handlers.DotHandler;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import logic.Move;
import logic.Player;
import visuals.Board;
import visuals.Dot;

import java.util.*;

public class Game {
    static ArrayList<Paint> colors = new ArrayList<>(Arrays.asList(Color.BLUE, Color.GREEN, Color.PURPLE, Color.ORANGE));
    static Board board;
    static Queue<Dot> enabledDots = new LinkedList<>();
    static HashMap<Player, ArrayList<Move>> gameHistory = new HashMap<>();
    public static ArrayList<Player> players = new ArrayList<>();
    public static HashMap<Player, Dot> playersDots = new HashMap<>();
    public static Player currentPlayer;
    static int currentPlayerIndex;
    static int numberOfPlayers;
    public static void makeMove(Dot dot, boolean outOfBounds) {
        playersDots.replace(currentPlayer, dot);
        Move lastMove = gameHistory.get(currentPlayer).get(gameHistory.get(currentPlayer).size()-1);
        Move move = new Move(lastMove);
        move.makeMove(dot);
        gameHistory.get(currentPlayer).add(move);
        dot.dot.setDisable(false);
        if(outOfBounds){
            board.notificationBoard.addNotification("Out of bounds!");
        }
        changePlayer();
    }
    private static void changePlayer(){
        currentPlayerIndex = (currentPlayerIndex + 1) % numberOfPlayers;
        currentPlayer = players.get(currentPlayerIndex);
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
                d.dot.setDisable(false);
                enabledDots.add(d);
            }
        }
    }
    public static void selectDot(Dot dot){
        disableDots();
        // Player's first selected dot
        if(gameHistory.get(currentPlayer).isEmpty()){
            Move move = makeZeroMove(currentPlayer, dot);
            setZeroDots(move, dot);
        }
        // Player's next selected dot
        else{
            playersDots.replace(currentPlayer, dot);
            changeDots(gameHistory.get(currentPlayer).get(gameHistory.get(currentPlayer).size()-1), dot);
        }
    }
    public static void start(int players){
        numberOfPlayers = players;
        currentPlayer = setPlayers(numberOfPlayers);
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
        for(int i = -1; i <= 1; ++i){
            if(centerDotX + i >= 0 && centerDotX + i <= board.rows) {
                for (int j = -1; j <= 1; ++j) {
                    if(centerDotY + j >= 0 && centerDotY + j <= board.columns){
                        Dot d = board.dotBoard.dots.get(centerDotX + i).get(centerDotY + j);
                        d.dot.setDisable(false);
                        enabledDots.add(d);
                    }
                }
            }
        }
    }
    public static Player setPlayers(int numberOfPlayers){
        Player firstPlayer = null;
        for(int i = 0; i < numberOfPlayers; ++i){
            Player player = new Player(i, colors.get(i%colors.size()));
            if(firstPlayer == null){
                firstPlayer = player;
            }
            players.add(player);
            gameHistory.put(player, new ArrayList<>());
        }
        return firstPlayer;
    }
}
