package main;

public class Tuple {
    public int x;
    public int y;

    public Tuple(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Tuple(Tuple tuple) {
        this.x = tuple.x;
        this.y = tuple.y;
    }

    @Override
    public String toString() {
        return "[x=" + x + ", y=" + y + "]";
    }
}