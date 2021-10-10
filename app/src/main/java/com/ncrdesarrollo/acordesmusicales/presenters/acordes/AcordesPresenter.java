package com.ncrdesarrollo.acordesmusicales.presenters.acordes;

import android.content.Context;

import com.ncrdesarrollo.acordesmusicales.includes.Variables;
import com.ncrdesarrollo.acordesmusicales.db.ConsultasAcordes;
import com.ncrdesarrollo.acordesmusicales.models.Acordes;
import com.ncrdesarrollo.acordesmusicales.views.acordes.IAcordesFragment;

import java.util.ArrayList;

public class AcordesPresenter implements IAcordesPresenter{

    private IAcordesFragment view;
    private Context context;
    private ConsultasAcordes consultasAcordes;
    private ArrayList<Acordes> acordesArrayList;

    public AcordesPresenter(IAcordesFragment view, Context context) {
        this.view = view;
        this.context = context;
        acordesArrayList = new ArrayList<>();
        consultasAcordes = new ConsultasAcordes(context);
    }

    @Override
    public void consultarAcordes(String acorde) {

        acordesArrayList = consultasAcordes.consultarListaacordes(acorde);
        view.mostrarListaAcordes(acordesArrayList);
    }

    @Override
    public void consultarAcorde(String nombre, String posicion) {
        consultasAcordes.consultarAcorde(nombre, posicion);
        view.mostrarWebView(Variables.nombreacorde, Variables.htmlacorde, Variables.posicionacorde);


    }
}
