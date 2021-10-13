package com.ncrdesarrollo.acordesmusicales.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.ncrdesarrollo.acordesmusicales.R;
import com.ncrdesarrollo.acordesmusicales.includes.SharedPref;

public class ConfiguracionesFragment extends Fragment {

    Switch sworientacion;
    Switch swpantallaactiva;
    Switch swmodonocturno;

    SharedPref sharedPref;

    public ConfiguracionesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_configuraciones, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sharedPref = new SharedPref(getContext());

        //modo nocturno
        swmodonocturno = view.findViewById(R.id.myswitchmodonocturno);
        if (sharedPref.loadNightModeState()==true){
            swmodonocturno.setChecked(true);
            swmodonocturno.setText("Desactivar modo nocturno");
        }else {
            swmodonocturno.setText("Activar modo nocturno");
        }
        swmodonocturno.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    sharedPref.setNightModeState(true);
                    actualizarPantaala();
                }else{
                    sharedPref.setNightModeState(false);
                    actualizarPantaala();
                }
            }
        });

        //orientacion de panttalla
        sworientacion = view.findViewById(R.id.myswitch);
        if (sharedPref.loadOrientacionPantalla()==true){
            sworientacion.setChecked(true);
            sworientacion.setText("Modo vertical");
        }else {
            sworientacion.setText("Modo horizontal");
        }
        sworientacion.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    sharedPref.setOrientacionPantalla(true);
                    actualizarPantaala();
                }else{
                    sharedPref.setOrientacionPantalla(false);
                    actualizarPantaala();
                }
            }
        });

        //para la pantalla activa
        swpantallaactiva = view.findViewById(R.id.myswitchpantallaactiva);
        if (sharedPref.loadPantallaActiva()==true){
            swpantallaactiva.setChecked(true);
            swpantallaactiva.setText("No mantener pantalla activa");
        }else {
            swpantallaactiva.setText("Mantener pantalla activa");
        }
        swpantallaactiva.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    sharedPref.setPantallaActiva(true);
                    actualizarPantaala();
                }else{
                    sharedPref.setPantallaActiva(false);
                    actualizarPantaala();
                }
            }
        });

    }

    public void actualizarPantaala(){
        getActivity().finish();
        getActivity().startActivity(getActivity().getIntent());
    }
}