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

        Call<Modelprincipal> call = adafruitApi.obtenerDatos();
        call.enqueue(new Callback<Modelprincipal>() {
            @Override
            public void onResponse(Call<Modelprincipal> call, Response<Modelprincipal> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Modelprincipal model = response.body();

                    // Itera sobre los datos y actualiza los TextView
                    for (Modelprincipal.FeedData feedData : model.getData()) {
                        String feedKey = feedData.getFeedKey();
                        String value = feedData.getValue();

                        switch (feedKey) {
                            case "temperatura":
                                textViewValorTemp.setText(value);
                                break;
                            case "humedad":
                                textViewValorHum.setText(value);
                                break;
                            case "gas":
                                textViewValorGas.setText(value);
                                break;
                            case "luz":
                                textViewValorLuz.setText(value);
                                break;
                            case "impacto": textViewValorImp.setText(value);
                                break;
                            case "ultrasonico":
                                String primerosCuatroDigitos = value.length() >= 4 ? value.substring(0, 4) : value;
                                textViewValorUltra.setText(primerosCuatroDigitos);
                                break;
                            default:

                                break;
                            // Agrega más casos según sea necesario
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<Modelprincipal> call, Throwable t) {
                Log.e("Error", "Error en la llamada a la API", t);
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