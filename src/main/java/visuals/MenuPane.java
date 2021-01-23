package visuals;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import logic.Sizes;
import sample.Game;
import sample.Main;

import java.util.ArrayList;
import java.util.Arrays;

public class MenuPane extends BorderPane {
    private FlowPane flowPane;
    private final int HEIGHT;
    private final int WIDTH;
    private ArrayList<Button> buttons;
    private int i;
    private final Stage stage;
    public MenuPane(int HEIGHT, int WIDTH, Stage stage){
        super();
        this.setPrefHeight(HEIGHT);
        this.setPrefWidth(WIDTH);
        this.HEIGHT = HEIGHT;
        this.WIDTH = WIDTH;
        this.stage = stage;
        this.setBackground(new Background(new BackgroundImage(new Image("vrace_bckg.jpeg"), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(this.WIDTH, this.HEIGHT, false, false, true, true))));
        i = 0;
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
        Button playButton = new Button("PLAY");
        Button optionButton = new Button("OPTIONS");
        Button exitButton = new Button("EXIT");
        this.buttons = new ArrayList<>(Arrays.asList(playButton, optionButton, exitButton));
        for(Button button : buttons){
            button.setPrefWidth(this.WIDTH/2f);
            button.setPrefHeight(this.HEIGHT/9f);
            button.setFont(Font.font("msbm10", 40));
            button.setShape(buttonShape());
            button.setOpacity(0.4);
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    if(button.getText().equalsIgnoreCase("PLAY")){
                        Main.goToSetup(stage);
                    }
                    else if(button.getText().equalsIgnoreCase("EXIT")){
                        Main.exit(stage);
                    }
                }
            });
        }
        this.flowPane.getChildren().addAll(playButton,optionButton, exitButton);
    }
    private Rectangle buttonShape(){
        Rectangle rectangle = new Rectangle(this.WIDTH/2f, this.HEIGHT/9f);
        rectangle.setArcHeight(this.HEIGHT/27f);
        rectangle.setArcWidth(this.HEIGHT/27f);

        return rectangle;
    }

}
