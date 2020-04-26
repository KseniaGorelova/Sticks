package com.client;

import com.sticks.panels.Field;
import com.sticks.status.PlayerEnum;

import java.io.Serializable;

public class ClientMessage implements Serializable {
    PlayerEnum player;
    Field field;

    public ClientMessage(PlayerEnum player, Field field){
        this.player = player;
        this.field = field;
    }

    public PlayerEnum getPlayer(){
        return player;
    }

    public void setPlayer(PlayerEnum player) {
        this.player = player;
    }

    public Field getField(){
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    @Override
    public String toString() {
        return "ClientPackage{" +
                "player =" + player +
                ", field =" + field +
                '}';
    }
}
