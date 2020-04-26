package com.sticks.panels;

public class Grid {
    public static final int SIZE = 3;
    Cell[][] cells;

    Field[][] horizontal;
    Field[][] vertical;

    public Grid() {
        createCells();
        createHorizontal();
        createVertical();
        linkCellsAndFields();
    }

    private void linkCellsAndFields() {
        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++) {
                cells[i][j].setLeftField(vertical[i][j]);
                cells[i][j].setTopField(horizontal[i][j]);
                cells[i][j].setRightField(vertical[i][j + 1]);
                cells[i][j].setDownField(horizontal[i + 1][j]);
            }
    }

    private void createVertical() {
        vertical = new Field[SIZE][SIZE+1];
        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE + 1; j++)
                if (j == 0)
                    vertical[i][j] = new Field(
                            Field.Position.VERTICAL, i, j,
                            null, cells[i][j]);
                else if (j == SIZE)
                    vertical[i][j] = new Field(
                            Field.Position.VERTICAL, i, j,
                            cells[i][j - 1], null);
                else
                    vertical[i][j] = new Field(
                            Field.Position.VERTICAL, i, j,
                            cells[i][j - 1], cells[i][j]);

    }

    private void createHorizontal() {
        horizontal = new Field[SIZE+1][SIZE];
        for (int i = 0; i < SIZE + 1; i++)
            for (int j = 0; j < SIZE; j++)
                if (i == 0)
                    horizontal[i][j] = new Field(
                            Field.Position.HORIZONTAL, i, j,
                            null, cells[i][j]);
                else if (i == SIZE)
                    horizontal[i][j] = new Field(
                            Field.Position.HORIZONTAL, i, j,
                            cells[i - 1][j], null);
                else
                    horizontal[i][j] = new Field(
                            Field.Position.HORIZONTAL, i, j,
                            cells[i - 1][j], cells[i][j]);

    }

    private void createCells() {
        cells = new Cell[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                cells[i][j] = new Cell();
                cells[i][j].x = i;
                cells[i][j].y = j;
            }
        }
    }

    public Cell getCell(int i, int j) {
        return cells[i][j];
    }

    public Field getHorizontal(int i, int j) {
        return horizontal[i][j];
    }

    public Field getVertical(int i, int j) {
        return vertical[i][j];
    }

    public Field getField(Field.Position position, int i, int j) {
        if (position == Field.Position.VERTICAL)
            return getVertical(i, j);
        else return getHorizontal(i, j);
    }
}
