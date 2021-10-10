package com.ncrdesarrollo.acordesmusicales.views.escalas;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.ncrdesarrollo.acordesmusicales.Adapters.FragmentAcordesAdapter;
import com.ncrdesarrollo.acordesmusicales.Adapters.FragmentEscalasAdapter;
import com.ncrdesarrollo.acordesmusicales.R;

import java.util.ArrayList;


public class EscalasFragment extends Fragment {

    ArrayList<String> titulos;
    FragmentEscalasAdapter fragmentEscalasAdapter;
    ViewPager2 viewPager;

    public EscalasFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_escalas, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        titulos = new ArrayList<>();
        titulos.add(new String("C (DO)"));
        titulos.add(new String("D (RE)"));
        titulos.add(new String("E (MI)"));
        titulos.add(new String("F (FA)"));
        titulos.add(new String("G (SOL)"));
        titulos.add(new String("A (LA)"));
        titulos.add(new String("B (SI)"));
        fragmentEscalasAdapter = new FragmentEscalasAdapter(this, titulos);
        viewPager = view.findViewById(R.id.pager);
        viewPager.setAdapter(fragmentEscalasAdapter);

        TabLayout tabLayout = view.findViewById(R.id.tab_layout);
        new TabLayoutMediator(tabLayout, viewPager,
                new TabLayoutMediator.TabConfigurationStrategy() {
                    @Override
                    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                        tab.setText(titulos.get(position));
                    }
                }
        ).attach();

    }
}