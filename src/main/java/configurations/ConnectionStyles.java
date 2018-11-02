package configurations;

import javafx.scene.layout.GridPane;

public class ConnectionStyles extends Style {
	// set stylesheeet and root transitions
	private String stylesheet = getClass().getClassLoader().getResource("css/ConnectionStyles.css").toExternalForm();

	public void setConnectionStyles(GridPane subPane) {
		setFonts();
		subPane.getStylesheets().add(stylesheet);

		// Transitions.setTransition(subPane);

	}

}
