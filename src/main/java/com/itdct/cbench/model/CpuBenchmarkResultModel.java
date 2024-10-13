package com.itdct.cbench.model;

import java.util.List;

/**
 * @author Zhouwx
 * @date 2024/10/13 0:04:33
 * @version 1.0
 * @description
 */
public class CpuBenchmarkResultModel {
    /**
     * CPU信息
     */
    private CpuInfoModel cpuInfoModel;

    /**
     * 单线程分数
     */
    private double singleThreadScore;

    /**
     * 单线程结果
     */
    private SingleThreadResultModel singleThreadResultModel;

    /**
     * 多线程分数
     */
    private double totalThreadScore;

    /**
     * 多线程结果
     */
    private List<SingleThreadResultModel> totalThreadResultModels;

    /**
     * 多线程与单线程分数比值
     */
    private double multipleThreadRatio;

    public CpuInfoModel getCpuInfoModel() {
        return cpuInfoModel;
    }

    public CpuBenchmarkResultModel setCpuInfoModel(CpuInfoModel cpuInfoModel) {
        this.cpuInfoModel = cpuInfoModel;
        return this;
    }

    public double getSingleThreadScore() {
        return singleThreadScore;
    }

    public CpuBenchmarkResultModel setSingleThreadScore(double singleThreadScore) {
        this.singleThreadScore = singleThreadScore;
        return this;
    }

    public double getTotalThreadScore() {
        return totalThreadScore;
    }

    public CpuBenchmarkResultModel setTotalThreadScore(double totalThreadScore) {
        this.totalThreadScore = totalThreadScore;
        return this;
    }

    public double getMultipleThreadRatio() {
        return multipleThreadRatio;
    }

    public CpuBenchmarkResultModel setMultipleThreadRatio(double multipleThreadRatio) {
        this.multipleThreadRatio = multipleThreadRatio;
        return this;
    }

    public SingleThreadResultModel getSingleThreadResultModel() {
        return singleThreadResultModel;
    }

    public CpuBenchmarkResultModel setSingleThreadResultModel(SingleThreadResultModel singleThreadResultModel) {
        this.singleThreadResultModel = singleThreadResultModel;
        return this;
    }

    public List<SingleThreadResultModel> getTotalThreadResultModels() {
        return totalThreadResultModels;
    }

    public CpuBenchmarkResultModel setTotalThreadResultModels(List<SingleThreadResultModel> totalThreadResultModels) {
        this.totalThreadResultModels = totalThreadResultModels;
        return this;
    }
}
