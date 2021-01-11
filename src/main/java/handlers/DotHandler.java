package handlers;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import logic.Player;
import sample.Game;
import visuals.Board;
import visuals.Dot;
import visuals.Vector;

public class DotHandler {
    public static Board backboard;

    public static void clickDot(Dot dot){
        Player currentPlayer = Game.currentPlayer;
        if(!dot.ifClicked()){
            dot.click();
            dot.changeColor(Color.LIGHTGREEN);
            backboard.dotBoard.clickedDots.add(dot);
            if(backboard.dotBoard.clickedDots.size() == 1){
                Game.selectDot(dot);
            }
            if(backboard.dotBoard.clickedDots.size() == 2){
                Vector v = drawLine(currentPlayer.getColor());
                Game.makeMove(dot, v.outOfBounds);
            }
        }
        else{
            dot.click();
            dot.changeColor(Color.BLUE);
            if(backboard.dotBoard.clickedDots.size() == 1){
                Dot d = backboard.dotBoard.clickedDots.remove();
                if(d != dot){
                    backboard.dotBoard.clickedDots.add(d);
                }
            }
        }
    }
    public static void enterDot(Dot dot){
        Player currentPlayer = Game.currentPlayer;
        dot.dot.setStroke(Color.RED);
        dot.dot.setStrokeWidth(2f);
        backboard.dotBoard.hoveredDot = dot;
        if(backboard.dotBoard.clickedDots.size() == 1){
            Dot d1 = backboard.dotBoard.clickedDots.peek();
            Dot d2 = backboard.dotBoard.hoveredDot;
            backboard.hoverLine = drawDashedLine(d1, d2, currentPlayer.getColor());
        }
    }
    public static void exitDot(Dot dot){
        dot.dot.setStroke(Color.BLACK);
        dot.dot.setStrokeWidth(1f);
        backboard.dotBoard.hoveredDot = null;
        if(backboard.hoverLine != null){
            backboard.vectorboard.getChildren().remove(backboard.hoverLine.line);
            backboard.hoverLine = null;
        }
    }
    public static Vector drawLine(Paint paint){
        Dot d1 = backboard.dotBoard.clickedDots.remove();
        Dot d2 = backboard.dotBoard.clickedDots.remove();
        clickDot(d1);
        clickDot(d2);
        Vector vector = new Vector(d1,d2, paint, false);
        backboard.vectorboard.getChildren().add(vector.line);
        return vector;
    }
    public static Vector drawDashedLine(Dot d1, Dot d2,Paint paint){
        Vector vector = new Vector(d1, d2, paint, true);
        backboard.vectorboard.getChildren().add(vector.line);
        return vector;
    }
}
