package com.client;

import com.server.ServerMessage;
import com.sticks.status.PlayerEnum;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMI_Client extends Remote {
    void getMessage(ServerMessage message) throws RemoteException;
    void unbind(PlayerEnum player) throws RemoteException;
}
