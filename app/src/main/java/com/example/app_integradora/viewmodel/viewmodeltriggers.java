package com.example.app_integradora.viewmodel;
import android.util.Log;

import com.example.app_integradora.ApiService;
import com.example.app_integradora.Modelos.Modeltriggers;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class viewmodeltriggers extends androidx.lifecycle.ViewModel {
    private ApiService apiService;
    private MutableLiveData<Modeltriggers> datosVentilador = new MutableLiveData<>();
    private MutableLiveData<Modeltriggers> datosCerradura = new MutableLiveData<>();

    // Variable para almacenar el estado inicial del ventilador
    private MutableLiveData<Boolean> estadoInicialVentilador = new MutableLiveData<>();

    // Resultado de las operaciones
    public MutableLiveData<Boolean> resultado = new MutableLiveData<>();

    public viewmodeltriggers() {
        apiService = ApiService.getApiService();

        // Observar cambios en los datos del ventilador y actualizar el estado inicial
        datosVentilador.observeForever(model -> {
            if (model != null) {
                // Obtener el valor y actualizar el estado inicial del ventilador
                estadoInicialVentilador.postValue(model.getValue().equals("ON"));
            }
        });
        obtenerDatosDelVentilador();
        obtenerDatosDeCerradura();
    }

    public LiveData<Modeltriggers> obtenerDatosDelVentilador() {
        apiService.obtenerDatosDelVentilador().enqueue(new Callback<Modeltriggers>() {

            @Override
            public void onResponse(Call<Modeltriggers> call, Response<Modeltriggers> response) {
                if (response.isSuccessful()) {
                    datosVentilador.postValue(response.body());

                    // Obtener el valor y actualizar el estado inicial del ventilador
                    estadoInicialVentilador.postValue(response.body().getValue().equals("ON"));
                } else {
                    // Manejar errores aquí
                    // Puedes imprimir el código de respuesta o cualquier otra información relevante
                    Log.e("ApiService", "Error en la llamada a la API: " + response.code());
                    datosVentilador.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<Modeltriggers> call, Throwable t) {
                // Manejar errores de conexión aquí
                t.printStackTrace();
                datosVentilador.postValue(null);
            }
        });

        return datosVentilador;
    }


    public LiveData<Modeltriggers> obtenerDatosDeCerradura() {
        apiService.obtenerDatosDeCerradura().enqueue(new Callback<Modeltriggers>() {
            @Override
            public void onResponse(Call<Modeltriggers> call, Response<Modeltriggers> response) {
                if (response.isSuccessful()) {
                    datosCerradura.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<Modeltriggers> call, Throwable t) {
                datosCerradura.postValue(null);
            }
        });

        return datosCerradura;
    }

    public MutableLiveData<Boolean> obtenerEstadoInicialVentilador() {
        return estadoInicialVentilador;
    }

    public MutableLiveData<Boolean> obtenerResultado() {
        return resultado;
    }

    public void enviarComandoVentilador(String comando) {
        Modeltriggers model = new Modeltriggers();
        model.setValue(comando);

        apiService.enviarComandoVentilador(model)
                .enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (!response.isSuccessful()) {
                            // Manejar errores aquí
                            resultado.setValue(null);
                        } else {
                            resultado.setValue(true); // Indicar que la operación fue exitosa
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        // Manejar errores de conexión aquí
                        resultado.setValue(null);
                    }
                });
    }


    public void enviarComandoCerradura(String comando) {
        Modeltriggers model = new Modeltriggers();
        model.setValue(comando);
        model.setDeviceType("cerradura"); // Establecer el tipo de dispositivo

        apiService.enviarComandoCerradura(model)
                .enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (!response.isSuccessful()) {
                            // Manejar errores aquí
                            resultado.setValue(null);
                        } else {
                            resultado.setValue(true); // Indicar que la operación fue exitosa
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        // Manejar errores de conexión aquí
                        resultado.setValue(null);
                    }
                });
    }


}