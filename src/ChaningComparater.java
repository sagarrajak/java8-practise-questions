import java.util.List;
import java.util.function.Predicate;

public class ChaningComparater {
    public static void main(String[] args) {
        Predicate<Apple> pre = apple -> apple.getWeight() > 1;
        List<Apple> apples = new java.util.ArrayList<>(List.of(
                new Apple(1.2f, AppleType.GRANNY_SMITH),
                new Apple(1.4f, AppleType.RED_DELICIOUS),
                new Apple(1.1f, AppleType.FUJI),
                new Apple(1.3f, AppleType.HONEYCRISP),
                new Apple(0.9f, AppleType.FUJI),
                new Apple(0.8f, AppleType.HONEYCRISP),
                new Apple(0.11f, AppleType.RED_DELICIOUS)
        ));
        apples.stream().filter(pre.negate()).forEach(System.out::println);

        // composing function

    }
}
