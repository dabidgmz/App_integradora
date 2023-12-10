package com.example.app_integradora;


import com.example.app_integradora.Modelos.Modeltriggers;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiService {
    @Headers({
            "Content-Type: application/json",
            "X-AIO-Key: aio_QUAu36PYGEgDrnkNnEimpVbutJDz"
    })
    @GET("feeds/ventilador/data?limit=1")
    Call<Modeltriggers> obtenerDatosDelVentilador();

    @Headers({
            "Content-Type: application/json",
            "X-AIO-Key: aio_QUAu36PYGEgDrnkNnEimpVbutJDz"
    })
    @POST("feeds/ventilador/data")
    Call<Void> enviarComandoVentilador(/* Puedes pasar los parámetros necesarios aquí */);
}
