package com.ncrdesarrollo.acordesmusicales.views.circulos;

import com.ncrdesarrollo.acordesmusicales.models.Acordes;

import java.util.ArrayList;

public interface ICirculosFragment {

    void generarSpinnerPosiciones();
    void mostrarWebView(String nombre, String html, String posicion);
    void mostrarListaAcordes(ArrayList<String> acordes);

}
