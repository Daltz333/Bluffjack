package scenes;

import javafx.scene.layout.GridPane;

public class SceneController {
    private static TitleScene title = new TitleScene();
    private static ConnectionScene connectScene = new ConnectionScene();
    private static BoardScene boardScene = new BoardScene();

    private static int selectedScene = 0;
    public static GridPane root;

    //display the appropriate scene
    public static void manageScenes() {
        switch (selectedScene) {
            case 0:
                //go to title scene
                title.setSceneState(root);
                break;

            case 1:
                //go to connect/host scene on title
                connectScene.setSceneState(root);
                break;

            case 2:
                //go to to the connect ip scene on title
                connectScene.showConnectScene(root);
                break;

            case 3:
                //start host game
                boardScene.setMainBoard(root);
                break;

            case 4:
                //start connect game
                break;

            case 5:
                //open options scene
                break;

            default:
                break;
        }

    }

    //clear our root nodes and set scene
    public void setScene(int state) {
        selectedScene = state;

        root.getChildren().clear();
        root.getChildren().removeAll();
        manageScenes();
    }

}
