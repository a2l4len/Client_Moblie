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

public class daftartanggalActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Button backbutton;

    private listdaftartanggaladapter listdaftartanggaladapter;

    @SuppressLint("MissingInflatedId")
    @Override

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daftartanggal_layout);

        backbutton = findViewById(R.id.backbutton);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listdaftartanggaladapter = new listdaftartanggaladapter();
        recyclerView.setAdapter(listdaftartanggaladapter);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(daftartanggalActivity.this, HalamanutamaActivity.class);
                startActivity(intent);
            }
        });
        new daftartanggalActivity.getJSON().execute();
    }
    private class getJSON extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            String response = null;
            HttpURLConnection conn = null;

            try {
                //URL u = new URL("http://192.168.1.11:7000/TanggalTersedia");
                URL u = new URL("http://172.22.2.22:7000/TanggalTersedia");
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
                    List<isilistdaftartanggal> list = parseResponse(jsonArray);
                    listdaftartanggaladapter.setdaftartangallist(list);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(daftartanggalActivity.this, "Failed to parse response", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(daftartanggalActivity.this, "Failed to fetch data", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private List<isilistdaftartanggal> parseResponse(JSONArray jsonArray) throws JSONException {
        List<isilistdaftartanggal> list = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            String Tanggal = jsonObject.getString("Tanggal");

            isilistdaftartanggal isilist = new isilistdaftartanggal(Tanggal);
            list.add(isilist);
        }

        return list;
    }
}
