package com.ncrdesarrollo.acordesmusicales.Adapters;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.ncrdesarrollo.acordesmusicales.models.AcordesCirculo;
import com.ncrdesarrollo.acordesmusicales.views.circulos.ContenedorCirculosFragment;
import com.ncrdesarrollo.acordesmusicales.views.circulos.ContenedorCirculosMenoresFragment;

import java.util.ArrayList;

public class FragmentCirculosMenoresAdapter extends FragmentStateAdapter {

    ArrayList<AcordesCirculo> list;

    public FragmentCirculosMenoresAdapter(@NonNull Fragment fragment, ArrayList<AcordesCirculo> list) {
        super(fragment);
        this.list = list;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment = new ContenedorCirculosMenoresFragment();
        Bundle args = new Bundle();
        args.putInt("iden", position +1);
        args.putString("acorde", list.get(position).getAcorde());
        args.putString("nota1", list.get(position).getNota1());
        args.putString("nota2", list.get(position).getNota2());
        args.putString("nota3", list.get(position).getNota3());
        args.putString("nota4", list.get(position).getNota4());
        args.putString("nota5", list.get(position).getNota5());
        args.putString("nota6", list.get(position).getNota6());

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
