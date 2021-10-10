package com.ncrdesarrollo.acordesmusicales.views.acordes;

import com.ncrdesarrollo.acordesmusicales.Adapters.AcordesAdapter;
import com.ncrdesarrollo.acordesmusicales.models.Acordes;

import java.util.ArrayList;

public interface IAcordesFragment {

    void generarSpinnerPosiciones();
    void mostrarWebView(String nombre, String html, String posicion);
    void mostrarListaAcordes(ArrayList<Acordes> acordes);
}
