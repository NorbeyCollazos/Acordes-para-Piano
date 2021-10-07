package com.ncrdesarrollo.acordesmusicales.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.ncrdesarrollo.acordesmusicales.Variables;
import com.ncrdesarrollo.acordesmusicales.models.Acordes;

import java.util.ArrayList;

public class ConsultasAcordes {

    Context context;
    private MyDatabaseAssets admin;
    private SQLiteDatabase db;
    private Cursor fila;
    private ArrayList<Acordes> acordesArrayList;

    public ConsultasAcordes(Context context) {
        this.context = context;
        admin = new MyDatabaseAssets(context);
        db = admin.getWritableDatabase();
        acordesArrayList = new ArrayList<>();
    }

    public ArrayList<Acordes> consultarListaacordes(String nota){

        if (admin != null){
            fila = db.rawQuery("SELECT id,nombre FROM Acordes WHERE nota = '"+nota+"' AND posicion = 'PF' ORDER BY nombre ASC", null);
            if (fila.moveToFirst()){
                do {
                    Acordes acorde = new Acordes();
                    acorde.setId(fila.getInt(0));
                    acorde.setNombre(fila.getString(1));
                    /*acorde.setHtml(fila.getString(2));
                    acorde.setNota(fila.getString(3));
                    acorde.setPosicion(fila.getString(4));*/
                    acordesArrayList.add(acorde);
                }while (fila.moveToNext());
            }
        }
        return acordesArrayList;
    }

    public ArrayList<Acordes> consultarAcorde(String nombre, String posicion){

        if (admin != null){
            fila = db.rawQuery("SELECT * FROM Acordes WHERE nombre = '"+nombre+"' AND posicion = '"+posicion+"'", null);
            if (fila.moveToFirst()){
                do {
                    Acordes acorde = new Acordes();
                    acorde.setId(fila.getInt(0));
                    acorde.setNombre(fila.getString(1));
                    acorde.setHtml(fila.getString(2));
                    acorde.setNota(fila.getString(3));
                    acorde.setPosicion(fila.getString(4));
                    /*Log.i("dssd", fila.getString(1));
                    Log.i("dssd", fila.getString(2));
                    Log.i("dssd", fila.getString(3));*/
                    Variables.htmlacorde = fila.getString(2);
                    acordesArrayList.add(acorde);
                }while (fila.moveToNext());
            }
        }
        return acordesArrayList;
    }

}
