package com.ncrdesarrollo.acordesmusicales.views.circulos;

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
import com.ncrdesarrollo.acordesmusicales.Adapters.FragmentCirculosAdapter;
import com.ncrdesarrollo.acordesmusicales.Adapters.FragmentCirculosMenoresAdapter;
import com.ncrdesarrollo.acordesmusicales.R;
import com.ncrdesarrollo.acordesmusicales.models.AcordesCirculo;

import java.util.ArrayList;

public class CirculosMenoresFragment extends Fragment {

    ArrayList<AcordesCirculo> titulos;
    FragmentCirculosMenoresAdapter fragmentCirculosAdapter;
    ViewPager2 viewPager;

    public CirculosMenoresFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_circulos_menores, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        titulos = new ArrayList<>();
        titulos.add(new AcordesCirculo("Cm (DOm)", "Ddim (REdim)", "D# (RE#)", "Fm (FAm)", "G (SOL)", "G#m (SOL#m)", "A# (LA#)"));
        titulos.add(new AcordesCirculo("Dm (REm)", "Edim (MIdim)", "F (FA)", "Gm (SOLm)", "A (LA)", "A# (LA#)","C (DO)"));
        titulos.add(new AcordesCirculo("Em (MIm)","F#dim (FA#dim)","G (SOL)","Am (LAm)","B (SI)","C (DO)","D (RE)"));
        titulos.add(new AcordesCirculo("Fm (FAm)","Gdim (SOLdim)","G# (SOL#)","A#m (LA#m)","C (DO)","C# (DO#)","D# (RE#)"));
        titulos.add(new AcordesCirculo("Gm (SOLm)","Adim (LAdim)","A# (LA#)","Cm (DOm)","D (RE)","D# (RE#)","F (FA)"));
        titulos.add(new AcordesCirculo("Am (LAm)","Bdim (SIdim)","C (DO)","Dm (REm)","E (MI)","F (FA)","G (SOL)"));
        titulos.add(new AcordesCirculo("Bm (SIm)","C#dim (DO#dim)","D (RE)","Em (MIm)","F# (FA#)","G (SOL)","A (LA)"));

        fragmentCirculosAdapter = new FragmentCirculosMenoresAdapter(this, titulos);
        viewPager = view.findViewById(R.id.pager);
        viewPager.setAdapter(fragmentCirculosAdapter);

        TabLayout tabLayout = view.findViewById(R.id.tab_layout);
        new TabLayoutMediator(tabLayout, viewPager,
                new TabLayoutMediator.TabConfigurationStrategy() {
                    @Override
                    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                        tab.setText(titulos.get(position).getAcorde());
                    }
                }
        ).attach();

    }
}