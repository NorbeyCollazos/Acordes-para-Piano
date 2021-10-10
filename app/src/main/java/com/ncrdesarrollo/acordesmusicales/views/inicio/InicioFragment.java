package com.ncrdesarrollo.acordesmusicales.views.inicio;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ncrdesarrollo.acordesmusicales.R;

import io.github.florent37.shapeofview.shapes.CircleView;

public class InicioFragment extends Fragment {

    CircleView btn_acordes;
    CircleView btn_escalas;
    CircleView btn_circulosarmonicos;

    public InicioFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_inicio, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final NavController navController = Navigation.findNavController(view);

        btn_acordes = view.findViewById(R.id.btn_acordes);
        btn_escalas = view.findViewById(R.id.btn_escalas);
        btn_circulosarmonicos = view.findViewById(R.id.btn_circulosarmonicos);

        btn_acordes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.acordesFragment);
            }
        });

        btn_escalas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.escalasFragment);
            }
        });

    }
}