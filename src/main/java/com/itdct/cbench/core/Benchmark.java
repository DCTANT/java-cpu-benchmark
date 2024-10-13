package com.itdct.cbench.core;

import com.itdct.cbench.model.CpuBenchmarkResultModel;
import com.itdct.cbench.model.CpuInfoModel;
import com.itdct.cbench.model.CpuLoopResultModel;
import com.itdct.cbench.model.SingleThreadResultModel;
import com.itdct.cbench.util.GetCpuInfo;
import com.itdct.cbench.util.PiCalculatorBenchmark;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * @author Zhouwx
 * @version 1.0
 * @date 2024/10/12 23:16:57
 * @description
 */
public class Benchmark {
    private int totalBenchmarkTime = 10000;

    public CpuBenchmarkResultModel benchmark() {
        CpuBenchmarkResultModel cpuBenchmarkResultModel = new CpuBenchmarkResultModel();
        GetCpuInfo getCpuInfo = new GetCpuInfo();
        CpuInfoModel cpuInfoModel = getCpuInfo.getCpuInfo();
        cpuBenchmarkResultModel.setCpuInfoModel(cpuInfoModel);

        System.out.println("开始执行单线程分数评估");
        SingleThreadResultModel singleThreadResultModel = singleThreadBenchmark();
        cpuBenchmarkResultModel.setSingleThreadResultModel(singleThreadResultModel);
        System.out.println("单线程任务执行完成");

        System.out.println("开始执行多线程分数评估");
        Vector<SingleThreadResultModel> singleThreadResultModels = multiThreadBenchmark();
        cpuBenchmarkResultModel.setTotalThreadResultModels(singleThreadResultModels);
        System.out.println("多线程任务执行完成");
        System.out.println();

        double totalScore = 0.0;
        for (SingleThreadResultModel threadResultModel : singleThreadResultModels) {
            double score = threadResultModel.getScore();
            totalScore+= score;
        }
        double resultTotalScore = new BigDecimal(totalScore).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

        cpuBenchmarkResultModel.setSingleThreadScore(singleThreadResultModel.getScore());
        cpuBenchmarkResultModel.setTotalThreadScore(resultTotalScore);
        double ratio = resultTotalScore / singleThreadResultModel.getScore();
        cpuBenchmarkResultModel.setMultipleThreadRatio(new BigDecimal(ratio).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());

        return cpuBenchmarkResultModel;
    }

    public Vector<SingleThreadResultModel> multiThreadBenchmark() {
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        Vector<SingleThreadResultModel> totalResultModels = new Vector<>();
        for (int i = 0; i < availableProcessors; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    SingleThreadResultModel singleThreadResultModel = singleThreadBenchmark();
                    totalResultModels.add(singleThreadResultModel);
                }
            });
            thread.start();
        }

        while (totalResultModels.size() < availableProcessors) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return totalResultModels;
    }
    public SingleThreadResultModel singleThreadBenchmark() {
        SingleThreadResultModel singleThreadResultModel = new SingleThreadResultModel();
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
//                System.out.printf("第 %d 轮执行完成，用时 %d 毫秒 \n", loopNum, useTime);

                CpuLoopResultModel cpuLoopResultModel = new CpuLoopResultModel();
                cpuLoopResultModel.setLoopCount(loopNum);
                cpuLoopResultModel.setIterations(iterations);
                cpuLoopResultModel.setUseTime(useTime);
                double score = 0.00314159265358979323846 * iterations;
//                System.out.printf("第 %d 轮，获取到的CPU分数为：%f \n\n", loopNum, score);
                cpuLoopResultModel.setScore(score);
                cpuLoopResultModels.add(cpuLoopResultModel);
            } else {
                singleThreadResultModel.setLoopCount(loopNum);
                singleThreadResultModel.setFinalIterations(iterations);
//                System.out.println("时间已到，停止计算");
                break;
            }
            loopNum++;
            iterations = (int) (iterations * 1.1);
        }

        double totalScore = 0.0;
        for (CpuLoopResultModel cpuLoopResultModel : cpuLoopResultModels) {
            double score = cpuLoopResultModel.getScore();
            totalScore += score;
        }

        double resultScore = new BigDecimal(totalScore).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        singleThreadResultModel.setScore(resultScore);
//        System.out.println("总计耗时：" + (System.currentTimeMillis() - programStartTime));
        return singleThreadResultModel;
    }

    public static void main(String[] args) {
        Benchmark benchmark = new Benchmark();
        benchmark.benchmark();
    }

}
