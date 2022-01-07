/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2020-2020. All rights reserved.
 */

package com.huawei.hms.ads.vast.demo;

import static com.huawei.hms.ads.vast.demo.Constants.APP_NAME;
import static com.huawei.hms.ads.vast.demo.Constants.IS_TEST;

import android.app.Application;
import android.content.Context;

import com.huawei.hms.ads.vast.adapter.SdkFactory;
import com.huawei.hms.ads.vast.player.VastApplication;

public class DemoApplication extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        VastApplication.init(DemoApplication.this, IS_TEST);
        SdkFactory.userAcceptAdLicense(true);
    }

    public static Context getContext() {
        return context;
    }
}
