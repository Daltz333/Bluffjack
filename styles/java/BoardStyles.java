package styles.java;

import helpers.Transitions;
import javafx.scene.layout.GridPane;

public class BoardStyles extends Style {

    private String stylesheet = this.getClass().getResource("/styles/css/BoardStyles.css").toExternalForm();

    public void setBoardtyles(GridPane board) {
        setFonts();
        board.getStylesheets().add(stylesheet);
        Transitions.setTransition(board);

    }

}
