package scenes;

import javafx.scene.layout.GridPane;

public class SceneController {
    private static TitleScene title = new TitleScene();
    private static ConnectionScene connectScene = new ConnectionScene();

    private static int selectedScene = 0;
    public static GridPane root;

    public static void manageScenes() {
        switch (selectedScene) {
            case 0:
                title.setSceneState(root);
                break;

            case 1:
                connectScene.setSceneState(root);
                break;

            case 2:
                connectScene.showConnectScene(root);
                break;

            default:
                break;
        }

    }

    public void setScene(int state) {
        selectedScene = state;

        root.getChildren().clear();
        root.getChildren().removeAll();
        manageScenes();
    }

}
