package com.game.tictactoe;
import com.game.tictactoe.AIPlayer;
import com.game.tictactoe.Board;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AiTest {

    @Test
    void aiShouldReturnValidMove() {
        Board.Tile[][] tiles = new Board.Tile[3][3];

        // Initialize empty board
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                tiles[x][y] = new Board().new Tile(x, y);
            }
        }

        AIPlayer ai = new AIPlayer();
        int[] move = ai.bestMove(tiles);

        assertNotNull(move);
        assertTrue(move[0] >= 0 && move[0] < 3);
        assertTrue(move[1] >= 0 && move[1] < 3);
    }
}
