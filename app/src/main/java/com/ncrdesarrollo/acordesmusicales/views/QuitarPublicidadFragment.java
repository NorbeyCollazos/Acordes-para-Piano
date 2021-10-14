package com.ncrdesarrollo.acordesmusicales.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.ncrdesarrollo.acordesmusicales.R;
import com.ncrdesarrollo.acordesmusicales.includes.IdsAdmob;
import com.ncrdesarrollo.acordesmusicales.includes.PublicidadVideoBonificado;

public class QuitarPublicidadFragment extends Fragment {

    private PublicidadVideoBonificado publicidadVideoBonificado;
    private static final String ID_VIDEO_BONIFICADO = IdsAdmob.idBonificado;


    public QuitarPublicidadFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quitar_publicidad, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        publicidadVideoBonificado = new PublicidadVideoBonificado(getContext(), ID_VIDEO_BONIFICADO);
        CardView ver_video = view.findViewById(R.id.card_ver_video);
        ver_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                publicidadVideoBonificado.iniciar();
            }
        });
    }
}