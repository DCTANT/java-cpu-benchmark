package com.itdct.cbench.util;

import com.itdct.cbench.model.CpuBenchmarkResultModel;
import com.itdct.cbench.model.CpuInfoModel;
import com.itdct.cbench.model.SingleThreadResultModel;

import java.math.BigDecimal;

/**
 * @author Zhouwx
 * @date 2024/10/13 23:14:27
 * @version 1.0
 * @description
 */
public class CpuBenchmarkResultUtil {

    public static String getCpuBenchmarkResult(CpuBenchmarkResultModel cpuBenchmarkResultModel) {
        if (cpuBenchmarkResultModel == null) {
            return "";
        }
        StringBuilder resultBuilder = new StringBuilder();
        CpuInfoModel cpuInfoModel = cpuBenchmarkResultModel.getCpuInfoModel();
        resultBuilder.append(cpuInfoModel.toString()).append("\n");
        resultBuilder.append("单线程共执行：")
                .append(cpuBenchmarkResultModel.getSingleThreadResultModel().getLoopCount())
                .append("轮")
                .append("\n");
        resultBuilder.append("单线程分数为：")
                .append(new BigDecimal(cpuBenchmarkResultModel.getSingleThreadScore()).setScale(2, BigDecimal.ROUND_HALF_UP)
                        .toString())
                .append("\n");
        resultBuilder.append("多线程共执行：")
                .append(cpuBenchmarkResultModel
                        .getTotalThreadResultModels()
                        .stream()
                        .map(SingleThreadResultModel::getLoopCount)
                        .reduce(Integer::sum)
                        .get()
                )
                .append("轮")
                .append("\n");
        resultBuilder.append("多线程分数为：")
                .append(new BigDecimal(cpuBenchmarkResultModel.getTotalThreadScore()).setScale(2, BigDecimal.ROUND_HALF_UP)
                        .toString())
                .append("\n");
        resultBuilder.append("总共 ")
                .append(Runtime.getRuntime().availableProcessors())
                .append( " 个核心参与计算\n");
        resultBuilder.append("多线程倍率为：")
                .append(cpuBenchmarkResultModel.getMultipleThreadRatio())
                .append("\n");

        return resultBuilder.toString();
    }

}
