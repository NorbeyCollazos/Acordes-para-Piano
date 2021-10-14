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
    private String idProject = IdsAdmob.idProject;

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

        long tiempo_publicidad = 555;

        if (RelativeTime.getTimeAgo(context) != null) {
            tiempo_publicidad = (long) RelativeTime.getTimeAgo(context);

        }
        if (tiempo_publicidad < 2) {
            view.setVisibility(View.GONE);
        }
    }

}
