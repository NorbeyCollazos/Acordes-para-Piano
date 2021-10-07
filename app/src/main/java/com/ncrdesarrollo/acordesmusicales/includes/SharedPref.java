package com.ncrdesarrollo.acordesmusicales.includes;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPref {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    public SharedPref(Context context){
        sharedPreferences = context.getSharedPreferences("preferences", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void setNota(String nota){
        editor.putString("nombrenota", nota);
        editor.commit();
    }

    public String getNota(){
        String nota = sharedPreferences.getString("nombrenota", "C - (DO)");
        return nota;
    }

    public void setPosicion(String posicion){
        editor.putString("posicion", posicion);
        editor.commit();
    }

    public String getPosicion(){
        String posicion = sharedPreferences.getString("posicion", "PF");
        return posicion;
    }
}
