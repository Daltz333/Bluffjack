package styles.java;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class RootStyles extends bluffjack.styles.java.Style {

    private String stylesheet = this.getClass().getResource("/styles/css/RootStyles.css").toExternalForm();

    public void setRootStyles(GridPane root) {
        setFonts();
        root.setId("rootPane");
        root.setAlignment(Pos.CENTER);
        root.getStylesheets().add(stylesheet);
        root.setGridLinesVisible(false);

        Stage stage = (Stage) root.getScene().getWindow();
        stage.getIcons().add(new Image(this.getClass().getResourceAsStream("/images/application_icon.png")));

    }

}
