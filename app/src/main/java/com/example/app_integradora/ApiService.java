package com.example.app_integradora;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import com.example.app_integradora.Modelos.Modelprincipal;
import com.example.app_integradora.Modelos.Modeltriggers;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiService {
    String BASE_URL = "https://io.adafruit.com/api/v2/1029384756/";

    static ApiService getApiService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(ApiService.class);
    }

    @Headers({
            "Content-Type: application/json",
            "X-AIO-Key: aio_dnLK52KIOyfcUx6QcY37EXNyHSWy"
    })
    @GET("feeds/ventilador/data?limit=1")
    Call<Modeltriggers> obtenerDatosDelVentilador();

    @Headers({
            "Content-Type: application/json",
            "X-AIO-Key:aio_dnLK52KIOyfcUx6QcY37EXNyHSWy"
    })
    @POST("feeds/ventilador/data")
    Call<Void> enviarComandoVentilador(@Body Modeltriggers comando);

    @Headers({
            "Content-Type: application/json",
            "X-AIO-Key:aio_dnLK52KIOyfcUx6QcY37EXNyHSWy"
    })
    @GET("feeds/led/data?limit=1")
    Call<Modeltriggers> obtenerDatosDeCerradura();

    @Headers({
            "Content-Type: application/json",
            "X-AIO-Key: aio_dnLK52KIOyfcUx6QcY37EXNyHSWy"
    })
    @POST("feeds/led/data")
    Call<Void> enviarComandoCerradura(@Body Modeltriggers comando);

}
