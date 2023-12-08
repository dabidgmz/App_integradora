package com.example.app_integradora.Interactor;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;
import com.example.app_integradora.viewmodel.viewModelToken;
import com.example.app_integradora.Retroft.ApiRequest;
import com.example.app_integradora.Retroft.PostUserLogin;
import com.example.app_integradora.Retroft.ResponsePostUserLogin;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginInteractor {

    private Context context;
    public  LoginInteractor (Context context){
        this.context = context;

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
                SharedPreferences.Editor editok = context.getSharedPreferences("LoginPrefs", Context.MODE_PRIVATE).edit();
                editok.remove("token");
                editok.apply();
                viewModelToken.clearToken(context);
                if (response.isSuccessful()) {
                    SharedPreferences.Editor editoken = context.getSharedPreferences("LoginPrefs", Context.MODE_PRIVATE).edit();
                    editoken.putString("token", response.body().getAccess_token());
                    editoken.apply();
                    Toast.makeText(context, "Si funciono", Toast.LENGTH_SHORT).show();
                  //error por falta de datos
                } else {
                    Toast.makeText(context, "no jalo raza", Toast.LENGTH_SHORT).show();

                }
            }
//no funciono por error de servidor
            @Override
            public void onFailure(Call<ResponsePostUserLogin> call, Throwable t) {
                Toast.makeText(context, "no jalo xdd", Toast.LENGTH_SHORT).show();
            }
        });
}
}
