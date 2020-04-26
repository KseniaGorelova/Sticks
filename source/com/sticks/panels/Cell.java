package com.sticks.panels;

import com.sticks.status.CellStatus;

import java.io.Serializable;

public class Cell implements Serializable {
    int x = 0;
    int y = 0;

    transient Field left;
    transient Field right;
    transient Field top;
    transient Field down;

    CellStatus status = CellStatus.FREE;

    public Cell() {}

    public Cell(int x, int y, Field left, Field right, Field top, Field down) {
        this.x = x;
        this.y = y;
        this.left = left;
        this.right = right;
        this.top = top;
        this.down = down;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Field getLeftField() {
        return left;
    }

    public void setLeftField(Field left) {
        this.left = left;
    }

    public Field getRightField() {
        return right;
    }

    public void setRightField(Field right) {
        this.right = right;
    }

    public Field getTopField() {
        return top;
    }

    public void setTopField(Field top) {
        this.top = top;
    }

    public Field getDownField() {
        return down;
    }

    public void setDownField(Field down) {
        this.down = down;
    }

    public CellStatus getStatus() {
        return status;
    }

    public void setStatus(CellStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Cell{" +
                "x=" + x +
                ", y=" + y +
                ", status=" + status +
                '}';
    }
}
