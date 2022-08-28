package home.utils;

import java.util.function.Consumer;

public class Utils {

    public static void measureSpendTime(Consumer<String[]> method, String[] args) {
        long startTimeNs = System.nanoTime();
        method.accept(args);
        long endTimeNs = System.nanoTime();
        long spendTimeNs = endTimeNs - startTimeNs;

        long spendTimeMs = spendTimeNs / 1_000_000;
        if (spendTimeMs != 0) {
            System.out.println("operation takes: " + spendTimeMs + " ms");
            return;
        }

        System.out.println("operation takes: " + spendTimeNs + " ns");
    }
}
