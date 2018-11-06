package logic;

import scenes.BoardScene;
import sockets.Server;

public class MultiplayerStates {

    public void handleClientData(String data) {
        String result = "Current data is: " + data + "\n";
        String dataType = null;
        String dataContent = null;

        System.out.println("Data is: " + result);

        try {
            dataType = data.substring(data.indexOf("["), data.indexOf("]") + 1);
            dataContent = data.substring(data.indexOf("]") + 2);;

            result = result + "Data Type is: " + dataType;
        } catch (NullPointerException e) {
            result = result + "Invalid Data Type";

        }

        if (dataContent != null) {
            setServerGameState(dataType, dataContent);

        }

    }

    private void setServerGameState(String dataType, String data) {

        switch (dataType) {
            case "[Username]":
                BoardScene.updateOpponentUsername(data);
                break;

            case "[Connect]":
                //send exclusion list to client, they should create the board
                break;

            case "[ServerMove]":
                //server turn, send new exclusion list to client
                break;
            case "[ClientMove]":
                //client turn, send exclusion list to server
                break;
            case "[ClientPass]":
                //set endGame state to true
                break;
            case "[ServerPass]":
                //set endGame state to true
                break;
            case "[EndGame]":
                //show the result and exit button
                break;
            default:
                //unknown data
                break;
        }

    }

}
