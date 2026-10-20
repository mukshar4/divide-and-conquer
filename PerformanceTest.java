import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class PerformanceTest {

    public static void main(String[] args) {

        int[] sizes = {100, 1000, 5000};

        for (int n : sizes) {

            ArrayList<Location> locations = createLocations(n);
            ArrayList<String> planned = createPlannedOrder(n);
            ArrayList<String> actual = new ArrayList<>(planned);
            Collections.reverse(actual);

            ArrayList<Integer> deliveryTimes =
                    createDeliveryTimes(n);

            long start;
            long end;

            // Test Task A
            start = System.nanoTime();
            TaskA.findClosestPair(locations);
            end = System.nanoTime();
            double taskATime = (end - start) / 1_000_000.0;

            // Test Task B
            start = System.nanoTime();
            TaskB.countInversions(planned, actual);
            end = System.nanoTime();
            double taskBTime = (end - start) / 1_000_000.0;

            // Test Task C
            start = System.nanoTime();
            TaskC.findKthSmallest(
                    deliveryTimes,
                    n / 2 + 1);
            end = System.nanoTime();
            double taskCTime = (end - start) / 1_000_000.0;

            System.out.println("Input size: " + n);
            System.out.println("Task A: " + taskATime + " ms");
            System.out.println("Task B: " + taskBTime + " ms");
            System.out.println("Task C: " + taskCTime + " ms");
            System.out.println();
        }
    }

    private static ArrayList<Location> createLocations(int n) {
        ArrayList<Location> locations = new ArrayList<>();
        Random random = new Random(10);

        for (int i = 0; i < n; i++) {
            locations.add(new Location(
                    "P" + i,
                    i * 2L,
                    random.nextInt(100000)));
        }

        Collections.shuffle(locations, random);
        return locations;
    }

    private static ArrayList<String> createPlannedOrder(int n) {
        ArrayList<String> planned = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            planned.add("D" + i);
        }

        return planned;
    }

    private static ArrayList<Integer> createDeliveryTimes(int n) {
        ArrayList<Integer> times = new ArrayList<>();
        Random random = new Random(20);

        for (int i = 0; i < n; i++) {
            times.add(random.nextInt(100000));
        }

        return times;
    }
}
