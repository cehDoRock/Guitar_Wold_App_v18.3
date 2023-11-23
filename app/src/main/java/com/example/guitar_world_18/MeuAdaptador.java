package com.example.guitar_world_18;
//cuidado com o package de vocês. Não apaguem ele.

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.sql.SQLTransactionRollbackException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class MeuAdaptador extends RecyclerView.Adapter<MeuAdaptador.ViewHolder> {
    List<Guitar> guitars;
    Context context;


    public MeuAdaptador(List<Guitar> contatos) {
        this.guitars = contatos;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView txtNome;
        final TextView txtPrice;
        final ImageView ivFoto;

        final ConstraintLayout layout;

        public ViewHolder(View view) {
            super(view);
            txtNome = (TextView) view.findViewById(R.id.txtNome);
            txtPrice = (TextView) view.findViewById(R.id.txtPrice);
            ivFoto = (ImageView) view.findViewById(R.id.ivFoto);
            layout = (ConstraintLayout) view.findViewById(R.id.contraintLayoutId);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_guitars, parent, false);

        this.context = parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Guitar guitar = guitars.get(position);
        holder.txtNome.setText(guitar.nome);
        holder.txtPrice.setText(guitar.price);
//        holder.ivFoto.setImageResource(guitar.foto);

        String BASE_URL = "http://10.0.2.2/guitar-world/";
        Picasso.get().load(BASE_URL+guitar.foto).into(holder.ivFoto);

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //para instanciar o FragmentTransaction é necessário usar o método  getSupportFragmentManager
                //deste modo, é preciso fazer o casting do context com ((FragmentActivity) context)
                FragmentTransaction fragmentTransaction = ((FragmentActivity) context).getSupportFragmentManager().beginTransaction();
                FragmentDetails fragmentDetails = FragmentDetails.newInstance(guitar.getId());
                fragmentTransaction.replace(R.id.fragmentContainerView, fragmentDetails);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return guitars.size();
    }
}