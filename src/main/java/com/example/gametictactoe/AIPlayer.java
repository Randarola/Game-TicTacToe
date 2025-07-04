package com.example.gametictactoe;
public class AIPlayer {
    public int[] bestMove(Board.Tile[][] board) {
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                if (board[x][y].text.getText().isEmpty()) {
                    return new int[]{x, y};
                }
            }
        }
        return null;
    }
}
