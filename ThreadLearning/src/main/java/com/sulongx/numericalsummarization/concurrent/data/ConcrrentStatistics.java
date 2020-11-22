package com.sulongx.numericalsummarization.concurrent.data;

import com.sulongx.numericalsummarization.common.Invoice;
import com.sulongx.numericalsummarization.common.Record;

import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 描述:
 *
 * @author xiongsulong
 * @create 2020-11-03 17:05
 */
public class ConcrrentStatistics {

    /**
     * 统计英国客户的订单数量
     * @param records
     */
    public static void customersFromUnitedKingdom(List<Record> records){
        System.out.println("****************************");
        System.out.println("Customer from UnitedKingdom");
        Map<String, List<Record>> map = records.parallelStream()
                .filter(r -> r.getCountry().equals("United Kingdom"))
                .collect(Collectors.groupingBy(Record::getCustomer));

        map.forEach((k, l) -> System.out.println(k + ": " + l.size()));
        System.out.println("******************************");
    }

    /**
     * 统计英国的订单数量的统计信息（最大值、最小值、平均值）
     * @param records
     */
    public static void quantityFromUnitedKingdom(List<Record> records){
        System.out.println("******************************");
        System.out.println("Quantity from United Kingdom");
        DoubleSummaryStatistics statistics = records.parallelStream()
                .filter(r -> r.getCountry().equals("United Kingdom"))
                .collect(Collectors.summarizingDouble(Record::getQuantity));

        System.out.println("Min: " + statistics.getMin());
        System.out.println("Max: " + statistics.getMax());
        System.out.println("Average: " + statistics.getAverage());
        System.out.println("*******************************");
    }

    /**
     * 获取订购了产品ID为'85123A'的国家
     * @param records
     */
    public static void countriesForProduct(List<Record> records){
        System.out.println("*******************************");
        System.out.println("Countries for product 85123A");

        records.parallelStream().filter(r -> r.getStockCode().equals("85123A"))
                .map(r -> r.getCountry())
                .distinct()
                .sorted()
                .forEachOrdered(System.out::println);
        System.out.println("*******************************");
    }

    /**
     * 复用一个流操作（注意复用一个流是错误方式）
     * 统计ID为'85123A'的产品记录（产品最大值、产品最小值）
     * @param records
     */
    public static void quantityForProduct(List<Record> records){
        System.out.println("********************************");
        System.out.println("Quantity for Product");

        //------------错误方式---------------
//        IntStream  stream = records.parallelStream()
//                .filter(r -> r.getStockCode().equals("85123A"))
//                .mapToInt(r -> r.getQuantity());
//
//        System.out.println("Max quantity: " + stream.max().getAsInt());
//        System.out.println("Min quantity: " + stream.min().getAsInt());

        //------------修改方法---------------
        Supplier<IntStream>  streamSupplier = () -> records.parallelStream()
                .filter(r -> r.getStockCode().equals("85123A"))
                .mapToInt(r -> r.getQuantity());

        System.out.println("Max quantity: " + streamSupplier.get().max().getAsInt());
        System.out.println("Min quantity: " + streamSupplier.get().min().getAsInt());

        System.out.println("*********************************");
    }

    /**
     * 使用两个流操作
     * 统计ID为'85123A'的产品记录（产品最大值、产品最小值）
     * @param records
     */
    public static void quantityForProductOK(List<Record> records){
        System.out.println("*********************************");
        System.out.println("Quantity for Product ok");
        int value = records.parallelStream()
                .filter(r -> r.getStockCode().equals("85123A"))
                .mapToInt(r -> r.getQuantity())
                .max()
                .getAsInt();
        System.out.println("Max quantity: " + value);

        value = records.parallelStream()
                .filter(r -> r.getStockCode().equals("85123A"))
                .mapToInt(r -> r.getQuantity())
                .min()
                .getAsInt();
        System.out.println("Min quantity: " + value);

        System.out.println("*********************************");

    }

    /**
     * 多重过滤操作，多个流分别执行，最后合并结果
     * @param records
     */
    public static void multipleFilterData(List<Record> records){
        System.out.println("*********************************");
        System.out.println("Multiple Filter");

        Stream<Record> stream1 = records.parallelStream()
                .filter(r -> r.getQuantity() > 50);

        Stream<Record> stream2 = records.parallelStream()
                .filter(r -> r.getUnitPrice() > 10);

        Stream<Record> complete = Stream.concat(stream1, stream2);
        long value = complete.parallel()
                .unordered()
                .map(r -> r.getStockCode())
                .distinct()
                .count();

        System.out.println("Number of Products: " + value);
        System.out.println("**********************************");
    }

    /**
     * 通过复合谓词合并多重筛选，通过一个流去执行过滤操作
     * @param records
     */
    public static void multipleFilterDataPredicate(List<Record> records){
        System.out.println("**********************************");
        System.out.println("Multiple Filter with Predicate");

        Predicate<Record> p1 =  r -> r.getQuantity() > 50;
        Predicate<Record> p2 =  r -> r.getUnitPrice() > 10;

        Predicate<Record> pred = Stream.of(p1, p2)
                .reduce(Predicate::or)
                .get();
        long value = records.parallelStream()
                .filter(pred)
                .count();
        System.out.println("Number of products " + value);
        System.out.println("***********************************");
    }

    /**
     * 计算每个产品的票据信息，列出前10个（出货量前10）
     * @param records
     */
    public static void getBiggestInvoiceAmounts(List<Record> records){
        System.out.println("***********************************");
        System.out.println("Biggest Invoice Amounts");

        ConcurrentMap<String, List<Record>> map = records
                .stream()
                .unordered()
                .parallel()
                .collect(Collectors.groupingByConcurrent(Record::getId));

        ConcurrentLinkedDeque<Invoice> invoices = new ConcurrentLinkedDeque();
        map.values().parallelStream().forEach(list ->{
            Invoice invoice = new Invoice();
            invoice.setId(list.get(0).getId());
            double amount = list.stream()
                    .mapToDouble(r -> r.getUnitPrice() * r.getQuantity())
                    .sum();
            invoice.setAmmount(amount);
            invoice.setCustomerId(list.get(0).getCustomer());

            invoices.add(invoice);
        });

        System.out.println("Invoices: " + invoices.size() + ": " + map.getClass());
        invoices.stream()
                .sorted(Comparator.comparingDouble(Invoice::getAmmount).reversed())
                .limit(10)
                .forEachOrdered(invoice -> {
                    System.out.println("Customer: " + invoice.getCustomerId()
                            + "; Amount: " + invoice.getAmmount());
                });

        System.out.println("*******************************************************");
    }


    /**
     * 统计产品单价在 1 - 10 之间的数量
     * @param records
     */
    public static void productsBetween1and10(List<Record> records){
        System.out.println("**************************************");
        System.out.println("Products between 1 and 10");
        int count = records.stream().unordered().parallel()
                .filter(r -> (r.getUnitPrice() >= 1) && (r.getUnitPrice() <= 10))
                .map(r -> r.getStockCode())
                .distinct()
                .mapToInt(r -> 1)
                .reduce(0, Integer::sum);
        System.out.println("Products between 1 and 10: " + count);
        System.out.println("***************************************");
    }
}
