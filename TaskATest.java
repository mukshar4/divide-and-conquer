import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Test;

public class TaskATest {

    @Test
    void testA1() {
        ArrayList<Location> points = new ArrayList<>();
        points.add(new Location("A", 0, 0));
        points.add(new Location("B", 3, 4));

        ClosestPairResult result =
                TaskA.findClosestPair(points);

        assertEquals(25, result.getDistanceSquared());
        assertTrue(hasPair(result, "A", "B"));
    }

    @Test
    void testA2() {
        ArrayList<Location> points = new ArrayList<>();
        points.add(new Location("A", 0, 0));
        points.add(new Location("B", 7, 2));
        points.add(new Location("C", 4, 5));
        points.add(new Location("D", 5, 5));
        points.add(new Location("E", 12, 2));
        points.add(new Location("F", 2, 9));

        ClosestPairResult result =
                TaskA.findClosestPair(points);

        assertEquals(1, result.getDistanceSquared());
        assertTrue(hasPair(result, "C", "D"));
    }

    @Test
    void testA3() {
        ArrayList<Location> points = new ArrayList<>();
        points.add(new Location("A", -3, 1));
        points.add(new Location("B", -3, 11));
        points.add(new Location("C", 1, 1));
        points.add(new Location("D", 2, 15));

        ClosestPairResult result =
                TaskA.findClosestPair(points);

        assertEquals(16, result.getDistanceSquared());
        assertTrue(hasPair(result, "A", "C"));
    }

    @Test
    void testA4() {
        ArrayList<Location> points = new ArrayList<>();
        points.add(new Location("A", -5, -5));
        points.add(new Location("B", -5, -2));
        points.add(new Location("C", 6, 8));

        ClosestPairResult result =
                TaskA.findClosestPair(points);

        assertEquals(9, result.getDistanceSquared());
        assertTrue(hasPair(result, "A", "B"));
    }

    @Test
    void testAdditionalTiedDistances() {
        ArrayList<Location> points = new ArrayList<>();
        points.add(new Location("A", 0, 0));
        points.add(new Location("B", 0, 1));
        points.add(new Location("C", 1, 0));
        points.add(new Location("D", 1, 1));

        ClosestPairResult result =
                TaskA.findClosestPair(points);

        assertEquals(1, result.getDistanceSquared());
    }

    private boolean hasPair(
            ClosestPairResult result,
            String firstId,
            String secondId) {

        String first = result.getFirstLocation().getId();
        String second = result.getSecondLocation().getId();

        return (first.equals(firstId) && second.equals(secondId))
                || (first.equals(secondId) && second.equals(firstId));
    }
}
