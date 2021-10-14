package com.ncrdesarrollo.acordesmusicales.includes;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;


public class PublicidadBanner {

    Context context;
    private String idProject = "ca-app-pub-3507476224103115~9192601807";

    public PublicidadBanner(Context context) {
        this.context = context;
        MobileAds.initialize(context, idProject);
    }

    public void adViewBottom(View view, String id) {

        //PARA LA PUBLICIDAD
        AdView mAdView = new AdView(context);
        mAdView.setAdSize(AdSize.SMART_BANNER);
        mAdView.setAdUnitId(id);
        ((RelativeLayout) view).addView(mAdView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        //ca-app-pub-3940256099942544/6300978111   para prueba
        //ca-app-pub-3507476224103115/3508782865   en producción

        long tiempo_publicidad = 555;

        if (RelativeTime.getTimeAgo(context) != null) {
            tiempo_publicidad = (long) RelativeTime.getTimeAgo(context);

        }
        if (tiempo_publicidad < 2) {
            view.setVisibility(View.GONE);
        }
    }

}
