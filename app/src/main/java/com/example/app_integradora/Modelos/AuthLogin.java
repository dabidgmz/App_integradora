package com.example.app_integradora.Modelos;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.app_integradora.Menu;
import com.example.app_integradora.Retroft.ApiRequest;
import com.example.app_integradora.Retroft.PostUserLogin;
import com.example.app_integradora.Retroft.ResponsePostUserLogin;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AuthLogin {

    public static void loginUser(Context context, String email, String password) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://3.138.171.241")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiRequest apiRequest = retrofit.create(ApiRequest.class);
        PostUserLogin userLogin = new PostUserLogin(email, password);
        Call<ResponsePostUserLogin> call = apiRequest.loginUser(userLogin);

        call.enqueue(new Callback<ResponsePostUserLogin>() {

            @Override
            public void onResponse(Call<ResponsePostUserLogin> call, Response<ResponsePostUserLogin> response) {
                if (response.isSuccessful()) {
                    Log.d("AuthLogin", "Login successful");
                    Toast.makeText(context, "¡Bienvenido!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, Menu.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    context.startActivity(intent);
                } else {
                    Log.e("AuthLogin", "Error response from server: " + response.code());
                    Toast.makeText(context, "Error: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponsePostUserLogin> call, Throwable t) {
                Log.e("AuthLogin", "Login failed", t);
                Toast.makeText(context, "Error de conexión: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}



