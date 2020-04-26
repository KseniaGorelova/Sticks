package com.server;

import com.client.ClientMessage;
import com.client.RMI_Client;
import com.sticks.status.PlayerEnum;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMI_Server extends Remote {
    void getMessage(ClientMessage clientMessage) throws RemoteException;
    void sendMessage(ServerMessage message, PlayerEnum player) throws RemoteException;
    PlayerEnum register(RMI_Client player) throws RemoteException;
    void unbind(PlayerEnum player) throws RemoteException;
}
