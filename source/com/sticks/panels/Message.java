package com.sticks.panels;

public enum Message {
    TRY_CONNECT("Click to bind"),
    WAIT_FOR_ANOTHER_PLAYER("Waiting for another player"),
    YOUR_TURN("Your turn"),
    ANOTHER_TURN("Another player's turn"),
    WINNER("Congratulations, you won!"),
    LOSER("Sorry, you lost");

    private String value;
    Message(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
