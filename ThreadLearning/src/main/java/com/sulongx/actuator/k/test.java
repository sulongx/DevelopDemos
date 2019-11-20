package com.sulongx.actuator.k;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/**
 * @author Sulongx
 */
@BenchmarkMode(Mode.Throughput)
@Warmup(iterations = 5,time = 1,timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 10,time = 5,timeUnit = TimeUnit.SECONDS)
@Threads(8)
@Fork(2)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class test {

    @Benchmark
    public void stringAddTest(){
        String s = "";
        for(int i = 0;i < 100;i ++){
            s += i;
        }
        System.out.println(s);
    }

    @Benchmark
    public void stringBuilderAdd(){
        StringBuilder builder = new StringBuilder();
        for(int i = 0;i < 100;i ++){
            builder.append(i);
        }
        System.out.println(builder);
    }


    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder().include(test.class.getSimpleName()).output("E:\\logs\\a.log").build();
        new Runner(options).run();
    }
}
