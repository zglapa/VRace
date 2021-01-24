package boards;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import controller.Main;
import visuals.GameButton;

import java.util.ArrayList;
import java.util.Arrays;

public class MenuPane extends BorderPane {
    private FlowPane flowPane;
    private final int HEIGHT;
    private final int WIDTH;
    private final Stage stage;
    public MenuPane(int HEIGHT, int WIDTH, Stage stage){
        super();
        this.setPrefHeight(HEIGHT);
        this.setPrefWidth(WIDTH);
        this.HEIGHT = HEIGHT;
        this.WIDTH = WIDTH;
        this.stage = stage;
        this.setBackground(new Background(new BackgroundImage(new Image("main_bckg.png"), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(this.WIDTH, this.HEIGHT, false, false, true, true))));
        setFlowPane();
        addButtons();
    }
    private void setFlowPane(){
        this.flowPane = new FlowPane();
        this.flowPane.setPrefHeight(this.HEIGHT);
        this.flowPane.setPrefWidth(2*this.WIDTH/3f);
        this.flowPane.setMaxWidth(Control.USE_PREF_SIZE);
        this.flowPane.setAlignment(Pos.CENTER);
        this.flowPane.setVgap(this.HEIGHT/18f);
        this.flowPane.setPadding(new Insets(100,0,0,0));
        this.setCenter(flowPane);
    }
    private void addButtons(){
        GameButton playButton = new GameButton(this.WIDTH/2f, this.HEIGHT/9f, "PLAY");
        GameButton highScoreButton = new GameButton(this.WIDTH/2f, this.HEIGHT/9f, "HIGHSCORE");
        GameButton exitButton = new GameButton(this.WIDTH/2f, this.HEIGHT/9f, "EXIT");

        ArrayList<GameButton> buttons = new ArrayList<>(Arrays.asList(playButton, highScoreButton, exitButton));
        for(Button button : buttons){
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    if(button.getText().equalsIgnoreCase("PLAY")){
                        Main.goToSetup(stage);
                    }
                    else if(button.getText().equalsIgnoreCase("EXIT")){
                        Main.exit(stage);
                    }
                    else if(button.getText().equalsIgnoreCase("HIGHSCORE")){
                        Main.highscore(stage);
                    }
                }
            });
        }
        this.flowPane.getChildren().addAll(playButton,highScoreButton, exitButton);
    }
}
