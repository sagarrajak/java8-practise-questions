import dish.Dish;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

enum CaloricLevel {
    DIET,
    NORMAL,
    FAT
}

public class CollectorGroupingExample {
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

        // Multilevel grouping

        Map<Dish.Type, Map<CaloricLevel, List<Dish>>> collect = menu.stream().collect(Collectors.groupingBy(Dish::getType, Collectors.groupingBy(dish -> {
            if (dish.getCalories() <= 400) return CaloricLevel.DIET;
            if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
            return CaloricLevel.FAT;
        })));

        Map<Dish.Type, IntSummaryStatistics> collect1 =
                    menu.stream()
                        .collect(
                            Collectors.groupingBy(
                                Dish::getType,
                                Collectors.summarizingInt(Dish::getCalories)
                            )
                        );

        // Collecting data in subgroups


    }
}
