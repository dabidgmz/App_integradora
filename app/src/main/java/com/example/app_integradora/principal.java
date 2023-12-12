package com.example.app_integradora;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.app_integradora.Modelos.Modelprincipal;
import com.example.app_integradora.viewmodel.viewmodelprincipal;
import com.google.android.material.navigation.NavigationView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class principal extends AppCompatActivity {
    DrawerLayout drawerLayout;
    private viewmodelprincipal viewModelprincipal;
    private TextView textViewValorUltra;
    private TextView textViewValorTemp;
    private TextView textViewValorHum;
    private TextView textViewValorGas;
    private TextView textViewValorLuz;
    private TextView textViewValorImp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        drawerLayout = findViewById(R.id.principal);
        Button buttonTriggers = findViewById(R.id.trigger);
        Button buttonSensors = findViewById(R.id.Sensor);
        LinearLayout ultrasonic = findViewById(R.id.ultrasonic);
        LinearLayout temperatura = findViewById(R.id.temperatura);
        LinearLayout humedad = findViewById(R.id.humedad);
        LinearLayout impact = findViewById(R.id.Impact);
        LinearLayout gas = findViewById(R.id.gas);
        LinearLayout luz = findViewById(R.id.luz);
        textViewValorUltra = findViewById(R.id.ult1);
        textViewValorTemp = findViewById(R.id.valortemp);
        textViewValorHum = findViewById(R.id.valorhum);
        textViewValorGas = findViewById(R.id.valorgas);
        textViewValorLuz = findViewById(R.id.valorluz);
        textViewValorImp = findViewById(R.id.valorimp);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AdafruitApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AdafruitApi adafruitApi = retrofit.create(AdafruitApi.class);
        Call<List<Modelprincipal>> call = adafruitApi.getUltrasonicoData();
        Call<List<Modelprincipal>> call2 = adafruitApi.gettempData();
        Call<List<Modelprincipal>> call3 = adafruitApi.gethumedadData();
        Call<List<Modelprincipal>> call4 = adafruitApi.getgasData();
        Call<List<Modelprincipal>> call5 = adafruitApi.getluzData();
        Call<List<Modelprincipal>> call6 = adafruitApi.getimpactoData();

        call.enqueue(new Callback<List<Modelprincipal>>() {
            @Override
            public void onResponse(Call<List<Modelprincipal>> call, Response<List<Modelprincipal>> response) {
                if (response.isSuccessful() && response.body() != null && !response.body().isEmpty()) {
                    String valorUltra = response.body().get(0).getValue();
                    String primerosCuatroDigitos = valorUltra.substring(0, Math.min(valorUltra.length(), 4));
                    textViewValorUltra.setText(primerosCuatroDigitos);
                } else {
                    Log.d("API_RESPONSE", "Response not successful. Code: " + response.code());
                    textViewValorUltra.setText("11");
                }
            }

            @Override
            public void onFailure(Call<List<Modelprincipal>> call, Throwable t) {
                textViewValorUltra.setText("Error");
            }
        });
        call2.enqueue(new Callback<List<Modelprincipal>>() {
            @Override
            public void onResponse(Call<List<Modelprincipal>> call2, Response<List<Modelprincipal>> response) {
                if (response.isSuccessful() && response.body() != null && !response.body().isEmpty()) {
                    String valorTemp = response.body().get(0).getValue();
                    textViewValorTemp.setText(valorTemp);
                } else {
                    Log.d("API_RESPONSE", "Response not successful. Code: " + response.code());
                    textViewValorTemp.setText("N/A");
                }
            }

            @Override
            public void onFailure(Call<List<Modelprincipal>> call, Throwable t) {
                textViewValorTemp.setText("Error");
            }
        });

        call3.enqueue(new Callback<List<Modelprincipal>>() {
            @Override
            public void onResponse(Call<List<Modelprincipal>> call3, Response<List<Modelprincipal>> response) {
                if (response.isSuccessful() && response.body() != null && !response.body().isEmpty()) {
                    String valorhum = response.body().get(0).getValue();
                    textViewValorHum.setText(valorhum);
                } else {
                    Log.d("API_RESPONSE", "Response not successful. Code: " + response.code());
                    textViewValorHum.setText("N/A");
                }
            }

            @Override
            public void onFailure(Call<List<Modelprincipal>> call, Throwable t) {
                textViewValorHum.setText("Error");
            }
        });

        call4.enqueue(new Callback<List<Modelprincipal>>() {
            @Override
            public void onResponse(Call<List<Modelprincipal>> call4, Response<List<Modelprincipal>> response) {
                if (response.isSuccessful() && response.body() != null && !response.body().isEmpty()) {
                    String valorgas = response.body().get(0).getValue();
                    textViewValorGas.setText(valorgas);
                } else {
                    Log.d("API_RESPONSE", "Response not successful. Code: " + response.code());
                    textViewValorGas.setText("N/A");
                }
            }

            @Override
            public void onFailure(Call<List<Modelprincipal>> call4, Throwable t) {
                textViewValorGas.setText("Error");
            }
        });

        call5.enqueue(new Callback<List<Modelprincipal>>() {
            @Override
            public void onResponse(Call<List<Modelprincipal>> call5, Response<List<Modelprincipal>> response) {
                if (response.isSuccessful() && response.body() != null && !response.body().isEmpty()) {
                    String valor = response.body().get(0).getValue();
                    textViewValorLuz.setText(valor);
                }
            }
            @Override
            public void onFailure(Call<List<Modelprincipal>> call5, Throwable t) {
                textViewValorTemp.setText("Error");
            }
        });
        call6.enqueue(new Callback<List<Modelprincipal>>() {
            @Override
            public void onResponse(Call<List<Modelprincipal>> call6, Response<List<Modelprincipal>> response) {
                if (response.isSuccessful() && response.body() != null && !response.body().isEmpty()) {
                    String valor = response.body().get(0).getValue();
                    textViewValorImp.setText(valor);
                }
            }
            @Override
            public void onFailure(Call<List<Modelprincipal>> call6, Throwable t) {
                textViewValorTemp.setText("Error");
            }
        });
        buttonTriggers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(principal.this, triggers.class);
                startActivity(intent);
            }
        });

        buttonSensors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(principal.this, Menu.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.imagMenu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    // botones de sensores
    public void onLinear(View view) {
        Intent intent = new Intent(this, Sensor_mov.class);
        startActivity(intent);
    }

    public void onLineartemp(View view) {
        Intent intent = new Intent(this, Sensor_temperatura.class);
        startActivity(intent);
    }

    public void onLinearhum(View view) {
        Intent intent = new Intent(this, Sensor_humedad.class);
        startActivity(intent);
    }

    public void onLineargas(View view) {
        Intent intent = new Intent(this, sensor_gas.class);
        startActivity(intent);
    }

    public void onLinearluz(View view) {
        Intent intent = new Intent(this, profile.class);
        startActivity(intent);
    }

    public void onLinearimp(View view) {
        Intent intent = new Intent(this, Sensor_impacto.class);
        startActivity(intent);
    }
}