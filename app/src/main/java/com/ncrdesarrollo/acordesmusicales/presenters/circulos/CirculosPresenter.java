package com.ncrdesarrollo.acordesmusicales.presenters.circulos;

import android.content.Context;

import com.ncrdesarrollo.acordesmusicales.db.ConsultasAcordes;
import com.ncrdesarrollo.acordesmusicales.includes.Variables;
import com.ncrdesarrollo.acordesmusicales.views.circulos.ICirculosFragment;

public class CirculosPresenter implements ICirculosPresenter {

    private ICirculosFragment view;
    Context context;
    private ConsultasAcordes consultasAcordes;

    public CirculosPresenter(ICirculosFragment view, Context context) {
        this.view = view;
        this.context = context;
        consultasAcordes = new ConsultasAcordes(context);
    }

    @Override
    public void consultarAcorde(String nombre, String posicion) {
        consultasAcordes.consultarAcorde(nombre, posicion);
        view.mostrarWebView(Variables.nombreacorde, Variables.htmlacorde, Variables.posicionacorde);
    }
}
