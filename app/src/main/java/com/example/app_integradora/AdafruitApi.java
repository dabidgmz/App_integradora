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
    String BASE_URL = "http://3.138.171.241/api/";


    @GET("feeds/")
    Call<Modelprincipal> obtenerDatos();

}
