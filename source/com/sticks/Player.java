package com.sticks;

import com.client.ClientController;
import com.sticks.panels.Cell;
import com.sticks.panels.Field;
import com.sticks.panels.Grid;
import com.sticks.status.PlayerEnum;

public class Player {
    ClientController clientController;
    Grid grid;

    public Player() {
        grid = new Grid();
    }

    public Player(ClientController clientController) {
        this.clientController = clientController;
        grid = new Grid();
    }

    public void setClientController(ClientController clientController) {
        this.clientController = clientController;
    }

    public Grid getGrid() {
        return grid;
    }

    public void refresh(Cell cell1, Cell cell2, Field field, PlayerEnum playerEnum) {
        if(field != null)
            grid.getField(field.getPosition(), field.getX(), field.getY()).setStatus(field.getStatus());
        if(cell1 != null)
            grid.getCell(cell1.getX(), cell1.getY()).setStatus(cell1.getStatus());
        if(cell2 != null)
            grid.getCell(cell2.getX(), cell2.getY()).setStatus(cell2.getStatus());
    }
}
