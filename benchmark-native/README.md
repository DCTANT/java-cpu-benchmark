# GraalVM本地镜像打包模块
## 准备
需要GraalVM JDK才能打包该模块，请自行下载

## 打包方法
可以在IDEA的Maven标签中找到benchmark-native这个模块，然后找到Plugins下面的native，双击native:build即可打包该模块。需要安装Visual Studio 2022才能完成该打包功能。

## 命令行打包
```shell
cd benchmark-native
mvn native:build -f pom.xml
```