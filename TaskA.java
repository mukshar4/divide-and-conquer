import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Set;

public class TaskA {

    public static ClosestPairResult findClosestPair(ArrayList<Location> locations) {

        // invalid input check
        if (locations == null || locations.size() < 2) {throw new IllegalArgumentException("At least two locations are required.");}

        // Make separate copies so the original list is not changed
        ArrayList<Location> pointsByX =new ArrayList<>(locations);

        ArrayList<Location> pointsByY =new ArrayList<>(locations);

        // Sort x cord 
        pointsByX.sort(Comparator.comparingLong(Location::getX).thenComparingLong(Location::getY));

        // Sort y cord 
        pointsByY.sort(Comparator.comparingLong(Location::getY).thenComparingLong(Location::getX));

        //divide-and-conquer 
        return closestPairRecursive(pointsByX, pointsByY);
    }

    private static ClosestPairResult closestPairRecursive(ArrayList<Location> pointsByX,ArrayList<Location> pointsByY) {

    int numberOfPoints = pointsByX.size();

    // Base case: compare directly when there are 3 or fewer points
    if (numberOfPoints <= 3) {
        return bruteForceClosestPair(pointsByX);
    }

    // Divide the x-sorted list into two halves
    int middle = numberOfPoints / 2;

    ArrayList<Location> leftByX = new ArrayList<>(pointsByX.subList(0, middle));

    ArrayList<Location> rightByX = new ArrayList<>(pointsByX.subList(middle, numberOfPoints));

    // Remember the exact Location objects in the left half
    Set<Location> leftPoints = Collections.newSetFromMap(new IdentityHashMap<>());

    leftPoints.addAll(leftByX);

    // Split the y-sorted list while preserving y-order
    ArrayList<Location> leftByY = new ArrayList<>();
    ArrayList<Location> rightByY = new ArrayList<>();

    for (Location point : pointsByY) {
        if (leftPoints.contains(point)) {
            leftByY.add(point);
        } else {
            rightByY.add(point);
        }
    }

    // Recursively solve the left and right halves
    ClosestPairResult leftResult = closestPairRecursive(leftByX, leftByY);

    ClosestPairResult rightResult = closestPairRecursive(rightByX, rightByY);

    // Keep the better result from the two halves
    ClosestPairResult bestResult;

    if (leftResult.getDistanceSquared()<= rightResult.getDistanceSquared()) {
        bestResult = leftResult;
    } else {
        bestResult = rightResult;
    }

    long bestDistanceSquared = bestResult.getDistanceSquared();

    // Create the vertical strip near the dividing line
    long dividingX = pointsByX.get(middle).getX();

    ArrayList<Location> strip = new ArrayList<>();

    for (Location point : pointsByY) {
        long dx = point.getX() - dividingX;

        if (dx * dx <= bestDistanceSquared) {
            strip.add(point);
        }
    }

    // Check nearby points in the strip
    for (int i = 0; i < strip.size(); i++) {

        // Compare with at most the next 7 points
        for (int j = i + 1;j < strip.size() && j <= i + 7; j++) {

            long dy = strip.get(j).getY()- strip.get(i).getY();

            // Because strip is sorted by y, we can stop here
            if (dy * dy > bestDistanceSquared) {
                break;
            }

            ClosestPairResult candidate =createResult(strip.get(i), strip.get(j));

            if (candidate.getDistanceSquared()< bestDistanceSquared) {

                bestResult = candidate;
                bestDistanceSquared = candidate.getDistanceSquared();
            }
        }
    }

    return bestResult;
    }
    
    private static ClosestPairResult bruteForceClosestPair( ArrayList<Location> points) {

    ClosestPairResult bestResult = null;

    for (int i = 0; i < points.size(); i++) {
        for (int j = i + 1; j < points.size(); j++) {

            ClosestPairResult candidate = createResult(points.get(i), points.get(j));

            if (bestResult == null|| candidate.getDistanceSquared()< bestResult.getDistanceSquared()) {

                bestResult = candidate;
            }
        }
    }

    return bestResult;
}

    private static ClosestPairResult createResult(
        Location first,
        Location second) {

    long dx = first.getX() - second.getX();
    long dy = first.getY() - second.getY();

    long distanceSquared = dx * dx + dy * dy;

    return new ClosestPairResult(first, second, distanceSquared);
}
}

