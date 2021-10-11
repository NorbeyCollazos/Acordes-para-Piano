package com.ncrdesarrollo.acordesmusicales.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.ncrdesarrollo.acordesmusicales.R;
import com.ncrdesarrollo.acordesmusicales.includes.SharedPref;
import com.ncrdesarrollo.acordesmusicales.models.Acordes;
import com.ncrdesarrollo.acordesmusicales.models.AcordesCirculo;

import java.util.ArrayList;

public class CirculosAdapter extends RecyclerView.Adapter<CirculosAdapter.acordesViewHolder> implements View.OnClickListener {
    ArrayList<String> acordes;
    Context context;
    private View.OnClickListener listener;
    SharedPref sharedPref;
    int posicionMarcada;

    public CirculosAdapter(ArrayList<String> acordes, Context context) {
        this.acordes = acordes;
        this.context = context;
        sharedPref = new SharedPref(context);

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
        //final AcordesCirculo acorde = acordes.get(position);
        holder.tvacorde.setText(acordes.get(position));
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
        private CardView card_content;
        private View view;

        public acordesViewHolder(@NonNull View itemView) {
            super(itemView);
            tvacorde = itemView.findViewById(R.id.tvacorde);
            card_content = itemView.findViewById(R.id.card_content);
            view = itemView;
        }
    }
}
