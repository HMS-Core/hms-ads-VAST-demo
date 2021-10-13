package com.huawei.hms.ads.vast.demo.main;

import static com.huawei.hms.ads.vast.demo.Constants.LINEAR_SLOT_ID;
import static com.huawei.hms.ads.vast.demo.Constants.TOTAL_DURATION;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
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
import com.huawei.hms.ads.vast.player.api.VastAdPlayer;
import com.huawei.hms.ads.vast.player.base.BaseVideoController;
import com.huawei.hms.ads.vast.player.model.adslot.LinearAdSlot;

public class AdPodsActivity extends Activity {
    private static final String TAG = "LinearAdActivity";

    private final AdConfig mAdConfig = new AdConfig();

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
        setContentView(R.layout.activity_ad_pods_ad);
        setAdViewStrategy();
        registerLinearAdView();
        startAdPods(getLinearAdSlot());
    }

    private void setAdViewStrategy() {
        VastAdPlayer.getInstance().setAdViewStrategy((expectedHeight, expectedWidth, height, width) -> true);
    }

    private void registerLinearAdView() {
        mProgressBar = findViewById(R.id.progress);
        mLinearAdView = findViewById(R.id.fl_linear_ad);
        boolean isCustomVideoPlayer = mAdConfig.isCustomVideoPlayer();

        BaseVideoController controller;
        if (isCustomVideoPlayer) {
            controller = new DefaultVideoController(this);
        } else {
            controller = new CustomVideoController(this);
        }

        VastAdPlayer.getInstance().registerLinearAdView(mLinearAdView, controller);
    }

    private void startAdPods(LinearAdSlot linearAdSlot) {
        VastAdPlayer.getInstance().startAdPods(linearAdSlot, new AdsRequestListener() {
            @Override
            public void onSuccess(View view, int responseCode) {
            }

            @Override
            public void onFailed(View view, int responseCode) {
            }

            @Override
            public void playAdReady() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (null == mLinearAdView) {
                            return;
                        }
                        mLinearAdView.setVisibility(View.VISIBLE);
                    }
                });
            }

            @Override
            public void playAdFinish() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (null == mLinearAdView) {
                            return;
                        }
                        mLinearAdView.setVisibility(View.INVISIBLE);
                    }
                });
            }

            @Override
            public void onBufferStart() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (mProgressBar == null) {
                            return;
                        }
                        mProgressBar.setVisibility(View.VISIBLE);
                    }
                });
            }

            @Override
            public void onBufferEnd() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (mProgressBar == null) {
                            return;
                        }
                        mProgressBar.setVisibility(View.INVISIBLE);
                    }
                });
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
        linearAdSlot.setMaxAdPods(mAdConfig.getAdPodsMaxCount());
        linearAdSlot.setContainer(mLinearAdView);
        return linearAdSlot;
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

    public static void launchAdPodsView(Context context, Bundle bundle) {
        Intent intent = new Intent(context, AdPodsActivity.class);
        intent.putExtras(bundle);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
