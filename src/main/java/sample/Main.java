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
import visuals.GameSetupPane;
import visuals.MenuPane;

public class Main extends Application {
    public final int HEIGHT = 900;
    public final int WIDTH = 900;
    public final int COLUMNS = 30;
    public final int ROWS = 30;
    @Override
    public void start(Stage primaryStage){
        primaryStage.setTitle("VRace");
        Sizes.set(HEIGHT,WIDTH,ROWS, COLUMNS);
        goToMainMenu(primaryStage);
    }
    public static void goToMainMenu(Stage primaryStage){
        Group root = mainMenu(primaryStage);
        primaryStage.setScene(new Scene(root, Sizes.getWIDTH(), Sizes.getHEIGHT()));
        primaryStage.getScene().getStylesheets().add("style.css");
        primaryStage.show();
    }
    public static Group mainMenu(Stage stage){
        FlowPane backgroundPane = new FlowPane();
        backgroundPane.setMinHeight(Sizes.getHEIGHT());
        backgroundPane.setMinWidth(Sizes.getWIDTH());
        backgroundPane.setAlignment(Pos.CENTER);
        MenuPane menuPane = new MenuPane(Sizes.getHEIGHT(), Sizes.getWIDTH(), stage);
        backgroundPane.getChildren().add(menuPane);
        return new Group(backgroundPane);
    }
    public static void goToSetup(Stage primaryStage){
        GameSetupPane gameSetupPane = new GameSetupPane(Sizes.getWIDTH(), Sizes.getHEIGHT(),primaryStage);
        gameSetupPane.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
        Group root = new Group(gameSetupPane);
        primaryStage.setScene(new Scene(root, Sizes.getWIDTH(), Sizes.getHEIGHT()));
        primaryStage.getScene().getStylesheets().add("style.css");
        primaryStage.show();
    }
    public static void game(Stage primaryStage){
        Board board = new Board(Sizes.getCOLUMNS(),Sizes.getROWS(), Sizes.getHEIGHT()*Sizes.getWIDTH());
        Game.board = board;
        Game.start(Sizes.getPLAYERNUMBER());
        FlowPane backgroundPane = new FlowPane();
        backgroundPane.setMinHeight(Sizes.getHEIGHT());
        backgroundPane.setMinWidth(Sizes.getWIDTH());
        backgroundPane.getChildren().add(board.backboard);
        backgroundPane.setAlignment(Pos.CENTER);
        backgroundPane.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
        Group root = new Group(backgroundPane);
        primaryStage.setScene(new Scene(root, Sizes.getWIDTH(), Sizes.getHEIGHT()));
        primaryStage.getScene().getStylesheets().add("style.css");
        primaryStage.show();
    }
    public static void exit(Stage primaryStage){
        primaryStage.close();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
