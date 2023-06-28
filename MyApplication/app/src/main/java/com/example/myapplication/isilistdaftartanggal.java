package com.example.myapplication;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class isilistdaftartanggal extends RecyclerView.Adapter {
    private String daftartanggal;

    public isilistdaftartanggal(String daftartanggal){
        this.daftartanggal = daftartanggal;
    }

    public String getDaftartanggal(){return daftartanggal;}

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
