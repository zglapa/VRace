package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import logic.Sizes;
import visuals.Board;
import visuals.MenuPane;

public class Main extends Application {
    public final int HEIGHT = 900;
    public final int WIDTH = 900;
    public final int COLUMNS = 30;
    public final int ROWS = 30;
    private MenuPane menuPane;
    @Override
    public void start(Stage primaryStage){
        primaryStage.setTitle("VRace");
        Sizes.set(HEIGHT,WIDTH,ROWS, COLUMNS);
        Group root = mainMenu(primaryStage);
        primaryStage.setScene(new Scene(root, this.WIDTH, this.HEIGHT));
        primaryStage.show();
    }

    public Group mainMenu(Stage stage){
        FlowPane backgroundPane = new FlowPane();
        backgroundPane.setMinHeight(this.HEIGHT);
        backgroundPane.setMinWidth(this.WIDTH);
        backgroundPane.setAlignment(Pos.CENTER);
        this.menuPane = new MenuPane(this.HEIGHT, this.WIDTH, stage);
        backgroundPane.getChildren().add(this.menuPane);
        return new Group(backgroundPane);
    }
    public static void game(Stage primaryStage){
        Board board = new Board(Sizes.getCOLUMNS(),Sizes.getROWS(), Sizes.getHEIGHT()*Sizes.getWIDTH());
        Game.board = board;
        Game.start(2);
        FlowPane backgroundPane = new FlowPane();
        backgroundPane.setMinHeight(Sizes.getHEIGHT());
        backgroundPane.setMinWidth(Sizes.getWIDTH());
        backgroundPane.getChildren().add(board.backboard);
        backgroundPane.setAlignment(Pos.CENTER);
        backgroundPane.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
        Group root = new Group(backgroundPane);
        primaryStage.setScene(new Scene(root, Sizes.getWIDTH(), Sizes.getHEIGHT()));
        primaryStage.show();
    }
    public static void exit(Stage primaryStage){
        primaryStage.close();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
