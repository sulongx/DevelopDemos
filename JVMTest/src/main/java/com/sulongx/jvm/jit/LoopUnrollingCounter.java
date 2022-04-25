package com.sulongx.jvm.jit;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author sulongx
 * @version 1.0
 * @description 逃逸分析-循环展开
 * @date 2022/4/24 16:35
 **/
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.SECONDS)
@State(Scope.Thread)
public class LoopUnrollingCounter {
    private static final int MAX = 1_000_000;

    private long[] data = new long[MAX];

    @Setup
    public void createData(){
        Random random = new Random();
        for (int i = 0; i < MAX; i++) {
            data[i] = random.nextLong();
        }
    }

    @Benchmark
    public long intStride(){
        long sum = 0L;
        for (int i = 0; i < MAX; i++) {
            sum += data[i];
        }
        return sum;
    }

    @Benchmark
    public long longStride(){
        long sum = 0L;
        for (long i = 0; i < MAX; i++) {
            sum += data[(int) i];
        }
        return sum;
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(LoopUnrollingCounter.class.getSimpleName())
                .result("result.json")
                .resultFormat(ResultFormatType.JSON).build();
        new Runner(opt).run();
    }
}
