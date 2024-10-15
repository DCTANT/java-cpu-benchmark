package com.itdct.cbench.report;

import com.itdct.cbench.model.CpuBenchmarkResultModel;

import java.util.function.Consumer;

/**
 * @author Zhouwx
 * @date 2024/10/15 22:55:12
 * @version 1.0
 * @description
 */
public abstract class ReportToServerAbstract {

    public void report(CpuBenchmarkResultModel cpuBenchmarkResultModel, String serverUrl, Consumer<String> onPrint) {
        // INFO: Zhouwx: 2024/10/15 空方法，为了服务器安全，以及上报数据的准确性，该模块不开源
        onPrint.accept("自行编译版本无法上报数据，请下载Gitee/Github上的正式发行版本");
    }

}
