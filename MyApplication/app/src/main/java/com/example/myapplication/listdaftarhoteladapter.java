package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class listdaftarhoteladapter extends RecyclerView.Adapter<listdaftarhoteladapter.daftarhotelview> {
    private List<isilistdaftarhotel> list;
    public listdaftarhoteladapter(){this.list = new ArrayList<>();}

    @NonNull
    @Override
    public daftarhotelview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_daftarhotel, parent, false);
        return new daftarhotelview(view);
    }

    @Override
    public void onBindViewHolder(@NonNull daftarhotelview hold, int position) {
        isilistdaftarhotel daftar = list.get(position);
        hold.bin(daftar);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setdaftarhotellist(List<isilistdaftarhotel> list){
        this.list = list;
        notifyDataSetChanged();
    }

    public class daftarhotelview extends RecyclerView.ViewHolder {
        private TextView txthotel;
        private TextView txtlokasi;

        public daftarhotelview(@NonNull View itemView) {
            super(itemView);
            txthotel = itemView.findViewById(R.id.txthotel);
            txtlokasi = itemView.findViewById(R.id.txtlokasi);
        }

        public void bin(isilistdaftarhotel daftar) {
            txthotel.setText(daftar.gettxthotel());
            txtlokasi.setText(daftar.gettxtlokasi());
            //txtjumlah.setText(String.valueOf(dftr.getJumlahkamar()));
        }
    }
}
