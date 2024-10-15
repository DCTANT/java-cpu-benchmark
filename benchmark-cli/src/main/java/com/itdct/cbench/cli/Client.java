package com.itdct.cbench.cli;

import com.itdct.cbench.cli.language.LangConstant;
import com.itdct.cbench.cli.language.LangType;
import com.itdct.cbench.cli.util.CpuBenchmarkResultUtil;
import com.itdct.cbench.cli.util.CpuInfoResultUtil;
import com.itdct.cbench.cli.util.Language;
import com.itdct.cbench.core.Benchmark;
import com.itdct.cbench.model.CpuBenchmarkResultModel;
import com.itdct.cbench.model.CpuInfoModel;
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
        while (true) {
            try {
                options();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void options() {
        System.out.println(Language.get("menu"));
        String input = new Scanner(System.in).nextLine().toUpperCase(Locale.ROOT);
        switch (input) {
            case "1":
                // 测试CPU性能
                Benchmark benchmark = new Benchmark();
                benchmark.setOnPrint(originString -> {
                    String result = originString;
                    if (originString.startsWith("开始执行第")) {
                        result = result.replace("开始执行第 ", Language.get(LangConstant.startExecute));
                        result = result.replace("轮", Language.get(LangConstant.round));
                        result = result.replace("，当前迭代次数为：", Language.get(LangConstant.nowIterationIs));
                    } else if (originString.equals("开始执行单线程分数评估")) {
                        result = Language.get(LangConstant.startSingleThreadEvaluate);
                    }else if (originString.equals("开始执行多线程分数评估")) {
                        result = Language.get(LangConstant.startMultiThreadEvaluate);
                    } else if (originString.equals("单线程任务执行完成")) {
                        result = Language.get(LangConstant.singleThreadExeFinish);
                    }else if (originString.equals("多线程任务执行完成")) {
                        result = Language.get(LangConstant.multiThreadExeFinish);
                    }

                    System.out.println(result);
                });
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
                System.out.println(CpuInfoResultUtil.getCpuInfoResult(cpuInfo));
                break;
            case "3":
                if (Language.languageType == LangType.ENGLISH) {
                    Language.languageType = LangType.CHINESE;
                } else {
                    Language.languageType = LangType.ENGLISH;
                }
                break;
            case "Q":
                // 退出程序
                System.exit(0);
                break;
            default:
                System.out.println(Language.get("wrongInput"));
        }
    }

}
