package com.example.app_integradora.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.app_integradora.Retroft.ApiRequest;
import com.example.app_integradora.Retroft.PostEmpresa;
import com.example.app_integradora.Retroft.ResponsePostEmpresa;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class viewmodelempresa extends ViewModel {

    private MutableLiveData<ResponsePostEmpresa> empresaResult = new MutableLiveData<>();
    private MutableLiveData<String> error = new MutableLiveData<>();

    public MutableLiveData<ResponsePostEmpresa> getEmpresaResult() {
        return empresaResult;
    }

    public MutableLiveData<String> getError() {
        return error;
    }

    public void createEmpresa(PostEmpresa empresa) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://3.138.171.241")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiRequest apiRequest = retrofit.create(ApiRequest.class);
        Call<ResponsePostEmpresa> call = apiRequest.empresa(empresa);

        call.enqueue(new Callback<ResponsePostEmpresa>() {
            @Override
            public void onResponse(Call<ResponsePostEmpresa> call, Response<ResponsePostEmpresa> response) {
                if (response.isSuccessful()) {
                    empresaResult.postValue(response.body());
                } else {
                    empresaResult.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<ResponsePostEmpresa> call, Throwable t) {
                error.postValue(t.getMessage());
            }
        });
    }
}
