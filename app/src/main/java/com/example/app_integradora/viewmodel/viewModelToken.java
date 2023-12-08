package com.example.app_integradora.viewmodel;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.app_integradora.Retroft.Interceptor.TokenProvider;
import com.example.app_integradora.Retroft.jwtUtils;


public class viewModelToken implements TokenProvider {
    private static viewModelToken viewModelTokenIns;
    private static String token;
    private static String userId;
    private static DecodedJWT decodedJWT;
    private viewModelToken() {
    }

    public static viewModelToken settoken(Context context) {
        SharedPreferences prefs = context.getSharedPreferences("loginPrefs", MODE_PRIVATE);
        token = prefs.getString("token", null);
        decodedJWT = jwtUtils.decode(token);

        if (decodedJWT != null) {
            userId = decodedJWT.getSubject();
            Log.e("El token", "el token es: " + decodedJWT);
        } else {
            Log.e("ViewModelTokenIns", "El token es nulo o inválido");
        }
        return null;
    }


    public static synchronized viewModelToken getinstance() {
        if (viewModelTokenIns == null) {
            viewModelTokenIns = new viewModelToken();
        }
        return viewModelTokenIns;
    }

    public static viewModelToken clearToken(Context context){
        SharedPreferences.Editor editorRM = context.getSharedPreferences("loginPrefs", MODE_PRIVATE).edit();
        editorRM.remove("token");
        editorRM.apply();
        return null;
    }

    public String getId() {
        return userId;
    }
    public void setToken(String token) {
        viewModelToken.token = token;
        if(token != null){
            decodedJWT = jwtUtils.decode(viewModelToken.token);
        }
    }
    public DecodedJWT getDecodedJWT() {
        return decodedJWT;
    }
    public void setId(String userId) {
        viewModelToken.userId = userId;
    }
    public String token() {
        return token;
    }

    @Override
    public String getToken() {
        return token;
    }
}