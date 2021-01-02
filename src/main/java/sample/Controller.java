package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
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
        Board board = new Board(10,10, 900*700);
        backgroundPane.add(board.backboard,0,0);
    }

}
