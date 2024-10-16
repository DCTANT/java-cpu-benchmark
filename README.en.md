# Java version CPU performance testing tool
![banner.jpg](asset%2Fbanner.jpg)
## **<font color='red '>CPU competition across all platforms</font>**
🇨🇳：[中文](README.md)

🇬🇧：[English](README.en.md)
## Preface
This project draws on SuperPI's CPU performance calculation method and estimates the current CPU performance through multiple iterations.
## Project Reason
I had a sudden idea to build a Java backend server using the Termux app on an Android device, but I was struggling due to my lack of knowledge about CPU performance and the absence of a fair CPU performance testing tool, which made it difficult to compare the CPU performance of mobile phones and desktop computers. Therefore, I came up with this idea.
Another thing I don't know about the CPU performance of the company's servers. I always feel too laggy. I want to compare the performance of these Xeon server CPUs with that of my computer (I want to ridicule the performance of the company's old servers). Unfortunately, there is no suitable CPU performance comparison tool (Linux, Windows, MACOS, Android, etc., and there is no general CPU performance comparison software), so I wrote this project.
The goal is to achieve algorithm consistency, fairness, and impartiality... reflecting the performance of the JVM on this CPU, haha!
## Advantages
* Balance, using unified standards, algorithms, and calculation time to avoid measurement inconsistency
* Widely covered, using a progressive algorithm, even very old and slow CPUs can run relatively accurate scores without getting stuck
* Fast, both single threaded and multi-threaded execution take 10 seconds by default, and the current CPU performance can be obtained in just 20 seconds
* Full platform support, supports Windows, Linux, MacOS, etc., and can even use Termux+JDK17 on mobile phones to calculate the CPU performance of the phone
* Developed based on JDK8, with better compatibility
* The algorithm is concise. Originally, various complex algorithms were considered to reconcile the final score, but it was found that the simplest one is actually the best
## Usage method
* Print the jar package
```shell
mvn clean package
```
* Execute CLI version jar package
```shell
java -jar jcpu-benchmark-cli-*.jar
```
* Execute UI version jar package
Double click to open the Java version CPU performance testing tool. jar. If unable to open, try using the command line to execute
* Execute native version
Just execute directly
Input: 1 to perform CPU performance testing
Input: q to exit the program
## TODOS
* MACOS CPU information cannot be obtained
* Add result submission function (currently the server has not been set up yet)
* Software update prompt
* Android APP version, to be developed
## CPU Performance Summary (Version 1.3)
|CPU model/phone model | Single core score | Multi core score|
|:-----------------:|:-------:|:--------:|
|    AMD R7 7700X    | 5984.44 | 55417.39 |
|Xiaomi 9 (SD 855) | 2136.29 | 9836.49|
|    AMD R5 5600G    | 3636.00 | 26248.29 |
|     AMD R3 3100    | 3110.49 | 16306.12 |
|  Xeon(R) E5-2603  | 1066.33 | 13071.72 |
| Xeon(R) E5-2698*2 | 1464.18 | 44581.40 |
|    AMD R5 3550H    | 1944.20 | 6942.51  |
|    AMD R7 2700X    | 2922.01 | 21649.12  |
|Huawei Mate10 (Kirin 970) | 1136.37 | 6096.32|
|Xiaomi 13 (SD 8Gen2) | 2744.84 | 14388.14|
## CPU Performance Summary (Testing may have errors) (Version 1.2)
|CPU model/phone model | Single core score | Multi core score|
|:---------------:| :---: | :---: |
|   AMD R7 7700X   | 5145.48 | 46456.64 |
|   AMD R5 5600G   | 3194.11 | 23789.40 |
|    AMD R3 3100   | 2639.38 | 15859.60 |
|Xiaomi 13 (SD 8Gen2) | 2399.23 | 13303.83|
|Xiaomi 9 (SD 855) | 1982.45 | 9188.78|
|Huawei Mate10 (Kirin 970) | 923.60 | 5293.64|
|Redmi 3S (SD 430) | 390.31 | 2127.60|
|     i7 8700K     | 2903.54 | 21985.10 |
## Project Structure
### [benchmark-cli](benchmark-cli)
No interface command line version, minimal form factor
### [benchmark-core](benchmark-core)
Core module, including CPU performance calculation algorithms, obtaining CPU information, etc
### [benchmark-native](benchmark-native)
Using native packaging for benchmark cli, it can be run directly on the corresponding system's server without the need for JVM
### [benchmark-report](benchmark-report)
The data result reporting module has not yet started production, and for the sake of server security and data accuracy, this module is not open source
### [benchmark-ui](benchmark-ui)
A graphical interface version with GUI created using Java Swing. At present, there are still issues with using Graalvm for packaging, and errors will occur during execution. It is currently unclear how to solve this problem
## Contact Information
### Welcome to join my group chat
#### Group ID: 170618278
That's right, it's a VMware virtual machine communication group, which is actually just an ordinary technology blowing group 🤣
![Welcome to join my group chat](asset%2Fmy-group.jpg)