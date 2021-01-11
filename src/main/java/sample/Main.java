package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import visuals.Board;

public class Main extends Application {
    public final int HEIGHT = 900 ;
    public final int WIDTH = 1600;
    @Override
    public void start(Stage primaryStage){
        primaryStage.setTitle("Hello World");
        Board board = new Board(50,50, this.HEIGHT*this.WIDTH);
        Game.board = board;
        Game.start(4);
        FlowPane backgroundPane = new FlowPane();
        backgroundPane.setMinHeight(this.HEIGHT);
        backgroundPane.setMinWidth(this.WIDTH);
        backgroundPane.getChildren().add(board.backboard);
        backgroundPane.setAlignment(Pos.CENTER);
        Group root = new Group(backgroundPane);
        primaryStage.setScene(new Scene(root, this.WIDTH, this.HEIGHT));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
