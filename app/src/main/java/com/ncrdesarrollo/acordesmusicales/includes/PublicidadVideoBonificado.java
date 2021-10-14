package com.ncrdesarrollo.acordesmusicales.includes;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.ncrdesarrollo.acordesmusicales.R;
import com.ncrdesarrollo.acordesmusicales.views.MainActivity;


import java.util.Date;

import static android.content.Context.MODE_PRIVATE;

public class PublicidadVideoBonificado implements RewardedVideoAdListener {

    Context context;
    String id;

    private RewardedVideoAd rewardedVideoAd;

    ProgressDialog progressDialog;

    SharedPreferences mPref;
    SharedPreferences.Editor editor;

    public PublicidadVideoBonificado(final Context context, final String id) {
        this.context = context;
        this.id = id;

        progressDialog = new ProgressDialog(context);
        progressDialog.setIcon(R.mipmap.ic_launcher);
        progressDialog.setMessage("Cargando...");

        mPref = context.getApplicationContext().getSharedPreferences("myPreferences", MODE_PRIVATE);
        editor = mPref.edit();

        //TODO CAMBIAR ID
        //AD PANTALLA COMPLETA VIDEO CON BONIFICADO
        rewardedVideoAd = MobileAds.getRewardedVideoAdInstance(context);
        rewardedVideoAd.setRewardedVideoAdListener(this);
        //rewardedVideoAd.loadAd("ca-app-pub-3507476224103115/1216186660", new AdRequest.Builder().build()); //original
        rewardedVideoAd.loadAd(id, new AdRequest.Builder().build()); //prueba

        rewardedVideoAd.setRewardedVideoAdListener(new RewardedVideoAdListener() {
            @Override
            public void onRewardedVideoAdLoaded() {

            }

            @Override
            public void onRewardedVideoAdOpened() {
                progressDialog.dismiss();
            }

            @Override
            public void onRewardedVideoStarted() {

            }

            @Override
            public void onRewardedVideoAdClosed() {
                rewardedVideoAd.loadAd(id, new AdRequest.Builder().build()); //prueba
                ((MainActivity) context).finish();
                context.startActivity(((MainActivity) context).getIntent());
            }

            @Override
            public void onRewarded(RewardItem rewardItem) {
                //Toast.makeText(MainActivity.this, "onRewarded", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRewardedVideoAdLeftApplication() {
                //Toast.makeText(MainActivity.this, "onRewardedVideoAdLeftApplication", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRewardedVideoAdFailedToLoad(int i) {
                //Toast.makeText(MainActivity.this, "onRewardedVideoAdFailedToLoad", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRewardedVideoCompleted() {
                //Toast.makeText(MainActivity.this, "onRewardedVideoCompleted", Toast.LENGTH_SHORT).show();
                editor.putLong("timestamp", new Date().getTime());
                editor.apply();
            }
        });


    }

    public void iniciar(){
        if (rewardedVideoAd.isLoaded()) {
            rewardedVideoAd.show();
        }
    }

    @Override
    public void onRewardedVideoAdLoaded() {

    }

    @Override
    public void onRewardedVideoAdOpened() {

    }

    @Override
    public void onRewardedVideoStarted() {

    }

    @Override
    public void onRewardedVideoAdClosed() {

    }

    @Override
    public void onRewarded(RewardItem rewardItem) {

    }

    @Override
    public void onRewardedVideoAdLeftApplication() {

    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int i) {

    }

    @Override
    public void onRewardedVideoCompleted() {

    }
}
