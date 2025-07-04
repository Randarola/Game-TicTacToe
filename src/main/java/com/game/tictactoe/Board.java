package com.game.tictactoe;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.input.MouseEvent;

public class Board {
    private final int SIZE = 3;
    private final Tile[][] tiles = new Tile[SIZE][SIZE];
    private boolean playerTurn = true; // true = X, false = O (AI)
    private final AIPlayer ai = new AIPlayer();

    private boolean checkWin(String player) {
        // Wiersze
        for (int y = 0; y < SIZE; y++) {
            if (tiles[0][y].text.getText().equals(player) &&
                    tiles[1][y].text.getText().equals(player) &&
                    tiles[2][y].text.getText().equals(player)) {
                return true;
            }
        }

        // Kolumny
        for (int x = 0; x < SIZE; x++) {
            if (tiles[x][0].text.getText().equals(player) &&
                    tiles[x][1].text.getText().equals(player) &&
                    tiles[x][2].text.getText().equals(player)) {
                return true;
            }
        }

        // Przekątne
        if (tiles[0][0].text.getText().equals(player) &&
                tiles[1][1].text.getText().equals(player) &&
                tiles[2][2].text.getText().equals(player)) {
            return true;
        }

        if (tiles[2][0].text.getText().equals(player) &&
                tiles[1][1].text.getText().equals(player) &&
                tiles[0][2].text.getText().equals(player)) {
            return true;
        }

        return false;
    }
    private void showAlert(String message) {
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
        alert.setTitle("Koniec gry");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();

        resetBoard();

    }
    private void resetBoard(){
        for (Tile[] row : tiles) {
            for (Tile tile : row) {
                tile.text.setText("");
            }
        }
        playerTurn = true;
    }

    public Pane createContent() {
        GridPane grid = new GridPane();
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                Tile tile = new Tile(x, y);
                tiles[x][y] = tile;
                grid.add(tile, x, y);
            }
        }
        return grid;
    }

    class Tile extends StackPane {
        final Text text = new Text();
        private final int x, y;

        public Tile(int x, int y) {
            this.x = x;
            this.y = y;

            Rectangle border = new Rectangle(100, 100);
            border.setFill(null);
            border.setStroke(Color.BLACK);

            text.setStyle("-fx-font-size: 40px;");

            setOnMouseClicked(this::handleClick);

            getChildren().addAll(border, text);
        }

        private void handleClick(MouseEvent event) {
            if (!text.getText().isEmpty()) {
                return;
            }
            if (playerTurn) {
                text.setText("X");

                if (checkWin("X")) {
                    showAlert("Gracz X wygrał!");
                    return;
                } else if (isBoardFull()) {
                    showAlert("Remis!");
                    return;
                }

                playerTurn = false;

                int[] move = ai.bestMove(tiles);
                if (move != null) {
                    tiles[move[0]][move[1]].drawO();

                    if (checkWin("O")) {
                        showAlert("Gracz O wygrał!");
                        return;
                    } else if (isBoardFull()) {
                        showAlert("Remis!");
                        return;
                    }
                }

                playerTurn = true;
            }
        }

        private void drawO() {
            text.setText("O");
        }
    }

    private boolean isBoardFull() {
        for (Tile[] row : tiles) {
            for (Tile tile : row) {
                if (tile.text.getText().isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }
}

