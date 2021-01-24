package boards;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import logic.Sizes;
import controller.Main;
import visuals.GameButton;

public class HighScorePane extends BorderPane {
    private final int WIDTH, HEIGHT;
    private HighScoreBoard highScoreBoard;
    private HBox hBox;
    private VBox vBox;
    private Stage stage;
    public HighScorePane(int WIDTH, int HEIGHT, Stage stage){
        super();
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
//        this.hBox = setHbox();
        this.stage = stage;
        this.vBox = setVbox();
//        this.setCenter(hBox);
        this.setCenter(vBox);
        this.setBottom(setReturnButton());
        this.setPadding(new Insets(200,0,0,0));
        this.setPrefHeight(HEIGHT);
        this.setPrefWidth(WIDTH);
        setBCKG();
    }
    private HBox setHbox(){
        HBox box = new HBox();
        box.setAlignment(Pos.BOTTOM_CENTER);
        box.getChildren().add(setVbox());
        highScoreBoard = new HighScoreBoard(HEIGHT/2f, WIDTH/4f, 20);
        box.getChildren().add(highScoreBoard);
        return box;
    }
    private VBox setVbox(){
        VBox box = new VBox();
        box.setAlignment(Pos.BOTTOM_CENTER);
        box.setSpacing(10);
        box.getChildren().addAll(setLabel(), setSlider());
        highScoreBoard = new HighScoreBoard(HEIGHT/2f, WIDTH/4f, 20);
        box.getChildren().add(highScoreBoard);
        return box;
    }
    private Label setLabel(){
        Label label = new Label("Choose board size");
        label.setId("header");
        return label;
    }
    private Slider setSlider(){
        Slider slider = new Slider(15, 50, 20);
        slider.setBlockIncrement(1);
        slider.setShowTickMarks(true);
        slider.setShowTickLabels(true);
        slider.setMajorTickUnit(5);
        slider.setMinorTickCount(0);
        slider.setSnapToTicks(true);
        slider.setMaxWidth(WIDTH/4f);
        slider.setMinWidth(WIDTH/4f);
        slider.valueProperty().addListener((observableValue, number, t1) -> highScoreBoard.changeHighScore(t1.intValue()));
        return slider;
    }
    private void setBCKG(){
        this.setBackground(new Background(new BackgroundImage(new Image("highscore_bckg.png"), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(Sizes.getWIDTH(), Sizes.getHEIGHT(), false, false, true, true))));
    }
    private HBox setReturnButton(){
        HBox hBox = new HBox();
        hBox.setPadding(new Insets(20,0,50,0));
        hBox.setAlignment(Pos.CENTER);
        GameButton button = new GameButton(this.WIDTH/4f, this.HEIGHT/18f, "RETURN");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Main.goToMainMenu(stage);
            }
        });
        hBox.getChildren().add(button);
        return hBox;
    }

}
