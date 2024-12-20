import java.util.Comparator;
import java.util.List;

enum AppleType {
    GRANNY_SMITH,
    RED_DELICIOUS,
    FUJI,
    HONEYCRISP
}


class Apple {
    private Float weight;
    private final AppleType type;

    public Apple(float weight, AppleType type) {
        this.weight = weight;
        this.type = type;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public AppleType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Apple{" +
                "weight=" + weight +
                ", type=" + type +
                '}';
    }
}
public class StreamExcersice {
    public static void main(String[] args) {
        List<Apple> apples = new java.util.ArrayList<>(List.of(
                new Apple(1.2f, AppleType.GRANNY_SMITH),
                new Apple(1.4f, AppleType.RED_DELICIOUS),
                new Apple(1.1f, AppleType.FUJI),
                new Apple(1.3f, AppleType.HONEYCRISP)
        ));

//        apples.sort((a1, a2) -> Comparator.comparing(Apple::getWeight).compare(a1, a2));
        apples.sort(Comparator.comparing(Apple::getWeight).reversed().thenComparing(Apple::getType));
        System.out.println(apples);
    }
}
