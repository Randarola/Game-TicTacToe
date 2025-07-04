package com.game.tictactoe;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        Board board = new Board();
        Scene scene = new Scene(board.createContent());
        stage.setScene(scene);
        stage.setTitle("Tic Tac Toe");
        stage.show();
    }
}
