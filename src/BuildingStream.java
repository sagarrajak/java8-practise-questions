import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class BuildingStream {
    public static void main(String[] args) {
        // stream
        Stream<String> something = Stream.of("Something", "Other", "OtherThing");
        // stream from array
        int[] numbers = {2, 3, 5, 7, 11, 13};
        IntStream stream = Arrays.stream(numbers);
        // Stream from function
        //  Fibonacci tuples series
        //  (0, 1), (1, 1), (1, 2), (2, 3), (3, 5), (5, 8), (8, 13), (13, 21)....
        Stream.iterate(new long[]{0, 1}, x -> new long[]{x[1], x[0] + x[1]})
                .limit(100).forEach(itm -> {
                    System.out.println(itm[0]+" "+itm[1]);
                });
    }
}
