package com.ncrdesarrollo.acordesmusicales.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
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
        Toast.makeText(getContext(), acorde, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void generarBotonesAcordes() {

    }

    @Override
    public void generarSpinnerPosiciones() {

    }

    @Override
    public void mostrarWebView(String nombre, String html, String posicion) {

    }

    @Override
    public void mostrarListaAcordes(ArrayList<Acordes> acordes) {

    }
}