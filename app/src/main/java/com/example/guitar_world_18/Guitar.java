package com.example.guitar_world_18;

import com.google.gson.annotations.SerializedName;

public class Guitar {
    @SerializedName("id_git")
    private int id;
    @SerializedName("name_git")
    String nome;
    @SerializedName("price_git")
    String price;
    @SerializedName("brand_brand")
    String marca;
    @SerializedName("color")
    String foto;


    public Guitar(int id, String nome, String price, String marca, String foto) {
        this.id = id;
        this.nome = nome;
        this.price = price;
        this.marca = marca;
        this.foto = foto;

    }
    public int getId() {
        return id;
    }
    public String getNome() {
        return this.nome;
    }
    public String getPrice() {
        return price;
    }
    public String getMarca() {
        return marca;
    }
    public String getFoto() {
        return foto;
    }
}

