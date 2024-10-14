package com.itdct.cbench.cli;

import com.itdct.cbench.core.Benchmark;
import com.itdct.cbench.model.CpuBenchmarkResultModel;
import com.itdct.cbench.model.CpuInfoModel;
import com.itdct.cbench.util.CpuBenchmarkResultUtil;
import com.itdct.cbench.util.GetCpuInfo;

import java.util.Locale;
import java.util.Scanner;

/**
 * @author Zhouwx
 * @date 2024/10/14 9:39:13
 * @version 1.0
 * @description
 */
public class Client {

    public void run(){
        System.out.println("欢迎来到DCT制作的Java版CPU性能测试工具\n" +
                "以下是功能选项：\n" +
                "1：开始CPU性能测试\n" +
                "2：获取CPU信息\n" +
                "Q：退出程序\n");

        while (true) {
            try {
                options();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void options() {
        System.out.print("请输入你的选择：");
        String input = new Scanner(System.in).nextLine().toUpperCase(Locale.ROOT);
        switch (input) {
            case "1":
                // 测试CPU性能
                Benchmark benchmark = new Benchmark();
                CpuBenchmarkResultModel cpuBenchmarkResultModel = benchmark.benchmark();
                if (cpuBenchmarkResultModel == null) {
                    return;
                }
                String cpuBenchmarkResult = CpuBenchmarkResultUtil.getCpuBenchmarkResult(cpuBenchmarkResultModel);
                System.out.println(cpuBenchmarkResult);
                break;
            case "2":
                // 获取CPU信息
                GetCpuInfo getCpuInfo = new GetCpuInfo();
                CpuInfoModel cpuInfo = getCpuInfo.getCpuInfo();
                System.out.println(cpuInfo);
                break;
            case "Q":
                // 退出程序
                System.exit(0);
                break;
            default:
                System.out.println("无效的选择，请重新输入。");
        }
    }

}
