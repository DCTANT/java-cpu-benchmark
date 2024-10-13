package com.itdct.cbench.core;

import com.itdct.cbench.model.CpuBenchmarkResultModel;
import com.itdct.cbench.model.CpuInfoModel;
import com.itdct.cbench.model.CpuLoopResultModel;
import com.itdct.cbench.util.GetCpuInfo;
import com.itdct.cbench.util.PiCalculatorBenchmark;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Zhouwx
 * @version 1.0
 * @date 2024/10/12 23:16:57
 * @description
 */
public class Benchmark {
    private int totalBenchmarkTime = 10000;

    public void benchmark() {
        CpuBenchmarkResultModel cpuBenchmarkResultModel = new CpuBenchmarkResultModel();
        GetCpuInfo getCpuInfo = new GetCpuInfo();
        CpuInfoModel cpuInfoModel = getCpuInfo.getCpuInfo();
        cpuBenchmarkResultModel.setCpuInfoModel(cpuInfoModel);

        System.out.println("开始执行单线程分数评估");
        double singledThreadBenchmark = singleThreadBenchmark();
        System.out.println("单线程任务执行完成");

        System.out.println("开始执行多线程分数评估");
        double multiThreadBenchmark = multiThreadBenchmark();
        System.out.println("多线程任务执行完成");
        System.out.println();

        cpuBenchmarkResultModel.setSingleThreadScore(singledThreadBenchmark);
        cpuBenchmarkResultModel.setTotalThreadScore(multiThreadBenchmark);
        double ratio = multiThreadBenchmark / singledThreadBenchmark;
        cpuBenchmarkResultModel.setMultipleThreadRatio(new BigDecimal(ratio).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());

        System.out.println("单线程总分为：" + singledThreadBenchmark);
        System.out.println("多线程总分为：" + multiThreadBenchmark);
        System.out.println("多线程倍率为：" + cpuBenchmarkResultModel.getMultipleThreadRatio());

    }

    public double multiThreadBenchmark() {
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        ArrayList<Double> threadScores = new ArrayList<>();
        for (int i = 0; i < availableProcessors; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    double singledThreadBenchmark = singleThreadBenchmark();
                    threadScores.add(singledThreadBenchmark);
                }
            });
            thread.start();
        }

        while (threadScores.size() < availableProcessors) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        double totalScore = 0.0;
        for (Double threadScore : threadScores) {
            totalScore+= threadScore;
        }

        return new BigDecimal(totalScore).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
    public double singleThreadBenchmark() {
        long programStartTime = System.currentTimeMillis();
        PiCalculatorBenchmark piCalculatorBenchmark = new PiCalculatorBenchmark();

        List<CpuLoopResultModel> cpuLoopResultModels=new ArrayList<>();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    long nowTime = System.currentTimeMillis();
                    if (nowTime - programStartTime > totalBenchmarkTime) {
                        piCalculatorBenchmark.setNeedStop(true);
                        break;
                    }
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        thread.start();

        int loopNum = 1;
        int iterations = 100;
        int precision = 100; // 精度设置为 50 位
        MathContext mc = new MathContext(precision); // 设置精度
        while (true) {
            System.out.printf("开始执行第 %d 轮，当前迭代次数为：%d \n", loopNum, iterations);
            long startTime = System.currentTimeMillis();
            boolean successFinish = piCalculatorBenchmark.calculatePi(iterations, mc);
            if (successFinish) {
                long endTime = System.currentTimeMillis();
                long useTime = endTime - startTime;
                System.out.printf("第 %d 轮执行完成，用时 %d 毫秒 \n", loopNum, useTime);

                CpuLoopResultModel cpuLoopResultModel = new CpuLoopResultModel();
                cpuLoopResultModel.setLoopCount(loopNum);
                cpuLoopResultModel.setIterations(iterations);
                cpuLoopResultModel.setUseTime(useTime);
                double score = 0.1 * iterations * loopNum / (useTime + 10);
                System.out.printf("第 %d 轮，获取到的CPU分数为：%f \n\n", loopNum, score);
                cpuLoopResultModel.setScore(score);
                cpuLoopResultModels.add(cpuLoopResultModel);
            } else {
                System.out.println("时间已到，停止计算");
                break;
            }
            loopNum++;
            iterations = (int) (iterations * 1.2);
        }

        double totalScore = 0.0;
        for (CpuLoopResultModel cpuLoopResultModel : cpuLoopResultModels) {
            double score = cpuLoopResultModel.getScore();
            totalScore += score;
        }

        double resultScore = new BigDecimal(totalScore).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
//        cpuBenchmarkResultModel.setSingleThreadScore(resultScore);
        System.out.println("总计耗时：" + (System.currentTimeMillis() - programStartTime));
//        System.out.println("单线程总分为：" + resultScore);

        return resultScore;
    }

    public static void main(String[] args) {
        Benchmark benchmark = new Benchmark();
        benchmark.benchmark();
    }

}
