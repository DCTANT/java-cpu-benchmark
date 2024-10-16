package com.itdct.cbench.model;

/**
 * @author Zhouwx
 * @date 2024/10/12 23:53:40
 * @version 1.0
 * @description
 */
public class CpuInfoModel {
    /**
     * 设备类型，Windows，Linux，Android还是MAC
     */
    private String deviceType;

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

    public String getDeviceType() {
        return deviceType;
    }

    public CpuInfoModel setDeviceType(String deviceType) {
        this.deviceType = deviceType;
        return this;
    }
}
