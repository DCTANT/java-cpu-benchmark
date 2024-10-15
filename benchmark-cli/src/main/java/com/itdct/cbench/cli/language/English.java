package com.itdct.cbench.cli.language;

import java.util.HashMap;

/**
 * @author Zhouwx
 * @date 2024/10/14 21:44:42
 * @version 1.0
 * @description
 */
public class English {
    public static final HashMap<String, String> map = new HashMap<>();

    static {
        map.put(LangConstant.menu, "Welcome to Java version CPU Benchmark Tool developed by DCT\n" +
                "Functional options\n" +
                "1: Start CPU Benchmark\n" +
                "2: Get CPU Information\n" +
                "3: Change to Chinese (切换到中文，可能出现乱码)\n" +
                "Q: Quit\n" +
                "Please input your choice: \n");

        map.put(LangConstant.inputWrong, "Invalid selection, please re-enter.");
        map.put(LangConstant.singleThreadTotalExecute, "Single thread executes: ");
        map.put(LangConstant.singleThreadScore, "Single thread score: ");
        map.put(LangConstant.round, " rounds");
        map.put(LangConstant.multiThreadTotalExecute, "Multi threads execute: ");
        map.put(LangConstant.multiThreadScore, "Multi thread score: ");
        map.put(LangConstant.total, "Total ");
        map.put(LangConstant.coreParticipate, " cores participated calculate");
        map.put(LangConstant.coreRatio, "Multi thread ratio: ");
        map.put(LangConstant.startExecute, "Start execute the ");
        map.put(LangConstant.nowIterationIs, ", Now iteration is: ");

        map.put(LangConstant.startSingleThreadEvaluate, "Start performing single thread score evaluation");
        map.put(LangConstant.startMultiThreadEvaluate, "Start performing multi threads score evaluation");
        map.put(LangConstant.singleThreadExeFinish, "Single thread task execution completed");
        map.put(LangConstant.multiThreadExeFinish, " Multi threads task execution completed");

        map.put(LangConstant.nowSystemName, "Now system name is: ");
        map.put(LangConstant.cpuInfo, "<----------- CPU info ----------->");
        map.put(LangConstant.cpuInfoEnd, "<-------------------------------->");
        map.put(LangConstant.cpuModelName, "CPU model name: ");
        map.put(LangConstant.deviceName, "Device name: ");
        map.put(LangConstant.cpuCoreNum, "Number of CPU cores: ");
        map.put(LangConstant.cpuLogicCoreNum, "Number of CPU logical processors: ");
        map.put(LangConstant.cpuFrequency, "Basic CPU frequency: ");
    }
}
