package bluffjack.styles.java;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;

public class TitleStyles extends Style{

    private String stylesheet = this.getClass().getResource("../css/TitleStyles.css").toExternalForm();

    public void setTitleStyles(GridPane title) {
            setFonts();
            title.getStylesheets().add(stylesheet);
            title.setAlignment(Pos.CENTER);

    }

}
