package com.example.mygreen;
import java.util.List;

import com.example.mygreen.Care;
import com.example.mygreen.Lighting;
import com.example.mygreen.Watering;
import com.example.mygreen.Spraying;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SpinnerApi {
    @GET("api/PlantCare")
    Call<List<Care>> createGetCare();

    @GET("api/SprayingPlants")
    Call<List<Spraying>> createGetSpraying();

    @GET("api/WateringPlants")
    Call<List<Watering>> createGetWatering();

    @GET("api/PlantLighting")
    Call<List<Lighting>> createGetLighting();
}
