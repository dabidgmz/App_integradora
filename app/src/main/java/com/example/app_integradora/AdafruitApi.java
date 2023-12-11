package com.example.app_integradora;

import android.util.Log;

import com.example.app_integradora.Modelos.Modelprincipal;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Headers;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AdafruitApi {
    String BASE_URL = "https://io.adafruit.com/api/v2/1029384756/";

    @Headers({
            "X-AIO-Key: aio_dnLK52KIOyfcUx6QcY37EXNyHSWy",
            "Content-Type: application/json"
    })
    @GET("feeds/ultrasonico/data?limit=1")
    Call<List<Modelprincipal>> getUltrasonicoData();

    @GET("feeds/temperatura/data?limit=1")
    Call<List<Modelprincipal>> gettempData();
    @GET("feeds/humedad/data?limit=1")
    Call<List<Modelprincipal>> gethumedadData();
    @GET("feeds/gas/data?limit=1")
    Call<List<Modelprincipal>> getgasData();

    @GET("feeds/luz/data?limit=1")
    Call<List<Modelprincipal>> getluzData();

    @GET("feeds/impacto/data?limit=1")
    Call<List<Modelprincipal>> getimpactoData();
}
