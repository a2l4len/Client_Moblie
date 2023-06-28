package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class PageloginActivity extends AppCompatActivity {
    private EditText editTextemail;
    private EditText editTextPassword;
    private Button loginbutton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pagelogin_layout);

        editTextemail = findViewById(R.id.editTextemail);
        editTextPassword = findViewById(R.id.editTextPassword);
        loginbutton = findViewById(R.id.loginbutton);

        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextemail.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();

                // Perform login action by sending a POST request to the server
                performLogin(email, password);
            }
        });
    }

    private void performLogin(String email, String password) {
        String u = "http://192.168.1.3:7000/loginreq";
        //String u = "http://172.22.2.22:7000/loginreq";

        try {
            JSONObject requestData = new JSONObject();
            requestData.put("EmailAddress", email);
            requestData.put("Password", password);

            Login login = new Login();
            login.execute(u, requestData.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private class Login extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            String url = params[0];
            String requestData = params[1];
            String result = "";

            try {
                URL link = new URL(url);
                HttpURLConnection connection = (HttpURLConnection) link.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setDoOutput(true);

                OutputStream outputStream = connection.getOutputStream();
                outputStream.write(requestData.getBytes());
                outputStream.flush();
                outputStream.close();

                int responseCode = connection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    InputStream inputStream = connection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    String line;
                    StringBuilder response = new StringBuilder();

                    while ((line = bufferedReader.readLine()) != null) {
                        response.append(line);
                    }

                    bufferedReader.close();
                    result = response.toString();
                } else {
                    result = "Error: " + responseCode;
                }
            } catch (IOException e) {
                e.printStackTrace();
                result = "Error: " + e.getMessage();
            }
            System.out.println("result: " + result);
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
//            try {
//                JSONObject responseJson = new JSONObject(result);
//                int status = responseJson.optInt("status");
//                String hasil = responseJson.optString("response");

                if (result.equals("Login Berhasil")/*status == 200 && hasil.equals("Login Berhasil")*/) {
                    // Login successful
                    Toast.makeText(PageloginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(PageloginActivity.this, HalamanutamaActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    // Login failed
                    Toast.makeText(PageloginActivity.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
                }
//            } catch (JSONException e) {
//                e.printStackTrace();
//                Toast.makeText(PageloginActivity.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
//            }
        }
    }
}
