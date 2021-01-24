package controller;

import boards.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import logic.Sizes;
import visuals.*;

import java.util.ArrayList;

public class Main extends Application {
    public final int HEIGHT = 900;
    public final int WIDTH = 1200;
    public final int COLUMNS = 30;
    public final int ROWS = 30;
    @Override
    public void start(Stage primaryStage){
        primaryStage.setTitle("VRace");
        primaryStage.setResizable(false);
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
        Group root = new Group(gameSetupPane);
        primaryStage.setScene(new Scene(root, Sizes.getWIDTH(), Sizes.getHEIGHT()));
        primaryStage.getScene().getStylesheets().add("style.css");
        primaryStage.show();
    }
    public static void game(Stage primaryStage, ArrayList<String> playerNames){
        GameButton exitToMenu = new GameButton(Sizes.getWIDTH()/4f, Sizes.getHEIGHT()/18f, "EXIT TO MENU", 30);
        exitToMenu.setId("exitToMenu");
        exitToMenu.setOnAction(actionEvent -> goToMainMenu(primaryStage));
        HighScoreBoard highScoreBoard = new HighScoreBoard(Sizes.getHEIGHT()*1f/2, 5f/6*(Sizes.getWIDTH() - Sizes.getHEIGHT()), Sizes.getCOLUMNS());
        FinishGameBoard finishGameBoard = new FinishGameBoard(Sizes.getHEIGHT()/2f, Sizes.getHEIGHT()/2f, Sizes.getHEIGHT()/4f, Sizes.getHEIGHT()/4f);
        Board board = new Board(Sizes.getCOLUMNS(),Sizes.getROWS(), Sizes.getHEIGHT()*Sizes.getWIDTH(), highScoreBoard, finishGameBoard);
        Game.board = board;
        Game.start(Sizes.getPLAYERNUMBER(), playerNames);
        FlowPane backgroundPane = new FlowPane();
        backgroundPane.setMinHeight(Sizes.getHEIGHT());
        backgroundPane.setMinWidth(Sizes.getWIDTH());
        backgroundPane.getChildren().add(board.backboard);
        backgroundPane.setAlignment(Pos.CENTER_LEFT);
        backgroundPane.setHgap(Sizes.getWIDTH()/40f);
        backgroundPane.setPadding(new Insets(10,10,10,10));
        backgroundPane.setBackground(new Background(new BackgroundImage(new Image("game_bckg.png"), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(Sizes.getWIDTH(), Sizes.getHEIGHT(), false, false, true, true))));
        VBox vBox = new VBox();
        vBox.setSpacing(30);
        vBox.setPadding(new Insets(200,0,0,0));
        vBox.getChildren().addAll(highScoreBoard, exitToMenu);
        backgroundPane.getChildren().add(vBox);
        Group root = new Group(backgroundPane);
        primaryStage.setScene(new Scene(root, Sizes.getWIDTH(), Sizes.getHEIGHT()));
        primaryStage.getScene().getStylesheets().add("style.css");
        primaryStage.show();
    }
    public static void highscore(Stage primaryStage){
        HighScorePane backgroundPane= new HighScorePane(Sizes.getWIDTH(), Sizes.getHEIGHT(),primaryStage);
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
