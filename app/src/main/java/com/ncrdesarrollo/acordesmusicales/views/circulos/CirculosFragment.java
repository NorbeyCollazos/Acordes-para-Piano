package com.ncrdesarrollo.acordesmusicales.views.circulos;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.ncrdesarrollo.acordesmusicales.Adapters.FragmentAcordesAdapter;
import com.ncrdesarrollo.acordesmusicales.Adapters.FragmentCirculosAdapter;
import com.ncrdesarrollo.acordesmusicales.R;
import com.ncrdesarrollo.acordesmusicales.models.AcordesCirculo;

import java.util.ArrayList;

public class CirculosFragment extends Fragment {

    ArrayList<AcordesCirculo> titulos;
    FragmentCirculosAdapter fragmentCirculosAdapter;
    ViewPager2 viewPager;

    public CirculosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_circulos, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        titulos = new ArrayList<>();
        titulos.add(new AcordesCirculo("C (DO)", "Dm (REm)", "Em (MIm)", "F (FA)", "G (SOL)", "Am (LAm)", "Bdim (SIdim)"));
        titulos.add(new AcordesCirculo("D (RE)", "Em (MIm)", "F#m (FA#m)", "G (SOL)", "A (LA)", "Bm (SIm)","C#dim (DO#dim)"));
        titulos.add(new AcordesCirculo("E (MI)","F#m (FA#m)","G#m (SOL#m)","A (LA)","B (SI)","C#m (DO#m)","D#dim (RE#dim)"));
        titulos.add(new AcordesCirculo("F (FA)","Gm (SOLm)","Am (LAm)","A# (LA#)","C (DO)","Dm (REm)","Edim (MIdim)"));
        titulos.add(new AcordesCirculo("G (SOL)","Am (LAm)","Bm (SIm)","C (DO)","D (RE)","Em (MIm)","F#dim (FA#dim)"));
        titulos.add(new AcordesCirculo("A (LA)","Bm (SIm)","C#m (DO#m)","D (RE)","E (MI)","F#m (FA#m)","G#dim (SOL#dim)"));
        titulos.add(new AcordesCirculo("B (SI)","C#m (DO#m)","D#m (RE#m)","E (MI)","F# (FA#)","G#m (SOL#m)","A#dim (LA#dim)"));

        fragmentCirculosAdapter = new FragmentCirculosAdapter(this, titulos);
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

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.circulos, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_menores:  {
                Navigation.findNavController(getView()).navigate(R.id.circulosMenoresFragment);
                return true;
            }

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}