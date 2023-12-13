package com.example.app_integradora.Retroft;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiRequest {
    @POST("api/auth/register")
    Call<ResponsePostUserRegister> registerUser(@Body PostUserRegister user);
    @POST("api/auth/login")
    Call<ResponsePostUserLogin> loginUser(@Body PostUserLogin postUserLogin);
@POST("api/auth/logout")
Call<ResponsePostUserLogout> logoutUser(@Header("Authorization") String authorizationHeader);

@GET("api/auth/me")
Call<ResponsePostUserMe> meUser(@Header("Authorization") String token);
    @POST("api/empresas")
    Call<ResponsePostEmpresa> empresa(@Body PostEmpresa postEmpresas);
}
