package transaction;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CollectingData {
    public static void main(String[] args) {
        List<TransactionExample.Transaction> transactions = TransactionExample.getTransactions();
        Map<String, List<TransactionExample.Transaction>> collect =
                transactions.stream()
                        .collect(Collectors.groupingBy(t -> t.getTrader().getCity()));
        collect.forEach((key, value) -> {
            System.out.println(key);
            System.out.println(value);
        });

        transactions.stream()
                .collect(Collectors.groupingBy(t -> t.getTrader().getCity()));
    }
}
