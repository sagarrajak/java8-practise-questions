import dish.Dish;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectorsExamples {
    public static void main(String[] args) {
        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH)
        );
        long count = menu.stream().collect(Collectors.counting());
        // Finding maximum and minimum of values in stream of values
        Optional<Dish> collect = menu.stream().collect(Collectors.maxBy(Comparator.comparing(Dish::getCalories)));
        // Summarization
        IntSummaryStatistics collect1 = menu.stream().collect(Collectors.summarizingInt(Dish::getCalories));
        // . Joining Strings
        String joinedString = menu.stream().map(Dish::getName).collect(Collectors.joining(","));
        System.out.println(joinedString);

        // Reducer in place of collectors
        Integer totalCalories = menu
                        .stream()
                        .collect(
                                Collectors.reducing(0, Dish::getCalories, (i, j) -> i + j)
                        );
    }
}
