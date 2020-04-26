package com.server;

import com.client.ClientMessage;
import com.client.RMI_Client;
import com.sticks.Host;
import com.sticks.status.GameStatus;
import com.sticks.status.PlayerEnum;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server implements RMI_Server {
    private RMI_Client player1 = null;
    private RMI_Client player2 = null;
    private ServerController serverController;

    public Server(){}

    public Server(ServerController serverController) {
        this.serverController = serverController;
    }

    public void start() {
        try {
            RMI_Server stub = (RMI_Server) UnicastRemoteObject.exportObject(this,0);
            Registry registry = LocateRegistry.createRegistry(Host.PORT);
            registry.bind(Host.sNAME,stub);
            System.out.println("Server is ready");
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    @Override
    public void getMessage(ClientMessage clientMessage) throws RemoteException {
        System.out.println(clientMessage);
        serverController.processMove(clientMessage);
    }

    @Override
    public void sendMessage(ServerMessage message, PlayerEnum player) {
        try {
            if (player == PlayerEnum.player1)
                player1.getMessage(message);
            else if (player == PlayerEnum.player2)
                player2.getMessage(message);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }

    public boolean isReadyToPlay(){
        return player1 != null && player2 != null;
    }

    @Override
    public PlayerEnum register(RMI_Client player) {
        if (player1 == null) {
            player1 = player;
            System.out.println("Player1 registered");
            try {
                player1.getMessage(new ServerMessage(null, null, null, null,
                        GameStatus.NEW_GAME, PlayerEnum.player1));
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e);
            }
            return PlayerEnum.player1;
        } else if (player2 == null) {
            player2 = player;
            System.out.println("Player2 registered");
            try {
                player1.getMessage(new ServerMessage(null, null, null, null,
                        GameStatus.CONTINUE, PlayerEnum.player1));
                player2.getMessage(new ServerMessage(null, null, null, null,
                        GameStatus.CONTINUE, PlayerEnum.player1));
            } catch (Exception e) {
                System.out.println(e);
            }

            if (isReadyToPlay())
                serverController.startGame();

            return PlayerEnum.player2;
        }
        return null;
    }

    @Override
    public void unbind(PlayerEnum playerEnum) {
        if (playerEnum == PlayerEnum.player1) {
            player1 = null;
            System.out.println("Player 1 unbind");
        }
        else if (playerEnum == PlayerEnum.player2) {
            player2 = null;
            System.out.println("Player2 unbind");
        }
    }
}
