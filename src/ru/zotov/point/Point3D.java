package ru.zotov.point;

public class Point3D extends Point {
    private int z;

    public Point3D(int x, int y, int z) {
        super(x, y);
        this.z = z;
    }

    @Override
    public String toString() {

        return "{" + getX() + ";" + getY() + ";" + z + "}";
    }
}
