package scenes;

import javafx.scene.layout.GridPane;
import styles.java.BoardStyles;

public class BoardScene {
    GridPane mainBoard = new GridPane();
    GridPane opponentSide = new GridPane();
    GridPane board = new GridPane();
    GridPane playerSide = new GridPane();

    BoardStyles boardStyles = new BoardStyles();

    public void setMainBoard(GridPane root) {
        boardStyles.setBoardtyles(mainBoard);
        mainBoard.setId("mainBoard");

        mainBoard.add(opponentSide, 0, 0);
        mainBoard.add(board, 0, 1);
        mainBoard.add(playerSide, 0, 2);

        root.add(mainBoard, 0, 0);

    }
}
