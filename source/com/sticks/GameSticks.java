package com.sticks;

import com.server.ServerController;
import com.sticks.panels.Cell;
import com.sticks.panels.Field;
import com.sticks.panels.Grid;
import com.sticks.status.*;

public class GameSticks {
    ServerController serverController;
    Grid grid;
    PlayerEnum player = PlayerEnum.player1;
    GameStatus gameStatus;
    MoveStatus moveStatus;
    Field field;
    Cell leftTop;
    Cell rightDown;

    public GameSticks(ServerController serverController) {
        this.serverController = serverController;
        grid = new Grid();
        gameStatus = GameStatus.CONTINUE;
    }

    public PlayerEnum getPlayer() {
        return player;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public MoveStatus getMoveStatus() {
        return moveStatus;
    }

    public Field getField() {
        return field;
    }

    public Cell getLeftTop() {
        return leftTop;
    }

    public Cell getRightDown() {
        return rightDown;
    }

    public void processMove(PlayerEnum player, Field field) {
        if(!validate(player, field)){
            moveStatus = MoveStatus.FAILED;
            return;
        }
        this.field = grid.getField(field.getPosition(), field.getX(), field.getY());
        if(player == PlayerEnum.player1)
            this.field.setStatus(FieldStatus.FIRST_TOOK);
        else this.field.setStatus(FieldStatus.SECOND_TOOK);

        boolean firstTook = false;
        boolean secondTook = false;

        if(field.getLeftTop()!=null) {
            leftTop = grid.getCell(field.getLeftTop().getX(),
                    field.getLeftTop().getY());
            firstTook = processCell(leftTop, this.field);
        }
        if(field.getRightDown() != null) {
            rightDown = grid.getCell(field.getRightDown().getX(),
                    field.getRightDown().getY());
            secondTook = processCell(rightDown, this.field);
        }

        checkStatus(player);

        if(!firstTook && !secondTook && gameStatus == GameStatus.CONTINUE)
            change();
        moveStatus = MoveStatus.SUCCESS;
    }

    private boolean processCell(Cell cell, Field lastField) {
        if(cell == null || cell.getStatus() != CellStatus.FREE)
            return false;
        FieldStatus leftStatus = cell.getLeftField().getStatus();
        FieldStatus rightStatus = cell.getRightField().getStatus();
        FieldStatus topStatus = cell.getTopField().getStatus();
        FieldStatus downStatus = cell.getDownField().getStatus();

        if((leftStatus == FieldStatus.FIRST_TOOK || leftStatus == FieldStatus.SECOND_TOOK) &&
                (rightStatus == FieldStatus.FIRST_TOOK || rightStatus == FieldStatus.SECOND_TOOK) &&
                (topStatus == FieldStatus.FIRST_TOOK || topStatus == FieldStatus.SECOND_TOOK) &&
                (downStatus == FieldStatus.FIRST_TOOK || downStatus == FieldStatus.SECOND_TOOK)) {
            if (lastField.getStatus() == FieldStatus.FIRST_TOOK)
                cell.setStatus(CellStatus.FIRST_TOOK);
            else cell.setStatus(CellStatus.SECOND_TOOK);
            return  true;
        }
        return false;
    }

    private void checkStatus(PlayerEnum player) {
        boolean allFull = true;
        int first = 0;
        int second = 0;
        for (int i = 0; i < grid.SIZE; i++)
            for (int j = 0; j < grid.SIZE; j++) {
                if (grid.getCell(i, j).getStatus() == CellStatus.FREE) {
                    allFull = false;
                    break;
                }
                else if (grid.getCell(i,j).getStatus()==CellStatus.FIRST_TOOK)
                    first++;
                else if (grid.getCell(i,j).getStatus()==CellStatus.SECOND_TOOK)
                    second++;
            }
        if (allFull == true)
            if (first > second)
                gameStatus = GameStatus.FIRST_WON;
            else if (second < first)
                gameStatus = GameStatus.SECOND_WON;
            else
                gameStatus = GameStatus.DRAWN_GAME;
    }

    private boolean validate(PlayerEnum player, Field field) {
        if(this.player != player)
            return false;
        if(field.getStatus() != FieldStatus.FREE)
            return false;
        return true;
    }

    private void change(){
        if(player == PlayerEnum.player1)
            player = PlayerEnum.player2;
        else
            player = PlayerEnum.player1;
    }
}
