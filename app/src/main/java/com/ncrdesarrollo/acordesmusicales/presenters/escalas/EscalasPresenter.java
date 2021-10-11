package com.ncrdesarrollo.acordesmusicales.presenters.escalas;

import android.content.Context;

import com.ncrdesarrollo.acordesmusicales.db.ConsultasEscalas;
import com.ncrdesarrollo.acordesmusicales.includes.Variables;
import com.ncrdesarrollo.acordesmusicales.models.Escalas;
import com.ncrdesarrollo.acordesmusicales.views.escalas.IEscalasFragment;

import java.util.ArrayList;

public class EscalasPresenter implements IEscalasPresenter{

    private IEscalasFragment view;
    Context context;
    private ConsultasEscalas consultasEscalas;
    private ArrayList<Escalas> escalasArrayList;

    public EscalasPresenter(IEscalasFragment view, Context context) {
        this.view = view;
        this.context = context;

    }

    @Override
    public void consultarLista(String nota) {
        consultasEscalas = new ConsultasEscalas(context);
        escalasArrayList = new ArrayList<>();
        escalasArrayList = consultasEscalas.consultarListaEscalas(nota);
        view.mostrarListaEscalas(escalasArrayList);
    }

    @Override
    public void consultarEscala(String nombre) {
        consultasEscalas = new ConsultasEscalas(context);
        consultasEscalas.consultarEscala(nombre);
        view.mostrarWebView(Variables.nombreescala, Variables.htmlescala);
    }
}
