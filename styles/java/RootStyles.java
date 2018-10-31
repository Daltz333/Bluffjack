package styles.java;

import constants.GeneralConstants;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class RootStyles extends styles.java.Style {
	// this applies to the MAIN root
	public void setRootStyles(GridPane root) {
		setFonts();
		root.setAlignment(Pos.CENTER);
		root.setGridLinesVisible(GeneralConstants.debugEnabled);

		Stage stage = (Stage) root.getScene().getWindow();
		stage.getIcons().add(new Image(this.getClass().getResourceAsStream("/images/application_icon.png")));

	}

}
