package com.ncrdesarrollo.acordesmusicales.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import android.widget.Toast;

import com.ncrdesarrollo.acordesmusicales.Adapters.AcordesAdapter;
import com.ncrdesarrollo.acordesmusicales.R;
import com.ncrdesarrollo.acordesmusicales.includes.SharedPref;
import com.ncrdesarrollo.acordesmusicales.models.Acordes;
import com.ncrdesarrollo.acordesmusicales.presenters.acordes.AcordesPresenter;
import com.ncrdesarrollo.acordesmusicales.views.acordes.IAcordesFragment;

import java.util.ArrayList;


public class ContenedorAcordesFragment extends Fragment implements IAcordesFragment {

    String html = "<!DOCTYPE html> <html lang=\"en\"> <head> <meta charset=\"UTF-8\"> <title>Pinao</title> <link rel=\"stylesheet\" type=\"text/css\" href=\"estilos.css\"> <script type=\"text/javascript\" src=\"funciones.js\"></script> <meta name=\"viewport\" content=\"initial-scale=1, maximum-scale=3\"> </head> <body>";
    String htmlcierre =" </body> </html>";
    WebView webView;
    Spinner spinner;
    RadioGroup groupt;
    RadioGroup groupta;
    AcordesPresenter acordesPresenter;
    SharedPref sharedPref;
    ArrayList<Acordes> acordesArrayList;
    RecyclerView recyclerView;
    AcordesAdapter adapter;

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

        spinner = (Spinner) view.findViewById(R.id.posiciones_spinner);
        generarSpinnerPosiciones();

        groupt = (RadioGroup) view.findViewById(R.id.radioGroupListaRepertorio);
        groupta = (RadioGroup) view.findViewById(R.id.radioGroupListaacordes);
        generarBotonesAcordes();

        webView = view.findViewById(R.id.webview);

        acordesArrayList = new ArrayList<>();

        recyclerView = view.findViewById(R.id.recyclerview);

        acordesPresenter.consultarAcordes(String.valueOf(acorde.charAt(0)));

    }

    @Override
    public void generarBotonesAcordes() {

    }

    @Override
    public void generarSpinnerPosiciones() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.posiciones, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
        String posicion = sharedPref.getPosicion();
        if (posicion.equals("PF")){
            spinner.setSelection(0);
        }else if (posicion.equals("1P")){
            spinner.setSelection(1);
        }else if (posicion.equals("2P")){
            spinner.setSelection(2);
        }


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        acordesPresenter.consultarAcorde(sharedPref.getNota(), "PF");
                        sharedPref.setPosicion("PF");
                        break;

                    case 1:
                        acordesPresenter.consultarAcorde(sharedPref.getNota(), "1P");
                        sharedPref.setPosicion("1P");
                        break;

                    case 2:
                        acordesPresenter.consultarAcorde(sharedPref.getNota(), "2P");
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

        webView.loadDataWithBaseURL("file:///android_asset/AcordesPiano/", html+htmlacorde+htmlcierre, "text/html", "UTF-8", null);

    }

    @Override
    public void mostrarListaAcordes(ArrayList<Acordes> acordes) {
        acordesArrayList = acordes;

        /*recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);*/

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 3);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new AcordesAdapter(acordesArrayList,getContext());
        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String acorde = acordes.get(recyclerView.getChildAdapterPosition(view)).getNombre();
                Toast.makeText(getActivity(), ""+acorde, Toast.LENGTH_SHORT).show();
                acordesPresenter.consultarAcorde(acorde, sharedPref.getPosicion());
            }
        });
        recyclerView.setAdapter(adapter);
    }
}