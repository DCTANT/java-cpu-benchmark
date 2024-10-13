package com.itdct.cbench.util;

import com.itdct.cbench.model.CpuInfoModel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Zhouwx
 * @version 1.0
 * @date 2024/10/12 23:34:47
 * @description
 */
public class GetCpuInfo {
    public CpuInfoModel getCpuInfo() {
        CpuInfoModel cpuInfoModel = new CpuInfoModel();
        String osName = System.getProperty("os.name").toLowerCase();
        System.out.println("当前系统名称为：" + osName);

        // TODO: Zhouwx: 2024/10/14 Termux获取手机型号
        if (osName.contains("linux")) {
            try {
                cpuInfoModel.setCpuModelName(getLinuxCpuInfo("model name"));
                cpuInfoModel.setCpuCoreNum(Integer.parseInt(getLinuxCpuInfo("cpu cores")));
                cpuInfoModel.setCpuLogicalProcessorNum(getLogicProcessorNum());
                cpuInfoModel.setCpuFrequency((int) Double.parseDouble(getLinuxCpuInfo("cpu MHz")));
            } catch (Exception e) {
                System.out.println("无法正确获取CPU信息");
            }
        } else if (osName.contains("windows")) {
            cpuInfoModel.setCpuModelName(getWindowsCpuInfo("Name"));
            cpuInfoModel.setCpuCoreNum(Integer.parseInt(getWindowsCpuInfo("NumberOfCores")));
            cpuInfoModel.setCpuLogicalProcessorNum(Integer.parseInt(getWindowsCpuInfo("NumberOfLogicalProcessors")));
            cpuInfoModel.setCpuFrequency((int) Double.parseDouble(getWindowsCpuInfo("CurrentClockSpeed")));
        } else {
            System.out.println("不支持该系统: " + osName);
        }

        return cpuInfoModel;
    }

    private static String getLinuxCpuInfo(String modelName) {
        String path = "/proc/cpuinfo";
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith(modelName)) {
                    return line.split(":")[1].trim();
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading " + path + ": " + e.getMessage());
            e.printStackTrace();
        }
        return "";
    }

    private static int getLogicProcessorNum() {
        int totalCount = 0;
        String path = "/proc/cpuinfo";
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("processor")) {
                    totalCount++;
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading " + path + ": " + e.getMessage());
            e.printStackTrace();
        }
        return totalCount;
    }

    private static String getWindowsCpuInfo(String type) {
        try {
            Process process = Runtime.getRuntime().exec("wmic cpu get " + type);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            boolean firstLine = true;
            StringBuilder stringBuilder = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue; // Skip the header line
                }
//                return line.trim();
                stringBuilder.append(line + "\n");
            }
            reader.close();
            process.waitFor();
            return stringBuilder.toString().trim();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
