package visuals;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.css.Size;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import logic.Paints;
import logic.Player;
import logic.Sizes;
import sample.Main;

import java.util.ArrayList;
import java.util.Arrays;

public class GameSetupPane extends BorderPane {
    private GridPane gridPane;
    private FlowPane flowPane;
    private final int WIDTH;
    private final int HEIGHT;
    private final Stage stage;
    private Slider trackSizeSlider;
    private Slider numberOfPlayersSilder;
    private ArrayList<Button> buttons;
    private ArrayList<String> names;
    private ArrayList<PlayerNameField> playerNameFields;
    public GameSetupPane(int WIDTH, int HEIGHT, Stage stage){
        super();
        this.setPrefWidth(WIDTH);
        this.setPrefHeight(HEIGHT);
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
        this.stage = stage;
        this.setPadding(new Insets(150,30,50,30));
        this.setBackground(new Background(new BackgroundImage(new Image("setup_bckg.png"), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(this.WIDTH, this.HEIGHT, false, false, true, true))));
        this.playerNameFields = new ArrayList<>();
        setGridPane();
        addNodes();
    }
    private void setGridPane(){
        this.gridPane = new GridPane();
        this.gridPane.setPrefHeight(this.HEIGHT*2f/3);
        this.gridPane.setPrefWidth(this.WIDTH*2f/3);
        this.setCenter(gridPane);
    }
    private void addNodes(){
        Label label1 = new Label("Select number of players");
        Slider numberOfPlayersSlider = new Slider(1, 4,2);
        numberOfPlayersSlider.setBlockIncrement(1);
        numberOfPlayersSlider.setShowTickMarks(true);
        numberOfPlayersSlider.setShowTickLabels(true);
        numberOfPlayersSlider.setMajorTickUnit(1);
        numberOfPlayersSlider.setMinorTickCount(0);
        numberOfPlayersSlider.setSnapToTicks(true);
        numberOfPlayersSlider.setMaxWidth(Sizes.getWIDTH()/3);
        numberOfPlayersSlider.setMinWidth(Sizes.getWIDTH()/3);
        numberOfPlayersSlider.valueProperty().addListener((observableValue, oldVal, newVal) -> manipulateSlider(numberOfPlayersSlider, oldVal, newVal));
        flowPane = new FlowPane();
        flowPane.setPrefHeight(2*Sizes.getHEIGHT()/5);
        gridPane.add(label1, 0,0);
        gridPane.add(flowPane, 0,2);
        flowPane.setVgap(20);
        gridPane.setAlignment(Pos.TOP_CENTER);
        gridPane.add(numberOfPlayersSlider, 0,1);
        gridPane.setVgap(20);
        gridPane.setHgap(20);
        Slider trackSizeSlider = new Slider(15, 50,30);
        trackSizeSlider.setBlockIncrement(1);
        trackSizeSlider.setShowTickMarks(true);
        trackSizeSlider.setShowTickLabels(true);
        trackSizeSlider.setMajorTickUnit(5);
        trackSizeSlider.setMinorTickCount(0);
        trackSizeSlider.setSnapToTicks(true);
        trackSizeSlider.setMinWidth(Sizes.getWIDTH()/3);
        trackSizeSlider.setMaxWidth(Sizes.getWIDTH()/3);
        this.trackSizeSlider = trackSizeSlider;
        this.numberOfPlayersSilder = numberOfPlayersSlider;
        manipulateSlider(numberOfPlayersSlider, 1,2);
        Label label2 = new Label("Select track size");

        gridPane.add(label2,1,0);
        gridPane.add(trackSizeSlider, 1,1);
        setButtons();
    }
    private void setButtons(){
        Button playButton = new Button("PLAY");
        Button returnButton = new Button("RETURN");
        buttons = new ArrayList<>(Arrays.asList(playButton, returnButton));
        for(Button button : buttons){
            button.setPrefWidth(Sizes.getWIDTH()/3);
            button.setPrefHeight(Sizes.getHEIGHT()/20);
            button.setFont(Font.font("msbm10", 40));
            button.setShape(buttonShape());
            button.setOpacity(0.8);
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    if(button.getText().equalsIgnoreCase("PLAY")){
                        Sizes.setROWSCOLUMNS((int)trackSizeSlider.getValue(), (int)trackSizeSlider.getValue());
                        Sizes.setPLAYERNUMBER((int)numberOfPlayersSilder.getValue());
                        names = new ArrayList<>();
                        for(PlayerNameField f : playerNameFields){
                            names.add(f.getName());
                        }
                        Main.game(stage, names);
                    }
                    else if(button.getText().equalsIgnoreCase("RETURN")){
                        Main.goToMainMenu(stage);
                    }
                }
            });
        }
        this.gridPane.add(playButton,1,3);
        this.gridPane.add(returnButton, 0, 3);
    }
    private void manipulateSlider(Slider slider, Number oldVal, Number newVal){
        if(oldVal.equals(newVal)) return;
        flowPane.getChildren().clear();
        playerNameFields.clear();
        for(int i = 0; i < newVal.intValue(); ++i){
            PlayerNameField pnf = new PlayerNameField(Paints.get(i), i, Sizes.getWIDTH()/3, Sizes.getHEIGHT()/20);
            playerNameFields.add(pnf);
            flowPane.getChildren().add(pnf);
        }
        switch (newVal.intValue()){
            case 2:
                trackSizeSlider.setMin(20);
                break;
            case 3:
                trackSizeSlider.setMin(30);
                break;
            case 4:
                trackSizeSlider.setMin(40);
                break;
            default:
                trackSizeSlider.setMin(15);
                break;
        }
    }
    private Rectangle buttonShape(){
        Rectangle rectangle = new Rectangle(Sizes.getWIDTH()/3, Sizes.getHEIGHT()/20);
        rectangle.setArcHeight(Sizes.getHEIGHT()/60f);
        rectangle.setArcWidth(Sizes.getHEIGHT()/60f);

        return rectangle;
    }
}
