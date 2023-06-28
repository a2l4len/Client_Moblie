package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

//halaman Utama dari Daftar Kamar
public class daftarkamarActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Button kembalibutton;

    private Listdaftarkamaradapter Listdaftarkamaradapter;

    @SuppressLint("MissingInflatedId")
    @Override

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        kembalibutton = findViewById(R.id.kembalibutton);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Listdaftarkamaradapter = new Listdaftarkamaradapter();
        recyclerView.setAdapter(Listdaftarkamaradapter);
        kembalibutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(daftarkamarActivity.this, HalamanutamaActivity.class);
                startActivity(intent);
            }
        });
        new getJSON().execute();
    }
    private class getJSON extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            String response = null;
            HttpURLConnection conn = null;

            try {
                URL u = new URL("http://192.168.1.3:7000/daftarkamar");
                //URL u = new URL("http://172.22.2.22:7000/daftarkamar");
                conn = (HttpURLConnection) u.openConnection();
                conn.setRequestMethod("GET");

                InputStream i = conn.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(i));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                response = stringBuilder.toString();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (conn != null) {
                    conn.disconnect();
                }
            }
            System.out.println("response: " + response);

            return response;

        }
        @Override
        protected void onPostExecute(String response) {
            if (response != null) {
                try {
                    JSONObject responseObject = new JSONObject(response);
                    JSONArray jsonArray = responseObject.getJSONArray("response");
                    List<isiListdaftarkamar> daftarlist = parseResponse(jsonArray);
                    Listdaftarkamaradapter.setdaftarkmrlist(daftarlist);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(daftarkamarActivity.this, "Failed to parse response", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(daftarkamarActivity.this, "Failed to fetch data", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private List<isiListdaftarkamar> parseResponse(JSONArray jsonArray) throws JSONException {
        List<isiListdaftarkamar> daftarlist = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            int jumlahkamar = jsonObject.getInt("Jumlah");
            String jeniskamar = jsonObject.getString("Jenis");

            isiListdaftarkamar isilist = new isiListdaftarkamar(jumlahkamar, jeniskamar);
            daftarlist.add(isilist);
        }

        return daftarlist;
    }
}