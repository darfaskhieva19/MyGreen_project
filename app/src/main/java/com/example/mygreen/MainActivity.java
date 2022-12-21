package com.example.mygreen;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Adapter pAdapter;
    private  List<Plant> listPlants = new ArrayList<>();
    EditText filter;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @SuppressLint("MissingInflatedId")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        filter = findViewById(R.id.filter);

        ListView ivProducts  = findViewById(R.id.listPlant);
        pAdapter = new Adapter(MainActivity.this, listPlants);
        ivProducts.setAdapter(pAdapter);
        new Get().execute();
    }

    public void onClear(View v)
    {
        filter.setText("");
    }

    public void Filter(View view) {
        Intent intent = new Intent(this, FilterActivity.class);
        startActivity(intent);
    }


    class Get extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL("https://ngknn.ru:5001/ngknn/ФасхиеваДР/api/Plants");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder result = new StringBuilder();
                String str = "";

                while ((str = reader.readLine()) != null) {
                    result.append(str);
                }
                return result.toString();
            } catch (Exception exception) {
                return null;
            }
        }

        @Override
        protected void onPostExecute(String s)
        {
            super.onPostExecute(s);
            try
            {
                JSONArray tempArray = new JSONArray(s);
                for (int i = 0; i < tempArray.length(); i++)
                {
                    JSONObject productJson = tempArray.getJSONObject(i);
                    Plant tempPlants = new Plant(
                            productJson.getInt("Id"),
                            productJson.getString("Title"),
                            productJson.getString("Description"),
                            productJson.getString("Photo"),
                            productJson.getString("Care"),
                            productJson.getString("Watering"),
                            productJson.getString("Lighting"),
                            productJson.getString("Spraying"),
                            productJson.getString("Link")
                    );
                    listPlants.add(tempPlants);
                    pAdapter.notifyDataSetInvalidated();
                }
            } catch (Exception ignored) {
            }
        }
    }
}