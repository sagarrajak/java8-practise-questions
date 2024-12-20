import java.io.BufferedReader;
import java.io.IOException;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

@FunctionalInterface
interface  MyPredicate<T> {
    boolean test(T value);
}

@FunctionalInterface
interface MyConsumer<T> {
   void accept(T value);
}

@FunctionalInterface
interface MyFunction<T, R> {
    R apply(T value);
}

@FunctionalInterface
interface MySupplier<T> {
    T get();
}

interface BufferReaderProcessor {
    String process(BufferedReader b) throws IOException;
}

public class FunctionalInterfacesExamples {
    public static void main(String[] args) {
        MyPredicate<Integer>  MyIsOdd = (Integer x) -> x % 2 == 1;
        System.out.println(MyIsOdd.test(12));
        System.out.println(MyIsOdd.test(13));
        // consumer
        MyConsumer<String> MyConsumer = System.out::println;
        MyConsumer.accept("3sdsdsd");
        // function
        MyFunction<Integer, String> toStringConverter = x -> Integer.valueOf(x + 12).toString();

        System.out.println(toStringConverter.apply(12));
        System.out.println(toStringConverter.apply(30));

    }
}
