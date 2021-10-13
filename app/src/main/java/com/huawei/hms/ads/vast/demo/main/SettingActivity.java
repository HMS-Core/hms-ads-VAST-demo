/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2020-2020. All rights reserved.
 */

package com.huawei.hms.ads.vast.demo.main;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RadioGroup;


import com.huawei.hms.ads.vast.demo.R;
import com.huawei.hms.ads.vast.demo.bean.AdConfig;

public class SettingActivity extends Activity {

    public static final String VAST_PLAYER_CONFIG = "VAST_PLAYER_CONFIG";

    private boolean mIsCustomVideoPlayer = true;

    private String mAdContentClassification = "A";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        listenerVideoPlayerType();
        launchLinearAdView();
        launchAdPodsView();
    }

    private void listenerVideoPlayerType() {
        this.<RadioGroup>findViewById(R.id.rg_select_video_mode).setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.rb_custom:
                    mIsCustomVideoPlayer = false;
                    break;
                case R.id.rb_default:
                    mIsCustomVideoPlayer = true;
                    break;
                default:
                    break;
            }
        });
    }

    private void launchLinearAdView() {
        findViewById(R.id.btn_linear_ad).setOnClickListener(v -> {
            AdConfig adConfig = new AdConfig(mIsCustomVideoPlayer, mAdContentClassification);
            LinearAdActivity.launchLinearAdView(this, generateBundle(adConfig));
        });

        findViewById(R.id.btn_launch_fetch_data_view)
                .setOnClickListener(v -> AdsDataLinearAdActivity.launchAdsView(SettingActivity.this));
    }

    private void launchAdPodsView() {
        findViewById(R.id.btn_adpods_ad).setOnClickListener(v -> {
            AdConfig adConfig = new AdConfig(mIsCustomVideoPlayer, mAdContentClassification);
            AdPodsActivity.launchAdPodsView(this, generateBundle(adConfig));
        });
    }

    private Bundle generateBundle(AdConfig adConfig) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(VAST_PLAYER_CONFIG, adConfig);
        return bundle;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}