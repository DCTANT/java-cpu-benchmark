package com.itdct.cbench.core;

import com.itdct.cbench.model.CpuBenchmarkResultModel;
import com.itdct.cbench.model.CpuInfoModel;
import com.itdct.cbench.model.CpuLoopResultModel;
import com.itdct.cbench.util.GetCpuInfo;
import com.itdct.cbench.util.PiCalculatorBenchmark;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * @author Zhouwx
 * @version 1.0
 * @date 2024/10/12 23:16:57
 * @description
 */
public class Benchmark {
    public CpuBenchmarkResultModel benchmark() {
        CpuBenchmarkResultModel cpuBenchmarkResultModel = new CpuBenchmarkResultModel();
        GetCpuInfo getCpuInfo = new GetCpuInfo();
        CpuInfoModel cpuInfoModel = getCpuInfo.getCpuInfo();
        cpuBenchmarkResultModel.setCpuInfoModel(cpuInfoModel);

        long programStartTime = System.currentTimeMillis();
        PiCalculatorBenchmark piCalculatorBenchmark = new PiCalculatorBenchmark();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    long nowTime = System.currentTimeMillis();
                    if (nowTime - programStartTime > 10000) {
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
                double score = 1.0 * iterations * loopNum / (useTime + 10);
                System.out.printf("第 %d 轮，获取到的CPU分数为：%f \n\n", loopNum, score);
                cpuLoopResultModel.setScore(score);
                cpuBenchmarkResultModel.getCpuLoopResultModels().add(cpuLoopResultModel);
            } else {
                System.out.println("时间已到，停止计算");
                break;
            }
            loopNum++;
            iterations = (int) (iterations * 1.2);
        }

        double totalScore = 0.0;
        for (CpuLoopResultModel cpuLoopResultModel : cpuBenchmarkResultModel.getCpuLoopResultModels()) {
            double score = cpuLoopResultModel.getScore();
            totalScore += score;
        }

        double resultScore = new BigDecimal(totalScore).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        cpuBenchmarkResultModel.setSingleThreadScore(resultScore);
        System.out.println("总计耗时：" + (System.currentTimeMillis() - programStartTime));
        System.out.println("单线程总分为：" + resultScore);

        return cpuBenchmarkResultModel;
    }

    public static void main(String[] args) {
        Benchmark benchmark = new Benchmark();
        benchmark.benchmark();
    }

}
