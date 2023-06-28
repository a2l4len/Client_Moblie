package com.example.myapplication;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

//buat format isi dari file
public class isiListdaftarkamar extends RecyclerView.Adapter {
    private int jumlahkamar;
    private String jeniskamar;

    public isiListdaftarkamar(int jumlahkamar, String jeniskamar){
        this.jumlahkamar = jumlahkamar;
        this.jeniskamar = jeniskamar;
    }

    public int getJumlahkamar(){return jumlahkamar;}
    public String getJeniskamar(){return jeniskamar;}

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
