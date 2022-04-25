package com.sulongx.jvm.jit.monomorphicdispatch;

import com.sulongx.jvm.jit.EscapeTestArraySize;
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
 * @description 单态分派-复态
 * @date 2022/4/25 11:37
 **/
@State(Scope.Thread)
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.SECONDS)
public class PeelMegamorphicCallsite {

    private Random random = new Random();

    private Shape triangle = new Triangle();

    private Shape square = new Square();

    private Shape octagon = new Octagon();

    @Benchmark
    public int runBimorphic(){
        Shape currentShape = null;
        switch (random.nextInt(2))
        {
            case 0:
                currentShape = triangle;
                break;
            case 1:
                currentShape = square;
                break;
        }
        return currentShape.getSides();
    }

    @Benchmark
    public int runMegamorphic() {
        Shape currentShape = null;
        switch (random.nextInt(3))
        {
            case 0:
                currentShape = triangle;
                break;
            case 1:
                currentShape = square;
                break;
            case 2:
                currentShape = octagon;
                break;
        }
        return currentShape.getSides();
    }

    @Benchmark
    public int runPeeledMegamorphic() {
        Shape currentShape = null;
        switch (random.nextInt(3))
        {
            case 0:
                currentShape = triangle;
                break;
            case 1:
                currentShape = square;
                break;
            case 2:
                currentShape = octagon;
                break;
        }
        // 从原始调用点剥离出一个观察到的类型
        if (currentShape instanceof Triangle) {
            return ((Triangle) currentShape).getSides();
        }
        else {
            return currentShape.getSides(); // 现在只有双态了
        }
    }


    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(PeelMegamorphicCallsite.class.getSimpleName())
                .result("result.json")
                .resultFormat(ResultFormatType.JSON).build();
        new Runner(opt).run();
    }
}
