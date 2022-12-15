# Petal Ads Demo for VAST
English | [中文](README_ZH.md)
## Table of Contents

 * [Introduction](#introduction)
 * [Installation](#installation)
 * [Configuration ](#configuration)
 * [Supported Environments](#supported-environments)
 * [Sample Code](#sample-code)
 * [Result](#result)
 * [License](#license)


## Introduction
The Video Ad Serving Template (VAST) specification is defined and released by the Interactive Advertising Bureau (IAB).Currently, Petal Ads supports only linear ads (pre-roll, mid-roll, and post-roll ads) under this specification.The VAST ad sample code provided by Petal Ads describes how to display linear ads by integrating the Petal Ads VAST SDK into your app. If you want to use the default player, integrate the HUAWEI Player SDK as well.

## Installation
Install the program on Huawei Android mobile phones.

## Configuration 
No additional configuration is required.

## Supported Environments
HMS Core (APK) 5.0.3 or later has been installed on Huawei Android phones.

## Sample Code
The VAST ad sample code provided by Petal Ads describes how to display linear ads by integrating the Petal Ads VAST SDK into your app. If you want to use the default player, integrate the HUAWEI Player SDK as well.

1). DemoApplication.java
Initializing the SDK.
<br>Code location: app/src/main/java/com/huawei/hms/ads/vast/DemoApplication.java</br>
    
2). LinearAdActivity.java
Loads and displays linear ads.
<br>Code location: app/src/main/java/com/huawei/hms/ads/vast/demo/LinearAdActivity.java</br>

## Result
linear Ads

<img src="result/linear.gif" width=200>

## Question or issues
If you want to evaluate more about HMS Core,
[HMSCore on Reddit](https://www.reddit.com/r/HuaweiDevelopers/) is for you to keep up with latest news about HMS Core, and to exchange insights with other developers.

If you have questions about how to use HMS samples, try the following options:
- [Stack Overflow]( https://stackoverflow.com/questions/tagged/huawei-mobile-services?tab=Votes) is the best place for any programming questions. Be sure to tag your question with 
`huawei-mobile-services`.
- [Huawei Developer Forum](https://forums.developer.huawei.com/forumPortal/en/home?fid=0101187876626530001) HMS Core Module is great for general questions, or seeking recommendations and opinions.

If you run into a bug in our samples, please submit an [issue](https://github.com/HMS-Core/hms-ads-VAST-demo/issues) to the Repository. Even better you can submit a [Pull Request](https://github.com/HMS-Core/hms-ads-VAST-demo/pulls) with a fix.

##  License
hms-ads-demo-javascript is licensed under the [Apache License, version 2.0](http://www.apache.org/licenses/LICENSE-2.0).
