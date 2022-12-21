package com.example.mygreen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FilterActivity extends AppCompatActivity {
    Spinner sort, care, spraying, watering, lighting;
    Button search, clear;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        String[]items = {"<по умолчанию>","По возрастанию","По убыванию"};
        sort = findViewById(R.id.spSort);
        care = findViewById(R.id.spCare);
        spraying = findViewById(R.id.spSpraying);
        watering = findViewById(R.id.spWatering);
        lighting = findViewById(R.id.spLighting);
        search = findViewById(R.id.btnSearch);
        clear = findViewById(R.id.btnClear);
    }
    void getCare(View view){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://ngknn.ru:5001/ngknn/Фасхиева/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        SpinnerApi retrofitAPI = retrofit.create(SpinnerApi.class);
        Call<List<Care>> call = retrofitAPI.createGetCare();
        call.enqueue(new Callback<List<Care>>() {
            @Override
            public void onResponse(Call<List<Care>> call, Response<List<Care>> response) {
                if (response.body() == null) {
                   Toast.makeText(FilterActivity.this,"Пусто", Toast.LENGTH_SHORT).show();
                } else {
                    List<String> list = new ArrayList<>();
                    list.add("Весь уход");
                    for (int i = 0; i < response.body().size(); i++) {
                        list.add(response.body().get(i).getCare());
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter(view.getContext(), android.R.layout.simple_spinner_item, list);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    care.setAdapter(adapter);
                }
            }
            @Override
            public void onFailure(Call<List<Care>> call, Throwable t) {
                Log.e(t.toString(), t.getMessage());
            }
        });
    }
    void getWatering(View view) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://ngknn.ru:5001/ngknn/ФасхиеваДР/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        SpinnerApi retrofitAPI = retrofit.create(SpinnerApi.class);
        Call<List<Watering>> call = retrofitAPI.createGetWatering();
        call.enqueue(new Callback<List<Watering>>() {
            @Override
            public void onResponse(Call<List<Watering>> call, Response<List<Watering>> response) {
                if (response.body() == null) {
                    Toast.makeText(FilterActivity.this, "Пусто", Toast.LENGTH_SHORT).show();
                } else {
                    List<String> list = new ArrayList<>();
                    list.add("Весь полив");
                    for (int i = 0; i < response.body().size(); i++) {
                        list.add(response.body().get(i).getWatering());
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter(view.getContext(), android.R.layout.simple_spinner_item, list);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    watering.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Watering>> call, Throwable t) {
                Log.e(t.toString(), t.getMessage());
            }
        });
    }
    void getLighting(View view) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://ngknn.ru:5001/ngknn/ФасхиеваДР/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        SpinnerApi retrofitAPI = retrofit.create(SpinnerApi.class);
        Call<List<Lighting>> call = retrofitAPI.createGetLighting();
        call.enqueue(new Callback<List<Lighting>>() {
            @Override
            public void onResponse(Call<List<Lighting>> call, Response<List<Lighting>> response) {
                if (response.body() == null) {
                    Toast.makeText(FilterActivity.this, "Пусто", Toast.LENGTH_SHORT).show();
                } else {
                    List<Lighting> list = new ArrayList<>();
                   //list.add("Все освещение");
                    for (int i = 0; i < response.body().size(); i++) {
                        //list.add(response.body().get(i).getLighting());
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter(view.getContext(), android.R.layout.simple_spinner_item, list);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    lighting.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Lighting>> call, Throwable t) {
                Log.e(t.toString(), t.getMessage());
            }
        });
    }
    void getSpraying(View view) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://ngknn.ru:5001/ngknn/ФасхиеваДР/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        SpinnerApi retrofitAPI = retrofit.create(SpinnerApi.class);
        Call<List<Spraying>> call = retrofitAPI.createGetSpraying();
        call.enqueue(new Callback<List<Spraying>>() {
            @Override
            public void onResponse(Call<List<Spraying>> call, Response<List<Spraying>> response) {
                if (response.body() == null) {
                    Toast.makeText(FilterActivity.this, "Пусто", Toast.LENGTH_SHORT).show();
                } else {
                    List<Lighting> list = new ArrayList<>();
                    //list.add("Все опрыскивание");
                    for (int i = 0; i < response.body().size(); i++) {
                       // list.add(response.body().get(i).getSpraying());
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter(view.getContext(), android.R.layout.simple_spinner_item, list);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spraying.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Spraying>> call, Throwable t) {
                Log.e(t.toString(), t.getMessage());
            }
        });
    }
}