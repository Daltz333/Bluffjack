package helpers;

import javafx.animation.FadeTransition;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

public class Transitions {

	// Reason for duplicates: Didn't know how to create the same function but
	// separate parameters without
	// creating an identical function with a separate parameter
	@SuppressWarnings("Duplicates")
	public static void setTransition(ImageView root) {
		FadeTransition ft = new FadeTransition(Duration.millis(200), root);
		ft.setFromValue(0.3);
		ft.setToValue(1.0);
		ft.setAutoReverse(true);

		ft.play();
	}

	@SuppressWarnings("Duplicates")
	public static void setTransition(BorderPane root) {
		FadeTransition ft = new FadeTransition(Duration.millis(50), root);
		ft.setFromValue(0.3);
		ft.setToValue(1.0);
		ft.setAutoReverse(false);

		ft.play();
	}

}
