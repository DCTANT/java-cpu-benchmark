package com.itdct.cbench.cli.language;

import java.util.HashMap;

/**
 * @author Zhouwx
 * @date 2024/10/14 21:44:36
 * @version 1.0
 * @description
 */
public class Chinese {
    public static final HashMap<String, String> map = new HashMap<>();

    static {
        map.put(LangConstant.menu, "欢迎来到DCT制作的Java版CPU性能测试工具\n" +
                "以下是功能选项：\n" +
                "1：开始CPU性能测试\n" +
                "2：获取CPU信息\n" +
                "3：Change language to English (if error encoding in Chinese)\n" +
                "Q：退出程序\n" +
                "请输入你的选择：\n");

        map.put(LangConstant.inputWrong, "无效的选择，请重新输入。");
        map.put(LangConstant.singleThreadTotalExecute, "单线程共执行：");
        map.put(LangConstant.singleThreadScore, "单线程分数为：");
        map.put(LangConstant.round, " 轮");
        map.put(LangConstant.multiThreadTotalExecute, "多线程共执行：");
        map.put(LangConstant.multiThreadScore, "多线程分数为：");
        map.put(LangConstant.total, "总共 ");
        map.put(LangConstant.coreParticipate, " 个核心参与计算");
        map.put(LangConstant.coreRatio, "多线程倍率为：");
        map.put(LangConstant.startExecute, "开始执行第 ");
        map.put(LangConstant.nowIterationIs, "，当前迭代次数为：");

        map.put(LangConstant.startSingleThreadEvaluate, "开始执行单线程分数评估");
        map.put(LangConstant.startMultiThreadEvaluate, "开始执行多线程分数评估");
        map.put(LangConstant.singleThreadExeFinish, "单线程任务执行完成");
        map.put(LangConstant.multiThreadExeFinish, "多线程任务执行完成");
    }

}
