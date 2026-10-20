public class ClosestPairResult {

    private final Location firstLocation;
    private final Location secondLocation;
    private final long distanceSquared;

    public ClosestPairResult(
            Location firstLocation,
            Location secondLocation,
            long distanceSquared) {

        this.firstLocation = firstLocation;
        this.secondLocation = secondLocation;
        this.distanceSquared = distanceSquared;
    }

    public Location getFirstLocation() {
        return firstLocation;
    }

    public Location getSecondLocation() {
        return secondLocation;
    }

    public long getDistanceSquared() {
        return distanceSquared;
    }

    public String getFirstId() {
        return firstLocation.getId();
    }

    public String getSecondId() {
        return secondLocation.getId();
    }

    @Override
    public String toString() {
        return firstLocation.getId() + ", "
                + secondLocation.getId()
                + "; d2 = " + distanceSquared;
    }
}
