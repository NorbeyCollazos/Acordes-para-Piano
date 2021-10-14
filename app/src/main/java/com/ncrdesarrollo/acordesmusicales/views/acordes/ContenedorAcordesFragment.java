package com.ncrdesarrollo.acordesmusicales.views.acordes;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ncrdesarrollo.acordesmusicales.Adapters.AcordesAdapter;
import com.ncrdesarrollo.acordesmusicales.R;
import com.ncrdesarrollo.acordesmusicales.includes.SharedPref;
import com.ncrdesarrollo.acordesmusicales.includes.Variables;
import com.ncrdesarrollo.acordesmusicales.models.Acordes;
import com.ncrdesarrollo.acordesmusicales.presenters.acordes.AcordesPresenter;
import com.ncrdesarrollo.acordesmusicales.views.acordes.IAcordesFragment;

import java.util.ArrayList;


public class ContenedorAcordesFragment extends Fragment implements IAcordesFragment {

    String acorde = "";
    String posicion = "PF";
    WebView webView;
    Spinner spinner;
    AcordesPresenter acordesPresenter;
    SharedPref sharedPref;
    ArrayList<Acordes> acordesArrayList;
    RecyclerView recyclerView;
    AcordesAdapter adapter;
    TextView tvacorde;
    CardView btnescuchar;

    public ContenedorAcordesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contenedor_acordes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle args = getArguments();
        String acorde = args.getString("acorde");

        acordesPresenter = new AcordesPresenter(this, getContext());
        sharedPref = new SharedPref(getContext());

        tvacorde = view.findViewById(R.id.tvacorde);

        webView = view.findViewById(R.id.webview);

        acordesArrayList = new ArrayList<>();

        recyclerView = view.findViewById(R.id.recyclerview);

        acordesPresenter.consultarAcorde(acorde, posicion);

        acordesPresenter.consultarAcordes(String.valueOf(acorde.charAt(0)));

        spinner = (Spinner) view.findViewById(R.id.posiciones_spinner);
        generarSpinnerPosiciones();

        btnescuchar = view.findViewById(R.id.btnescuchar);
        btnescuchar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                webView.loadUrl("javascript:(function() {var audio = document.getElementById(\"audio\");audio.play();})()");
            }
        });


    }


    @Override
    public void generarSpinnerPosiciones() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.posiciones, R.layout.item_spinner);

        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.item_lista_spinner);

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        acordesPresenter.consultarAcorde(acorde, "PF");
                        posicion = "PF";
                        sharedPref.setPosicion("PF");

                        break;

                    case 1:
                        acordesPresenter.consultarAcorde(acorde, "1P");
                        posicion = "1P";
                        sharedPref.setPosicion("1P");
                        break;

                    case 2:
                        acordesPresenter.consultarAcorde(acorde, "2P");
                        posicion = "2P";
                        sharedPref.setPosicion("2P");
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void mostrarWebView(String nombre, String htmlacorde, String posicion) {
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
    public void mostrarListaAcordes(ArrayList<Acordes> acordes) {
        acordesArrayList = acordes;

        /*recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);*/

        //RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 3);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        //layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new AcordesAdapter(acordesArrayList,getContext());
        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                acorde = acordes.get(recyclerView.getChildAdapterPosition(view)).getNombre();
                acordesPresenter.consultarAcorde(acorde, posicion);
                sharedPref.setNota(acorde);
            }
        });
        recyclerView.setAdapter(adapter);
    }
}