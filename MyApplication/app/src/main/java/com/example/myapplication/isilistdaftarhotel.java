package com.example.myapplication;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class isilistdaftarhotel extends RecyclerView.Adapter {
    private String txthotel;
    private String txtlokasi;

    public isilistdaftarhotel(String txthotel, String txtlokasi){
        this.txthotel = txthotel;
        this.txtlokasi = txtlokasi;
    }

    public String gettxthotel() {return txthotel;}
    public String gettxtlokasi() {return txtlokasi;}

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
