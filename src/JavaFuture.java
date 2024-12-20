import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class JavaFuture {
    private final ExecutorService executor =  Executors.newSingleThreadExecutor();
    private Future<Integer> calculate(Integer input) {
        return executor.submit(() -> {
          Thread.sleep(1000);
          return input*1000;
        });
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        JavaFuture javaFuture = new JavaFuture();
//        Future<Integer> calculate = javaFuture.calculate(1222);
//        Integer i = calculate.get();
//        System.out.println(i);

        // Using CompletableFuture as a Simple Future
        CompletableFuture<Void> word = CompletableFuture.supplyAsync(() -> "Hello")
                .thenApply(s -> s + " World")
                .thenCompose(s -> CompletableFuture.supplyAsync(() -> s + " Something"))
                .thenAccept(System.out::println);
        word.get();
        // combining multiple future in java
        CompletableFuture<String> combined = CompletableFuture.supplyAsync(() -> "Combine multiple")
                .thenCombine(CompletableFuture.supplyAsync(() -> " World"), (s1, s2) -> s1 + s2);

        UserService userService = new UserService();
        userService.getAllUsers();
    }
}

class UserService {
    public CompletableFuture<String> getUserById(Long userId) {
        return CompletableFuture.supplyAsync(() -> {
            // Simulate a delay
            try { Thread.sleep(1000);  } catch (InterruptedException ignored) {}
            System.out.println("Executing for id "+ userId);
            return "User" + userId;
        });
    }

    void getAllUsers() {
        UserService userService = new UserService();
        List<Long> list = Arrays.asList(1L, 2L, 5L, 8L, 3L, 10L, 20L, 21L, 22L, 23L, 24L, 25L, 26L, 27L, 28L, 29L, 30L, 31L);
        List<CompletableFuture<String>> futures = list.stream().map(userService::getUserById).toList();
        CompletableFuture<Void> allFutures = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
        allFutures.join();
        List<String> output = futures.stream().map(CompletableFuture::join)
                .toList();
        System.out.println(output);
    }
}