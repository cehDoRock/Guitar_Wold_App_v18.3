package com.example.guitar_world_18;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BlankFragment_register#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BlankFragment_register extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BlankFragment_register() {
        // Required empty public constructor
    }




        /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment_register.
     */
    // TODO: Rename and change types and number of parameters
    public static BlankFragment_register newInstance(String param1, String param2) {
        BlankFragment_register fragment = new BlankFragment_register();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        loginWithGet();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank_register, container, false);
    }

    private void loginWithGet() {
        // Só muda isto, o restante é igual
        Call<User> call = RetrofitClient.getInstance().getMyApi().loginWithGet("cissa2007cs@gmail.com", "1234");

        // A chamada é igual ao método loginWithPost, poderia ser substituída por um método
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user1 = response.body();
                Log.d("isso dinc", String.valueOf(user1));
                TextView tvId = getView().findViewById(R.id.txt_1);
                TextView tvFullname = getView().findViewById(R.id.txt_2);

                if (user1 != null) {
                    if (user1.isSuccess()) {
                        tvId.setText("String.valueOf(user1.getId())");
                        tvFullname.setText(user1.getFullname());
                    } else {
                        tvFullname.setText("Deu errado");
                    }
                } else {
                    // Tratar o caso em que user1 é nulo
                    Log.e("ERROR", "O objeto User recebido é nulo.");
                }

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getContext(), "Ocorreu um erro", Toast.LENGTH_LONG).show();
                Log.e("TESTE", t.toString());
            }
        });
    }


}