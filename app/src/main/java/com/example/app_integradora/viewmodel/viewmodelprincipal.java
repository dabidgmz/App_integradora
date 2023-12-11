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

    public void obtenerDatosDelUltrasonico() {
        AdafruitApi api = AdafruitApi.getAdafruitApi();
        Call<Modelprincipal> call = api.obtenerDatosDelUltrasonico();

        call.enqueue(new Callback<Modelprincipal>() {
            @Override
            public void onResponse(Call<Modelprincipal> call, Response<Modelprincipal> response) {
                if (response.isSuccessful()) {
                    Modelprincipal model = response.body();
                    String valorUltra = model.getValorUltra();

                    Log.d("API_RESPONSE", "Valor Ultra: " + valorUltra);  // Imprime la respuesta en el log

                    valorUltraLiveData.setValue(valorUltra);  // Actualiza el valor de valorUltraLiveData
                } else {
                    Log.d("API_RESPONSE", "Response not successful. Code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Modelprincipal> call, Throwable t) {
                // Maneja el error
            }
        });
    }
}
