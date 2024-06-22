package positioning;

public class Direction {
    private int dx, dy;

    public static Direction NORTH = new Direction(0, -1);
    public static Direction SOUTH = new Direction(0, 1);
    public static Direction EAST = new Direction(1, 0);
    public static Direction WEST = new Direction(-1, 0);
    public static Direction NORTHEAST = new Direction(1, -1);
    public static Direction NORTHWEST = new Direction(-1, -1);
    public static Direction SOUTHEAST = new Direction(1, 1);
    public static Direction SOUTHWEST = new Direction(-1, 1);

    public Direction(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public int getDeltaX() {
        return this.dx;
    }

    public int getDeltaY() {
        return this.dy;
    }
}
