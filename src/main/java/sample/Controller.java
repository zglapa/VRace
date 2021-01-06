package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import visuals.Board;
import visuals.Dot;
import visuals.Vector;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    GridPane backgroundPane;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Board board = new Board(50,50, 1920*1080);
        backgroundPane.add(board.backboard,0,0);
    }

}
