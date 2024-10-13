package com.itdct.cbench.model;

import java.util.ArrayList;
import java.util.HashMap;
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

    private double singleThreadScore;
    private double totalThreadScore;

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
}
