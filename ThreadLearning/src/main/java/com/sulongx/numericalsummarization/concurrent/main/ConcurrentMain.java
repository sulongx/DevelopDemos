package com.sulongx.numericalsummarization.concurrent.main;

import com.sulongx.numericalsummarization.common.Record;
import com.sulongx.numericalsummarization.concurrent.data.ConcrrentStatistics;
import com.sulongx.numericalsummarization.concurrent.data.ConcurrentDataLoader;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 描述:
 *
 * @author xiongsulong
 * @create 2020-11-04 11:00
 */
public class ConcurrentMain {
    static Map<String, List<Double>> totalTimes = new LinkedHashMap<>();
    static List<Record> records;

    public static void measure(String name, Runnable r){
        long start = System.nanoTime();
        r.run();
        long end = System.nanoTime();
        totalTimes.computeIfAbsent(name, k -> new ArrayList<>()).add((end - start) / 1_000_000.0);
    }

    public static void main(String[] args) throws IOException {
        Path path = Paths.get("/Users/xiongsulong/dev/my_project/DevelopDemos/ThreadLearning/src/main/data", "Online_Retail.csv");
        List<Record> records = ConcurrentDataLoader.load(path);

        for(int i = 0; i < 10; i++){
            measure("Customer from UnitedKingdom", () -> ConcrrentStatistics.customersFromUnitedKingdom(records));
            measure("Quantity from United Kingdom", () -> ConcrrentStatistics.quantityFromUnitedKingdom(records));
            measure("Countries for product 85123A", () -> ConcrrentStatistics.countriesForProduct(records));
            measure("Quantity for Product", () -> ConcrrentStatistics.quantityForProduct(records));
            measure("Quantity for Product ok", () -> ConcrrentStatistics.quantityForProductOK(records));
            measure("Multiple Filter", () -> ConcrrentStatistics.multipleFilterData(records));
            measure("Multiple Filter with Predicate", () -> ConcrrentStatistics.multipleFilterDataPredicate(records));
            measure("Biggest Invoice Amounts", () -> ConcrrentStatistics.getBiggestInvoiceAmounts(records));
            measure("Products between 1 and 10", () -> ConcrrentStatistics.productsBetween1and10(records));
        }

        totalTimes.forEach((name, times) -> System.out.printf("%25s: %s [avg: %6.2f] ms%n", name,
                times.stream().map(t -> String.format("%6.2f", t)).collect(Collectors.joining(" ")),
                times.stream().mapToDouble(Double::doubleValue).average().getAsDouble()));
    }
}
