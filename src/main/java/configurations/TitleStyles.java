package configurations;

import helpers.Transitions;
import javafx.scene.control.DialogPane;
import javafx.scene.layout.BorderPane;

public class TitleStyles extends Style {

	private String stylesheet = getClass().getClassLoader().getResource("css/TitleStyles.css").toExternalForm();

	public void setTitleStyles(BorderPane title) {
		setFonts();
		title.getStylesheets().add(stylesheet);
		Transitions.setTransition(title);

	}

	public void setDialogPaneStyle(DialogPane pane) {
		setFonts();
		pane.setId("dialogPaneMain");
		pane.getStylesheets().add(stylesheet);
	}

}
