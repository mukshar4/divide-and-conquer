import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TaskB {

    public static long countInversions(
            ArrayList<String> planned,
            ArrayList<String> actual) {

        if (planned == null || actual == null) {throw new IllegalArgumentException("Orders cannot be null.");
        }

        if (planned.isEmpty()|| planned.size() != actual.size()) {throw new IllegalArgumentException("Orders must have the same nonzero size.");}

        int n = planned.size();

        // Store each planned ID with its planned-order rank
        Map<String, Integer> plannedRanks = new HashMap<>();

        for (int i = 0; i < n; i++) {
            String id = planned.get(i);

            if (id == null || plannedRanks.containsKey(id)) {throw new IllegalArgumentException("Planned IDs must be unique and non-null.");}

            plannedRanks.put(id, i + 1);
        }

        // Convert actual order into planned-order ranks
        int[] actualRanks = new int[n];
        Set<String> actualSeen = new HashSet<>();

        for (int i = 0; i < n; i++) {
            String id = actual.get(i);

            if (id == null || !actualSeen.add(id)) {
                throw new IllegalArgumentException(
                        "Actual IDs must be unique and non-null.");
            }

            Integer rank = plannedRanks.get(id);

            if (rank == null) {
                throw new IllegalArgumentException(
                        "Actual order contains an unknown ID: " + id);
            }

            actualRanks[i] = rank;
        }

        int[] temporary = new int[n];

        return countInversions(
                actualRanks,
                temporary,
                0,
                n - 1);
    }

    private static long countInversions(
            int[] values,
            int[] temporary,
            int left,
            int right) {

        if (left >= right) {
            return 0;
        }

        int middle = (left + right) / 2;

        long inversions = 0;

        inversions += countInversions(
                values, temporary, left, middle);

        inversions += countInversions(
                values, temporary, middle + 1, right);

        inversions += mergeAndCount(
                values, temporary, left, middle, right);

        return inversions;
    }

    private static long mergeAndCount(
            int[] values,
            int[] temporary,
            int left,
            int middle,
            int right) {

        int i = left;
        int j = middle + 1;
        int k = left;

        long inversions = 0;

        while (i <= middle && j <= right) {

            if (values[i] <= values[j]) {
                temporary[k] = values[i];
                i++;
            } else {
                temporary[k] = values[j];
                j++;

                // Every remaining left value forms an inversion
                inversions += middle - i + 1;
            }

            k++;
        }

        while (i <= middle) {
            temporary[k] = values[i];
            i++;
            k++;
        }

        while (j <= right) {
            temporary[k] = values[j];
            j++;
            k++;
        }

        for (int index = left; index <= right; index++) {
            values[index] = temporary[index];
        }

        return inversions;
    }
}