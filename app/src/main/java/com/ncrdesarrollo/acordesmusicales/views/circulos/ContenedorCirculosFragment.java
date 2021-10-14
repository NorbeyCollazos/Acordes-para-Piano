package com.ncrdesarrollo.acordesmusicales.views.circulos;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.ncrdesarrollo.acordesmusicales.Adapters.CirculosAdapter;
import com.ncrdesarrollo.acordesmusicales.R;
import com.ncrdesarrollo.acordesmusicales.includes.SharedPref;
import com.ncrdesarrollo.acordesmusicales.includes.Variables;
import com.ncrdesarrollo.acordesmusicales.presenters.circulos.CirculosPresenter;

import java.util.ArrayList;


public class ContenedorCirculosFragment extends Fragment implements ICirculosFragment{

    String acordeG = "";
    String posicion = "PF";
    WebView webView;
    Spinner spinner;
    CirculosPresenter circulosPresenter;
    SharedPref sharedPref;
    ArrayList<String> acordesArrayList;
    RecyclerView recyclerView;
    CirculosAdapter adapter;
    TextView tvacorde;
    CardView btnescuchar;

    public ContenedorCirculosFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_contenedor_circulos, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle args = getArguments();
        String acorde = args.getString("acorde");
        String nota1 = args.getString("nota1");
        String nota2 = args.getString("nota2");
        String nota3 = args.getString("nota3");
        String nota4 = args.getString("nota4");
        String nota5 = args.getString("nota5");
        String nota6 = args.getString("nota6");

        acordesArrayList = new ArrayList<>();
        circulosPresenter = new CirculosPresenter(this, getContext());

        acordesArrayList.add(acorde);
        acordesArrayList.add(nota1);
        acordesArrayList.add(nota2);
        acordesArrayList.add(nota3);
        acordesArrayList.add(nota4);
        acordesArrayList.add(nota5);
        acordesArrayList.add(nota6);

        spinner = (Spinner) view.findViewById(R.id.posiciones_spinner);
        generarSpinnerPosiciones();

        tvacorde = view.findViewById(R.id.tvacorde);

        webView = view.findViewById(R.id.webview);

        recyclerView = view.findViewById(R.id.recyclerview);

        circulosPresenter.consultarAcorde(acorde,"PF");

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        //layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);

        mostrarListaAcordes(acordesArrayList);


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

        adapter.setDropDownViewResource(R.layout.item_lista_spinner);

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        circulosPresenter.consultarAcorde(acordeG, "PF");
                        posicion = "PF";
                        break;

                    case 1:
                        circulosPresenter.consultarAcorde(acordeG, "1P");
                        posicion = "1P";
                        break;

                    case 2:
                        circulosPresenter.consultarAcorde(acordeG, "2P");
                        posicion = "2P";
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
    public void mostrarListaAcordes(ArrayList<String> acordes) {
        adapter = new CirculosAdapter(acordes,getContext());
        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                acordeG = acordes.get(recyclerView.getChildAdapterPosition(view));
                circulosPresenter.consultarAcorde(acordeG, posicion);
            }
        });
        recyclerView.setAdapter(adapter);
    }
}