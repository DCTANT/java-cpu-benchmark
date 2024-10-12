package com.itdct.cbench.util;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * @author Zhouwx
 * @version 1.0
 * @date 2024/10/12 22:58:47
 * @description
 */
public class PiCalculatorBenchmark {
    private static final BigDecimal TWO = new BigDecimal("2");
    private static final BigDecimal FOUR = new BigDecimal("4");

    private boolean needStop = false;

//    public static void main(String[] args) {
//        long startTime = System.currentTimeMillis();
//        int iterations = 10000; // 迭代次数
//        int precision = 500; // 精度设置为 50 位
//        MathContext mc = new MathContext(precision); // 设置精度
//
//        BigDecimal pi = calculatePi(iterations, mc);
//        long endTime = System.currentTimeMillis();
//        System.out.println("使用时间："+ (endTime - startTime) + "ms");
//        System.out.println("Estimated value of Pi after " + iterations + " iterations is " + pi.toPlainString());
//    }

    /**
     * 使用高斯-勒让德算法计算 Pi 的值。
     *
     * @param iterations 迭代次数
     * @param mc         MathContext 对象，用于设置精度
     * @return 计算出的 Pi 值
     */
    public boolean calculatePi(int iterations, MathContext mc) {
        BigDecimal a = BigDecimal.ONE; // 初始化 a
        BigDecimal b = BigDecimal.ONE.divide(sqrt(BigDecimal.valueOf(2), mc), mc); // 初始化 b
        BigDecimal t = BigDecimal.ONE.divide(BigDecimal.valueOf(4), mc); // 初始化 t
        BigDecimal p = BigDecimal.ONE; // 初始化 p

        for (int i = 0; i < iterations; i++) {
            if (needStop) {
                return false;
            }
            BigDecimal aNew = a.add(b).divide(TWO, mc);
            BigDecimal bNew = sqrt(a.multiply(b), mc);
            BigDecimal tNew = t.subtract(p.multiply(a.subtract(aNew).pow(2)), mc);
            BigDecimal pNew = p.multiply(TWO);

            a = aNew;
            b = bNew;
            t = tNew;
            p = pNew;
        }

        BigDecimal piEstimate = a.add(b).pow(2).divide(FOUR.multiply(t), mc);
        return true;
//        return piEstimate;
    }

    /**
     * 使用牛顿-拉弗森迭代法计算平方根。
     *
     * @param number 要计算平方根的数字
     * @param mc     MathContext 对象，用于设置精度
     * @return 计算出的平方根
     */
    private static BigDecimal sqrt(BigDecimal number, MathContext mc) {
        BigDecimal result = number.divide(TWO, mc);
        BigDecimal lastResult = BigDecimal.ZERO;

        while (!result.equals(lastResult)) {
            lastResult = result;
            result = number.divide(result, mc).add(result).divide(TWO, mc);
        }

        return result;
    }

    public boolean isNeedStop() {
        return needStop;
    }

    public PiCalculatorBenchmark setNeedStop(boolean needStop) {
        this.needStop = needStop;
        return this;
    }
}
