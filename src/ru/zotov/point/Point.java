package ru.zotov.point;

public class Point implements Cloneable {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public Point clone() {
        return new Point(this.x, this.y);
    }

    @Override
    public String toString() {
        return "{" + x + ";" + y + "}";
    }
}