package visuals;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import logic.Player;

import java.util.ArrayList;


public class FinishGameBoard extends StackPane {
    private VBox vBox;
    public FinishGameBoard(double width, double height, double x, double y){
        super();
        this.setPrefHeight(height);
        this.setPrefWidth(width);
        this.setLayoutX(x);
        this.setLayoutY(y);
        this.setVisible(false);
        setBackground(width,height);
        setvBox(width,height);

    }
    private void setBackground(double width, double height){
        Rectangle rectangle = new Rectangle();
        rectangle.setHeight(height);
        rectangle.setWidth(width);
        rectangle.setArcWidth(width/20f);
        rectangle.setArcHeight(width/20f);
        rectangle.setFill(Color.WHITE);
        rectangle.setOpacity(0.8);
        this.getChildren().add(rectangle);
    }
    private void setvBox(double width, double height){
        VBox vBox = new VBox();
        vBox.setPrefHeight(height*7f/8);
        vBox.setPrefWidth(width*3f/4);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(30);
        this.vBox = vBox;
        this.getChildren().add(vBox);
    }
    private Label addHeader(String text, Paint fill){
        Label label = new Label(text);
        label.setId("announcement");
        label.setPadding(new Insets(0,0,20,0));
        label.setTextFill(fill);
        return label;
    }
    private Label addScore(String text){
        Label label = new Label(text);
        label.setId("score");
        return label;
    }
    public void finishGame(ArrayList<Player> players){
        players.sort(new Player.PlayerSort());
        for(Player player : players){
            System.out.println(player.getName() + " " + player.getNumberOfMoves());
        }
        vBox.getChildren().add(addHeader(players.get(0).getName() + " won!", players.get(0).getColor()));
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < players.size(); ++i){
            if(players.get(i).isOutOfBounds()){
                sb.append(i+1).append(". ").append(players.get(i).getName()).append(" -> crashed(").append(players.get(i).getNumberOfMoves()).append(")").append("\n");
            }
            else
            sb.append(i+1).append(". ").append(players.get(i).getName()).append(" -> ").append(players.get(i).getNumberOfMoves()).append("\n");
        }
        vBox.getChildren().add(addScore(sb.toString()));
    }
}
