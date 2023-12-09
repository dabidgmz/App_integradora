package com.example.app_integradora.viewmodel;

import androidx.lifecycle.ViewModel;
public class ViewModelPerfil extends ViewModel {
   /* private MutableLiveData<ResponsePostUserMe> userProfile = new MutableLiveData<>();
    private MutableLiveData<String> errorLiveData = new MutableLiveData<>();
    private ApiRequest apiRequest;

    public ViewModelPerfil(ApiRequest apiService) {
        this.apiRequest = apiService;
    }

    public LiveData<ResponsePostUserMe> getUserProfile() {
        String token = viewModelToken.getinstance().token();
        Call<ResponsePostUserMe> call = apiRequest.meUser("Bearer " + token);

        call.enqueue(new Callback<ResponsePostUserMe>() {
            @Override
            public void onResponse(Call<ResponsePostUserMe> call, Response<ResponsePostUserMe> response) {
                if (response.isSuccessful()) {
                    userProfile.setValue(response.body());
                } else {
                    int statusCode = response.code();
                    if (statusCode == 401) {
                        errorLiveData.setValue("Tu sesión ha expirado. Inicia sesión nuevamente.");
                    } else {
                        errorLiveData.setValue("Error al cargar el perfil. Inténtalo de nuevo más tarde.");
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponsePostUserMe> call, Throwable t) {
                errorLiveData.setValue("Error de conexión. Verifica tu conexión a Internet.");
            }
        });

        return userProfile;
    }

    public LiveData<String> getErrorLiveData() {
        return errorLiveData;
    }*/
}
