package com.server;

import com.sticks.panels.Cell;
import com.sticks.panels.Field;
import com.sticks.status.GameStatus;
import com.sticks.status.MoveStatus;
import com.sticks.status.PlayerEnum;

import java.io.Serializable;

public class ServerMessage implements Serializable {
    Cell cell1;
    Cell cell2;

    Field field;

    MoveStatus moveStatus;
    GameStatus gameStatus;
    PlayerEnum player;

    public ServerMessage(Cell cell1, Cell cell2, Field field, MoveStatus moveStatus, GameStatus gameStatus, PlayerEnum player) {
        this.cell1 = cell1;
        this.cell2 = cell2;
        this.field = field;
        this.moveStatus = moveStatus;
        this.gameStatus = gameStatus;
        this.player = player;
    }

    public Cell getCell1() {
        return cell1;
    }

    public void setCell1(Cell cell1) {
        this.cell1 = cell1;
    }

    public Cell getCell2() {
        return cell2;
    }

    public void setCell2(Cell cell2) {
        this.cell2 = cell2;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public MoveStatus getMoveStatus() {
        return moveStatus;
    }

    public void setMoveStatus(MoveStatus moveStatus) {
        this.moveStatus = moveStatus;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public PlayerEnum getPlayer() {
        return player;
    }

    public void setPlayer(PlayerEnum player) {
        this.player = player;
    }

    @Override
    public String toString() {
        return "ServerInfo{" +
                "cell1=" + cell1 +
                ", cell2=" + cell2 +
                ", field=" + field +
                ", moveStatus=" + moveStatus +
                ", gameStatus=" + gameStatus +
                '}';
    }
}
