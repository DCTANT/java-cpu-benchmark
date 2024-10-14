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

        if (osName.contains("linux")) {
            String bashResult = getBashResult("uname -a");
            if (bashResult.endsWith("Android")) {
                cpuInfoModel.setDeviceName(getBashResult("getprop ro.product.brand") + " " + getBashResult("getprop ro.product.model"));
                String cpuInfo = getBashResult("cat /proc/cpuinfo");
                if (cpuInfo != null && !cpuInfo.isEmpty()) {
                    int processorNum = 0;
                    for (String string : cpuInfo.split("\n")) {
                        if (string.startsWith("processor")) {
                            processorNum++;
                        } else if (string.startsWith("Hardware")) {
                            String cpuName = string.split(":")[1].trim();
                            cpuInfoModel.setCpuModelName(cpuName);
                        }
                    }
                    cpuInfoModel.setCpuCoreNum(processorNum);
                }
                if (cpuInfoModel.getCpuModelName() == null || cpuInfoModel.getCpuModelName().isEmpty()) {
                    cpuInfoModel.setCpuModelName(getBashResult("getprop ro.soc.manufacturer") + " " + getBashResult("getprop ro.soc.model"));
                }
                cpuInfoModel.setCpuLogicalProcessorNum(getLogicProcessorNum());
            } else {
                try {
                    cpuInfoModel.setCpuModelName(getLinuxCpuInfo("model name"));
                    cpuInfoModel.setCpuCoreNum(Integer.parseInt(getLinuxCpuInfo("cpu cores")));
                    cpuInfoModel.setCpuLogicalProcessorNum(getLogicProcessorNum());
                    cpuInfoModel.setCpuFrequency((int) Double.parseDouble(getLinuxCpuInfo("cpu MHz")));
                } catch (Exception e) {
                    System.out.println("无法正确获取CPU信息");
                }
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

    private static String getBashResult(String command) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            Process process = Runtime.getRuntime().exec(command);
            BufferedReader combinedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String combinedLine;
            while ((combinedLine = combinedReader.readLine()) != null) {
                stringBuilder.append(combinedLine);
            }
            combinedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        String bashResult = getBashResult("wmic cpu get Name");
        System.out.println(bashResult);
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
