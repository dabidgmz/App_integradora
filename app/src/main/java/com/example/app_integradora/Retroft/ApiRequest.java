package com.example.app_integradora.Retroft;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiRequest {
    @POST("api/auth/register")
    Call<ResponsePostUserRegister> registerUser(@Body PostUserRegister user);
    @POST("api/auth/login")
    Call<ResponsePostUserLogin> loginUser(@Body PostUserLogin postUserLogin);
@POST("api/auth/logout")
Call<ResponsePostUserLogout> logoutUser(@Header("Authorization") String authorizationHeader);

@GET("api/auth/me")
Call<ResponsePostUserMe> meUser(@Header("Authorization") String token);
    @POST("api/auth/empresas")
    Call<ResponseEmpresa> empresa(@Header("Authorization")String token, @Body PostEmpresa postEmpresas);


    @GET("api/auth/Usuario/id") // La URL puede variar dependiendo de tu configuración
    Call<GetEmpresa> obtenerEmpresas(@Header("Authorization") String token);


}
