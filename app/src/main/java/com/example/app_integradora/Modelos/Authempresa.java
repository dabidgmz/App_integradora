package com.example.app_integradora.Modelos;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;
import com.example.app_integradora.Menu;
import com.example.app_integradora.Retroft.ApiRequest;
import com.example.app_integradora.Retroft.PostEmpresa;
import com.example.app_integradora.Retroft.ResponsePostEmpresa;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Authempresa {
    public static void empresa(Context context, String nombre) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://3.138.171.241/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiRequest apiRequest = retrofit.create(ApiRequest.class);
        PostEmpresa postEmpresa = new PostEmpresa(nombre);
        Call<ResponsePostEmpresa> call = apiRequest.empresa(postEmpresa);

        call.enqueue(new Callback<ResponsePostEmpresa>() {
            @Override
            public void onResponse(Call<ResponsePostEmpresa> call, Response<ResponsePostEmpresa> response) {
                if (response.isSuccessful()) {
                    Log.d("AuthEmpresa", "Empresa creada  successfully");

                } else {
                    Log.e("AuthEmpresa", "Error response from server: " + response.code());

                }
            }

            @Override
            public void onFailure(Call<ResponsePostEmpresa> call, Throwable t) {
                Log.e("AuthEmpresa", "Request failed", t);
            }
        });
    }
}
