package bluffjack.styles.java;

import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;

public class RootStyles extends Style{

    private String stylesheet = this.getClass().getResource("../css/RootStyles.css").toExternalForm();

    public void setRootStyles(GridPane root) {
        setFonts();
        root.setId("rootPane");
        root.setAlignment(Pos.CENTER);
        root.getStylesheets().add(stylesheet);
        root.setGridLinesVisible(true);

    }

}
