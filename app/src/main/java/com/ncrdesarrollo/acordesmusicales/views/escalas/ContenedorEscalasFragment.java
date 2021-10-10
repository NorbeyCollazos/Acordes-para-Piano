package com.ncrdesarrollo.acordesmusicales.views.escalas;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ncrdesarrollo.acordesmusicales.Adapters.AcordesAdapter;
import com.ncrdesarrollo.acordesmusicales.Adapters.EscalasAdapter;
import com.ncrdesarrollo.acordesmusicales.R;
import com.ncrdesarrollo.acordesmusicales.includes.SharedPref;
import com.ncrdesarrollo.acordesmusicales.includes.Variables;
import com.ncrdesarrollo.acordesmusicales.models.Acordes;
import com.ncrdesarrollo.acordesmusicales.models.Escalas;
import com.ncrdesarrollo.acordesmusicales.presenters.acordes.AcordesPresenter;
import com.ncrdesarrollo.acordesmusicales.presenters.escalas.EscalasPresenter;

import java.util.ArrayList;

public class ContenedorEscalasFragment extends Fragment implements IEscalasFragment{

    String acorde = "";
    WebView webView;
    EscalasPresenter escalasPresenter;
    ArrayList<Escalas> escalasArrayList;
    RecyclerView recyclerView;
    EscalasAdapter adapter;
    TextView tvacorde;

    public ContenedorEscalasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contenedor_escalas, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle args = getArguments();
        String acorde = args.getString("acorde");

        escalasPresenter = new EscalasPresenter(this, getContext());

        tvacorde = view.findViewById(R.id.tvacorde);

        webView = view.findViewById(R.id.webview);

        escalasArrayList = new ArrayList<>();

        recyclerView = view.findViewById(R.id.recyclerview);

        escalasPresenter.consultarLista(String.valueOf(acorde.charAt(0)));


    }


    @Override
    public void mostrarWebView(String nombre, String htmlacorde) {
        tvacorde.setText(nombre);
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(false);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        //este codigo es para ajustar el webview al tamaño de la pantalla
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.setInitialScale(30);
        /////////////////////////////////////////////////////////////////
        String pianohtml = htmlacorde;
        if (htmlacorde.isEmpty()){
            pianohtml = Variables.piano;
        }
        webView.loadDataWithBaseURL("file:///android_asset/AcordesPiano/", Variables.html+pianohtml+Variables.htmlcierre, "text/html", "UTF-8", null);

    }

    @Override
    public void mostrarListaEscalas(ArrayList<Escalas> escalas) {
        escalasArrayList = escalas;

        /*recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);*/

        //RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 3);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        //layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new EscalasAdapter(escalasArrayList,getContext());
        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                acorde = escalas.get(recyclerView.getChildAdapterPosition(view)).getNombre();
                escalasPresenter.consultarEscala(acorde);
                escalasArrayList.clear();
                escalasPresenter.consultarLista(String.valueOf(acorde.charAt(0)));
            }
        });
        recyclerView.setAdapter(adapter);
    }
}