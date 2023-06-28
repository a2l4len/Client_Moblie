package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class HalamanutamaActivity extends AppCompatActivity implements View.OnClickListener {

    private Button daftarkamarbutton;
    private Button bookingpagebutton;
    private Button daftarhotelbutton;
    private Button Logoutbutton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainmenu_layout);

        daftarkamarbutton = findViewById(R.id.daftarkamarbutton);
        bookingpagebutton = findViewById(R.id.bookingpagebutton);
        //daftartanggalbutton = findViewById(R.id.daftartanggalbutton);
        daftarhotelbutton = findViewById(R.id.daftarhotelbutton);
        Logoutbutton = findViewById(R.id.logoutbutton);

        daftarkamarbutton.setOnClickListener(this);
        bookingpagebutton.setOnClickListener(this);
        daftarhotelbutton.setOnClickListener(this);
        //daftartanggalbutton.setOnClickListener(this::onClick);
        Logoutbutton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.daftarkamarbutton:
                // masuk ke page daftar kamar
                Intent intent = new Intent(this, daftarkamarActivity.class);
                startActivity(intent);
                break;
            case R.id.bookingpagebutton:
                // Masuk ke halaman booking
                Intent inten = new Intent(this, BookingHotel.class);
                startActivity(inten);
                break;
//            case R.id.daftartanggalbutton:
//                // masuk ke page daftar tanggal
//                Intent tent = new Intent(this, daftartanggalActivity.class);
//                startActivity(tent);
//                break;
            case R.id.daftarhotelbutton:
                // masuk ke page daftar hotel
                Intent ten = new Intent(this, daftarhotelActivity.class);
                startActivity(ten);
                break;
            case R.id.logoutbutton:
                // Log out
                Intent in = new Intent(this, PageloginActivity.class);
                startActivity(in);
                break;
        }
    }
}
