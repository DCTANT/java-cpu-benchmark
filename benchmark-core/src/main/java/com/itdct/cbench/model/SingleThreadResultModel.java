package com.itdct.cbench.model;

/**
 * @author Zhouwx
 * @date 2024/10/13 15:10:21
 * @version 1.0
 * @description 单个线程执行完成的结果
 */
public class SingleThreadResultModel {
    /**
     * 单个线程执行的分数
     */
    private double score;

    /**
     * 循环次数
     */
    private int loopCount;

    /**
     * 最终迭代次数
     */
    private int finalIterations;

    /**
     * 是否是强行终止的
     */
    private boolean abort = false;

    public double getScore() {
        return score;
    }

    public SingleThreadResultModel setScore(double score) {
        this.score = score;
        return this;
    }

    public int getLoopCount() {
        return loopCount;
    }

    public SingleThreadResultModel setLoopCount(int loopCount) {
        this.loopCount = loopCount;
        return this;
    }

    public int getFinalIterations() {
        return finalIterations;
    }

    public SingleThreadResultModel setFinalIterations(int finalIterations) {
        this.finalIterations = finalIterations;
        return this;
    }

    public boolean isAbort() {
        return abort;
    }

    public SingleThreadResultModel setAbort(boolean abort) {
        this.abort = abort;
        return this;
    }
}
