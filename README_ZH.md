# 华为广告服务VAST协议示例代码
中文 | [English](https://github.com/HMS-Core/hms-ads-VAST-demo/edit/master/README.md)
## 目录

 * [简介](#简介)
 * [安装](#安装)
 * [配置](#配置)
 * [环境要求](#环境要求)
 * [示例代码](#示例代码)
 * [运行结果](#运行结果)
 * [授权许可](#授权许可)


## 简介
VAST（Video Ad Serving Template，视频广告服务模板）协议为IAB（Interactive Advertising Bureau，互联网广告署）指定的开放协议。HUAWEI Ads平台目前仅支持符合VAST协议的线性广告（投放形式为前、中、后贴片广告）。华为广告服务（HUAWEI Ads Kit）VAST协议示例代码向您介绍如何将HUAWEI VAST SDK集成到您的应用中，同时如果您需要使用默认的播放器，请集成HUAWEI Player SDK，实现线性广告的展示。

## 安装
在华为手机上安装该示例代码项目。

## 配置
无需配置。

## 环境要求
在华为手机上安装HMS Core (APK) 5.0.3及以上版本。

## 示例代码
华为广告服务（HUAWEI Ads Kit）VAST协议示例代码向您介绍如何将HUAWEI VAST SDK集成到您的应用中，同时如果您需要使用默认的播放器，请集成HUAWEI Player SDK，实现线性广告的展示:

1). DemoApplication.java
用于初始化SDK。
<br>代码位置：app/src/main/java/com/huawei/hms/ads/vast/DemoApplication.java</br>

2). LinearAdActivity.java
用于加载、展示线性广告。
<br>代码位置：app/src/main/java/com/huawei/hms/ads/vast/demo/LinearAdActivity.java</br>

## 运行结果
linear Ads

<img src="https://github.com/HMS-Core/hms-ads-VAST-demo/result/linear.gif" width=200>

## 技术支持
如果您对HMS Core还处于评估阶段，可在[Reddit社区](https://www.reddit.com/r/HuaweiDevelopers/)获取关于HMS Core的最新讯息，并与其他开发者交流见解。

如果您对使用HMS示例代码有疑问，请尝试：
- 开发过程遇到问题上[Stack Overflow](https://stackoverflow.com/questions/tagged/huawei-mobile-services)，在`huawei-mobile-services`标签下提问，有华为研发专家在线一对一解决您的问题。
- 到[华为开发者论坛](https://forums.developer.huawei.com/forumPortal/en/home?fid=0101187876626530001) HMS Core板块与其他开发者进行交流。

如果您在尝试示例代码中遇到问题，请向仓库提交[issue](https://github.com/HMS-Core/hms-ads-VAST-demo/issues)，也欢迎您提交[Pull Request](https://github.com/HMS-Core/hms-ads-VAST-demo/pulls)。

##  授权许可
华为广告服务JavaScript示例代码经过 [Apache License, version 2.0](http://www.apache.org/licenses/LICENSE-2.0)授权许可。
