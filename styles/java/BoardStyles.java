package styles.java;

import helpers.Transitions;
import javafx.scene.layout.BorderPane;

public class BoardStyles extends Style {

    private String stylesheet = this.getClass().getResource("/styles/css/BoardStyles.css").toExternalForm();

    public void setBoardtyles(BorderPane board) {
        setFonts();
        board.getStylesheets().add(stylesheet);
        Transitions.setTransition(board);

    }

}
