package com.example.app_integradora.viewmodel;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.example.app_integradora.AdafruitApi;
import com.example.app_integradora.Modelos.Modelprincipal;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import androidx.lifecycle.MutableLiveData;

import java.text.BreakIterator;

public class viewmodelprincipal extends ViewModel {
    private Modelprincipal modelPrincipal;
    private MutableLiveData<String> datosUltraLiveData = new MutableLiveData<>();
    private MutableLiveData<String> valorUltraLiveData = new MutableLiveData<>();

    public LiveData<String> getValorUltraLiveData() {
        return valorUltraLiveData;
    }


    public MutableLiveData<String> getDatosUltraLiveData() {
        return datosUltraLiveData;
    }


}
