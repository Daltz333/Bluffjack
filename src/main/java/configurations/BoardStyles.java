package configurations;

import helpers.Transitions;
import javafx.scene.layout.BorderPane;

public class BoardStyles extends Style {
	// set stylesheet and root transitions
	private String stylesheet = getClass().getClassLoader().getResource("css/BoardStyles.css").toExternalForm();

	public void setBoardStyles(BorderPane board) {
		setFonts();
		board.getStylesheets().add(stylesheet);
		Transitions.setTransition(board);

	}

}
