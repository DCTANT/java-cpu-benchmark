package com.itdct.cbench.util;

import com.itdct.cbench.model.CpuInfoModel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

/**
 * @author Zhouwx
 * @version 1.0
 * @date 2024/10/12 23:34:47
 * @description
 */
public class GetCpuInfo {
    public static void main(String[] args) {
        System.out.println(new GetCpuInfo().getCpuInfo());
    }

    public CpuInfoModel getCpuInfo() {
        CpuInfoModel cpuInfoModel = new CpuInfoModel();
        String osName = System.getProperty("os.name").toLowerCase();


        if (osName.contains("linux")) {
            cpuInfoModel.setCpuModelName(getLinuxCpuInfo("model name"));
            cpuInfoModel.setCpuCoreNum(Integer.parseInt(getLinuxCpuInfo("cpu cores")));
            cpuInfoModel.setCpuLogicalProcessorNum(Runtime.getRuntime().availableProcessors());
            cpuInfoModel.setCpuFrequency(Integer.parseInt(getLinuxCpuInfo("cpu MHz")));
        } else if (osName.contains("windows")) {
            cpuInfoModel.setCpuModelName(getWindowsCpuInfo("Name"));
            cpuInfoModel.setCpuCoreNum(Integer.parseInt(getWindowsCpuInfo("NumberOfCores")));
            cpuInfoModel.setCpuLogicalProcessorNum(Integer.parseInt(getWindowsCpuInfo("NumberOfLogicalProcessors")));
            cpuInfoModel.setCpuFrequency(Integer.parseInt(getWindowsCpuInfo("CurrentClockSpeed")));
        } else {
            System.out.println("Unsupported OS: " + osName);
        }

        return cpuInfoModel;
    }

    private static String getLinuxCpuInfo(String modelName) {
        try {
            File file = new File("/proc/cpuinfo");
            FileInputStream fis = new FileInputStream(file);
            FileChannel channel = fis.getChannel();
            MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
            String content = StandardCharsets.UTF_8.decode(buffer).toString();
            String[] lines = content.split("\n");
            for (String line : lines) {
                if (line.startsWith(modelName)) {
                    return line.split(":")[1].trim();
                }
            }
            channel.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
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
