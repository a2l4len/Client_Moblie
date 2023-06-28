package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class listdaftartanggaladapter extends RecyclerView.Adapter<listdaftartanggaladapter.daftartanggalview>{
    private List<isilistdaftartanggal> list;
    public listdaftartanggaladapter(){this.list = new ArrayList<>();}

    @NonNull
    @Override
    public daftartanggalview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_daftartanggal, parent, false);
        return new daftartanggalview(view);
    }

    @Override
    public void onBindViewHolder(@NonNull daftartanggalview hold, int position) {
        isilistdaftartanggal daftar = list.get(position);
        hold.binder(daftar);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setdaftartangallist(List<isilistdaftartanggal> list){
        this.list = list;
        notifyDataSetChanged();
    }

    public class daftartanggalview extends RecyclerView.ViewHolder {
        private TextView txttanggal;

        public daftartanggalview(@NonNull View itemView) {
            super(itemView);
            txttanggal = itemView.findViewById(R.id.txttanggal);
        }

        public void binder(isilistdaftartanggal daftar) {
            txttanggal.setText(daftar.getDaftartanggal());
            //txtjumlah.setText(String.valueOf(dftr.getJumlahkamar()));
        }
    }
}
