package com.sticks.panels;

import com.sticks.status.FieldStatus;

import java.io.Serializable;

public class Field implements Serializable {
    public enum Position {
        VERTICAL,
        HORIZONTAL
    }

    FieldStatus status = FieldStatus.FREE;
    Position position;
    Cell leftTop;
    Cell rightDown;
    int x;
    int y;

    public Field(Position position, int x, int y, Cell leftTop, Cell rightDown) {
        this.position = position;
        this.x = x;
        this.y = y;
        this.leftTop = leftTop;
        this.rightDown = rightDown;
    }

    public FieldStatus getStatus(){
        return status;
    }

    public void setStatus(FieldStatus status) {
        this.status = status;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Cell getLeftTop() {
        return leftTop;
    }

    public void setLeftTop(Cell leftTop) {
        this.leftTop = leftTop;
    }

    public Cell getRightDown() {
        return rightDown;
    }

    public void setRightDown(Cell rightDown) {
        this.rightDown = rightDown;
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

    @Override
    public String toString() {
        return "Board{" +
                "status=" + status +
                ", position=" + position +
                ", leftTop=" + leftTop +
                ", rightDown=" + rightDown +
                '}';
    }
}
