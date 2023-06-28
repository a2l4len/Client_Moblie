package com.example.myapplication;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.icu.util.Calendar;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Locale;

public class BookingHotel extends AppCompatActivity  {
    private EditText isiemail;
    private EditText tanggalnginap;
    private EditText isihotel;
    private EditText isitempat;
    private EditText isinama;
    private EditText isijeniskamar;
    private Button bookingbutton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.booking_layout);

        isiemail = findViewById(R.id.inputemail);
        isinama = findViewById(R.id.inputnama);
        tanggalnginap = findViewById(R.id.inputtanggal);
        isihotel = findViewById(R.id.inputhotel);
        isitempat = findViewById(R.id.inputlokasihotel);
        isijeniskamar = findViewById(R.id.inputkamar);
        bookingbutton = findViewById(R.id.bookingbutton);

        bookingbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = isiemail.getText().toString().trim();
                String nama  = isinama.getText().toString().trim();
                String tanggal = tanggalnginap.getText().toString().trim();
                String hotel = isihotel.getText().toString().trim();
                String tempat = isitempat.getText().toString().trim();
                String jeniskamar = isijeniskamar.getText().toString().trim();


                if (email.isEmpty() || nama.isEmpty() || tanggal.isEmpty() || hotel.isEmpty() || tempat.isEmpty() || jeniskamar.isEmpty()) {
                    Toast.makeText(BookingHotel.this, "Please fill in all datas", Toast.LENGTH_SHORT).show();
                } else {
                    JSONObject databooking = new JSONObject();
                    try {
                        databooking.put("EmailAddress", email);
                        databooking.put("nama",nama);
                        databooking.put("TanggalTersedia", tanggal);
                        databooking.put("TempatTersedia", hotel);
                        databooking.put("lokasiHotel", tempat);
                        databooking.put("kamar",jeniskamar);

                        new Bookingtodb().execute(databooking.toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private class Bookingtodb extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            String result = "";
            try {
                String databooking = params[0];
                URL u = new URL("http://192.168.1.3:7000/booking");
                //URL u = new URL("http://172.22.2.22:7000/booking");

                HttpURLConnection connection = (HttpURLConnection) u.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setDoOutput(true);

                OutputStream os = connection.getOutputStream();
                os.write(databooking.getBytes());
                os.flush();

                int responseCode = connection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuilder responseBuilder = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        responseBuilder.append(line);
                    }
                    result = responseBuilder.toString();
                } else {
                    result = "Error: " + responseCode;
                }

                connection.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
                result = "Error occurred";
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(BookingHotel.this, result, Toast.LENGTH_SHORT).show();
        }
    }

    public void showDatePickerDialog(View view) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, dateSetListener,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
            // Do something with the selected date (e.g., update the EditText)
            String selectedDate = String.format(Locale.getDefault(), "%02d-%02d-%d", year, monthOfYear + 1, dayOfMonth);
            tanggalnginap = findViewById(R.id.inputtanggal);
            tanggalnginap.setText(selectedDate);
        }
    };

}
