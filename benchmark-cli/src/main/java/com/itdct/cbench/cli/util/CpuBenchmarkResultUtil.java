package com.itdct.cbench.cli.util;

import com.itdct.cbench.cli.language.LangConstant;
import com.itdct.cbench.model.CpuBenchmarkResultModel;
import com.itdct.cbench.model.CpuInfoModel;
import com.itdct.cbench.model.SingleThreadResultModel;

import java.math.BigDecimal;

import static com.itdct.cbench.cli.language.LangConstant.coreParticipate;
import static com.itdct.cbench.cli.language.LangConstant.coreRatio;
import static com.itdct.cbench.cli.language.LangConstant.multiThreadScore;
import static com.itdct.cbench.cli.language.LangConstant.multiThreadTotalExecute;
import static com.itdct.cbench.cli.language.LangConstant.round;
import static com.itdct.cbench.cli.language.LangConstant.singleThreadScore;
import static com.itdct.cbench.cli.language.LangConstant.singleThreadTotalExecute;
import static com.itdct.cbench.cli.language.LangConstant.total;

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
        resultBuilder.append(CpuInfoResultUtil.getCpuInfoResult(cpuInfoModel)).append("\n");
        resultBuilder.append(Language.get(singleThreadTotalExecute))
                .append(cpuBenchmarkResultModel.getSingleThreadResultModel().getLoopCount())
                .append(Language.get(round))
                .append("\n");
        resultBuilder.append(Language.get(singleThreadScore))
                .append(new BigDecimal(cpuBenchmarkResultModel.getSingleThreadScore()).setScale(2, BigDecimal.ROUND_HALF_UP)
                        .toString())
                .append("\n");
        resultBuilder.append(Language.get(multiThreadTotalExecute))
                .append(cpuBenchmarkResultModel
                        .getTotalThreadResultModels()
                        .stream()
                        .map(SingleThreadResultModel::getLoopCount)
                        .reduce(Integer::sum)
                        .get()
                )
                .append(Language.get(round))
                .append("\n");
        resultBuilder.append(Language.get(multiThreadScore))
                .append(new BigDecimal(cpuBenchmarkResultModel.getTotalThreadScore()).setScale(2, BigDecimal.ROUND_HALF_UP)
                        .toString())
                .append("\n");
        resultBuilder.append(Language.get(total))
                .append(Runtime.getRuntime().availableProcessors())
                .append(Language.get(coreParticipate))
                .append("\n");
        resultBuilder.append(Language.get(coreRatio))
                .append(cpuBenchmarkResultModel.getMultipleThreadRatio())
                .append("\n");

        return resultBuilder.toString();
    }

}
