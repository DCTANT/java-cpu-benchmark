# Java版CPU性能测试工具

## 前言
该项目借鉴了SuperPI的CPU性能计算方式，通过多轮迭代估算出当前CPU的性能成绩。

## 优势
* 快速，默认单线程和多线程都执行10秒，仅需20秒即可得出当前CPU的性能成绩
* 全平台支持，支持Windows、Linux、MacOS等，甚至在手机上可以使用Termux+JDK17，能够计算手机的CPU性能

## 使用方法
* 打出jar包
```shell
mvn clean package
```

* 执行jar包
```shell
java -jar CpuBenchmark*.jar
```

输入：1，即可执行CPU性能测试

输入：q，可退出程序

