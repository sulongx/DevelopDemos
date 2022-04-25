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
 * @description 逃逸分析-限制条件
 * @date 2022/4/25 9:43
 **/
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.SECONDS)
@State(Scope.Thread)
public class EscapeTestArraySize {

    private Random random = new Random();

    @Benchmark
    public long arraySize63(){
        long[] a = new long[63];
        a[0] = random.nextLong();
        a[1] = random.nextLong();

        return a[0] + a[1];
    }

    @Benchmark
    public long arraySize64(){
        long[] a = new long[64];
        a[0] = random.nextLong();
        a[1] = random.nextLong();

        return a[0] + a[1];
    }

    @Benchmark
    public long arraySize65(){
        long[] a = new long[65];
        a[0] = random.nextLong();
        a[1] = random.nextLong();

        return a[0] + a[1];
    }


    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(EscapeTestArraySize.class.getSimpleName())
                .result("result.json")
                .resultFormat(ResultFormatType.JSON).build();
        new Runner(opt).run();
    }
}
