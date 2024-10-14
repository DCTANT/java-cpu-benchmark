package com.itdct.cbench.cli;

import com.itdct.cbench.core.Benchmark;
import com.itdct.cbench.model.CpuBenchmarkResultModel;
import com.itdct.cbench.model.CpuInfoModel;
import com.itdct.cbench.model.SingleThreadResultModel;
import com.itdct.cbench.util.CpuBenchmarkResultUtil;
import com.itdct.cbench.util.GetCpuInfo;

import java.math.BigDecimal;
import java.util.Locale;
import java.util.Scanner;

/**
 * @author Zhouwx
 * @date 2024/10/13 21:55:35
 * @version 1.0
 * @description
 */
public class Main {
    public static void main(String[] args) {
        Client client = new Client();
        client.run();
    }


}