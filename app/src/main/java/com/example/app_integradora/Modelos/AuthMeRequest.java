package com.example.app_integradora.Modelos;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.app_integradora.Retroft.ApiRequest;
import com.example.app_integradora.Retroft.ResponsePostUserMe;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AuthMeRequest {

    public static void getUserProfile(Context context, String token) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://3.138.171.241")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiRequest apiRequest = retrofit.create(ApiRequest.class);
        Call<ResponsePostUserMe> call = apiRequest.meUser("Bearer " + token);

        call.enqueue(new Callback<ResponsePostUserMe>() {
            @Override
            public void onResponse(Call<ResponsePostUserMe> call, Response<ResponsePostUserMe> response) {
                if (response.isSuccessful()) {
                    ResponsePostUserMe userProfile = response.body();
                    String userId = userProfile.getId();
                    String userName = userProfile.getName();
                    String Useremail=userProfile.getEmail();
                    String Userlastname=userProfile.getLastname();
                    String userphone=userProfile.getPhone();
                    String UserDepartament=userProfile.getDepartament();
                } else {
                    Log.e("AuthMeRequest", "Error response from server: " + response.code());
                    if (call != null && call.request() != null && call.request().body() != null) {
                        Log.e("AuthMeRequest", "Request body: " + call.request().body().toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponsePostUserMe> call, Throwable t) {
                Toast.makeText(context, "Error de conexión: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
