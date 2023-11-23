package com.example.guitar_world_18;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BlankFragment_catalog extends Fragment {
    private List<Guitar> guitars;
    private RecyclerView rvContatos;
    private MeuAdaptador adaptador;

    public BlankFragment_catalog() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void carregarDadosDaAPI() {
        Call<List<Guitar>> call = RetrofitClient.getInstance().getMyApi().getGuitars();
        call.enqueue(new Callback<List<Guitar>>() {
            @Override
            public void onResponse(Call<List<Guitar>> call, Response<List<Guitar>> response) {
                guitars = response.body();
                adaptador = new MeuAdaptador(guitars);
                RecyclerView.LayoutManager layout = new LinearLayoutManager(requireContext());
                rvContatos.setLayoutManager(layout);
                rvContatos.setAdapter(adaptador);
            }

            @Override
            public void onFailure(Call<List<Guitar>> call, Throwable t) {
                // Trate o erro, se necessário
            }
        });
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blank_catalog, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvContatos = view.findViewById(R.id.rvContatos);
        carregarDadosDaAPI();
    }
}