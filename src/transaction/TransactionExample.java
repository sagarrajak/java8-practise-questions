package transaction;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TransactionExample {
    public static void main(String[] args) {
        List<Transaction> transactions = getTransactions();

        // Find all transactions in the year 2011 and sort them by value (small to high).
        List<Transaction> list = transactions
                .stream()
                .filter(item -> item.year == 2011)
                .sorted(Comparator.comparing(Transaction::getValue).reversed())
                .toList();

        // What are all the unique cities where the traders work?
        Set<String> uniqueCity = transactions
                .stream()
                .map(item -> item.trader.city)
                .distinct()
                .collect(Collectors.toSet());

        // 3. Find all traders from Cambridge and sort them by name
        List<Transaction> cambridgeTraders = transactions.stream()
                .filter(item -> item.trader.city.equalsIgnoreCase("cambridge"))
                .sorted(Comparator.comparing(item -> item.trader.name))
                .toList();
        System.out.println(cambridgeTraders);

        // Return a string of all traders’ names sorted alphabetically
        String allTradersName = transactions.stream()
                .map(transaction -> transaction.trader.name)
                .sorted()
                .reduce("", (n1, n2) -> n1 + n2);
//        System.out.println(allTradersName);

        // What’s the highest value of all the transactions?
//             transactions.stream()
//                .map(Transaction::getValue)
//                .reduce(Integer::max)
//                .ifPresent(System.out::println);
//
//        // Print all transactions’ values from the traders living in
//        //Cambridge
//        transactions.stream()
//                .filter(item -> item.trader.city.equalsIgnoreCase("cambridge"))
//                .map(Transaction::getValue)
//                .forEach(System.out::println);
    }

    public static List<Transaction> getTransactions() {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario","Milan");
        Trader alan = new Trader("Alan","Cambridge");
        Trader brian = new Trader("Brian","Cambridge");
        return Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
    }

    static class Trader{
        private final String name;
        private final String city;
        public Trader(String n, String c){
            this.name = n;
            this.city = c;
        }
        public String getName(){
            return this.name;
        }
        public String getCity(){
            return this.city;
        }
        public String toString(){
            return "Trader:"+this.name + " in " + this.city;
        }
    }

    static class Transaction {
        private final Trader trader;
        private final int year;
        private final int value;
        public Transaction(Trader trader, int year, int value){
            this.trader = trader;
            this.year = year;
            this.value = value;
        }
        public Trader getTrader(){
            return this.trader;
        }
        public int getYear(){
            return this.year;
        }
        public int getValue(){
            return this.value;
        }
        public String toString(){
            return "{" + this.trader + ", " +
                    "year: "+this.year+", " +
                    "value:" + this.value +"}";
        }
    }


}
