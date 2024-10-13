package com.itdct.cbench.model;

/**
 * @author Zhouwx
 * @date 2024/10/13 0:10:27
 * @version 1.0
 * @description CPU单次执行圆周率计算结果
 */
public class CpuLoopResultModel {
    private int loopCount;
    private int iterations;

    private long useTime;

    private double score;

    public int getLoopCount() {
        return loopCount;
    }

    public CpuLoopResultModel setLoopCount(int loopCount) {
        this.loopCount = loopCount;
        return this;
    }

    public int getIterations() {
        return iterations;
    }

    public CpuLoopResultModel setIterations(int iterations) {
        this.iterations = iterations;
        return this;
    }

    public long getUseTime() {
        return useTime;
    }

    public CpuLoopResultModel setUseTime(long useTime) {
        this.useTime = useTime;
        return this;
    }

    public double getScore() {
        return score;
    }

    public CpuLoopResultModel setScore(double score) {
        this.score = score;
        return this;
    }
}
