package com.ncrdesarrollo.acordesmusicales.includes;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;

import java.util.Date;

import static android.content.Context.MODE_PRIVATE;

import androidx.annotation.NonNull;

public class PublicidadVideoBonificado {

    Activity context;
    String id;

    RewardedAd rewardedAd;
    AdRequest adRequest;
    private final String TAG = "VideoRewardedAd";

    ProgressDialog progressDialog;

    SharedPreferences mPref;
    SharedPreferences.Editor editor;

    public PublicidadVideoBonificado(final Activity context, final String id) {
        this.context = context;
        this.id = id;

        mPref = context.getApplicationContext().getSharedPreferences("myPreferences", MODE_PRIVATE);
        editor = mPref.edit();

        new Thread(
                () -> {
                    // Initialize the Google Mobile Ads SDK on a background thread.
                    MobileAds.initialize(context, initializationStatus -> {});
                })
                .start();

        loadRewardedAd();
    }

    private void loadRewardedAd() {
        AdRequest adRequest = new AdRequest.Builder().build();
        RewardedAd.load(context, id, adRequest, new RewardedAdLoadCallback() {
            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
                rewardedAd = null;
            }

            @Override
            public void onAdLoaded(@NonNull RewardedAd ad) {
                super.onAdLoaded(rewardedAd);
                rewardedAd = ad;
            }
        });
    }

    public void iniciar(){
        if (rewardedAd != null) {
            rewardedAd.show(context, new OnUserEarnedRewardListener() {
                @Override
                public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                    // Handle the reward.
                    Log.d(TAG, "The user earned the reward.");
                    int rewardAmount = rewardItem.getAmount();
                    String rewardType = rewardItem.getType();
                    editor.putLong("timestamp", new Date().getTime());
                    editor.apply();
                }
            });
        } else {
            Log.d(TAG, "The rewarded ad wasn't ready yet.");
        }
        loadRewardedAd();
    }
}
