package com.ncrdesarrollo.acordesmusicales.views.acordes;

import android.app.Dialog;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.ncrdesarrollo.acordesmusicales.Adapters.AcordesAdapter;
import com.ncrdesarrollo.acordesmusicales.Adapters.FragmentAcordesAdapter;
import com.ncrdesarrollo.acordesmusicales.R;
import com.ncrdesarrollo.acordesmusicales.includes.SharedPref;
import com.ncrdesarrollo.acordesmusicales.models.Acordes;
import com.ncrdesarrollo.acordesmusicales.models.AcordesCirculo;
import com.ncrdesarrollo.acordesmusicales.presenters.acordes.AcordesPresenter;

import java.util.ArrayList;

public class AcordesFragment extends Fragment{

    ArrayList<String> titulos;
    FragmentAcordesAdapter fragmentAcordesAdapter;
    ViewPager2 viewPager;

    public AcordesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_acordes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        titulos = new ArrayList<>();

        titulos.add(new String("C (DO)"));
        titulos.add(new String("D (RE)"));
        titulos.add(new String("E (MI)"));
        titulos.add(new String("F (FA)"));
        titulos.add(new String("G (SOL)"));
        titulos.add(new String("A (LA)"));
        titulos.add(new String("B (SI)"));
        fragmentAcordesAdapter = new FragmentAcordesAdapter(this, titulos);
        viewPager = view.findViewById(R.id.pager);
        viewPager.setAdapter(fragmentAcordesAdapter);

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