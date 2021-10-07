package com.ncrdesarrollo.acordesmusicales.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ncrdesarrollo.acordesmusicales.R;
import com.ncrdesarrollo.acordesmusicales.includes.SharedPref;
import com.ncrdesarrollo.acordesmusicales.models.Acordes;
import com.ncrdesarrollo.acordesmusicales.presenters.acordes.AcordesPresenter;

import java.util.ArrayList;

public class AcordesAdapter extends RecyclerView.Adapter<AcordesAdapter.acordesViewHolder> implements View.OnClickListener {
    ArrayList<Acordes> acordes;
    Context context;
    private View.OnClickListener listener;

    public AcordesAdapter(ArrayList<Acordes> acordes, Context context) {
        this.acordes = acordes;
        this.context = context;

    }

    @NonNull
    @Override
    public acordesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_acorde, parent, false);
        view.setOnClickListener(this);
        return new acordesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull acordesViewHolder holder, int position) {
        final Acordes acorde = acordes.get(position);
        holder.tvacorde.setText(acorde.getNombre());

    }

    @Override
    public int getItemCount() {
        return acordes.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if (listener != null){
            listener.onClick(view);
        }
    }

    public class acordesViewHolder extends RecyclerView.ViewHolder {
        private TextView tvacorde;
        private View view;

        public acordesViewHolder(@NonNull View itemView) {
            super(itemView);
            tvacorde = itemView.findViewById(R.id.tvacorde);
            view = itemView;
        }
    }
}
