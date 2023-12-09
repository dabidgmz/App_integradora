package com.example.app_integradora.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.app_integradora.Retroft.ResponsePostUserLogin;

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
