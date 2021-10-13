/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2020-2020. All rights reserved.
 */

package com.huawei.hms.ads.vast.demo.main;

import static com.huawei.hms.ads.vast.demo.Constants.BLACK_COLOR;
import static com.huawei.hms.ads.vast.demo.Constants.CORNER_RADIUS;
import static com.huawei.hms.ads.vast.demo.Constants.DESC_TEXT_SIZE;
import static com.huawei.hms.ads.vast.demo.Constants.DETAIL_TEXT_SIZE;
import static com.huawei.hms.ads.vast.demo.Constants.GRAY_COLOR;
import static com.huawei.hms.ads.vast.demo.Constants.LINEAR_SLOT_ID;
import static com.huawei.hms.ads.vast.demo.Constants.TITLE_TEXT_SIZE;
import static com.huawei.hms.ads.vast.demo.Constants.TOTAL_DURATION;
import static com.huawei.hms.ads.vast.demo.Constants.WHITE_COLOR;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.huawei.hms.ads.vast.application.requestinfo.CreativeMatchStrategy;
import com.huawei.hms.ads.vast.demo.DemoApplication;
import com.huawei.hms.ads.vast.demo.R;
import com.huawei.hms.ads.vast.demo.bean.AdConfig;
import com.huawei.hms.ads.vast.demo.custome.CustomVideoController;
import com.huawei.hms.ads.vast.openalliance.ad.beans.parameter.RequestOptions;
import com.huawei.hms.ads.vast.player.api.AdsRequestListener;
import com.huawei.hms.ads.vast.player.api.DefaultVideoController;
import com.huawei.hms.ads.vast.player.api.PlayerConfig;
import com.huawei.hms.ads.vast.player.api.VastAdPlayer;
import com.huawei.hms.ads.vast.player.base.BaseVideoController;
import com.huawei.hms.ads.vast.player.model.adslot.LinearAdSlot;

public class LinearAdActivity extends Activity {
    private static final String TAG = "LinearAdActivity";

    private FrameLayout mFrameLayoutSingle;

    private AdConfig mAdConfig = new AdConfig();

    private FrameLayout mLinearAdView;

    private ProgressBar mProgressBar;

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
        setContentView(R.layout.activity_linear_ad);
        handleIntent();
        setAdViewStrategy();
        registerLinearAdView();
        initPlayerConfig();
        startLinearAd(getLinearAdSlot());
    }

    private void handleIntent() {
        Bundle bundleExtra = getIntent().getExtras();
        if (bundleExtra != null) {
            mAdConfig = (AdConfig) bundleExtra.getSerializable(SettingActivity.VAST_PLAYER_CONFIG);
        }
    }

    private void setAdViewStrategy() {
        VastAdPlayer.getInstance().setAdViewStrategy((expectedHeight, expectedWidth, height, width) -> true);
    }

    private void registerLinearAdView() {
        mLinearAdView = findViewById(R.id.fl_linear_ad);
        mProgressBar = findViewById(R.id.progress);
        VastAdPlayer.getInstance().registerLinearAdView(mLinearAdView, getController());
    }

    private void initPlayerConfig() {
        PlayerConfig playerConfig = PlayerConfig.newBuilder()
                .setEnableRotation(false)
                .setIsEnableCutout(false)
                .setSkipLinearAd(false)
                .setIsEnablePortrait(false)
                .build();
        VastAdPlayer.getInstance().setConfig(playerConfig);
    }

    private void startLinearAd(LinearAdSlot linearAdSlot) {
        VastAdPlayer.getInstance().startLinearAd(linearAdSlot, new AdsRequestListener() {
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
                mLinearAdView.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onBufferStart() {
                mProgressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onBufferEnd() {
                mProgressBar.setVisibility(View.INVISIBLE);
            }
        });
    }

    private LinearAdSlot getLinearAdSlot() {
        CreativeMatchStrategy creativeMatchStrategy =
                new CreativeMatchStrategy(CreativeMatchStrategy.CreativeMatchType.ANY);
        RequestOptions requestOptions = new RequestOptions.Builder().setRequestLocation(true)
                .setAdContentClassification(mAdConfig.getAdContentClassification())
                .setTagForChildProtection(mAdConfig.getCoppa())
                .setTagForUnderAgeOfPromise(mAdConfig.getTfua())
                .build();
        LinearAdSlot linearAdSlot = new LinearAdSlot();
        linearAdSlot.setSlotId(LINEAR_SLOT_ID);
        linearAdSlot.setTotalDuration(TOTAL_DURATION);
        linearAdSlot.setCreativeMatchStrategy(creativeMatchStrategy);
        linearAdSlot.setAllowMobileTraffic(mAdConfig.getAmttpv() != 0);
        linearAdSlot.setOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        linearAdSlot.setRequestOptions(requestOptions);
        linearAdSlot.setContainer(mLinearAdView);
        return linearAdSlot;
    }

    private BaseVideoController getController() {
        return mAdConfig.isCustomVideoPlayer() ? new DefaultVideoController(this)
                : new CustomVideoController(this);
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
    protected void onStop() {
        super.onStop();
        VastAdPlayer.getInstance().pause();
    }

    @Override
    protected void onStart() {
        super.onStart();
        VastAdPlayer.getInstance().resume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        VastAdPlayer.getInstance().unregisterLinearAdView(mLinearAdView);
        VastAdPlayer.getInstance().release();
    }

    public static void launchLinearAdView(Context context, Bundle bundle) {
        Intent intent = new Intent(context, LinearAdActivity.class);
        intent.putExtras(bundle);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
