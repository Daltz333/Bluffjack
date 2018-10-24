package bluffjack.styles.java;

import javafx.beans.property.ObjectProperty;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.DialogPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class TitleStyles extends Style{

    private String stylesheet = this.getClass().getResource("../css/TitleStyles.css").toExternalForm();

    public void setTitleStyles(BorderPane title) {
            setFonts();
            title.getStylesheets().add(stylesheet);

    }

    public void setDialogPaneStyle(DialogPane pane) {
        setFonts();
        pane.setId("dialogPaneMain");
        pane.getStylesheets().add(stylesheet);
    }

}
