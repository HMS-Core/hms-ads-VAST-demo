/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2020-2020. All rights reserved.
 */

package com.huawei.hms.ads.vast.demo.bean;

import com.huawei.hms.ads.vast.demo.Constants;

import java.io.Serializable;

public class AdConfig implements Serializable {
    private static final long serialVersionUID = 8350479932312689911L;

    private boolean mIsCustomVideoPlayer = true;

    private String mAdContentClassification;

    private int mAdPodsMaxCount = 2;

    private int mCoppa = -1;

    private int mTfua = -1;

    private int paToOther = 0;

    private int amttpv = 1;

    private float mAdTipTextSize = Constants.TIP_TEXT_SIZE;

    private int mViewDetailBackgroundColor = Constants.BLUE_COLOR;

    public AdConfig() {
    }

    public AdConfig(boolean isCustomVideoPlayer, String adContentClassification) {
        mIsCustomVideoPlayer = isCustomVideoPlayer;;
        mAdContentClassification = adContentClassification;
    }

    public boolean isCustomVideoPlayer() {
        return mIsCustomVideoPlayer;
    }

    public void setIsCustomVideoPlayer(boolean isCustomVideoPlayer) {
        mIsCustomVideoPlayer = isCustomVideoPlayer;
    }

    public String getAdContentClassification() {
        return mAdContentClassification;
    }

    public void setAdContentClassification(String adContentClassification) {
        mAdContentClassification = adContentClassification;
    }

    public int getAdPodsMaxCount() {
        return mAdPodsMaxCount;
    }

    public void setAdPodsMaxCount(int adPodsMaxCount) {
        mAdPodsMaxCount = adPodsMaxCount;
    }

    public int getCoppa() {
        return mCoppa;
    }

    public void setCoppa(int coppa) {
        mCoppa = coppa;
    }

    public int getTfua() {
        return mTfua;
    }

    public void setTfua(int tfua) {
        mTfua = tfua;
    }

    public int getPaToOther() {
        return paToOther;
    }

    public void setPdToOther(int paToOther) {
        this.paToOther = paToOther;
    }

    public int getAmttpv() {
        return amttpv;
    }

    public void setAmttpv(int amttpv) {
        this.amttpv = amttpv;
    }

    public float getAdTipTextSize() {
        return mAdTipTextSize;
    }

    public void setAdTipTextSize(float adTipTextSize) {
        mAdTipTextSize = adTipTextSize;
    }

    public int getViewDetailBackgroundColor() {
        return mViewDetailBackgroundColor;
    }

    public void setViewDetailBackgroundColor(int viewDetailBackgroundColor) {
        mViewDetailBackgroundColor = viewDetailBackgroundColor;
    }
}
