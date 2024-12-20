import java.math.BigInteger;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkJoinPoolExample {
    public static void main(String[] args) {
//        ForkJoinPool forkJoinPool = new ForkJoinPool(20);
//        FactorialSquareCalculator factorialSquareCalculator = new FactorialSquareCalculator(2);
//        BigInteger output = forkJoinPool.invoke(factorialSquareCalculator);
//        System.out.println("output is "+output);
    }
}


class FactorialSquareCalculator  extends RecursiveTask<BigInteger> {
    private final Integer n;

    FactorialSquareCalculator(Integer n) {
        System.out.println(n);
        this.n = n;
    }

    @Override
    protected BigInteger compute() {
        if (n<=1) return BigInteger.valueOf(n);
        FactorialSquareCalculator factorialCalculator = new FactorialSquareCalculator(n - 1);
        return BigInteger.valueOf(n).multiply(BigInteger.valueOf(n)).add(factorialCalculator.join());
    }
}

class MaxElementFinder<T extends Number> extends RecursiveTask<T> {
    private final T[] array;
    private final int start;
    private final int end;
    private static final int THRESHOLD = 10;

    MaxElementFinder(T[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    private T findMaxSequentially(T[] array, int start, int end) {
        T max = array[start];
        for (int i = start + 1; i <= end; i++) {
            max = max(max, array[i]);
        }
        return max;
    }

    private T max(T num1, T num2) {
        double maxVal = Math.max(num1.doubleValue(), num2.doubleValue());
        if (num1 instanceof  Double) {
            return (T)Double.valueOf(maxVal);
        }
        else if (num1 instanceof Float) {
            return (T)Float.valueOf((float) maxVal);
        }
        else if (num1 instanceof Long) {
            return (T) Long.valueOf((long) maxVal);
        } else {
            return (T) Integer.valueOf((int) maxVal);
        }
    }


    @Override
    protected T compute() {
        if (end - start <= THRESHOLD) {
            return this.findMaxSequentially(array, start, end);
        }
        int mid = start + (end - start)/2;
        MaxElementFinder maxElementFinder = new MaxElementFinder(array, start, mid);
        MaxElementFinder maxElementFinder1 = new MaxElementFinder(array, mid + 1, end);
        maxElementFinder1.fork();
        maxElementFinder.fork();

        T rightResult = (T) maxElementFinder.compute(); // Compute right subtask synchronously
        T leftResult = (T) maxElementFinder1.join();

        return max(rightResult, leftResult);
    }
}