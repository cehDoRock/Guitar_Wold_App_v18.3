package com.example.guitar_world_18;
//cuidado com o package de vocês. Não apaguem ele.

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class MeuAdaptador extends RecyclerView.Adapter<MeuAdaptador.ViewHolder> {
    ArrayList<Guitar> guitars;

    public MeuAdaptador(ArrayList<Guitar> contatos) {
        this.guitars = contatos;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView txtNome;
        final TextView txtPrice;
        final ImageView ivFoto;

        public ViewHolder(View view) {
            super(view);
            txtNome = (TextView) view.findViewById(R.id.txtNome);
            txtPrice = (TextView) view.findViewById(R.id.txtPrice);
            ivFoto = (ImageView) view.findViewById(R.id.ivFoto);
        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_guitars, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Guitar guitar = guitars.get(position);
        holder.txtNome.setText(guitar.nome);
        holder.txtPrice.setText(guitar.price);
        holder.ivFoto.setImageResource(guitar.foto);
    }

    @Override
    public int getItemCount() {
        return guitars.size();
    }
}