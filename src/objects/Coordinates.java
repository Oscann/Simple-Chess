package objects;

public class Coordinates {
    int x, y;

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj.getClass() != this.getClass())
            return false;

        Coordinates coordObj = (Coordinates) obj;

        return coordObj.x == this.x && coordObj.y == this.y;
    }

    @Override
    public int hashCode() {
        return this.y * 8 + this.x;
    }
}
