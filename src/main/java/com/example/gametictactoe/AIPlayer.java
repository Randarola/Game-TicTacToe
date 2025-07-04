package com.example.gametictactoe;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class AIPlayer {
    private static final Random random = new Random();

    public int[] bestMove(Board.Tile[][] board) {
        List<int[]> available = new ArrayList<>();

        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                if (board[x][y].text.getText().isEmpty()) {
                    available.add(new int[]{x, y});
                }
            }
        }

        if (available.isEmpty()) return null;

        return available.get(random.nextInt(available.size()));
    }
}
