package com.example.mygreen;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;

public class InfoActivity extends AppCompatActivity {

    TextView Title, Description, Link, Watering, Spraying, Lighting, Care;
    ImageView Image;
    View v;
    Bundle arg;
    Plant plant;
    Bitmap bitmap=null;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        arg = getIntent().getExtras();
        plant = arg.getParcelable(Plant.class.getSimpleName());

        Title = findViewById(R.id.Header);
        Description = findViewById(R.id.tv_Descr);
        Link = findViewById(R.id.tv_Links);
        Watering = findViewById(R.id.textWater);
        Lighting = findViewById(R.id.textLight);
        Spraying = findViewById(R.id.textSpray);
        Care = findViewById(R.id.textCare);
        Image = findViewById(R.id.imgPlant);

        Title.setText(plant.getTitle());
        Description.setText(plant.getDescription());
        Link.setText(plant.getLink());
        Watering.setText(plant.getWatering());
        Spraying.setText(plant.getSpraying());
        Lighting.setText(plant.getLighting());
        Care.setText(plant.getCare());

        DecodeImage decodeImage = new DecodeImage(InfoActivity.this);
        Bitmap userImage = decodeImage.getUserImage(plant.getPhoto());
        Image.setImageBitmap(userImage);
        if(!plant.getPhoto().equals("null")) {
            bitmap = userImage;
        }
    }

    public void onClickBack(View v)
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}