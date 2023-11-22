package com.example.guitar_world_18;

import android.os.Bundle;
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
    private ArrayList<Guitar> guitars;
    private RecyclerView rvContatos;
    private MeuAdaptador adaptador;

    public BlankFragment_catalog() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        guitars = new ArrayList<>();
        // Remova a chamada para gerarContatos
        // gerarContatos();
        carregarDadosDaAPI();
    }

    private void carregarDadosDaAPI() {
        Call<List<Results>> call = RetrofitClient.getInstance().getMyApi().getSuperHeroes();
        call.enqueue(new Callback<List<Results>>() {
            @Override
            public void onResponse(Call<List<Results>> call, Response<List<Results>> response) {
                List<Results> myHeroList = response.body();
                for (Results result : myHeroList) {
                    criarContato(result.getName(), result.getName(), R.drawable.goldem1); // Substitua 'seu_icone' pelo ícone apropriado
                }
                adaptador.notifyDataSetChanged(); // Notifique o adaptador sobre as mudanças nos dados
            }

            @Override
            public void onFailure(Call<List<Results>> call, Throwable t) {
                // Trate o erro, se necessário
            }
        });
    }

    private void criarContato(String nome, String descricao, int foto) {
        Guitar contato = new Guitar(nome, descricao, foto);
        guitars.add(contato);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blank_catalog, container, false);
        rvContatos = view.findViewById(R.id.rvContatos);
        adaptador = new MeuAdaptador(guitars);
        RecyclerView.LayoutManager layout = new LinearLayoutManager(requireContext());
        rvContatos.setLayoutManager(layout);
        rvContatos.setAdapter(adaptador);
        return view;
    }
}