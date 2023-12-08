package com.example.app_integradora.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.app_integradora.Retroft.ApiRequest;
import com.example.app_integradora.Retroft.PostUserLogin;
import com.example.app_integradora.Retroft.ResponsePostUserLogin;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

public class viewmodelogin extends AndroidViewModel {
    private MutableLiveData<ResponsePostUserLogin> loginresult = new MutableLiveData<>();
    private MutableLiveData<String> error = new MutableLiveData<>();

    public viewmodelogin(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<ResponsePostUserLogin> getLoginresult() {
        return loginresult;
    }

    public MutableLiveData<String> getError() {
        return error;
    }



}
