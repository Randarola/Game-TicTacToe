package com.example;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.input.MouseEvent;
import javafx.scene.Node;

public class Board {
    private final int SIZE = 3;
    private final Tile[][] tiles = new Tile[SIZE][SIZE];
    private boolean playerTurn = true; // true = X, false = O (AI)
    private final AIPlayer ai = new AIPlayer();

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

    private class Tile extends StackPane {
        private final Text text = new Text();
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
                playerTurn = false;

                if (!isBoardFull()) {
                    int[] move = ai.bestMove(tiles);
                    if (move != null) {
                        tiles[move[0]][move[1]].drawO();
                        playerTurn = true;
                    }
                }
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

