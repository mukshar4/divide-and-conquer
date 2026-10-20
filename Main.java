import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        ArrayList<Location> locations = new ArrayList<>();

        locations.add(new Location("A", -3, 1));
        locations.add(new Location("B", -3, 11));
        locations.add(new Location("C", 1, 1));
        locations.add(new Location("D", 2, 15));

        ClosestPairResult result =
                TaskA.findClosestPair(locations);

        System.out.println("Closest pair: "
                + result.getFirstId()
                + " and "
                + result.getSecondId());

        System.out.println("Squared distance: "
                + result.getDistanceSquared());
    }
}
