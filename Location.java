public class Location {

    private final String id;
    private final long x;
    private final long y;

    public Location(String id, long x, long y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }

    public String getId() {
        return id;
    }

    public long getX() {
        return x;
    }

    public long getY() {
        return y;
    }
}