package utilities;

import java.util.Objects;

public class Size {
    public final int dx, dy;

    public static Size of(int dx, int dy) {
        assert dx > 0 && dy > 0;

        return new Size(dx, dy);
    }

    private Size(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public long count() {
        return Math.abs((long) dx * dy);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Size)) return false;
        Size size = (Size) o;
        return dx == size.dx &&
                dy == size.dy;
    }

    @Override
    public int hashCode() {
        return Objects.hash(dx, dy);
    }

    @Override
    public String toString() {
        return dx + "x" + dy;
    }
}
