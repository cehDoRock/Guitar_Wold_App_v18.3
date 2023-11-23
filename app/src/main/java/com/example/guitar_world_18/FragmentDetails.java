package com.example.guitar_world_18;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentDetails extends Fragment {

    private static final String ARG_GUIT_ID = "id";
    private int id;

    public FragmentDetails() {
        // Required empty public constructor
    }

    public static FragmentDetails newInstance(int guitarId) {
        FragmentDetails fragment = new FragmentDetails();
        Bundle args = new Bundle();
        args.putInt(ARG_GUIT_ID, guitarId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            id = getArguments().getInt(ARG_GUIT_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details, container, false);
        renderGuitar(view);

        return view;
    }

    private void renderGuitar(View view) {
        Call<List<Guitar2>> call = RetrofitClient.getInstance().getMyApi().getGuitarById(id);
        call.enqueue(new Callback<List<Guitar2>>() {
            @Override
            public void onResponse(Call<List<Guitar2>> call, Response<List<Guitar2>> response) {
                List<Guitar2> guitars = response.body();
                if (guitars != null && !guitars.isEmpty()) {
                    Guitar2 guitar = guitars.get(0);
                    TextView txtName = view.findViewById(R.id.txt_Name_detail);
                    TextView txtPrice = view.findViewById(R.id.txtPrice_detail);
                    TextView txtMarca = view.findViewById(R.id.txt_marca_details);
                    ImageView imageView = view.findViewById(R.id.imageView);
                    // Populando os elementos com as informações da guitarra
                    txtName.setText(guitar.getNome());
                    txtPrice.setText(guitar.getPrice());
                    txtMarca.setText(guitar.getMarca());

                    String BASE_URL = "http://10.0.2.2/guitar-world/";
                    Picasso.get().load(BASE_URL + guitar.getFoto()).into(imageView);

                    Log.d("GuitarDetails", "Nome da guitarra: " + guitar.getNome());
                    Log.d("GuitarDetails", "Preço da guitarra: " + guitar.getPrice());
                    Log.d("GuitarDetails", "Marca da guitarra: " + guitar.getMarca());
                    Log.d("GuitarDetails", "URL da imagem: " + BASE_URL + guitar.getFoto());
                } else {
                    // Trate o caso em que a lista de guitarras está vazia ou nula
                    Log.e("TESTE", "Lista de guitarras vazia ou nula");
                }
            }

            @Override
            public void onFailure(Call<List<Guitar2>> call, Throwable t) {
                Log.d("testando o que ta recebendo", t.toString());
            }
        });
    }
}
