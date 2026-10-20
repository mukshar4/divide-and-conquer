import java.util.ArrayList;

public class TaskC {

    public static int findKthSmallest(
            ArrayList<Integer> deliveryTimes,
            int k) {

        if (deliveryTimes == null
                || deliveryTimes.isEmpty()) {throw new IllegalArgumentException("The list cannot be empty.");
        }

        if (k < 1 || k > deliveryTimes.size()) {throw new IllegalArgumentException("k must be between 1 and the list size.");
        }

        int[] values = new int[deliveryTimes.size()];

        for (int i = 0; i < deliveryTimes.size(); i++) {
            if (deliveryTimes.get(i) == null|| deliveryTimes.get(i) < 0) {
                throw new IllegalArgumentException("Delivery times must be nonnegative.");
            }

            values[i] = deliveryTimes.get(i);
        }

        // k is 1-based, but array indexes are 0-based
        return select(values, 0, values.length - 1, k - 1);
    }

    private static int select(
            int[] values,
            int left,
            int right,
            int targetIndex) {

        if (left == right) {
            return values[left];
        }

        int pivot = choosePivot(values, left, right);

        int[] equalRange = partition(values, left, right, pivot);

        int lessEnd = equalRange[0];
        int greaterStart = equalRange[1];

        if (targetIndex < lessEnd) {
            return select(
                    values,
                    left,
                    lessEnd - 1,
                    targetIndex);
        }

        if (targetIndex > greaterStart) {
            return select(
                    values,
                    greaterStart + 1,
                    right,
                    targetIndex);
        }

        return pivot;
    }

    private static int choosePivot(
            int[] values,
            int left,
            int right) {

        int size = right - left + 1;

        if (size <= 5) {
            insertionSort(values, left, right);
            return values[left + size / 2];
        }

        int numberOfMedians = 0;

        for (int groupStart = left;
             groupStart <= right;
             groupStart += 5) {

            int groupEnd = Math.min(groupStart + 4, right);

            insertionSort(values, groupStart, groupEnd);

            int medianIndex = groupStart + (groupEnd - groupStart) / 2;

            swap(
                    values,
                    left + numberOfMedians,
                    medianIndex);

            numberOfMedians++;
        }

        int medianOfMediansIndex =
                left + numberOfMedians / 2;

        return select(
                values,
                left,
                left + numberOfMedians - 1,
                medianOfMediansIndex);
    }

    private static int[] partition(
            int[] values,
            int left,
            int right,
            int pivot) {

        int less = left;
        int current = left;
        int greater = right;

        while (current <= greater) {

            if (values[current] < pivot) {
                swap(values, less, current);
                less++;
                current++;

            } else if (values[current] > pivot) {
                swap(values, current, greater);
                greater--;

            } else {
                current++;
            }
        }

        return new int[]{less, greater};
    }

    private static void insertionSort(
            int[] values,
            int left,
            int right) {

        for (int i = left + 1; i <= right; i++) {

            int current = values[i];
            int j = i - 1;

            while (j >= left
                    && values[j] > current) {

                values[j + 1] = values[j];
                j--;
            }

            values[j + 1] = current;
        }
    }

    private static void swap(
            int[] values,
            int first,
            int second) {

        int temporary = values[first];
        values[first] = values[second];
        values[second] = temporary;
    }
}
