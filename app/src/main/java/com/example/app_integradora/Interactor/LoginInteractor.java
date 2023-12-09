package com.example.app_integradora.Interactor;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.example.app_integradora.Retroft.ApiRequest;
import com.example.app_integradora.Retroft.PostUserLogin;
import com.example.app_integradora.Retroft.ResponsePostUserLogin;
import com.example.app_integradora.viewmodel.viewmodelogin;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginInteractor {

    private Context context;
    private viewmodelogin viewModel;

    public LoginInteractor(Context context, viewmodelogin viewModel) {
        this.context = context;
        this.viewModel = viewModel;
    }

    public void loginUser(PostUserLogin user) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://3.138.171.241")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiRequest apiRequest = retrofit.create(ApiRequest.class);
        Call<ResponsePostUserLogin> call = apiRequest.loginUser(user);

        call.enqueue(new Callback<ResponsePostUserLogin>() {
            @Override
            public void onResponse(Call<ResponsePostUserLogin> call, Response<ResponsePostUserLogin> response) {
                if (response.isSuccessful()) {
                    SharedPreferences.Editor editTokenSuccess = context.getSharedPreferences("LoginPrefs", Context.MODE_PRIVATE).edit();
                    editTokenSuccess.putString("token", response.body().getAccess_token());
                    editTokenSuccess.apply();
                    Toast.makeText(context, "¡Inicio de sesión exitoso!", Toast.LENGTH_SHORT).show();

                    // Notificar al ViewModel sobre el inicio de sesión exitoso
                    viewModel.getLoginresult().setValue(response.body());
                } else {
                    Toast.makeText(context, "Error en el inicio de sesión: " + response.message(), Toast.LENGTH_SHORT).show();

                    // Notificar al ViewModel sobre el error de inicio de sesión
                    viewModel.getError().setValue("Error en el inicio de sesión: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<ResponsePostUserLogin> call, Throwable t) {
                Toast.makeText(context, "Error en el inicio de sesión: " + t.getMessage(), Toast.LENGTH_SHORT).show();

                // Notificar al ViewModel sobre el error de inicio de sesión
                viewModel.getError().setValue("Error en el inicio de sesión: " + t.getMessage());
            }
        });
    }
}
