import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        ArrayList<Integer> deliveryTimes =
                new ArrayList<>(
                        Arrays.asList(
                                18, 4, 29, 11, 4,
                                16, 35, 9, 22));

        int result =
                TaskC.findKthSmallest(deliveryTimes, 5);

        System.out.println(
                "5th smallest value: " + result);
    }
}
