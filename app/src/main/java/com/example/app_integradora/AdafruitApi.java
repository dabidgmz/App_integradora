package com.example.app_integradora;

import android.util.Log;

import com.example.app_integradora.Modelos.Modelprincipal;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface AdafruitApi {
    String BASE_URL = "https://io.adafruit.com/api/v2/1029384756/";

    static AdafruitApi getAdafruitApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AdafruitApi api = retrofit.create(AdafruitApi.class);
        Log.d("RETROFIT_SETUP", "Retrofit setup successful");

        return api;
    }

    @Headers({
            "Content-Type: application/json",
            "X-AIO-Key: aio_KxuJ02enTG1A6CqUOmJwAXqe3LH3"
    })
    @GET("feeds/ultrasonico/data?limit=1")
    Call<Modelprincipal> obtenerDatosDelUltrasonico();
}
