package com.example.mygreen;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class Adapter extends BaseAdapter {

    protected Context mContext;
    List<Plant> listPlants;

    public Adapter(Context mContext, List<Plant> listPlants) {
        this.mContext = mContext;
        this.listPlants = listPlants;
    }

    @Override
    public int getCount() {
        return listPlants.size();
    }

    @Override
    public Object getItem(int position) {
        return listPlants.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listPlants.get(position).getID();
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        @SuppressLint("ViewHolder") View v = View.inflate(mContext,R.layout.item_libraryplants,null);
        TextView Title = v.findViewById(R.id.Title);
        TextView Description = v.findViewById(R.id.Description);
        ImageView Photo = v.findViewById(R.id.Img);
        Plant plant = listPlants.get(i);
        Title.setText(plant.getTitle());
        Description.setText(plant.getDescription());
        DecodeImage DI = new DecodeImage(mContext);
        Photo.setImageBitmap(DI.getUserImage(plant.getPhoto()));
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MainActivity.class);
                intent.putExtra(Plant.class.getSimpleName(), plant);
                mContext.startActivity(intent);
            }
        });
        return  v;
    }
}
