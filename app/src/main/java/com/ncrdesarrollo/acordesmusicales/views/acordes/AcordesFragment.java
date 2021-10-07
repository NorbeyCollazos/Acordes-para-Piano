package com.ncrdesarrollo.acordesmusicales.views.acordes;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.ncrdesarrollo.acordesmusicales.Adapters.AcordesAdapter;
import com.ncrdesarrollo.acordesmusicales.Adapters.FragmentAcordesAdapter;
import com.ncrdesarrollo.acordesmusicales.R;
import com.ncrdesarrollo.acordesmusicales.includes.SharedPref;
import com.ncrdesarrollo.acordesmusicales.models.Acordes;
import com.ncrdesarrollo.acordesmusicales.presenters.acordes.AcordesPresenter;

import java.util.ArrayList;

public class AcordesFragment extends Fragment implements IAcordesFragment{

    String html = "<!DOCTYPE html> <html lang=\"en\"> <head> <meta charset=\"UTF-8\"> <title>Pinao</title> <link rel=\"stylesheet\" type=\"text/css\" href=\"estilos.css\"> <script type=\"text/javascript\" src=\"funciones.js\"></script> <meta name=\"viewport\" content=\"initial-scale=1, maximum-scale=3\"> </head> <body>";
    String htmlcierre =" </body> </html>";
    String nota = "<ul class=\"set\">\n" +
            "            <li onclick=\" jsNota(261.626)\" class=\"white e C \"><span class=\"bl\">DO<br>C</span></li>\n" +
            "            <li onclick=\" jsNota(277.183)\" class=\"black ds C# \"></li>\n" +
            "            <li onclick=\" jsNota(293.665)\" class=\"white d D \"><span class=\"bl\">RE<br>D</span></li>\n" +
            "            <li onclick=\" jsNota(311.127)\" class=\"black cs D# \"></li>\n" +
            "            <li onclick=\" jsNota(329.628)\" class=\"white c E \"><span class=\"bl\">MI<br>E</span></li>\n" +
            "            <li onclick=\" jsNota(349.228)\" class=\"white b F \"><span class=\"bl\">FA<br>F</span></li>\n" +
            "            <li onclick=\" jsNota(369.994)\" class=\"black as F# active\"></li>\n" +
            "            <li onclick=\" jsNota(391.995)\" class=\"white a G \"><span class=\"bl\">SOL<br>G</span></li>\n" +
            "            <li onclick=\" jsNota(415.305)\" class=\"black gs G# \"></li>\n" +
            "            <li onclick=\" jsNota(440.000)\" class=\"white g A \"><span class=\"bl\">LA<br>A</span></li>\n" +
            "            <li onclick=\" jsNota(466.164)\" class=\"black fs A# \"></li>\n" +
            "            <li onclick=\" jsNota(493.883)\" class=\"white f B active\"><span class=\"bl\">SI<br>B</span></li>\n" +
            "            <li onclick=\" jsNota(523.251)\" class=\"white e C \"><span class=\"bl\">DO<br>C</span></li>\n" +
            "            <li onclick=\" jsNota(554.365)\" class=\"black ds C# \"></li>\n" +
            "            <li onclick=\" jsNota(587.330)\" class=\"white d D active\"><span class=\"bl\">RE<br>D</span></li>\n" +
            "            <li onclick=\" jsNota(622.254)\" class=\"black cs D# \"></li>\n" +
            "            <li onclick=\" jsNota(659.255)\" class=\"white c E \"><span class=\"bl\">MI<br>E</span></li>\n" +
            "            <li onclick=\" jsNota(698.456)\" class=\"white b F \"><span class=\"bl\">FA<br>F</span></li>\n" +
            "            <li onclick=\" jsNota(739.989)\" class=\"black as F# \"></li>\n" +
            "            <li onclick=\" jsNota(783.991)\" class=\"white a G \"><span class=\"bl\">SOL<br>G</span></li>\n" +
            "            <li onclick=\" jsNota(830.609)\" class=\"black gs G# \"></li>\n" +
            "            <li onclick=\" jsNota(880.000)\" class=\"white g A\"><span class=\"bl\">LA<br>A</span></li>\n" +
            "            <li onclick=\" jsNota(932.328)\" class=\"black fs A# \"></li>\n" +
            "            <li onclick=\" jsNota(987.767)\" class=\"white f B \"><span class=\"bl\">SI<br>B</span></li>       \n" +
            "    </ul>\n" +
            "<audio id=\"audio\" controls>\n" +
            "<source type=\"audio/mp3\" src=\"SONIDOS/bmp2.mp3\">\n" +
            "</audio>\n" +
            "<button onclick=\"sonar()\"class=\"btn_sonar\">Escuchar</button>";
    WebView webView;
    Spinner spinner;
    RadioGroup groupt;
    RadioGroup groupta;
    AcordesPresenter acordesPresenter;
    SharedPref sharedPref;
    ArrayList<Acordes> acordesArrayList;
    RecyclerView recyclerView;
    AcordesAdapter adapter;
    ArrayList<String> titulos;
    FragmentAcordesAdapter fragmentAcordesAdapter;
    ViewPager2 viewPager;

    public AcordesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_acordes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        acordesPresenter = new AcordesPresenter(this, getContext());
        sharedPref = new SharedPref(getContext());

        /*spinner = (Spinner) view.findViewById(R.id.posiciones_spinner);
        generarSpinnerPosiciones();

        groupt = (RadioGroup) view.findViewById(R.id.radioGroupListaRepertorio);
        groupta = (RadioGroup) view.findViewById(R.id.radioGroupListaacordes);
        generarBotonesAcordes();

        webView = view.findViewById(R.id.webview);

        acordesArrayList = new ArrayList<>();*/

        //mostrarWebView();

        //acordesPresenter.consultarAcorde(sharedPref.getNota(), sharedPref.getPosicion());



        /*recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);*/

        titulos = new ArrayList<>();
        titulos.add(new String("C (DO)"));
        titulos.add(new String("D (RE)"));
        titulos.add(new String("E (MI)"));
        titulos.add(new String("F (FA)"));
        titulos.add(new String("G (SOL)"));
        titulos.add(new String("A (LA)"));
        titulos.add(new String("B (SI)"));
        fragmentAcordesAdapter = new FragmentAcordesAdapter(this, titulos);
        viewPager = view.findViewById(R.id.pager);
        viewPager.setAdapter(fragmentAcordesAdapter);

        TabLayout tabLayout = view.findViewById(R.id.tab_layout);
        new TabLayoutMediator(tabLayout, viewPager,
                new TabLayoutMediator.TabConfigurationStrategy() {
                    @Override
                    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                        tab.setText(titulos.get(position));
                    }
                }
        ).attach();


    }

    @Override
    public void generarBotonesAcordes() {
        String[] opcionesAcordes = this.getResources().getStringArray(R.array.notas);

        for (int i = 0; i < opcionesAcordes.length; i++) {
            final String acorde = opcionesAcordes[i];

            final Button btn = new Button(getActivity());
            btn.setText(acorde);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    acordesArrayList.clear();
                    acorde.charAt(0);
                    acordesPresenter.consultarAcordes(String.valueOf(acorde.charAt(0)));

                }
            });
            groupt.addView(btn);



        }
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

        /*Log.i("dssd", acordes.get(0).getNombre());
        Log.i("dssd", acordes.get(0).getHtml());
        Log.i("dssd", acordes.get(0).getPosicion());*/



    }

    @Override
    public void mostrarListaAcordes(ArrayList<Acordes> acordes) {

        /*final Dialog dialogF = new Dialog(getActivity());
        dialogF.setContentView(R.layout.seleccionaracorde);
        dialogF.setTitle("ELEGIR LA NOTA");

        //createRadioButtonsFondo();
        RadioGroup groupt = (RadioGroup) dialogF.findViewById(R.id.radioGroupListaacordes);

        for (int i = 0; i < acordes.size(); i++) {
            final String acorde = acordes.get(i).getNombre();

            final RadioButton rbAcorde = new RadioButton(getActivity());

            rbAcorde.setText(acorde);

            int position = i;
            rbAcorde.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    acordesPresenter.consultarAcorde(acordes.get(position).getNombre(), sharedPref.getPosicion());
                    sharedPref.setNota(acorde);
                    acordes.clear();
                    dialogF.dismiss();
                }
            });

            groupt.addView(rbAcorde);
        }

        dialogF.show();*/

        acordesArrayList = acordes;

        for (int i = 0; i < acordesArrayList.size(); i++) {
            final String acorde = acordesArrayList.get(i).getNombre();

            final Button rbAcorde = new Button(getActivity());

            rbAcorde.setText(acorde);

            int position = i;
            rbAcorde.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    acordesPresenter.consultarAcorde(acordes.get(position).getNombre(), sharedPref.getPosicion());
                    sharedPref.setNota(acorde);

                }
            });

            groupta.addView(rbAcorde);
        }

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