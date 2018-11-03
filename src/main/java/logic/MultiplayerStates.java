package logic;

public class MultiplayerStates {

    public void setGameState(String dataType) {

        switch (dataType) {
            case "[Username]":
                //set player username, send data to client with success
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
