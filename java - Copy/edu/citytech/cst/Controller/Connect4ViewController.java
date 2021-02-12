package edu.citytech.cst.Controller;

import com.jbbwebsolutions.http.utility.*;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class Connect4ViewController implements Initializable {

    @FXML
    private FlowPane fpSlots;

    @FXML
    private Button newGame;

    @FXML
    private Text winnerDisplay;

    @FXML
    private Text whosTurn;

    private String color = "red";
    private String move = "X";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for (int i = 0; i < 42; i++) {
            Label l = new Label("?");
            l.setId(""+i);

            l.setOnMouseClicked(e -> {
                Label label = (Label)e.getSource();
                nextMove(label);
                checkForWinner();
                checkForDraw();
            });
            fpSlots.getChildren().add(l);
        }
        whosTurn.setText(color.toUpperCase() + "'s TURN");

        newGame.setOnMouseClicked(e -> {
            resetGame();
        });
    }

    @FXML
    private void nextMove(Label position) {
        if(position.getStyleClass().contains("highlight-red") ||
                position.getStyleClass().contains("highlight-yellow")) {
            return;
        }

        int index = Integer.parseInt(position.getId()); //check the clicked position id
        int lowestPosition = index;
        while(lowestPosition < 42) {
            lowestPosition += 7;
        }
        lowestPosition -= 7;

        ObservableList<Node> children = fpSlots.getChildren();
        for (int i = lowestPosition; i >= index; i-=7) {
            Label label = (Label)children.get(i);
            if (label.getStyleClass().contains("highlight-red") ||
                label.getStyleClass().contains("highlight-yellow")) {
                    continue;
            } else
                position = label;
            break;
        }

        position.getStyleClass().add("highlight-" + color);
        color = color.equals("red") ? "yellow" : "red";

        move = move.equals("X") ? "O" : "X";
        position.setText(move);
        whosTurn.setText(color.toUpperCase() + "'s TURN");
    }

    private void checkForWinner() {
        final String sURL = "http://localhost:9416/game/connect4?moves=";
        ObservableList<Node> children = fpSlots.getChildren();
        StringBuilder moves = new StringBuilder();
        for (Node child : children) {
            Label label = (Label)child;
            moves.append(label.getText());
        }
        Map<String, Object> board = URLUtility.get(sURL+moves, Map.class);
        String isWinner = board.get("Winner").toString();
        if(isWinner.equals("true")) {
            String winner = board.get("whoWon").toString();
            stopGame();
            whosTurn.setText("");
            if (winner.equals("X"))
                winnerDisplay.setText("YELLOW WINS!");
            else
                winnerDisplay.setText("RED WINS!");
        }
    }

    private void checkForDraw(){
        ObservableList<Node> children = fpSlots.getChildren();
        int positionsFilled = 0;
        for (Node child : children) {
            Label label = (Label)child;
            if (label.getStyleClass().contains("highlight-red") ||
                label.getStyleClass().contains("highlight-yellow")) {
                positionsFilled++;
            }
        }
        if(positionsFilled == 42) {
            winnerDisplay.setText("TIE!");
            whosTurn.setText("");
        }
    }

    private void stopGame() {
        ObservableList<Node> children = fpSlots.getChildren();
        for (Node child : children) {
            Label label = (Label)child;
            label.setOnMouseClicked(e -> {});
        }
    }

    @FXML
    public void resetGame() {
        ObservableList<Node> children = fpSlots.getChildren();
        for (Node child : children) {
            Label label = (Label) child;
            label.getStyleClass().remove("highlight-red");
            label.getStyleClass().remove("highlight-yellow");
            label.setText("?");

            label.setOnMouseClicked(e -> {
                Label position = (Label) e.getSource();
                nextMove(position);
                checkForWinner();
                checkForDraw();
            });
        }
        whosTurn.setText(color.toUpperCase() + "'s TURN");
        winnerDisplay.setText("");
    }
}