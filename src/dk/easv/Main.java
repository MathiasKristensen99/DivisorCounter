package dk.easv;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {

    public static void main(String[] args) throws Exception {

        // Fetches the start time of the method.
        Instant start = Instant.now();

        // Invokes the divisor counter
        ExecutorService es = Executors.newFixedThreadPool(2);
        DivisorCounter task = new DivisorCounter(1, 50000);
        DivisorCounter task2 = new DivisorCounter(50001, 100000);
        System.out.println("Looking for the best result...");
        es.invokeAll(Arrays.asList(task, task2));
        //Runnable task = new DivisorCounter(1, 10000);
        //es.submit(task);

        // Fetches the end time of the method.
        Instant end = Instant.now();

        // Find the highest result
        Result result = DivisorCounter.getBestResult();
        System.out.println(result.getNumber() + " maxResult " + result.getDivisorCounter() + " divisors!");
        System.out.println("Duration: " + Duration.between(start, end).toMillis() + " ms");
        es.shutdown();
    }
}
