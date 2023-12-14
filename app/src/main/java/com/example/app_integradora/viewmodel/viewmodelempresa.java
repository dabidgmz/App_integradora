package com.example.app_integradora.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.app_integradora.Retroft.ApiRequest;
import com.example.app_integradora.Retroft.PostEmpresa;
import com.example.app_integradora.Retroft.ResponseEmpresa;
import com.example.app_integradora.Retroft.ResponsePostEmpresa;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class viewmodelempresa extends ViewModel {


    private MutableLiveData<ResponseEmpresa> empresaResult = new MutableLiveData<>();
    private MutableLiveData<String> error = new MutableLiveData<>();

    public MutableLiveData<ResponseEmpresa> getEmpresaResult() {
        return empresaResult;
    }

    public MutableLiveData<String> getError() {
        return error;
    }

    public void createEmpresa(String token, PostEmpresa empresa) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://3.138.171.241")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiRequest apiRequest = retrofit.create(ApiRequest.class);
        Call<ResponseEmpresa> call = apiRequest.empresa(token,empresa);

        call.enqueue(new Callback <ResponseEmpresa>() {
            @Override
            public void onResponse(Call<ResponseEmpresa> call, Response<ResponseEmpresa> response) {
                if (response.isSuccessful()) {
                    empresaResult.postValue(response.body());
                } else {
                    empresaResult.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<ResponseEmpresa> call, Throwable t) {
                error.postValue(t.getMessage());
            }
        });
    }
}
