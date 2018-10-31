package styles.java;

import javafx.scene.layout.GridPane;

public class ConnectionStyles extends styles.java.Style {
	// set stylesheeet and root transitions
	private String stylesheet = this.getClass().getResource("/styles/css/ConnectionStyles.css").toExternalForm();

	public void setConnectionStyles(GridPane subPane) {
		setFonts();
		subPane.getStylesheets().add(stylesheet);

		// Transitions.setTransition(subPane);

	}

}
