package visuals;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.util.Pair;
import logic.HighScoreList;
import logic.Sizes;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;


public class HighScoreBoard extends StackPane{

    private final double HEIGHT, WIDTH;
    private final Rectangle backgroundRectangle;
    private final Label highScore;
    public HighScoreBoard(double HEIGHT, double WIDTH, int size){
        super();
        this.HEIGHT = HEIGHT;
        this.WIDTH = WIDTH;
        this.setPrefHeight(HEIGHT);
        this.setPrefWidth(WIDTH);
        this.backgroundRectangle = setBackground();
        this.highScore = setHighScore(size);
        this.getChildren().addAll(backgroundRectangle, highScore);
        this.setAlignment(Pos.CENTER);
    }

    private Rectangle setBackground(){
        Rectangle rectangle = new Rectangle();
        rectangle.setHeight(HEIGHT);
        rectangle.setWidth(WIDTH);
        rectangle.setArcHeight(WIDTH/3);
        rectangle.setArcWidth(WIDTH/3);
        rectangle.setOpacity(0.2);
        rectangle.setFill(Color.WHITE);
        rectangle.setStroke(Color.WHITE);
        rectangle.setStrokeWidth(3f);
        return rectangle;
    }
    public void updateHighScore(){
        HighScoreList score;
        try(
                FileInputStream file = new FileInputStream("src/main/resources/highscore.out");
                ObjectInputStream in = new ObjectInputStream(file)
        ){
            score = (HighScoreList) in.readObject();
        }catch(IOException | ClassNotFoundException e){
            e.printStackTrace();
            return;
        }
        ArrayList<Pair<String, Integer>> list = score.get(Sizes.getCOLUMNS());
        StringBuilder stringBuilder = new StringBuilder();

        for(int i = 0; i < list.size(); ++i){
            if(list.get(i).getValue() == null){
                stringBuilder.append(i+1).append(". ").append(list.get(i).getKey()).append("\n");
            }
            else stringBuilder.append(i+1).append(". ").append(list.get(i).getKey()).append(" -> ").append(list.get(i).getValue()).append("\n");
        }
        highScore.setText(stringBuilder.toString());

    }
    public void changeHighScore(int size){
        HighScoreList score;
        try(
                FileInputStream file = new FileInputStream("src/main/resources/highscore.out");
                ObjectInputStream in = new ObjectInputStream(file)
        ){
            score = (HighScoreList) in.readObject();
        }catch(IOException | ClassNotFoundException e){
            e.printStackTrace();
            return;
        }
        try {
            StringBuilder stringBuilder = new StringBuilder();
            ArrayList<Pair<String, Integer>> list = score.get(size);
            for (int i = 0; i < list.size(); ++i) {
                if (list.get(i).getValue() == null) {
                    stringBuilder.append(i + 1).append(". ").append(list.get(i).getKey()).append("\n");
                } else
                    stringBuilder.append(i + 1).append(". ").append(list.get(i).getKey()).append(" -> ").append(list.get(i).getValue()).append("\n");
            }
            highScore.setText(stringBuilder.toString());
        }catch (IndexOutOfBoundsException ignored){}
    }
    @SuppressWarnings("unchecked")
    private Label setHighScore(int size){
        HighScoreList score;
        try(
                FileInputStream file = new FileInputStream("src/main/resources/highscore.out");
                ObjectInputStream in = new ObjectInputStream(file)
                ){
            score = (HighScoreList) in.readObject();
        }catch(IOException | ClassNotFoundException e){
            e.printStackTrace();
            return  new Label("");
        }

        Label highscore = new Label();
        highscore.setId("highscore");
        highscore.setPrefWidth(7f/8* WIDTH);
        highscore.setMaxWidth(7f/8 * WIDTH);
        StringBuilder stringBuilder = new StringBuilder();
        ArrayList<Pair<String, Integer>> list = score.get(size);
        for(int i = 0; i < list.size(); ++i){
            if(list.get(i).getValue() == null){
                stringBuilder.append(i+1).append(". ").append(list.get(i).getKey()).append("\n");
            }
            else stringBuilder.append(i+1).append(". ").append(list.get(i).getKey()).append(" -> ").append(list.get(i).getValue()).append("\n");
        }
        highscore.setText(stringBuilder.toString());
        highscore.setTextFill(Color.BLACK);
        return highscore;
    }
}
