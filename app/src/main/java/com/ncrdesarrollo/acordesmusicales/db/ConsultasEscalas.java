package com.ncrdesarrollo.acordesmusicales.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ncrdesarrollo.acordesmusicales.includes.Variables;
import com.ncrdesarrollo.acordesmusicales.models.Acordes;
import com.ncrdesarrollo.acordesmusicales.models.Escalas;

import java.util.ArrayList;

public class ConsultasEscalas {

    Context context;
    private MyDatabaseAssets admin;
    private SQLiteDatabase db;
    private Cursor fila;
    private ArrayList<Escalas> escalasArrayList;

    public ConsultasEscalas(Context context) {
        this.context = context;
        admin = new MyDatabaseAssets(context);
        db = admin.getWritableDatabase();
        escalasArrayList = new ArrayList<>();
    }

    public ArrayList<Escalas> consultarListaEscalas(String nota){

        if (admin != null){
            fila = db.rawQuery("SELECT id,nombre FROM Escalas WHERE nota = '"+nota+"' ORDER BY nombre ASC", null);
            if (fila.moveToFirst()){
                do {
                    Escalas escala = new Escalas();
                    escala.setId(fila.getInt(0));
                    escala.setNombre(fila.getString(1));
                    /*escala.setHtml(fila.getString(2));
                    escala.setNota(fila.getString(3));
                    escala.setPosicion(fila.getString(4));*/
                    escalasArrayList.add(escala);
                }while (fila.moveToNext());
            }
        }
        return escalasArrayList;
    }

    public ArrayList<Escalas> consultarEscala(String nombre){

        if (admin != null){
            fila = db.rawQuery("SELECT * FROM Escalas WHERE nombre = '"+nombre+"' ", null);
            if (fila.moveToFirst()){
                do {
                    Escalas escala = new Escalas();
                    escala.setId(fila.getInt(0));
                    escala.setNombre(fila.getString(1));
                    escala.setHtml(fila.getString(2));
                    escala.setNota(fila.getString(3));
                    /*Log.i("dssd", fila.getString(1));
                    Log.i("dssd", fila.getString(2));
                    Log.i("dssd", fila.getString(3));*/
                    Variables.nombreescala = fila.getString(1);
                    Variables.htmlescala = fila.getString(2);
                    escalasArrayList.add(escala);
                }while (fila.moveToNext());
            }
        }
        return escalasArrayList;
    }

}
