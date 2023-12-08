package com.example.app_integradora.Retroft;


import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import static android.content.Context.MODE_PRIVATE;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.app_integradora.Retroft.Interceptor.AuthInterceptor;
import com.example.app_integradora.Retroft.jwtUtils;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import com.example.app_integradora.viewmodel.viewModelToken;
import retrofit2.converter.gson.GsonConverterFactory;

public class retrofitApiModelToken {

    private static final String API_BASE_URL = "http://3.138.171.241";
    private final String token;
    private Context context;
    private  static DecodedJWT decodedJWT;
    private  static String userId;
    public retrofitApiModelToken() {
        SharedPreferences prefs = context.getSharedPreferences("loginPrefs", MODE_PRIVATE);
        token = prefs.getString("token", null);
        decodedJWT = jwtUtils.decode(token);

            userId = decodedJWT.getSubject();

    }
    public Retrofit provideRetrofit() {
        viewModelToken tokenProvider = viewModelToken.getinstance();

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new AuthInterceptor(tokenProvider))
                .build();

        return new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

}