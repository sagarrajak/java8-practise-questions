package dish;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Basic {
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
        List<String> higherCalorieDishName = menu.stream()
                .filter(d -> d.getCalories() > 300)
                .map(Dish::getName)
                .limit(3)
                .toList();
//        System.out.println(higherCalorieDishName);
        String[] words =  {"Goodbye", "World"};
        List<String> list = Arrays.stream(words)
                .map(word -> word.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .toList();
//        System.out.println(list);
        // Given two lists of numbers, how would you return all pairs of numbers? For example, given a
        //list [1, 2, 3] and a list [3, 4] you should return [(1, 3), (1, 4), (2, 3), (2, 4), (3, 3), (3, 4)]. For
        //simplicity, you can represent a pair as an array with two elements.
        List<Integer> list1 = Arrays.asList(1, 2, 3);
        List<Integer> list2 = Arrays.asList(3, 4);
        List<int[]> finalList = list1.stream()
                            .flatMap(item ->
                                    list2.stream()
                                            .filter(ls -> (ls+ item) % 3 == 0)
                                            .map(ls -> new int[]{item, ls})
                            )
                            .toList();
        finalList.forEach(item -> System.out.println(item[0]+" "+item[1]));
        // numeric stream
        int sum = menu.stream().mapToInt(Dish::getCalories).sum();
        // max of the stream
        int maxValue = menu.stream().mapToInt(Dish::getCalories).max().orElse(0);
        System.out.println(maxValue);
        // range stream
        IntStream.rangeClosed(1, 1000).boxed().forEach(System.out::println);
    }

}
