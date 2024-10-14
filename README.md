# Java版CPU性能测试工具
**全平台CPU同场竞技！**

## 前言
该项目借鉴了SuperPI的CPU性能计算方式，通过多轮迭代估算出当前CPU的性能成绩。

## 优势
* 均衡，使用统一标准，统一算法，统一计算时间，避免了度量的不一致性
* 覆盖广泛，采用的是递进算法，即使是很古老的，速度很慢的CPU也能跑出较为准确的分数，不至于卡死
* 快速，默认单线程和多线程都执行10秒，仅需20秒即可得出当前CPU的性能成绩
* 全平台支持，支持Windows、Linux、MacOS等，甚至在手机上可以使用Termux+JDK17，能够计算手机的CPU性能
* 基于JDK8开发，兼容性更好
* 算法简洁，原本最终成绩考虑使用各种复杂算法对最终分数进行调和，但是发现最简单的反而是最好的

## 使用方法
* 打出jar包
```shell
mvn clean package
```

* 执行cli版本jar包
```shell
java -jar benchmark-cli*.jar
```

* 执行ui版本jar包
双击打开Java版CPU性能测试工具.jar，即可。如果无法打开，则尝试使用命令行执行

* 执行native版本
直接执行即可

输入：1，即可执行CPU性能测试

输入：q，可退出程序

## TODOS
* 尚未测试过MacOS
* Android手机的CPU信息无法获取
* 增加结果提交功能（目前服务器还没搭建完成）

## CPU性能汇总（1.3版）
|   CPU型号/手机型号    | 单核分数 | 多核分数 |
|:---------------:| :---: | :---: |
|  AMD R7 7700X   | 5984.44 | 55417.39 |
|   小米9(SD 855)   | 2136.29 | 9836.49 |
|  AMD R5 5600G   | 3636.00 | 26248.29 |
|   AMD R3 3100   | 3110.49 | 16306.12 |

## CPU性能汇总（测试可能有误差）(1.2版)
|   CPU型号/手机型号    | 单核分数 | 多核分数 |
|:---------------:| :---: | :---: |
|  AMD R7 7700X   | 5145.48 | 46456.64 |
|  AMD R5 5600G   | 3194.11 | 23789.40 |
|   AMD R3 3100   | 2639.38 | 15859.60 |
| 小米13(SD 8Gen2)  | 2399.23 | 13303.83 |
|   小米9(SD 855)   | 1982.45 | 9188.78 |
| 华为Mate10(麒麟970) | 923.60 | 5293.64 |
|  红米3S(SD 430)   | 390.31 | 2127.60 |
|    i7 8700K     | 2903.54 | 21985.10 |


