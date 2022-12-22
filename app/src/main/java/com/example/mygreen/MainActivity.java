package com.example.mygreen;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {
    Adapter pAdapter;
    private  List<Plant> listPlants = new ArrayList<>();
    EditText filter;
    Spinner spinner;
    ListView lvPlant;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @SuppressLint("MissingInflatedId")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        filter = findViewById(R.id.EtFilter);
        String[] i = {"по умолчанию","по наименованию"};
        lvPlant = findViewById(R.id.listPlant);

        ListView ivProducts  = findViewById(R.id.listPlant);
        pAdapter = new Adapter(MainActivity.this, listPlants);
        ivProducts.setAdapter(pAdapter);
        //new Get().execute();

        spinner=findViewById(R.id.spSort);
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, i);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
               if(filter.getText().toString().isEmpty()) {
                   Sort(listPlants);
               }
               else{
                   Search();
               }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }
        });
        filter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Search();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void Search(){
        List<Plant> lstFilter = listPlants.stream().filter(x-> (x.Title.toLowerCase(Locale.ROOT).contains(filter.getText().toString().toLowerCase(Locale.ROOT)))).collect(Collectors.toList());
        Sort(lstFilter);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void Sort(List<Plant> list){
        lvPlant.setAdapter(null);
        switch(spinner.getSelectedItemPosition()){
            case 0:
                new Get().execute();
                break;
            case 1:
                Collections.sort(list, Comparator.comparing(Plant::getTitle));
                break;
            default:
                break;
        }
        SetAdapter(list);
    }

    public void SetAdapter(List<Plant> list)
    {
        pAdapter = new Adapter(MainActivity.this,list);
        lvPlant.setAdapter(pAdapter);
        pAdapter.notifyDataSetInvalidated();
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