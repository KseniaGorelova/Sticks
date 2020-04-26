package com.sticks.panels;

import com.client.ClientController;
import com.sticks.status.CellStatus;
import com.sticks.status.FieldStatus;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePanel {
    ClientController clientController;
    JPanel[][] cells;
    JButton[][] horizontal;
    JButton[][] vertical;
    JPanel jPanel;

    int size;
    int width;

    Color fieldColor = Color.LIGHT_GRAY;
    Color cellColor = Color.WHITE;
    Color firstCell = new Color(167, 204, 249);
    Color secondCell = new Color(242, 199, 197);
    Color firstTookColor = Color.BLUE;
    Color secondTookColor = Color.RED;

    public GamePanel(JPanel jPanel) {
        cells = new JPanel[Grid.SIZE][Grid.SIZE];
        horizontal = new JButton[Grid.SIZE+1][Grid.SIZE];
        vertical = new JButton[Grid.SIZE][Grid.SIZE+1];

        this.jPanel = jPanel;
        jPanel.setLayout(null);

        double part = 0.2;
        size = (int)(jPanel.getWidth()/(Grid.SIZE + part));
        width = (int)(size * part + 1);

        drawHorizontal();
        drawVertical();
        drawCells();
    }

    private void drawHorizontal() {
        for (int i = 0; i < Grid.SIZE + 1; i++)
            for (int j = 0; j < Grid.SIZE; j++) {
                horizontal[i][j] = createField(i,j, Field.Position.HORIZONTAL);
                jPanel.add(horizontal[i][j]);
            }
    }

    private JButton createField(int x, int y, Field.Position position) {
        JButton button = new JButton();
        if (position == Field.Position.HORIZONTAL)
            button.setBounds(x * size,
                    y * size + width, width, size - width);
        else button.setBounds(x * size + width,
                y * size, size - width, width);
        button.setBackground(fieldColor);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clientController.makeMove(position, x, y);
            }
        });
        button.setEnabled(false);
        return button;
    }

    private void drawVertical() {
        for (int i = 0; i < Grid.SIZE; i++)
            for (int j = 0; j < Grid.SIZE + 1; j++) {
                vertical[i][j] = createField(i,j, Field.Position.VERTICAL);
                jPanel.add(vertical[i][j]);
            }
    }

    private void drawCells() {
        JPanel cell = new JPanel(null);
        cell.setBounds(width / 2, width / 2,
                jPanel.getWidth() - width, jPanel.getHeight() - width);
        for (int i = 0; i < Grid.SIZE; i++)
            for (int j = 0; j < Grid.SIZE; j++) {
                cells[i][j] = createCells(i * size, j * size, size, size);
                cell.add(cells[i][j]);
            }
        jPanel.add(cell);
    }

    private JPanel createCells(int x, int y, int width, int height) {
        JPanel cell = new JPanel();
        cell.setBounds(x, y, width, height);
        cell.setBackground(cellColor);
        return cell;
    }

    public void refresh(Grid grid) {
        reCells(grid);
        reHorizontal(grid);
        reVertical(grid);
        jPanel.repaint();
    }

    private void reVertical(Grid grid) {
        for (int i = 0; i < Grid.SIZE; i++)
            for (int j = 0; j < Grid.SIZE + 1; j++)
                if (grid.getVertical(i, j).getStatus() == FieldStatus.FIRST_TOOK)
                    vertical[i][j].setBackground(firstTookColor);
                else if (grid.getVertical(i, j).getStatus() == FieldStatus.SECOND_TOOK)
                    vertical[i][j].setBackground(secondTookColor);
                else vertical[i][j].setBackground(fieldColor);
    }

    private void reHorizontal(Grid grid) {
        for (int i = 0; i < Grid.SIZE + 1; i++)
            for (int j = 0; j < Grid.SIZE; j++)
                if (grid.getHorizontal(i, j).getStatus() == FieldStatus.FIRST_TOOK)
                    horizontal[i][j].setBackground(firstTookColor);
                else if (grid.getHorizontal(i, j).getStatus() == FieldStatus.SECOND_TOOK)
                    horizontal[i][j].setBackground(secondTookColor);
                else horizontal[i][j].setBackground(fieldColor);
    }

    private void reCells(Grid grid) {
        for (int i = 0; i < Grid.SIZE; i++)
            for (int j = 0; j < Grid.SIZE; j++)
                if (grid.getCell(i, j).getStatus() == CellStatus.FIRST_TOOK)
                    cells[i][j].setBackground(firstCell);
                else if (grid.getCell(i, j).getStatus() == CellStatus.SECOND_TOOK)
                    cells[i][j].setBackground(secondCell);
                else cells[i][j].setBackground(cellColor);
    }

    public void enableField() {
        for (int i = 0; i < Grid.SIZE; i++)
            for (int j = 0; j < Grid.SIZE + 1; j++)
                vertical[i][j].setEnabled(true);
        for (int i = 0; i < Grid.SIZE + 1; i++)
            for (int j = 0; j < Grid.SIZE; j++)
                horizontal[i][j].setEnabled(true);
    }

    public void setClientController(ClientController clientController) {
        this.clientController = clientController;
    }
}
