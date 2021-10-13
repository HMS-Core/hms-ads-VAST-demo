/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2020-2020. All rights reserved.
 */

package com.huawei.hms.ads.vast.demo.main;

import static com.huawei.hms.ads.vast.demo.Constants.LINEAR_HEIGHT_SIZE;
import static com.huawei.hms.ads.vast.demo.Constants.LINEAR_SLOT_ID;
import static com.huawei.hms.ads.vast.demo.Constants.LINEAR_WIDTH_SIZE;
import static com.huawei.hms.ads.vast.demo.Constants.TOTAL_DURATION;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.huawei.hms.ads.vast.application.requestinfo.CreativeMatchStrategy;
import com.huawei.hms.ads.vast.demo.R;
import com.huawei.hms.ads.vast.openalliance.ad.beans.parameter.RequestOptions;
import com.huawei.hms.ads.vast.player.api.AdsRequestListener;
import com.huawei.hms.ads.vast.player.api.DefaultVideoController;
import com.huawei.hms.ads.vast.player.api.VastAdPlayer;
import com.huawei.hms.ads.vast.player.model.adslot.AdsData;
import com.huawei.hms.ads.vast.player.model.adslot.LinearAdSlot;
import com.huawei.hms.ads.vast.player.model.remote.RequestCallback;

public class AdsDataLinearAdActivity extends Activity {
    private FrameLayout mLinearAdView;

    private AdsData mAdsData;

    private LinearAdSlot mLinearAdSlot;

    private boolean mIsStart;

    @Override
    public void onBackPressed() {
        if (VastAdPlayer.getInstance().onBackPressed(this)) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fetch_ads_data);
        initView();
        initRequestAdsInfo();
        registerLinearAd();
    }

    private void initView() {
        mLinearAdView = findViewById(R.id.fl_linear_ad);
        Button mBtnFetchData = findViewById(R.id.btn_fetch_ad_data);
        Button mBtnPlayAd = findViewById(R.id.btn_play_ad);

        mBtnFetchData.setOnClickListener(v -> loadAdData());
        mBtnPlayAd.setOnClickListener(v -> handleAdPlay());
    }

    private void loadAdData() {
        VastAdPlayer.getInstance().loadLinearAd(mLinearAdSlot, new RequestCallback() {
            @Override
            public void onAdsLoadedSuccess(AdsData adsData) {
                mAdsData = adsData;
            }

            @Override
            public void onAdsLoadFailed() {
                mAdsData = null;
            }
        });
    }

    private void handleAdPlay() {
        if (mAdsData == null) {
            return;
        }
        if (mIsStart) {
            return;
        }

        mIsStart = true;

        playLinearAd();
    }

    private void playLinearAd() {
        VastAdPlayer.getInstance().playLinearAds(mLinearAdSlot, mAdsData, new AdsRequestListener() {
            @Override
            public void onSuccess(View view, int responseCode) {
            }

            @Override
            public void onFailed(View view, int responseCode) {
            }

            @Override
            public void playAdReady() {
                mLinearAdView.setVisibility(View.VISIBLE);
            }

            @Override
            public void playAdFinish() {
                mIsStart = false;
                mLinearAdView.setVisibility(View.INVISIBLE);
            }
        });
    }

    private void initRequestAdsInfo() {
        CreativeMatchStrategy creativeMatchStrategy =
                new CreativeMatchStrategy(CreativeMatchStrategy.CreativeMatchType.ANY);
        RequestOptions requestOptions = new RequestOptions.Builder().setRequestLocation(true)
                .setNonPersonalizedAd(0)
                .setConsent("")
                .setAdContentClassification("A")
                .setTagForChildProtection(-1)
                .setTagForUnderAgeOfPromise(-1)
                .build();

        mLinearAdSlot = new LinearAdSlot();
        mLinearAdSlot.setSlotId(LINEAR_SLOT_ID);
        mLinearAdSlot.setTotalDuration(TOTAL_DURATION);
        mLinearAdSlot.setCreativeMatchStrategy(creativeMatchStrategy);
        mLinearAdSlot.setAllowMobileTraffic(true);
        mLinearAdSlot.setOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        mLinearAdSlot.setRequestOptions(requestOptions);
        mLinearAdSlot.setContainer(mLinearAdView);
        mLinearAdSlot.setSize(LINEAR_WIDTH_SIZE, LINEAR_HEIGHT_SIZE);
        mLinearAdView.setVisibility(View.INVISIBLE);
    }

    private void registerLinearAd() {
        VastAdPlayer.getInstance().registerLinearAdView(mLinearAdView, new DefaultVideoController(this));
    }

    public static void launchAdsView(Context context) {
        Intent intent = new Intent(context, AdsDataLinearAdActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        VastAdPlayer.getInstance().resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        VastAdPlayer.getInstance().pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        VastAdPlayer.getInstance().release();
    }
}
