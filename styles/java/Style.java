package styles.java;

import javafx.scene.text.Font;

public class Style {
    //load our fonts
    public void setFonts() {
        Font.loadFont(this.getClass().getResource("/fonts/BLKCHCRY.TTF").toExternalForm(), 10);
        Font.loadFont(this.getClass().getResource("/fonts/CHLORINR.TTF").toExternalForm(), 10);

    }

}
