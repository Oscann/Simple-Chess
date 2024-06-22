package positioning;

import rendering.Panel;

public class Coordinates {
    int x, y;

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Coordinates(Coordinates c) {
        this.x = c.getX();
        this.y = c.getY();
    }

    public static Coordinates coordsFromMouseEvent(int mouseX, int mouseY) {
        int px = (int) (mouseX / Panel.SQUARE_SIZE);
        int py = (int) (mouseY / Panel.SQUARE_SIZE);

        return new Coordinates(px, py);
    }

    public Coordinates translate(Direction dir) {
        this.x += dir.getDeltaX();
        this.y += dir.getDeltaY();

        return this;
    }

    public boolean isValid() {
        return this.getX() >= 0 &&
                this.getX() < 8 &&
                this.getY() >= 0 &&
                this.getY() < 8;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    @Override
    public String toString() {
        return String.format("Position at (%d, %d)", this.getX(), this.getY());
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;
        if (obj.getClass() != this.getClass()) {
            return false;
        }

        Coordinates coordObj = (Coordinates) obj;

        return coordObj.x == this.x && coordObj.y == this.y;
    }

    @Override
    public int hashCode() {
        return this.y * 8 + this.x;
    }
}
