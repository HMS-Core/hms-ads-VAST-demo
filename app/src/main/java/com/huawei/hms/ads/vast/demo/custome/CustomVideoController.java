/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2020-2020. All rights reserved.
 */

package com.huawei.hms.ads.vast.demo.custome;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.huawei.hms.ads.vast.demo.DemoApplication;
import com.huawei.hms.ads.vast.demo.R;
import com.huawei.hms.ads.vast.player.api.VastPlayerListener;
import com.huawei.hms.ads.vast.player.base.BaseVideoController;
import com.huawei.hms.ads.vast.player.misc.utils.AudioUtil;

public class CustomVideoController extends BaseVideoController implements VastPlayerListener {
    private CheckBox btnMute;

    private ViewGroup clContent;

    private Button btnSkip;

    private Button btnDetailView;

    private Button btnScreen;

    private Button btnPlay;

    public CustomVideoController(Context context) {
        this(context, null);
    }

    public CustomVideoController(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomVideoController(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
        initMuteState();
        setPlayerListener(this);
    }

    private void initView(Context context) {
        clContent = findViewById(R.id.cl_content);
        btnSkip = findViewById(R.id.demo_bt_skip);
        btnSkip.setOnClickListener(v -> playNextAdOrFinish());
        btnDetailView = findViewById(R.id.demo_bt_detail);
        btnScreen = findViewById(R.id.demo_bt_full_screen);
        btnPlay = findViewById(R.id.demo_bt_play);
        btnMute = findViewById(R.id.demo_bt_voice);

        btnScreen.setOnClickListener(v -> toggleScreen((Activity) context));
        btnDetailView.setOnClickListener(v -> launchAdDetailView((Activity) context));
        btnPlay.setOnClickListener(v -> startOrPause());
        clContent.setOnClickListener(v -> launchAdDetailView((Activity) context));
    }

    private void initMuteState() {
        btnMute.setChecked(isMute());
        btnMute.setOnCheckedChangeListener((button, checked) -> toggleMuteState(checked));
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_video_player;
    }

    @Override
    public boolean isMute() {
        if (mIsForceMute) {
            return btnMute.isChecked();
        } else {
            return AudioUtil.isSystemVolumeZero(mActivity);
        }
    }

    @Override
    public void onVolumeChanged(float volume) {
    }

    @Override
    public void onScreenViewChanged(int screenState) {
    }

    @Override
    public void onPlayStateChanged(int playState) {
    }

    @Override
    public void onProgressChanged(long duration, long currentPosition, long skipDuration) {

    }
}
