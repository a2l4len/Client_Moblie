package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

public class daftarhotelActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Button backbutton;

    private listdaftarhoteladapter listdaftarhoteladapter;

    @SuppressLint("MissingInflatedId")
    @Override

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daftarhotel_layout);

        backbutton = findViewById(R.id.backbutton);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listdaftarhoteladapter = new listdaftarhoteladapter();
        recyclerView.setAdapter(listdaftarhoteladapter);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(daftarhotelActivity.this, HalamanutamaActivity.class);
                startActivity(intent);
            }
        });
        new daftarhotelActivity.getJSON().execute();
    }
    private class getJSON extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            String response = null;
            HttpURLConnection conn = null;

            try {
                URL u = new URL("http://192.168.1.3:7000/TempatTersedia");
                //URL u = new URL("http://172.22.2.22:7000/TempatTersedia");
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
                    List<isilistdaftarhotel> list = parseResponse(jsonArray);
                    listdaftarhoteladapter.setdaftarhotellist(list);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(daftarhotelActivity.this, "Failed to parse response", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(daftarhotelActivity.this, "Failed to fetch data", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private List<isilistdaftarhotel> parseResponse(JSONArray jsonArray) throws JSONException {
        List<isilistdaftarhotel> list = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            String Hotel = jsonObject.getString("Nama_Hotel");
            String Lokasi = jsonObject.getString("Tempat_Hotel");

            isilistdaftarhotel isilist = new isilistdaftarhotel(Hotel,Lokasi);
            list.add(isilist);
        }

        return list;
    }
}
