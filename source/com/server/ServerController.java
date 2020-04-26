package com.server;

import com.client.ClientMessage;
import com.sticks.GameSticks;
import com.sticks.status.GameStatus;
import com.sticks.status.MoveStatus;
import com.sticks.status.PlayerEnum;

public class ServerController {
    Server server;
    GameSticks game;

    public static void main(String[] args) {
        ServerController serverController = new ServerController();
        Server server = new Server(serverController);
        serverController.server = server;
        serverController.startServer();
    }

    public ServerController() {}

    public ServerController(Server server) {
        this.server = server;
    }

    public Server getServer() {
        return server;
    }

    public void setServer(Server server) {
        this.server = server;
    }

    public GameSticks getGame() {
        return game;
    }

    public void setGame(GameSticks game) {
        this.game = game;
    }

    public void startServer(){
        server.start();
    }

    public void startGame(){
        game = new GameSticks(this);
    }

    public void processMove(ClientMessage clientMessage) {
        if(!server.isReadyToPlay()) {
            server.sendMessage(new ServerMessage(null,null, null, null,
                    GameStatus.NEW_GAME, null), clientMessage.getPlayer());
            return;
        }

        game.processMove(clientMessage.getPlayer(), clientMessage.getField());
        if(game.getMoveStatus() == MoveStatus.SUCCESS) {
            ServerMessage serverMessage = new ServerMessage(game.getLeftTop(),
                    game.getRightDown(), game.getField(), game.getMoveStatus(),
                    game.getGameStatus(), game.getPlayer());
            server.sendMessage(serverMessage, PlayerEnum.player1);
            server.sendMessage(serverMessage, PlayerEnum.player2);
        } else {
            server.sendMessage(new ServerMessage(null, null, null,
                    game.getMoveStatus(), game.getGameStatus(), game.getPlayer()), clientMessage.getPlayer());
        }
    }
}
