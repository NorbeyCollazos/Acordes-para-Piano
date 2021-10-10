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

    // this method will save the nightMode State : True or False
    public void setNightModeState(Boolean state) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("NightMode",state);
        editor.commit();
    }
    // this method will load the Night Mode State
    public Boolean loadNightModeState (){
        Boolean state = sharedPreferences.getBoolean("NightMode",false);
        return  state;
    }


    public void setOrientacionPantalla(Boolean state) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("orientacion",state);
        editor.commit();
    }

    public Boolean loadOrientacionPantalla (){
        Boolean state = sharedPreferences.getBoolean("orientacion",false);
        return  state;
    }

}
