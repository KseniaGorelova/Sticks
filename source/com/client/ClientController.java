package com.client;

import com.server.ServerMessage;
import com.sticks.Player;
import com.sticks.panels.Field;
import com.sticks.panels.GamePanel;
import com.sticks.panels.MainPanel;
import com.sticks.status.GameStatus;
import com.sticks.status.MoveStatus;
import com.sticks.status.PlayerEnum;

public class ClientController {
    Client client;
    Player player;
    PlayerEnum playerEnum;
    GameStatus gameStatus;
    GamePanel gamePanel;
    MainPanel mainPanel;

    public static void main(String[] args) {
        Client client = new Client();
        Player player = new Player();

        MainPanel mainPanel = new MainPanel();
        ClientController clientController = new ClientController(client, player, mainPanel);

        client.setController(clientController);
        player.setClientController(clientController);
        mainPanel.setClientController(clientController);
        mainPanel.getGamePanel().setClientController(clientController);
    }

    public ClientController(Client client, Player player, MainPanel mainPanel) {
        this.client = client;
        this.player = player;
        this.gamePanel = mainPanel.getGamePanel();
        this.mainPanel = mainPanel;
    }

    public void connected() {
        playerEnum = client.bind();
    }

    public void disconnected() {
        client.unbind(playerEnum);
    }

    public void makeMove(Field.Position position, int x, int y) {
        if(playerEnum != null)
            client.sendMessage(new ClientMessage(playerEnum, player.getGrid().getField(position, x, y)));
    }

    public void processServerMessage(ServerMessage serverMessage) {
        mainPanel.setInfo(serverMessage.getGameStatus(), playerEnum,
                playerEnum == serverMessage.getPlayer());
        if (serverMessage.getGameStatus() == GameStatus.NEW_GAME)
            return;
        if (serverMessage.getMoveStatus() == MoveStatus.SUCCESS) {
            gameStatus = serverMessage.getGameStatus();
            player.refresh(serverMessage.getCell1(), serverMessage.getCell2(), serverMessage.getField(),
                    serverMessage.getPlayer());
            gamePanel.refresh(player.getGrid());
        }
    }
}
