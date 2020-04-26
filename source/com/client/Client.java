package com.client;

import com.server.RMI_Server;
import com.server.ServerMessage;
import com.sticks.Host;
import com.sticks.status.PlayerEnum;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Client implements RMI_Client{
    RMI_Server server;
    ClientController controller;
    public Client() {}

    public Client(ClientController controller) {
        this.controller = controller;
    }

    public void setController(ClientController controller) {
        this.controller = controller;
    }

    public PlayerEnum bind() {
        try {
            Registry registry = LocateRegistry.getRegistry(Host.HOST, Host.PORT);
            server = (RMI_Server) registry.lookup(Host.sNAME);
            System.out.println("Connected");
            RMI_Client stub = (RMI_Client) UnicastRemoteObject.exportObject( this, 0);
            return server.register(stub);
        } catch (Exception e) {
            System.err.println(e);
            e.printStackTrace();
        }
        return null;
    }

    public void unbind(PlayerEnum playerEnum) {
        try {
            server.unbind(playerEnum);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void getMessage(ServerMessage message){
        System.out.println(message);
        controller.processServerMessage(message);
    }

    public void sendMessage(ClientMessage message) {
        try {
            server.getMessage(message);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }
}

