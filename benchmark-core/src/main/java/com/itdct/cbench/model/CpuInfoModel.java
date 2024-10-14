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

    /**
     * 设备名称（针对Android手机）
     */
    private String deviceName;

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

    public String getDeviceName() {
        return deviceName;
    }

    public CpuInfoModel setDeviceName(String deviceName) {
        this.deviceName = deviceName;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("CPU信息：\n");
        if (cpuModelName != null) {
            stringBuilder.append("CPU型号：" + cpuModelName + "\n");
        }
        if (deviceName != null) {
            stringBuilder.append("设备名称：" + deviceName + "\n");
        }
        stringBuilder.append("CPU核心数：" + cpuCoreNum + "\n");
        stringBuilder.append("CPU逻辑处理器数：" + cpuLogicalProcessorNum + "\n");
        stringBuilder.append("CPU基础主频：" + cpuFrequency + "MHz\n");
        return stringBuilder.toString();

    }
}
