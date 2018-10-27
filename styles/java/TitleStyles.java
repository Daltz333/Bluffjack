package styles.java;

import helpers.Transitions;
import javafx.scene.control.DialogPane;
import javafx.scene.layout.BorderPane;

public class TitleStyles extends styles.java.Style {

    private String stylesheet = this.getClass().getResource("/styles/css/TitleStyles.css").toExternalForm();

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
