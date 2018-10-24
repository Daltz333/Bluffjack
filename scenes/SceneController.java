package bluffjack.scenes;

import javafx.scene.layout.GridPane;

public class SceneController {
    private TitleScene title = new TitleScene();

    private int selectedScene = 0;

    public void manageScenes(GridPane root) {
        switch (selectedScene) {
            case 0:
                title.setSceneState(root);
                break;

            default:
                break;
        }

    }

}
