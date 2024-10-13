package com.itdct.cbench.model;

/**
 * @author Zhouwx
 * @date 2024/10/12 23:53:40
 * @version 1.0
 * @description
 */
public class CpuInfoModel {
    /**
     * CPU名称
     */
    private String cpuModelName;

    /**
     * CPU核心数
     */
    private int cpuCoreNum;

    /**
     * CPU逻辑处理器数
     */
    private int cpuLogicalProcessorNum;

    /**
     * CPU频率
     */
    private int cpuFrequency;

    public String getCpuModelName() {
        return cpuModelName;
    }

    public CpuInfoModel setCpuModelName(String cpuModelName) {
        this.cpuModelName = cpuModelName;
        return this;
    }

    public int getCpuCoreNum() {
        return cpuCoreNum;
    }

    public CpuInfoModel setCpuCoreNum(int cpuCoreNum) {
        this.cpuCoreNum = cpuCoreNum;
        return this;
    }

    public int getCpuLogicalProcessorNum() {
        return cpuLogicalProcessorNum;
    }

    public CpuInfoModel setCpuLogicalProcessorNum(int cpuLogicalProcessorNum) {
        this.cpuLogicalProcessorNum = cpuLogicalProcessorNum;
        return this;
    }

    public int getCpuFrequency() {
        return cpuFrequency;
    }

    public CpuInfoModel setCpuFrequency(int cpuFrequency) {
        this.cpuFrequency = cpuFrequency;
        return this;
    }

    @Override
    public String toString() {
        return "CPU信息：\n" +
                "CPU型号：" + cpuModelName + "\n" +
                "CPU核心数：" + cpuCoreNum + "\n" +
                "CPU逻辑处理器数：" + cpuLogicalProcessorNum + "\n" +
                "CPU基础主频：" + cpuFrequency + "MHz\n";

    }
}
