package com.example.app_integradora.Modelos;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.app_integradora.Retroft.ApiRequest;
import com.example.app_integradora.Retroft.PostUserRegister;
import com.example.app_integradora.Retroft.ResponsePostUserRegister;
import com.example.app_integradora.User_Register;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AuthRegister {
    public static void registerUser(Context context, String name, String lastName, String phone, String email, String password, String department, String passwordConfirmation) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://3.138.171.241")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiRequest apiRequest = retrofit.create(ApiRequest.class);

        // Make sure to provide all the necessary parameters in the constructor
        PostUserRegister user = new PostUserRegister(name, lastName, phone, email, password, department, passwordConfirmation, ""); // add any additional parameter if required

        Call<ResponsePostUserRegister> call = apiRequest.registerUser(user);

        call.enqueue(new Callback<ResponsePostUserRegister>() {
            @Override
            public void onResponse(Call<ResponsePostUserRegister> call, Response<ResponsePostUserRegister> response) {
                if (response.isSuccessful()) {
                    Log.d("AuthRegister", "Registration successful: " + response.body().getMsg());
                    Intent intent = new Intent(context, User_Register.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    context.startActivity(intent);
                } else {
                    Log.e("AuthRegister", "Error response from server: " + response.code());
                    Toast.makeText(context, "Error: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponsePostUserRegister> call, Throwable t) {
                Toast.makeText(context, "Error de conexión: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
