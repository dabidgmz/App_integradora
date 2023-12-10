package com.example.app_integradora.viewmodel;
import com.example.app_integradora.ApiService;
import com.example.app_integradora.Modelos.Modeltriggers;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import androidx.lifecycle.LiveData;
        import androidx.lifecycle.MutableLiveData;

public class viewmodeltriggers  extends androidx.lifecycle.ViewModel{
    private ApiService apiService;

    public viewmodeltriggers() {
           apiService = ApiService.getApiService();
    }

    public LiveData<Modeltriggers> obtenerDatosDelVentilador() {
        MutableLiveData<Modeltriggers> data = new MutableLiveData<>();

        apiService.obtenerDatosDelVentilador().enqueue(new Callback<Modeltriggers>() {
            @Override
            public void onResponse(Call<Modeltriggers> call, Response<Modeltriggers> response) {
                if (response.isSuccessful()) {
                    data.setValue(response.body());
                } else {
                    // Manejar errores aquí
                }
            }
            @Override
            public void onFailure(Call<Modeltriggers> call, Throwable t) {
                // Manejar errores de conexión aquí
            }
        });

        return data;
    }

    public void enviarComandoVentilador(/* Puedes pasar los parámetros necesarios aquí */String on) {
        // Lógica para enviar comandos al ventilador
        apiService.enviarComandoVentilador(/* Puedes pasar los parámetros necesarios aquí */)
                .enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (!response.isSuccessful()) {
                            // Manejar errores aquí
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        // Manejar errores de conexión aquí
                    }
                });
    }
}