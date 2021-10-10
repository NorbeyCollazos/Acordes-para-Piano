package com.ncrdesarrollo.acordesmusicales.Adapters;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.ncrdesarrollo.acordesmusicales.views.acordes.ContenedorAcordesFragment;

import java.util.ArrayList;

public class FragmentAcordesAdapter extends FragmentStateAdapter {

    ArrayList<String> list;

    public FragmentAcordesAdapter(@NonNull Fragment fragment, ArrayList<String> list) {
        super(fragment);
        this.list = list;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment = new ContenedorAcordesFragment();
        Bundle args = new Bundle();
        args.putInt("iden", position +1);
        args.putString("acorde", list.get(position));
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
