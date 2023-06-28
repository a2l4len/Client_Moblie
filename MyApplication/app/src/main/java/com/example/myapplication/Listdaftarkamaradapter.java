package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
//nampilno list dek aplikasi
public class Listdaftarkamaradapter extends RecyclerView.Adapter<Listdaftarkamaradapter.daftarkamarview>{
    private List<isiListdaftarkamar> daftarlist;
    public Listdaftarkamaradapter(){this.daftarlist = new ArrayList<>();}

    @NonNull
    @Override
    public daftarkamarview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_daftarkamar, parent, false);
        return new daftarkamarview(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Listdaftarkamaradapter.daftarkamarview holder, int position) {
        isiListdaftarkamar dftr = daftarlist.get(position);
        holder.bind(dftr);
    }

    @Override
    public int getItemCount() {
        return daftarlist.size();
    }

    public void setdaftarkmrlist(List<isiListdaftarkamar> daftarlist){
        this.daftarlist = daftarlist;
        notifyDataSetChanged();
    }

    public class daftarkamarview extends RecyclerView.ViewHolder {
        private TextView txtjumlah;
        private TextView txtjenis;

        public daftarkamarview(@NonNull View itemView) {
            super(itemView);
            txtjumlah = itemView.findViewById(R.id.txtjumlah);
            txtjenis = itemView.findViewById(R.id.txtjenis);
        }

        public void bind(isiListdaftarkamar dftr) {
            txtjenis.setText(dftr.getJeniskamar());
            txtjumlah.setText(String.valueOf(dftr.getJumlahkamar()));
        }
    }
}
