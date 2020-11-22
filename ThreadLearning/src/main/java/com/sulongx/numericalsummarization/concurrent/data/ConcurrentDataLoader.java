package com.sulongx.numericalsummarization.concurrent.data;

import com.sulongx.numericalsummarization.common.Record;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 描述:
 *
 * @author xiongsulong
 * @create 2020-11-03 16:55
 */
public class ConcurrentDataLoader {

    public static List<Record> load(Path path) throws IOException {
        System.out.println("Loading data...\n");

        List<String> lines = Files.readAllLines(path);

        List<Record> records = lines
                .parallelStream()
                .skip(1).map(l -> l.split(";"))
                .map(t -> new Record(t))
                .collect(Collectors.toList());

        return records;
    }
}
