package com.itdct.cbench.cli.util;

import com.itdct.cbench.cli.language.LangConstant;
import com.itdct.cbench.model.CpuInfoModel;

/**
 * @author Zhouwx
 * @date 2024/10/15 23:32:38
 * @version 1.0
 * @description
 */
public class CpuInfoResultUtil {
    public static String getCpuInfoResult(CpuInfoModel cpuInfoModel) {
        String osName = System.getProperty("os.name").toLowerCase();
        System.out.println(Language.get(LangConstant.nowSystemName) + osName);

        String cpuModelName = cpuInfoModel.getCpuModelName();
        String deviceName = cpuInfoModel.getDeviceName();

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Language.get(LangConstant.cpuInfo) + "\n");
        if (cpuModelName != null) {
            stringBuilder.append(Language.get(LangConstant.cpuModelName) + cpuModelName + "\n");
        }
        if (deviceName != null) {
            stringBuilder.append(Language.get(LangConstant.deviceName) + deviceName + "\n");
        }
        stringBuilder.append(Language.get(LangConstant.cpuCoreNum) + cpuInfoModel.getCpuCoreNum() + "\n");
        stringBuilder.append(Language.get(LangConstant.cpuLogicCoreNum) + cpuInfoModel.getCpuLogicalProcessorNum() + "\n");
        stringBuilder.append(Language.get(LangConstant.cpuFrequency) + cpuInfoModel.getCpuFrequency() + "MHz\n");
        stringBuilder.append(Language.get(LangConstant.cpuInfoEnd) + "\n");
        return stringBuilder.toString();
    }
}
