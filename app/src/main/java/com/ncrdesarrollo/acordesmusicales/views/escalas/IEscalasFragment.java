package com.ncrdesarrollo.acordesmusicales.views.escalas;

import com.ncrdesarrollo.acordesmusicales.models.Acordes;
import com.ncrdesarrollo.acordesmusicales.models.Escalas;

import java.util.ArrayList;

public interface IEscalasFragment {

    void mostrarWebView(String nombre, String html);
    void mostrarListaEscalas(ArrayList<Escalas> escalas);
}
